package dao;

import models.Boleta;
import models.Tarjeta;
import models.Usuario;
import org.example.until.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TarjetaDAO {

    public Tarjeta findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tarjeta.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Tarjeta findByUser(long usuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tarjeta.class, usuario);
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
}