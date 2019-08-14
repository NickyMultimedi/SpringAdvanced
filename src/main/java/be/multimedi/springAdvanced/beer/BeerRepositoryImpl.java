package be.multimedi.springAdvanced.beer;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository("beerRepository")
public class BeerRepositoryImpl implements BeerRepository {
    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Beer getBeerById(int id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            Beer beer = manager.find(Beer.class, id);
            transaction.commit();
            return beer;
        } catch (Exception ex) {
            transaction.rollback();
            throw ex;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    @Override
    public List<Beer> getBeerByAlcohol(float alcohol) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            TypedQuery<Beer> query = manager.createQuery("select b from Beer b where b.alcohol = ?1", Beer.class);
            query.setParameter(1, alcohol);
            transaction.begin();
            List<Beer> beers = query.getResultList();
            transaction.commit();
            return beers;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    @Override
    public void updateBeer(Beer b) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.merge(b);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }
}
