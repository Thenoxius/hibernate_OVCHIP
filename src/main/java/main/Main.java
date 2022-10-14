package main;

import DAO.AdresDAOHibernate;
import DAO.OVChipkaartDAOHibernate;
import DAO.ProductDAOHibernate;
import DAO.ReizigerDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testFunction();
        //testFetchAll();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFunction() throws SQLException {
        ReizigerDaoHibernate rdao = new ReizigerDaoHibernate(factory);
        OVChipkaartDAOHibernate ovdao = new OVChipkaartDAOHibernate(factory);
        Reiziger reiziger = rdao.findById(2);
        ProductDAOHibernate pdao = new ProductDAOHibernate(factory);
        AdresDAOHibernate adao = new AdresDAOHibernate(factory);
        String gbdatum2 = "1991-04-22";
        Reiziger test = new Reiziger(26, "R", "van", "Rijnveld", java.sql.Date.valueOf(gbdatum2));
        Adres adrestest = new Adres(17, "2801NL", "10B", "Keizerstraat", "Gouda", test);
        test.setAdres(adrestest);
        rdao.save(test);
        System.out.println(rdao.findById(26));
        rdao.delete(test);
        OVChipkaart chip = ovdao.findByKaartNummer(35283);
        System.out.println(adao.findByReiziger(reiziger));
        System.out.println(ovdao.findByReiziger(reiziger));
        System.out.println(pdao.findAll());
    }
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }
}
