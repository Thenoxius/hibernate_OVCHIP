package DAO;

import main.OVChipkaart;
import main.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    public boolean save(OVChipkaart chipkaart) throws SQLException;
    public boolean update(OVChipkaart chipkaart);
    public boolean delete(OVChipkaart chipkaart);
    public List<OVChipkaart> findByReiziger(Reiziger reiziger);
    public OVChipkaart findByKaartNummer(OVChipkaart ovChipkaart);
    public List<OVChipkaart> findAll() throws SQLException;

    void setRdao(ReizigerDAO reizigerDAO);

}
