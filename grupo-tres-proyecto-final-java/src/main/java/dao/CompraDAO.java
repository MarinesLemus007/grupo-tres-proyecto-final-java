package dao;

import models.Compra;
import org.example.until.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CompraDAO {
    public Compra findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Compra.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Compra> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Compra", Compra.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Compra> findByName(String numero_compra) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Compra> query = session.createQuery("FROM Compra WHERE numero_compra = :numero_compra", Compra.class);
            query.setParameter("numero_compra", numero_compra);
            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void insert(Compra compra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(compra);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void update(Compra compra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(compra);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void delete(Compra compra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(compra);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    /*public void addPagoToCompra(int numero_compra, Pagos pagos){
        Transaction transaction = null;
        try (Session session= HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            //obtener total compra
            Compra compra = session.get(Compra.class, numero_compra);
            if (compra != null){
                compra.pagoRealizado(true);
                session.saveOrUpdate(compra);
            }
            transaction.commit();
        }catch (Exception ex){
            if (transaction !=null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }*/
}
