package dao;

import model.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public class OrderDAO extends AbstractCRUD<Order> {
    @PersistenceContext(unitName = "pUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDAO() {
        super(Order.class);
    }
}
