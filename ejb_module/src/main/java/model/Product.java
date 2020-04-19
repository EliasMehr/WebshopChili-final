package model;

import javax.persistence.*;
import java.io.Serializable;


@NamedQuery(name = "Product.findAll", query = "select p from Product p")

@Entity
public class Product implements Serializable {

    private static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String scoville_strength;

    private String description;

    private double price;

    private String name;

    @Column(name = "image_url")
    private String image_url;


    public String getScoville_strength() {
        return scoville_strength;
    }

    public void setScoville_strength(String scoville_strength) {
        this.scoville_strength = scoville_strength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
