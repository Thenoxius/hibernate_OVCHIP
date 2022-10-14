package DAO;

import main.OVChipkaart;
import main.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO{

    public OVChipkaartDAOHibernate(SessionFactory sessionFactory){
        this.session = sessionFactory.openSession();
    }
    private AdresDAO adao;
    private ReizigerDaoHibernate rdao;
    private ProductDAO pdao;
    public static Session session;
    @Override
    public boolean save(OVChipkaart chipkaart) throws SQLException {
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            this.session.save(chipkaart);
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
    public boolean update(OVChipkaart chipkaart) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.update(chipkaart);
            transaction.commit();
            session.flush();
            return true;
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(OVChipkaart chipkaart) {
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            session.delete(chipkaart);
            transaction.commit();
            session.flush();
            return true;
        } catch(RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        Query query = session.createQuery("from Ov_chipkaart WHERE reiziger = :reizigerid").setParameter("reizigerid", reiziger);
        return query.list();
    }

    @Override
    public OVChipkaart findByKaartNummer(int kaartnummer) {
        return session.get(OVChipkaart.class, kaartnummer);
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        Query query = session.createQuery("from Ov_chipkaart ");
        return query.list();
    }

    @Override
    public void setRdao(ReizigerDAO reizigerDAO) {
        this.rdao = rdao;
    }
}
