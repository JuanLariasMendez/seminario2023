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
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author juanl
 */
/**
 * CRUD de la factura
 */

public class CRUDVentaDetalle {
        
    //Select * from (Where)
    public static List<VentaDetalle> universo(Integer idVenta){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession(/*Solo para ver si la session ya esta abierta*/);
        List<VentaDetalle>lista=null;
        try{
            session.beginTransaction();
            Criteria criteria=session.createCriteria(VentaDetalle.class);//La tabla que vamos a utilizar
            criteria.createAlias("venta", "v");
            criteria.createAlias("producto", "p");
            criteria.add(Restrictions.eq("v.idVenta",idVenta));//Para que unicamente liste las ventas que se estan manejenado, de lo contrario listaria todas las ventas existentes
            criteria.addOrder(Order.desc("idVentaDetalle")/*where*/);//Que muestre todos los estados activos de la tabla productos
            //criteria.addOrder(Order.desc("idProducto"));//Para ordenar en desendete o ascendente[Se recomienda ascendente para ver el ultimo ingresado]
            criteria.setMaxResults(500);//Se puede agregar un limite en las busquedas, para ser mas precisos y que el sistema sea menos lento
            lista=criteria.list();
        }catch(HibernateException e){
            System.out.println("Error="+e);//Esta para ver cuales son los errores de logica
        }finally{
            session.getTransaction().commit();//Esto es lo que hace que las sessiones sin filalizar se cierren automaticamente
        }
        return lista;
              
    }
    
    
    
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
    
        //metodo eliminar
        public static boolean eliminar(Integer idVentaDetalle){
        boolean flag=false;
        Date fecha = new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(VentaDetalle.class);
        criteria.add(Restrictions.eq("idVentaDetalle",idVentaDetalle));
        VentaDetalle update=(VentaDetalle)criteria.uniqueResult();//ejecuta el codigo
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            //para ver si ya existe, y si sí, que lo modifique
            if(update!=null){
                session.delete(update);//Esta es la linea de codigo para hacer que el codigo borre una fila entera, el resto queda igual al metodo anular
                flag=true;
            }
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();//en caso que la transaccion no se complete por alguna razon, se hace un rollback
            System.out.println("Error: "+e);
        }finally{
            session.close();
        }
        return flag;
    }
}