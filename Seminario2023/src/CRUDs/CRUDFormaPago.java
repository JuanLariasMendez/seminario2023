/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.FormaPago;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author juanl
 */
public class CRUDFormaPago {
    
    public static List<FormaPago> universo(){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession(/*Solo para ver si la session ya esta abierta*/);
        List<FormaPago>lista=null;
        try{
            session.beginTransaction();
            Criteria criteria=session.createCriteria(FormaPago.class);//La tabla que vamos a utilizar
            criteria.add(Restrictions.eq("estado"/*where*/, true));//Que muestre todos los estados activos de la tabla productos
            criteria.addOrder(Order.desc("idFormaPago"));//Para ordenar en desendete o ascendente[Se recomienda ascendente para ver el ultimo ingresado]
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
