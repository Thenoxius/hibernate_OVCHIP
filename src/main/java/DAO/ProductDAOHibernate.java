package DAO;

import main.OVChipkaart;
import main.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO{
    public ProductDAOHibernate(SessionFactory sessionFactory){
        this.session = sessionFactory.openSession();
    }
    private AdresDAO adao;
    private ReizigerDAO rdao;
    private OVChipkaartDAO ovdao;
    public static Session session;
    @Override
    public boolean save(Product product) throws SQLException {
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            session.save(product);
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
    public boolean update(Product product) throws SQLException {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.update(product);
            transaction.commit();
            session.flush();
            return true;
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            session.delete(product);
            transaction.commit();
            session.flush();
            return true;
        } catch(RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException {
        Query query = session.createQuery("from Product WHERE Ov_chipkaart.kaartnummer = :ovchip").setParameter("ovchip", ovChipkaart.getKaartnummer());
        return query.list();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        Query query = session.createQuery("from Product ");
        return query.list();
    }

    @Override
    public void setRdao(ReizigerDAO rdao) {

    }

}
