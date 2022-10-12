package DAO;

import main.OVChipkaart;
import main.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ReizigerDaoHibernate implements ReizigerDAO{
    public ReizigerDaoHibernate(SessionFactory sessionFactory){
        this.session = sessionFactory.openSession();
    }
    public static Session session;
    public boolean save(Reiziger r){
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            this.session.save(r);
            transaction.commit();
            return true;
        } catch (RuntimeException e){
            transaction.rollback();
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            this.session.update(reiziger);
            transaction.commit();
            return true;
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            this.session.delete(reiziger);
            transaction.commit();
            return true;
        } catch(RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        return session.get(Reiziger.class, id);
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) throws SQLException {
        Date date = java.sql.Date.valueOf(datum);
        Query query = session.createQuery("from Reiziger WHERE geboortedatum = :gbd").setParameter("gbd", date);
        return query.list();
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        Query query = session.createQuery("from Reiziger");
        return query.list();
    }

    @Override
    public void setAdao(AdresDAO adao) {

    }

    @Override
    public void setOvdao(OVChipkaartDAO ovdao) {

    }

    @Override
    public void setPdao(ProductDAO pdao) {

    }

    @Override
    public ProductDAO getPdao() {
        return null;
    }

    @Override
    public OVChipkaartDAO getOvdao() {
        return null;
    }

    @Override
    public List<OVChipkaart> findReizigerKaarten(Reiziger reiziger) {
        return null;
    }
}