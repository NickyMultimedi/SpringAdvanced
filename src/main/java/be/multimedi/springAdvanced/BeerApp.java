package be.multimedi.springAdvanced;

import be.multimedi.springAdvanced.beer.Beer;
import be.multimedi.springAdvanced.beer.BeerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BeerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BeerApp.class, args);

        BeerRepository repo = ctx.getBean("beerRepository", BeerRepository.class);
        Beer beer = repo.getBeerById(7);

        System.out.println(beer);

    }
}
