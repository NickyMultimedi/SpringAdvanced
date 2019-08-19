package be.multimedi.springAdvanced.order;


public interface BeerOrderService {
    int orderBeer(String name, int beerId, int number);
    int orderBeers(String name, int[][] order);
}
