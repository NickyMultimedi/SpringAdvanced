package be.multimedi.springAdvanced.beer;

import be.multimedi.springAdvanced.brewer.Brewer;
import be.multimedi.springAdvanced.category.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Beers")
public class Beer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private float price;

    @Column(name = "Stock")
    private int stock;

    @Column(name = "Alcohol")
    private float alcohol;

    @Version
    @Column(name = "Version")
    private int version;

    @ManyToOne
    @JoinColumn(name = "BrewerId")
    private Brewer brewer;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(float alcohol) {
        this.alcohol = alcohol;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Brewer getBrewer() {
        return brewer;
    }

    public void setBrewer(Brewer brewer) {
        this.brewer = brewer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", alcohol=" + alcohol +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return getId() == beer.getId() &&
                Float.compare(beer.getPrice(), getPrice()) == 0 &&
                getStock() == beer.getStock() &&
                Float.compare(beer.getAlcohol(), getAlcohol()) == 0 &&
                getVersion() == beer.getVersion() &&
                getName().equals(beer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getStock(), getAlcohol(), getVersion());
    }
}
