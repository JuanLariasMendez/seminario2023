/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario2023;

import CRUDs.CRUDCliente;
import CRUDs.CRUDProducto;
import java.math.BigDecimal;

/**
 *
 * @author juanl
 */
public class Seminario2023 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //BigDecimal precio=new BigDecimal(10.5);
//----------------------------CRUD PRODUCTO-------------------------------------
//        System.out.println("insert="+CRUDs.CRUDProducto.insert("Consome",100,precio,1));
//        System.out.println("update="+CRUDs.CRUDProducto.update(1, "maler", precio, 1)); //IdProducto - "NombreDelProducto" - variablede arriba(precio) - Usuaurio

        /*Entorno de pruebas de consultas*/
//        for(int i=0;i<CRUDProducto.universo().size();i++){
//              /*sout[shorcutPara generar este medoto]*/
//              System.out.println("Producto="+CRUDProducto.universo().get(i).getNombreProducto());
//              System.out.println("Cantidad="+CRUDProducto.universo().get(i).getCantidad());
//              System.out.println("Precio="+CRUDProducto.universo().get(i).getPrecio());
//          }  
//        System.out.println("anular="+CRUDs.CRUDProducto.anular(1,1)); //IdProducto - Usuaurio
//        System.out.println("eliminar="+CRUDs.CRUDProducto.eliminar(1,1)); //IdProducto - Usuaurio
//        System.out.println("Nombre="+CRUDs.CRUDProducto.select(2).getNombreProducto()); //IdProducto - Usuaurio


//----------------------------CRUD CLIENTE-------------------------------------
//        System.out.println("Cliente"+CRUDs.CRUDCliente.insert("Juan", "Amilcar", "Larias", "Mendez", "10994808", "Cambote", "45454545", 1));        
//          System.out.println("update="+CRUDs.CRUDCliente.update(1, "20202020", "Huehue", "60606060",1)); //IdProducto - "NombreDelProducto" - variablede arriba(precio) - Usuaurio          
//        System.out.println("anular="+CRUDs.CRUDCliente.anular(1,1)); //IdProducto - Usuaurio
//        System.out.println("eliminar="+CRUDs.CRUDCliente.eliminar(1,1)); //IdProducto - Usuaurio
//        System.out.println("Nombre= "+CRUDs.CRUDCliente.select(2).getNombre1());

//        /*Entorno de pruebas de consultas*/
//        for(int i=0;i<CRUDCliente.universo().size();i++){
//              /*sout[shorcutPara generar este medoto]*/
//              System.out.println("Nombre= "+CRUDCliente.universo().get(i).getNombre1());
//              System.out.println("Apellido= "+CRUDCliente.universo().get(i).getApellido1());
//              System.out.println("Nit= "+CRUDCliente.universo().get(i).getNit());
//              System.out.println("Direccion= "+CRUDCliente.universo().get(i).getDireccion());
//              System.out.println("Direccion= "+CRUDCliente.universo().get(i).getTelefono());
//          } 
//----------------------------CRUD VENTA DETALLE-------------------------------------
        BigDecimal precioVD=new BigDecimal(100);
//      System.out.println("Forma de pago"+CRUDs.CRUDVenta.insert(2,1,1));
        System.out.println("detalle"+CRUDs.CRUDVentaDetalle.insert(3, 1, 5, precioVD));
    }
}
