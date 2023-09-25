/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import POJOs.Usuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author juanl
 */
@ManagedBean
@ViewScoped
public class BeanVenta {



    private Integer IdCliente; //atraves de lista
    private Integer idFormaPago;
    private Integer idVenta;
    private Integer idProducto; //atraves de lista
    private Integer cantidad;
    private BigDecimal monto;
    private List listaCliente;
    private List listaFormaPago;
    private List listaProducto;
    private List listaVentaDelle;
    private boolean componentes=false;
    
    @PostConstruct // para cargar todo lo de BBD a la lista (para que inicie cuando abre la pagina/para que lea que tiene en la lista)
    public void inicio(){
        llenarComboCliente();
        llenarComboFormaPago();
        llenarComboProducto();
        mostrar();//cuando abra va a tener que llenarse donde se selecciono
    }
    
    public void mostrarDetalle(){
        setListaVentaDelle(CRUDs.CRUDVentaDetalle.universo(idVenta));
    }
    
    public void mostrar(){
        //Suponiendo que hay un estado abierto
        Usuario usuario= new Usuario();
        usuario.setIdUsuario(1);
        idVenta=CRUDs.CRUDVenta.select(usuario).getIdVenta();//Mira si hay una venta abierta
        if(idVenta!=0){ //Y si hay algo abierto, entonces nos lo listara en la parte inferior
            IdCliente=CRUDs.CRUDVenta.select(usuario).getCliente().getIdCliente();
            idFormaPago=CRUDs.CRUDVenta.select(usuario).getFormaPago().getIdFormaPago();
            componentes=true;
            mostrarDetalle();
        }
        else{
            componentes=false;
        }
    }
    public List<SelectItem> llenarComboCliente() {
        setListaCliente(new ArrayList<SelectItem>());
        List<POJOs.Cliente> lstCliente = CRUDs.CRUDCliente.universo();
        for (POJOs.Cliente cliente : lstCliente) {
            SelectItem clienteItem = new SelectItem(cliente.getIdCliente(), cliente.getNombre1() + " " + cliente.getApellido1() +  " "+ "NIT: " + cliente.getNit());
            getListaCliente().add(clienteItem);
        }
        return getListaCliente();
    }
    
    public List<SelectItem> llenarComboFormaPago() {
            setListaFormaPago(new ArrayList<SelectItem>());
            List<POJOs.FormaPago> lstFormaPago = CRUDs.CRUDFormaPago.universo();
            for (POJOs.FormaPago formaPago : lstFormaPago) {

                SelectItem FormaPagoItem = new SelectItem(formaPago.getIdFormaPago(), formaPago.getDescripcion());
                getListaFormaPago().add(FormaPagoItem);
            }
            return getListaFormaPago();
        }
    
    public List<SelectItem> llenarComboProducto() {
        setListaProducto(new ArrayList<SelectItem>());
        List<POJOs.Producto> lstProducto = CRUDs.CRUDProducto.universo();
        for (POJOs.Producto producto : lstProducto) {

            SelectItem ProductoItem = new SelectItem(producto.getIdProducto(), producto.getNombreProducto());
            getListaProducto().add(ProductoItem);
        }
        return getListaProducto();
    }
    
        public void insertar(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(1);
            boolean flag=CRUDs.CRUDVenta.insert(IdCliente, idFormaPago, 1);
            if(flag){
                componentes=true; //desabilitado falso (bloqueado)
                context.addMessage(null, new FacesMessage("Exito","Reguistro ingresado"));
            }else{
                context.addMessage(null, new FacesMessage("Exito","Revise que no haya sido ingresado antes"));
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error"+e));
            System.out.println("error ="+e);
        }
    }
        
        public void insertarDetalle(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            boolean flag=CRUDs.CRUDVentaDetalle.insert(idVenta,idProducto,cantidad,monto);
            if(flag){
                limpiarDetalle();
                mostrarDetalle();
                context.addMessage(null, new FacesMessage("Exito","Reguistro ingresado"));
            }else{
                context.addMessage(null, new FacesMessage("Exito","Revise que no haya sido ingresado antes"));
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error"+e));
            System.out.println("error ="+e);
        }
    }
        
    public void limpiarDetalle(){
        setIdProducto(null);
        setCantidad(null);
        setMonto(null);
    }
        
            
//    Se extrae lo de la tabla y llena la informacion de cada insert en la tabla
    public void selecionarDatos(POJOs.VentaDetalle VentaDetalle){
        FacesContext context=FacesContext.getCurrentInstance();
        CRUDs.CRUDVentaDetalle.eliminar(VentaDetalle.getIdVentaDetalle());
        mostrarDetalle();
            try{
            //System.out.println("id detalle="+VentaDetalle.getIdVentaDetalle());
            boolean flag=CRUDs.CRUDVentaDetalle.eliminar(VentaDetalle.getIdVentaDetalle());
            if(flag){
                mostrarDetalle(); //Actualiza la tabla
                limpiarDetalle();
                context.addMessage(null, new FacesMessage("Exito","Reguistro eliminado"));
            }else{
                context.addMessage(null, new FacesMessage("Error","Error, no se elimino el reguistro"));
            }
 
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error"+e));
            System.out.println("error ="+e);
        }
    }
    

    
    
    //------Setter's y Getter's------
    
    /**
     * @return the IdCliente
     */
    public Integer getIdCliente() {
        return IdCliente;
    }

    /**
     * @param IdCliente the IdCliente to set
     */
    public void setIdCliente(Integer IdCliente) {
        this.IdCliente = IdCliente;
    }

    /**
     * @return the idFormaPago
     */
    public Integer getIdFormaPago() {
        return idFormaPago;
    }

    /**
     * @param idFormaPago the idFormaPago to set
     */
    public void setIdFormaPago(Integer idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    /**
     * @return the idVenta
     */
    public Integer getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }
    
        /**
     * @return the idProducto
     */
    public Integer getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the monto
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
        /**
     * @return the listaCliente
     */
    public List getListaCliente() {
        return listaCliente;
    }

    /**
     * @param listaCliente the listaCliente to set
     */
    public void setListaCliente(List listaCliente) {
        this.listaCliente = listaCliente;
    }
    
        /**
     * @return the listaFormaPago
     */
    public List getListaFormaPago() {
        return listaFormaPago;
    }

    /**
     * @param listaFormaPago the listaFormaPago to set
     */
    public void setListaFormaPago(List listaFormaPago) {
        this.listaFormaPago = listaFormaPago;
    }
    
    
    /**
     * @return the listaProducto
     */
    public List getListaProducto() {
        return listaProducto;
    }

    /**
     * @param listaProducto the listaProducto to set
     */
    public void setListaProducto(List listaProducto) {
        this.listaProducto = listaProducto;
    }

        /**
     * @return the componentes
     */
    public boolean isComponentes() {
        return componentes;
    }

    /**
     * @param componentes the componentes to set
     */
    public void setComponentes(boolean componentes) {
        this.componentes = componentes;
    }

    /**
     * @return the listaVentaDelle
     */
    public List getListaVentaDelle() {
        return listaVentaDelle;
    }

    /**
     * @param listaVentaDelle the listaVentaDelle to set
     */
    public void setListaVentaDelle(List listaVentaDelle) {
        this.listaVentaDelle = listaVentaDelle;
    }


}
