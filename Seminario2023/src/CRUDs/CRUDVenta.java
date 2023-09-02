/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;
/**
 * CRUD en el que tendrémos los métodos que utilizaremos posteriormente en el xhtml
 * de las ventas
 * Aca utilizamos polimorfismo para hacer las tablas relacionadas y utilizar las FK
 * de la BDD
 */
import POJOs.Cliente;
import POJOs.FormaPago;
import POJOs.Usuario;
import POJOs.Venta;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author juanl
 */

public class CRUDVenta {
    //Toma de las llave foranea IDCliente y IDVenta
    //metodo Estatico
    public static boolean insert(Integer idCliente,Integer idFormaPago, Integer idUsuario){
        boolean flag=false;
        Date fecha=new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Venta.class);
        criteria.createAlias("usuarioByUsuarioIngreso", "u");
        //Una unica venta abierta por usuario, por usuario puedo tener una venta con una unica venta con estado 0 
        criteria.add(Restrictions.eq("estadoFinalizado",false));
        //criteria.add(Restrictions.eq("usuarioByUsuarioIngreso.idUsuario",true));
        criteria.add(Restrictions.eq("u.idUsuario", idUsuario));
        criteria.add(Restrictions.eq("estado",true));
        Venta insert=(Venta)criteria.uniqueResult();
        Transaction transaction=null;
        
        //Restriccion para evitar que se repitan las ventas
        //Solo puede por usuario una venta abierta
        try{
            transaction=session.beginTransaction();
            if(insert==null){
                insert=new Venta();
                insert.setEstado(true);
                insert.setEstadoFinalizado(false);
                //Para utilizar las llaves foraneas, y mandar info a otras tablas relacionadas, utilizamos instancias de esos tipos de obketos
                //Instancia del objeto Cliente
                Cliente cliente = new Cliente();
                cliente.setIdCliente(idCliente);
                insert.setCliente(cliente);
                insert.setFechaVenta(fecha);
                //Instancia del objeto FormaPago
                FormaPago formapago = new FormaPago();
                formapago.setIdFormaPago(idFormaPago);
                insert.setFormaPago(formapago);
                //Instancia del objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                insert.setUsuarioByUsuarioIngreso(usuario);
                insert.setFechaIngreso(fecha);
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
