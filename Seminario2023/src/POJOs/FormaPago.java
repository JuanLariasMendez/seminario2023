package POJOs;
// Generated 28/08/2023 06:27:19 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FormaPago generated by hbm2java
 */
public class FormaPago  implements java.io.Serializable {


     private Integer idFormaPago;
     private Usuario usuarioByUsuarioModifica;
     private Usuario usuarioByUsuarioIngreso;
     private Boolean estado;
     private String descripcion;
     private Date fechaIngreso;
     private Date fechaModifica;
     private Set<Venta> ventas = new HashSet<Venta>(0);

    public FormaPago() {
    }

    public FormaPago(Usuario usuarioByUsuarioModifica, Usuario usuarioByUsuarioIngreso, Boolean estado, String descripcion, Date fechaIngreso, Date fechaModifica, Set<Venta> ventas) {
       this.usuarioByUsuarioModifica = usuarioByUsuarioModifica;
       this.usuarioByUsuarioIngreso = usuarioByUsuarioIngreso;
       this.estado = estado;
       this.descripcion = descripcion;
       this.fechaIngreso = fechaIngreso;
       this.fechaModifica = fechaModifica;
       this.ventas = ventas;
    }
   
    public Integer getIdFormaPago() {
        return this.idFormaPago;
    }
    
    public void setIdFormaPago(Integer idFormaPago) {
        this.idFormaPago = idFormaPago;
    }
    public Usuario getUsuarioByUsuarioModifica() {
        return this.usuarioByUsuarioModifica;
    }
    
    public void setUsuarioByUsuarioModifica(Usuario usuarioByUsuarioModifica) {
        this.usuarioByUsuarioModifica = usuarioByUsuarioModifica;
    }
    public Usuario getUsuarioByUsuarioIngreso() {
        return this.usuarioByUsuarioIngreso;
    }
    
    public void setUsuarioByUsuarioIngreso(Usuario usuarioByUsuarioIngreso) {
        this.usuarioByUsuarioIngreso = usuarioByUsuarioIngreso;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }
    
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public Date getFechaModifica() {
        return this.fechaModifica;
    }
    
    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }
    public Set<Venta> getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set<Venta> ventas) {
        this.ventas = ventas;
    }




}


