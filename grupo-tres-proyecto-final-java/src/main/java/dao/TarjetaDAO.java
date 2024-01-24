package dao;

import models.Boleta;
import models.Tarjeta;
import models.Usuario;
import org.example.until.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class TarjetaDAO {

    public Tarjeta findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tarjeta.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Tarjeta findByUser(long dni_usuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tarjeta> query = session.createQuery("SELECT t FROM Tarjeta t WHERE t.usuario.dni_usuario = :dni_usuario", Tarjeta.class);
            query.setParameter("dni_usuario", dni_usuario);
            return query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void insert(Tarjeta tarjeta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tarjeta);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void update(Tarjeta tarjeta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tarjeta);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void delete(Tarjeta tarjeta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(tarjeta);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public List<Tarjeta> saldo(long dni_usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tarjeta> query = session.createQuery("FROM Tarjeta WHERE dni_usuario = :dni_usuario", Tarjeta.class);
            query.setParameter("dni_usuario", dni_usuario);
            return query.list();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return null;
    }
}