package be.multimedi.springAdvanced.order;

import be.multimedi.springAdvanced.beer.Beer;
import be.multimedi.springAdvanced.beer.exceptions.BeerOutOfStockException;
import be.multimedi.springAdvanced.beer.BeerRepository;
import be.multimedi.springAdvanced.beer.exceptions.InvalidBeerException;
import be.multimedi.springAdvanced.beer.exceptions.InvalidNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BeerOrderServiceTest {
    @Autowired
    private BeerOrderService service;
    @Autowired
    private BeerOrderRepository orderRepo;
    @Autowired
    private BeerRepository beerRepo;

    @Test
    void testOrderBeer() {
        service.orderBeer("Table 1", 1, 2);
        testOrderPlaced();
        testBeerStockChanged();
    }

    @Test
    private void testOrderPlaced() {
        BeerOrder order = orderRepo.getBeerOrderById(2);
        assertEquals(
                "Table 1",
                order.getName()
        );
    }

    @Test
    private void testBeerStockChanged() {
        Beer beer = beerRepo.getBeerById(1);
        assertEquals(
                98,
                beer.getStock()
        );
    }

    @Test
    void testBeerOutOfStockExceptionIsThrown() {
        assertThrows(
                BeerOutOfStockException.class,
                () -> service.orderBeer("Table 1", 1, 101)
        );
    }

    @Test
    void testInvalidBeerExceptionIsThrown() {
        assertThrows(
                InvalidBeerException.class,
                () -> service.orderBeer("Table 1", 10, 2)
        );
    }

    @Test
    void testInvalidNumberExceptionIsThrown() {
        assertThrows(
                InvalidNumberException.class,
                () -> service.orderBeer("Table 1", 1, -1)
        );
    }
}