/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;


/**
 *
 * @author vicen
 */
import POJOs.Cliente;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author USUARIO
 */
public class CRUDCliente {
    public static List<Cliente>universo(){
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        List<Cliente> lista=null;
        try {
            session.beginTransaction();
            Criteria criteria=session.createCriteria(Cliente.class);
            criteria.createAlias("persona", "p");
         
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("p.nombre"))
                    .add(Projections.property("p.cedula"))
                    .add(Projections.property("p.edad"))
                    .add(Projections.property("direccion"))
                    .add(Projections.property("telefono"))
            );
            criteria.addOrder(Order.desc("idCliente"));
            lista =criteria.list();
                    
        } catch (Exception e) {
            System.out.println("error"+e);
        } finally{
        session.getTransaction().commit();
        
        }
        return lista;
    }
    /*
      public static boolean crear(String nombre, Integer cedula, Integer edad){
        
  boolean flag=false;
            Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
            Criteria criteria=session.createCriteria(Cliente.class);
            criteria.add(Restrictions.eq("cedula", cedula));
            criteria.add(Restrictions.eq("estado", true));
            Cliente insert = (Cliente)criteria.uniqueResult();
            Transaction transaction = null;
            
            try {
                transaction = session.beginTransaction();
                
                if (insert==null) {
                    insert=new Cliente();
                    insert.setNombre(nombre);
                    insert.setCedula(cedula);
                    insert.setEdad(edad);
                    insert.setEstado(true);
                    session.save(insert);
                    flag = true;
                }
                
                transaction.commit();
                
            } catch (Exception e) {
                transaction.rollback();
            }finally{
            session.close();
            }  
            return flag;
        }
        
public static boolean actualizar(Integer idCliente, String nombre, Integer cedula, Integer edad) {
    boolean flag = false;
    Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
    Criteria criteria = session.createCriteria(Cliente.class);
    criteria.add(Restrictions.eq("idCliente", idCliente)); // Corregir a "idCliente"
    Cliente insert = (Cliente) criteria.uniqueResult(); // Obtener la persona existente
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();

        if (insert != null) { // Si la persona existe
            insert.setNombre(nombre);   // Actualiza los campos
            insert.setCedula(cedula);
            insert.setEdad(edad);
            session.update(insert);     // Guarda los cambios en la base de datos
            flag = true;                // Actualización exitosa
        }

        transaction.commit();

    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }

    return flag;
}

            public static boolean anular(Integer idCliente) {
    boolean flag = false;
    Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
    Criteria criteria = session.createCriteria(Cliente.class);
    criteria.add(Restrictions.eq("idCliente", idCliente)); // Corregir el nombre a "idCliente"
    Cliente anular = (Cliente) criteria.uniqueResult(); // Buscar la persona por id

    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();

        if (anular != null) { // Si la persona existe
            anular.setEstado(false); // Cambiar el estado a false
            session.update(anular);  // Actualizar el objeto en la base de datos
            flag = true;             // Operación exitosa
        } else {
            System.out.println("No se encontró la persona con idCliente: " + idCliente);
        }

        transaction.commit();

    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }

    return flag;
}*/
    
}