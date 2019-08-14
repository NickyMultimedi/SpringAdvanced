package be.multimedi.springAdvanced.order;


import be.multimedi.springAdvanced.beer.Beer;

import javax.persistence.*;

@Entity
@Table(name = "BeerOrderItems")
public class BeerOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "BeerId")
    private Beer beer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    @Override
    public String toString() {
        return "BeerOrderItem{" +
                "id=" + id +
                ", number=" + number +
                ", beer=" + beer +
                '}';
    }
}
