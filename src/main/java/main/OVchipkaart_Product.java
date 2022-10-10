package main;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity(name = "OVchipkaart_Product")
@Table(name = "ov_chipkaart_Product")
public class OVchipkaart_Product implements Serializable {
    @Id
    @ManyToOne(
            fetch = FetchType.LAZY
    )@JoinColumn(name = "kaart_nummer")
    private OVChipkaart ovChipkaart;
    @Id
    @ManyToOne(
            fetch = FetchType.LAZY
    )@JoinColumn(name = "product_nummer")
    private Product product;
    @Column(name = "status")
    private String status;
    @Column(name = "last_update")
    private Date last_update;
    public OVchipkaart_Product(){}
    public OVchipkaart_Product(OVChipkaart ovChipkaart, Product product){
        this.product=product;
        this.ovChipkaart=ovChipkaart;
    }

    public String getStatus() {
        return status;
    }

    public OVChipkaart getOvChipkaart() {
        return ovChipkaart;
    }

    public void setOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaart = ovChipkaart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return ovChipkaart + "\n" + product + "\n" +
                "status= '" + status + '\'' +
                ", last_update = " + last_update;
    }
}
