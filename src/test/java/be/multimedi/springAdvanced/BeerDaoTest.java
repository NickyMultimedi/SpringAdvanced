package be.multimedi.springAdvanced;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BeerDaoTest {
    @Autowired
    private BeerDao test;

    @Test
    public void testGetBeerById() {
        String beer = test.getBeerById(1);

        assertEquals(
                "TestBeer 7.0 2.75 100",
                beer
        );
    }

    @Test
    public void testUpdateStock() {
        test.setStock(1, 200);

        String beer = test.getBeerById(1);

        assertEquals(
                "TestBeer 7.0 2.75 200",
                beer
        );
    }

    @Test
    public void testGetBeerByAlcohol() {
        String beer = test.getBeerByAlcohol(7.0f).get(0);

        assertEquals(
                "TestBeer 7.0 2.75 200",
                beer
        );
    }
}