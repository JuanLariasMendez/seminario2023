/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juanl
 */
@ManagedBean
@ViewScoped
public class BeanCliente {
    private Integer idCliente;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String nit;
    private String direccion;
    private String telefono;
    private List listaClientes;

    @PostConstruct
    public void mostrar(){
        setListaClientes(CRUDs.CRUDCliente.universo());
    }
    
    public void prueba() {
        System.out.println("Prueba usuario: " + getNombre1() + getApellido1() + " nit: " + getNit()+ " dirección:" + getDireccion() + " telefono: "+ getTelefono());
    }
    
    public void limpiar(){
        setNombre1("");
        setNombre2("");
        setApellido1("");
        setApellido2("");
        setNit("");
        setDireccion("");
        setTelefono("");
    }
    
    public void insertar2(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            boolean flag=CRUDs.CRUDCliente.insert(nombre1, nombre2, apellido1, apellido2, nit, direccion, telefono, 1);
            if(flag){
                mostrar();
                limpiar();
                context.addMessage(null, new FacesMessage("Exito","Cliente ingresado"));
            }else{
                context.addMessage(null, new FacesMessage("Error","Revise que no haya sido ingresado antes"));
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error"+e));
            System.out.println("error ="+e);
        }
    }
    
    public void modificar(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
//            unica linea que cambia con el de guardar
            boolean flag=CRUDs.CRUDCliente.update(getIdCliente(), getNit(), getDireccion(), getTelefono(),1);
            if(flag){
                mostrar();
                limpiar();
                context.addMessage(null, new FacesMessage("Exito","Reguistro modificado"));
            }else{
                context.addMessage(null, new FacesMessage("Error","Revise que no los datos ingresados sean correctos"));
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error"+e));
            System.out.println("error ="+e);
        }
    }
    
    public void anular(){
        FacesContext context=FacesContext.getCurrentInstance();
        //Control de excepciones
        try{
//            unica linea que cambia con el de guardar
            boolean flag=CRUDs.CRUDCliente.anular(getIdCliente(),1);
            if(flag){
                mostrar(); //Actualiza la tabla
                limpiar(); //Si no se ejecuta, las variables se quedan en la memoria, y vuelve a hacer la ejecucion, por eso se limpia de la memoria
//                anular();
                context.addMessage(null, new FacesMessage("Exito","Reguistro anulado"));
            }else{
                context.addMessage(null, new FacesMessage("Error","Revise que no los datos ingresados sean correctos"));
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error"+e));
            System.out.println("error ="+e);
        }
    }
    
//    Se extrae lo de la tabla y llena la informacion de cada insert en la tabla
    public void selecionarDatos(POJOs.Cliente cliente){
        setIdCliente(cliente.getIdCliente());
        setNombre1(cliente.getNombre1());
        setNombre2(cliente.getNombre2());
        setApellido1(cliente.getApellido1());
        setApellido2(cliente.getApellido2());
        setNit(cliente.getNit());
        setDireccion(cliente.getDireccion());
        setTelefono(cliente.getTelefono());
    }

//    -----------------------------------------------------------------------------------

    /**
     * @return the idCliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the nombre1
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * @param nombre1 the nombre1 to set
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 the nombre2 to set
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the listaClientes
     */
    public List getListaClientes() {
        return listaClientes;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListaClientes(List listaClientes) {
        this.listaClientes = listaClientes;
    } 
}
