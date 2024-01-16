package dao;

import models.Usuario;
//import org.example.until.HibernateUtil;
import org.example.until.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UsuarioDAO {

    public Usuario findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Usuario> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario", Usuario.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Usuario> findByRole(String role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = session.createQuery("FROM Usuario WHERE role = :role", Usuario.class);
            query.setParameter("role", role);
            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void insert(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void update(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void delete(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(usuario);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }


}