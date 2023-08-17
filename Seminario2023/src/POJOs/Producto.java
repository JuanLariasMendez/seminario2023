package POJOs;
// Generated 17/07/2023 07:04:57 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
public class Producto  implements java.io.Serializable {


     private int idProducto;
     private Usuario usuarioByUsuarioModifica;
     private Usuario usuarioByUsuarioIngreso;
     private Boolean estado;
     private String nombreProducto;
     private Integer cantidad;
     private BigDecimal precio;
     private Date fechaIngreso;
     private Date fechaModifica;
     private Set<VentaDetalle> ventaDetalles = new HashSet<VentaDetalle>(0);
    public Producto() {
    }

	
    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }
    public Producto(int idProducto, Usuario usuarioByUsuarioModifica, Usuario usuarioByUsuarioIngreso, Boolean estado, String nombreProducto, Integer cantidad, BigDecimal precio, Date fechaIngreso, Date fechaModifica, Set<VentaDetalle> ventaDetalles) {
       this.idProducto = idProducto;
       this.usuarioByUsuarioModifica = usuarioByUsuarioModifica;
       this.usuarioByUsuarioIngreso = usuarioByUsuarioIngreso;
       this.estado = estado;
       this.nombreProducto = nombreProducto;
       this.cantidad = cantidad;
       this.precio = precio;
       this.fechaIngreso = fechaIngreso;
       this.fechaModifica = fechaModifica;
       this.ventaDetalles = ventaDetalles;
    }
   
    public int getIdProducto() {
        return this.idProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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
    public String getNombreProducto() {
        return this.nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
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
    public Set<VentaDetalle> getVentaDetalles() {
        return this.ventaDetalles;
    }
    
    public void setVentaDetalles(Set<VentaDetalle> ventaDetalles) {
        this.ventaDetalles = ventaDetalles;
    }




}

