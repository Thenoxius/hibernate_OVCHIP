package main;

import org.hibernate.hql.internal.ast.tree.UnaryArithmeticNode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Entity(name = "Reiziger")
@Table(name = "reiziger")
public class Reiziger implements Serializable {
    @Id @GeneratedValue
    @Column(name = "reiziger_id")
    private int id;
    @Column(name = "voorletters")
    private String voorletters;
    @Column(name = "tussenvoegsel")

    private String tussenvoegsel;
    @Column(name = "achternaam")
    private String achternaam;
    @OneToOne(
            mappedBy = "reiziger",
            cascade= CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Adres mijnAdres;

    public Reiziger() {

    }

    public void setAdres(Adres adres){
        mijnAdres = adres;
    }

    @Column(name = "geboortedatum")
    private Date geboortedatum;
    @OneToMany(
            mappedBy = "reiziger",
            cascade= CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OVChipkaart> mijnChipkaarten = new ArrayList<>();


    public void addToChipkaarten (OVChipkaart chip){
        mijnChipkaarten.add(chip);
        chip.setMijnReiziger(this);
    }
    public List<OVChipkaart> getMijnChipkaarten(){
        return mijnChipkaarten;
    }

    public Reiziger(int id,String vl, String tv, String ach, Date gb){
        this.id = id;
        this.voorletters = vl;
        this.tussenvoegsel = tv;
        this.achternaam = ach;
        this.geboortedatum = gb;
    }
    public Adres getMijnAdres(){
        return mijnAdres;
    }

    public int getId(){
        return id;
    }

    public void setId(int nieuwId){
        id = nieuwId;
    }
    public String getNaam(){

        if (tussenvoegsel == null){
            return voorletters + " " + " " + achternaam;
        } else {
            return voorletters + " " +tussenvoegsel+ " " + achternaam;
        }
    }
    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }
    public java.sql.Date getGeboortedatum() {
        return (java.sql.Date) geboortedatum;
    }
    public String toString(){
        return "Reiziger: " + this.getNaam() + " met ID: " + this.getId();
        }

    public boolean equals(Object andereObject){
        boolean gelijkeObjecten = false;
        if (andereObject instanceof Reiziger) {
            Reiziger anderReiziger = (Reiziger) andereObject;
            if (this.id == anderReiziger.id &&  this.voorletters.equals(anderReiziger.voorletters) && this.tussenvoegsel.equals(anderReiziger.tussenvoegsel)
                    && this.achternaam.equals(anderReiziger.achternaam) && this.geboortedatum.equals(anderReiziger.geboortedatum)){
                gelijkeObjecten = true;}
        }
        return gelijkeObjecten;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voorletters, tussenvoegsel, achternaam, mijnAdres, geboortedatum, mijnChipkaarten);
    }
}
