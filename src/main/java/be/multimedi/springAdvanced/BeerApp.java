package be.multimedi.springAdvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BeerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BeerApp.class, args);

        BeerDao dao = ctx.getBean("beerDao" ,BeerDao.class);

        System.out.println(dao.getBeerById(20));

        dao.setStock(20, 100);

        dao.getBeerByAlcohol(2.0f).stream().forEach(System.out::println);
    }
}
