package be.multimedi.springAdvanced.order;

import be.multimedi.springAdvanced.beer.*;
import be.multimedi.springAdvanced.beer.exceptions.BeerOutOfStockException;
import be.multimedi.springAdvanced.beer.exceptions.InvalidBeerException;
import be.multimedi.springAdvanced.beer.exceptions.InvalidNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("beerOrderService")
public class BeerOrderServiceImpl implements BeerOrderService {
    @Autowired
    private BeerRepository beerRepo;
    @Autowired
    private BeerOrderRepository beerOrderRepo;

    @Override
    @Transactional(rollbackFor = {BeerOutOfStockException.class, InvalidBeerException.class, InvalidNumberException.class})
    public int orderBeer(String name, int beerId, int number) {
        Beer beer = beerRepo.getBeerById(beerId);

        if (beer == null) {
            throw new InvalidBeerException("Beer: Beer not found");
        }
        if (number < 0) {
            throw new InvalidNumberException("Order amount: Number is less than 0");
        }
        if (beer.getStock() < number) {
            throw new BeerOutOfStockException("Beer Stock: Not enough beer in stock");
        }

        beer.setStock(beer.getStock() - number);

        BeerOrder order = new BeerOrder();
        order.setName(name);
        List<BeerOrderItem> orderItems = new ArrayList<>();
        BeerOrderItem item = new BeerOrderItem();
        item.setBeer(beer);
        item.setNumber(number);
        orderItems.add(item);
        order.setItems(orderItems);

        beerRepo.updateBeer(beer);
        beerOrderRepo.saveOrder(order);

        return order.getId();
    }

    @Override
    @Transactional(rollbackFor = {BeerOutOfStockException.class, InvalidBeerException.class, InvalidNumberException.class})
    public int orderBeers(String name, int[][] ordered) {
        BeerOrder order = new BeerOrder();
        order.setName(name);

        List<BeerOrderItem> orderItems = new ArrayList<>();

        for (int[] el: ordered) {
            int beerId = el[0];
            int number = el[1];
            Beer beer = beerRepo.getBeerById(beerId);

            if (beer == null) {
                throw new InvalidBeerException("Beer: Beer not found");
            }
            if (number < 0) {
                throw new InvalidNumberException("Order amount: Number is less than 0");
            }
            if (beer.getStock() < number) {
                throw new BeerOutOfStockException("Beer Stock: Not enough beer in stock");
            }

            beer.setStock(beer.getStock() - number);

            BeerOrderItem item = new BeerOrderItem();
            item.setBeer(beer);
            item.setNumber(number);
            orderItems.add(item);

            beerRepo.updateBeer(beer);
        }

        order.setItems(orderItems);

        beerOrderRepo.saveOrder(order);

        return order.getId();
    }
}
