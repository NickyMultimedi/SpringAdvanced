package be.multimedi.springAdvanced.beer;

import java.util.List;

public interface BeerRepository {
    Beer getBeerById(int id);
    List<Beer> getBeerByAlcohol(float alcohol);
    void updateBeer(Beer b);
}
