/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author IngeMayk
 */
public class CRUDUsuario {
    public static Usuario select1(String nombreUsuario){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Usuario slct = null;
        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria.add(Restrictions.eq("usuario", nombreUsuario));
            criteria.add(Restrictions.eq("estado", true));
            slct = (Usuario) criteria.uniqueResult();
        } catch (Exception e) {
            System.out.println("erro" + e);
        } finally{
        session.flush();
        session.clear();
        session.close();
        }
        
        return slct;
        
    }
}
