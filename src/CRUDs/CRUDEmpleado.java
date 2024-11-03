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
import POJOs.Empleado;
import POJOs.Persona;
import java.math.BigDecimal;
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
public class CRUDEmpleado {
    public static List<Empleado>universo(){
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        List<Empleado> lista=null;
        try {
            session.beginTransaction();
            Criteria criteria=session.createCriteria(Empleado.class);
            criteria.createAlias("persona", "p");
            criteria.add(Restrictions.eq("estado",true));
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("idEmpleado"))
                    .add(Projections.property("horasTrabajo"))
                    .add(Projections.property("dpi"))
                    .add(Projections.property("estadoCivil"))
                    .add(Projections.property("sueldo"))
                    .add(Projections.property("p.nombre"))
            );
            criteria.addOrder(Order.desc("idEmpleado"));
            lista =criteria.list();
                    
        } catch (Exception e) {
            System.out.println("error"+e);
        } finally{
        session.getTransaction().commit();
        
        }
        return lista;
    }
      public static boolean crear(Integer horasTrabajo, String dpi, String estadoCivil, BigDecimal sueldo, Integer idPersona){
        
  boolean flag=false;
            Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
            Criteria criteria=session.createCriteria(Empleado.class);
            criteria.add(Restrictions.eq("dpi", dpi));
            criteria.add(Restrictions.eq("estado", true));
            Empleado insert = (Empleado)criteria.uniqueResult();
            Transaction transaction = null;
            
            try {
                transaction = session.beginTransaction();
                
                if (insert==null) {
                    insert=new Empleado();
                  insert.setHorasTrabajo(horasTrabajo);
                    insert.setDpi(dpi);
                    insert.setEstadoCivil(estadoCivil);
                    insert.setSueldo(sueldo);
                    // aqui es donde relaciono la relacion 
                    Persona persona = new Persona();
                    insert.setPersona(persona);
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
        
public static boolean actualizar(Integer idEmpleado, Integer horasTrabajo, String dpi, String estadoCivil, BigDecimal sueldo, Integer idPersona) {
    boolean flag = false;
    Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
    Criteria criteria = session.createCriteria(Empleado.class);
    criteria.add(Restrictions.eq("idEmpleado", idEmpleado)); // Corregir a "idEmpleado"
    Empleado insert = (Empleado) criteria.uniqueResult(); // Obtener la persona existente
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();

        if (insert != null) { // Si la persona existe
                  insert.setHorasTrabajo(horasTrabajo);
                    insert.setDpi(dpi);
                    insert.setEstadoCivil(estadoCivil);
                    insert.setSueldo(sueldo);
                    Persona persona = new Persona();
                    insert.setPersona(persona);
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

            public static boolean anular(Integer idEmpleado) {
    boolean flag = false;
    Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
    Criteria criteria = session.createCriteria(Empleado.class);
    criteria.add(Restrictions.eq("idEmpleado", idEmpleado)); // Corregir el nombre a "idEmpleado"
    Empleado anular = (Empleado) criteria.uniqueResult(); // Buscar la persona por id

    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();

        if (anular != null) { // Si la persona existe
            anular.setEstado(false); // Cambiar el estado a false
            session.update(anular);  // Actualizar el objeto en la base de datos
            flag = true;             // Operación exitosa
        } else {
            System.out.println("No se encontró la persona con idEmpleado: " + idEmpleado);
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

    
}