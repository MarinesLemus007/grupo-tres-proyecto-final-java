package daos;

import models.Usuario;
import org.example.until.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClienDAO {

    public Usuario findById(Long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Usuario.class, 1l);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<Usuario> findAll(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Usuario").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<Usuario> findByName(String nombre_usuario){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Client WHERE firstName='"+nombre_usuario+"'").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public void insert(Usuario usuario){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void update(Usuario usuario){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(usuario);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void delete(Usuario usuario){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(usuario);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
