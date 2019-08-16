package be.multimedi.springAdvanced.order;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository("beerOrderRepository")
public class BeerOrderRepositoryImpl implements BeerOrderRepository {
    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public int saveOrder(BeerOrder order) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            manager.persist(order);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw ex;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }

        return order.getId() ;
    }

    @Override
    public BeerOrder getBeerOrderById(int id) {
        BeerOrder order;

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            order = manager.find(BeerOrder.class, 1);

            transaction.commit();
        }
        catch (Exception ex) {
            transaction.rollback();
            throw ex;
        }
        finally {
            manager.close();
        }

        return order;
    }
}
