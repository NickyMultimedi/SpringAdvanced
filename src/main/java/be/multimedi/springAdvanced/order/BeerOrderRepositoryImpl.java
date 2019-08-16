package be.multimedi.springAdvanced.order;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository("beerOrderRepository")
public class BeerOrderRepositoryImpl implements BeerOrderRepository {
    private EntityManager manager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    @Transactional
    public int saveOrder(BeerOrder order) {
        manager.persist(order);
        return order.getId();
    }

    @Override
    @Transactional
    public BeerOrder getBeerOrderById(int id) {
        return manager.find(BeerOrder.class, id);
    }
}
