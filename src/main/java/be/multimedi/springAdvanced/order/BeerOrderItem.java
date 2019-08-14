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
}
