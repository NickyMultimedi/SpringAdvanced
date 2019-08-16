package be.multimedi.springAdvanced.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BeerOrderRepositoryTest {
    @Autowired
    private BeerOrderRepository repo;

    @Test
    void testGetBeerOrderById() {
        BeerOrder order = repo.getBeerOrderById(1);
        assertEquals(
                "TestOrder",
                order.getName()
        );
    }

    @Test
    void testSaveOrder() {
        int id = repo.saveOrder(new BeerOrder());

        assertEquals(
                2,
                id
        );
    }
}