package DAO;

import main.Adres;
import main.OVChipkaart;
import main.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO{
    public AdresDAOHibernate(SessionFactory sessionFactory){
        this.session = sessionFactory.openSession();
    }
    private OVChipkaartDAO ovdao;
    private ReizigerDaoHibernate rdao;
    private ProductDAO pdao;
    public static Session session;
    @Override
    public boolean save(Adres adres) throws SQLException {
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            session.save(adres);
            transaction.commit();
            session.flush();
            return true;
        } catch (RuntimeException e){
            transaction.rollback();
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean update(Adres adres) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.update(adres);
            transaction.commit();
            session.flush();
            return true;
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Adres adres) {
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            session.delete(adres);
            transaction.commit();
            session.flush();
            return true;
        } catch(RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        return session.get(Adres.class, reiziger.getId());
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        Query query = session.createQuery("from Adres ");
        return query.list();
    }

    @Override
    public void setRdao(ReizigerDAO reizigerDAO) {
        this.rdao=rdao;
    }
}
