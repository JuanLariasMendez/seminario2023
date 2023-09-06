/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    
    @PostConstruct // para cargar todo lo de BBD a la lista
    public void inicio(){
        llenarComboCliente();
        llenarComboFormaPago();
    }
    
    public List<SelectItem> llenarComboCliente() {
        setListaCliente(new ArrayList<SelectItem>());
        List<POJOs.Cliente> lstCliente = CRUDs.CRUDCliente.universo();
        for (POJOs.Cliente cliente : lstCliente) {
            SelectItem clienteItem = new SelectItem(cliente.getIdCliente(), "NIT: " + cliente.getNit() + "Nombre: " + cliente.getNombre1() + " Apellido: " + cliente.getApellido1());
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
        return getListaCliente();
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

}
