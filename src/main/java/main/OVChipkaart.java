package main;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Entity(name = "Ov_chipkaart")
@Table(name = "ov_chipkaart")
public class OVChipkaart implements Serializable {
    @Id @GeneratedValue
    @Column(name = "kaart_nummer")
    private int kaartnummer;
    @Column(name = "geldig_tot")
    private Date geldig_tot;
    @Column(name = "klasse")
    private int klasse;
    @Column(name = "saldo")
    private long saldo;
    @ManyToOne(
            fetch = FetchType.LAZY
    )@JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;
    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(
            name = "OVchipkaart_Product",
            joinColumns = @JoinColumn(name = "kaart_nummer"),
            inverseJoinColumns = @JoinColumn(name = "product_nummer")
    )
    private List <Product> mijnProducten = new ArrayList<>();

    public OVChipkaart() {

    }

    public void addToProducten(Product product){
        if (!mijnProducten.contains(product)){
            mijnProducten.add(product);
            product.addOvChipkaart(this);
        }
    }
    public void removeProducten(Product product){
        if (mijnProducten.contains(product)){
            mijnProducten.remove(product);
            product.removeOvChipkaart(null);
        }
    }

    public OVChipkaart(int kaartnummer, Date geldig_tot, int klasse, long saldo, Reiziger reiziger){
        this.kaartnummer=kaartnummer;
        this.geldig_tot=geldig_tot;
        this.klasse=klasse;
        this.saldo=saldo;
        this.reiziger=reiziger;
    }



    public int getKaartnummer() {
        return kaartnummer;
    }


    public java.sql.Date getGeldig_tot() {
        return (java.sql.Date)  geldig_tot;
    }


    public int getKlasse() {
        return klasse;
    }
    public void setKlasse(int klasse){
        this.klasse=klasse;
    }


    public long getSaldo() {
        return saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }
    public void setMijnReiziger(Reiziger reiziger){this.reiziger= reiziger;}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OVChipkaart that = (OVChipkaart) o;
        return kaartnummer == that.kaartnummer && klasse == that.klasse && saldo == that.saldo && Objects.equals(geldig_tot, that.geldig_tot) && Objects.equals(reiziger, that.reiziger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kaartnummer, geldig_tot, klasse, saldo, reiziger);
    }

    public String toString(){
        return "kaart met #:" + kaartnummer + " is geldig tot " + geldig_tot + " en bevat een saldo van €" + saldo + " en is te gebruiken voor klasse " + klasse;
    }

}
