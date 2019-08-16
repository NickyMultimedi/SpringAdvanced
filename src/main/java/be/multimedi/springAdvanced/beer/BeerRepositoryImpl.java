package be.multimedi.springAdvanced.beer;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository("beerRepository")
public class BeerRepositoryImpl implements BeerRepository {
    private EntityManager manager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    @Transactional
    public Beer getBeerById(int id) {
        return manager.find(Beer.class, id);
    }

    @Override
    @Transactional
    public List<Beer> getBeerByAlcohol(float alcohol) {
        TypedQuery<Beer> query = manager.createQuery("select b from Beer b where b.alcohol = ?1", Beer.class);
        query.setParameter(1, alcohol);
        List<Beer> beers = query.getResultList();
        return beers;
    }

    @Override
    @Transactional
    public void updateBeer(Beer beer) {
        manager.merge(beer);
    }
}
