package be.multimedi.springAdvanced.beer;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BeerRepositoryTest {
    @Autowired
    private BeerRepository repo;

    @Test
    void getBeerById() {
        Beer beer = repo.getBeerById(1);

        assertEquals(
                "TestBeer",
                beer.getName()
        );
    }

    @Test
    void getBeerByAlcohol() {
        Beer beer = repo.getBeerByAlcohol(7.0f).get(0);

        assertEquals(
                7.0f,
                beer.getAlcohol()
        );
    }

    @Test
    void updateBeer() {
        Beer beer = repo.getBeerById(1);
        beer.setStock(213);
        repo.updateBeer(beer);

        Beer beer2 = repo.getBeerById(1);

        assertTrue(
                beer.getStock() == beer2.getStock()
        );
    }
}