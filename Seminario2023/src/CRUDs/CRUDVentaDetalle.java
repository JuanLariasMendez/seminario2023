/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Producto;
import POJOs.Venta;
import POJOs.VentaDetalle;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author juanl
 */
/**
 * CRUD de la factura
 */

public class CRUDVentaDetalle {
    //Toma de las llave foranea IDVenta, idProducto
    //metodo Estatico
    public static boolean insert(Integer idVenta,Integer idProducto, Integer cantidad, BigDecimal monto){
        boolean flag=false;
        Date fecha=new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(VentaDetalle.class);
        //Conversión de los idVenta e idProducto ya que eran tipo Venta y tipo Producto
        criteria.createAlias("venta", "v");
        criteria.createAlias("producto", "p");
        //Una unica venta abierta por usuario, por usuario puedo tener una venta con una unica venta con estado 0 
        //Con estos 2 bloques, evitamos que se repitab los productos, en vez de eso, se agrupan si son de un mismo tipo
        criteria.add(Restrictions.eq("p.idProducto",idProducto));
        criteria.add(Restrictions.eq("v.idVenta", idVenta));
        VentaDetalle insert=(VentaDetalle)criteria.uniqueResult();
        Transaction transaction=null;
        
        //Restriccion para evitar que se repitan las ventas
        try{
            transaction=session.beginTransaction();
            if(insert==null){
                insert=new VentaDetalle();
                //Para utilizar las llaves foraneas, y mandar info a otras tablas relacionadas, utilizamos instancias de esos tipos de objetos
                //Instancia del objeto Venta, ya que es una FK
                Venta venta = new Venta();
                venta.setIdVenta(idVenta);
                insert.setVenta(venta);
                //Instancia del objeto Producto
                Producto producto = new Producto();
                producto.setIdProducto(idProducto);
                insert.setProducto(producto);
                insert.setCantidad(cantidad);
                insert.setMonto(monto);
                session.save(insert);
                flag=true;
            }
            transaction.commit();
        }catch(HibernateException e){
            transaction.rollback();
            System.out.print("Error="+e);
        }finally{
           session.close();             
        }
        return flag;
    }
}