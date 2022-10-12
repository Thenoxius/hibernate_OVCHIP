package DAO;

import main.OVChipkaart;
import main.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO{
    public boolean save(Reiziger reiziger) throws SQLException;
    public boolean update(Reiziger reiziger) throws SQLException;
    public boolean delete(Reiziger reiziger) throws SQLException;
    public Reiziger findById(int id) throws SQLException;
    public List<Reiziger> findByGbdatum(String datum) throws SQLException;
    public List<Reiziger> findAll() throws SQLException;

    void setAdao(AdresDAO adao);

    void setOvdao(OVChipkaartDAO ovdao);

    void setPdao(ProductDAO pdao);
    public ProductDAO getPdao();
    public OVChipkaartDAO getOvdao();

    public List<OVChipkaart>  findReizigerKaarten(Reiziger reiziger);
}
