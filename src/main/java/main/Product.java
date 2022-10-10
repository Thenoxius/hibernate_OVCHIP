package main;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity(name = "Product")
@Table(name = "product")
public class Product implements Serializable {
    @Id @GeneratedValue
    @Column(name = "product_nummer")
    private int product_nummer;
    @Column(name = "naam")
    private String naam;
    @Column(name = "beschrijving")
    private String beschrijving;
    @Column(name = "prijs")
    private long prijs;
    @OneToMany(
            mappedBy = "product",
            cascade= CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OVchipkaart_Product> ovKaartenMetProduct = new ArrayList<>();

    public Product() {

    }

    public List<OVchipkaart_Product> getChipkaarten(){
        return ovKaartenMetProduct;
    }
    public void addOvChipkaart(OVchipkaart_Product oVchipkaart_product){
        if (!ovKaartenMetProduct.contains(oVchipkaart_product)){
            ovKaartenMetProduct.add(oVchipkaart_product);
            oVchipkaart_product.setProduct(this);
        }
    }
    public void removeOvChipkaart(OVchipkaart_Product oVchipkaart_product){
        if (ovKaartenMetProduct.contains(oVchipkaart_product)){
            ovKaartenMetProduct.remove(oVchipkaart_product);
            oVchipkaart_product.setProduct(null);
        }
    }

    public Product(int pnr, String nm, String bsv, long prs){
        this.product_nummer=pnr;
        this.naam=nm;
        this.beschrijving=bsv;
        this.prijs=prs;
    }


    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public long getPrijs() {
        return prijs;
    }

    public void setPrijs(long prijs) {
        this.prijs = prijs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product_nummer == product.product_nummer && prijs == product.prijs && Objects.equals(naam, product.naam) && Objects.equals(beschrijving, product.beschrijving);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_nummer, naam, beschrijving, prijs);
    }

    public String toString(){
        return "Product: #" + product_nummer + " naam: " + naam + "  " + beschrijving + " Prijs: €" + prijs+ ".-";
    }
}
