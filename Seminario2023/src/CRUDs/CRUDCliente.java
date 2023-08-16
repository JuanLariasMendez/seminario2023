/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Cliente;
import POJOs.Usuario;
import java.util.Date;
import java.util.List;
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
public class CRUDCliente {
    public static boolean insert(String nombre1, String nombre2, String apellido1,
        String apellido2, String nit, String direccion, String telefono, int usuario) {
        boolean flag = false;
        Date fecha = new Date();
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("nombre1", nombre1));
        criteria.add(Restrictions.eq("nombre2", nombre2));
        criteria.add(Restrictions.eq("apellido1", apellido1));
        criteria.add(Restrictions.eq("apellido2", apellido1));

        criteria.add(Restrictions.eq("estado", true));
        Cliente insert = (Cliente) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (insert == null) {
                insert = new Cliente();
                insert.setEstado(true);
                insert.setNombre1(nombre1);
                insert.setNombre2(nombre2);
                insert.setApellido1(apellido1);
                insert.setApellido2(apellido2);
                insert.setNit(nit);
                insert.setDireccion(direccion);
                insert.setTelefono(telefono);

                Usuario usuario2 = new Usuario();
                usuario2.setIdUsuario(usuario);
                insert.setUsuarioByUsuarioIngreso(usuario2);

                insert.setFechaIngreso(fecha);
                session.save(insert);
                flag = true;

            }
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Error = " + e);

        } finally {
            session.close();

        }
        return flag;
    }
    
    public static boolean update(Integer idCliente, String nit, String direccion, String telefono, int idUsuario){
        boolean flag=false;
        Date fecha = new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("idCliente",idCliente));
        Cliente update=(Cliente)criteria.uniqueResult();//ejecuta el codigo
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            //para ver si ya existe, y si sí, que lo modifique
            if(update!=null){
                //para conocer los nuevos valores[Estados,]
                update.setNit(nit);
                update.setTelefono(telefono);
                update.setDireccion(direccion);
                update.setFechaModifica(fecha);
                Usuario usuario=new Usuario();
                usuario.setIdUsuario(idUsuario);
                update.setUsuarioByUsuarioModifica(usuario);
                session.update(update);//Los del parantesis son los valores nuevos, y el update fuera es la accion que se esta haciendo
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
    
    public static boolean anular(Integer idCliente,Integer idUsuario){
        boolean flag=false;
        Date fecha = new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("idCliente",idCliente));
        Cliente update=(Cliente)criteria.uniqueResult();//ejecuta el codigo
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            //para ver si ya existe, y si sí, que lo modifique
            if(update!=null){
                //para conocer los nuevos valores[Estados,]
                update.setEstado(false);
                update.setFechaModifica(fecha);
                Usuario usuario=new Usuario();
                usuario.setIdUsuario(idUsuario);
                update.setUsuarioByUsuarioModifica(usuario);
                session.update(update);//Los del parantesis son los valores nuevos, y el update fuera es la accion que se esta haciendo
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
    
    //metodo eliminar
        public static boolean eliminar(Integer idCliente,Integer idUsuario){
        boolean flag=false;
        Date fecha = new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("idCliente",idCliente));
        Cliente update=(Cliente)criteria.uniqueResult();//ejecuta el codigo
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
        
        public static Cliente select(Integer idCliente){
            Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
            Criteria criteria=session.createCriteria(Cliente.class);
            criteria.add(Restrictions.eq("idCliente",idCliente));
            Cliente select=(Cliente)criteria.uniqueResult();//para ejecutar las 2 lineas de codigo anterior
            if(select==null){
                select=new Cliente();
                select.setIdCliente(0);
            }
            session.close();
            return select;
        } 
        
        //metodo para pruebas
            //Select * from (Where)
    public static List<Cliente> universo(){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession(/*Solo para ver si la session ya esta abierta*/);
        List<Cliente>lista=null;
        try{
            session.beginTransaction();
            Criteria criteria=session.createCriteria(Cliente.class);//La tabla que vamos a utilizar
            criteria.add(Restrictions.eq("estado"/*where*/, true));//Que muestre todos los estados activos de la tabla productos
            criteria.addOrder(Order.desc("idCliente"));//Para ordenar en desendete o ascendente[Se recomienda ascendente para ver el ultimo ingresado]
            criteria.setMaxResults(500);//Se puede agregar un limite en las busquedas, para ser mas precisos y que el sistema sea menos lento
            lista=criteria.list();
        }catch(HibernateException e){
            System.out.println("Error="+e);//Esta para ver cuales son los errores de logica
        }finally{
            session.getTransaction().commit();//Esto es lo que hace que las sessiones sin filalizar se cierren automaticamente
        }
        return lista;
              
    }
}
