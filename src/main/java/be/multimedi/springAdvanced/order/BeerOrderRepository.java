package be.multimedi.springAdvanced.order;

public interface BeerOrderRepository {
    int saveOrder(BeerOrder order);
    BeerOrder getBeerOrderById(int id);
}
