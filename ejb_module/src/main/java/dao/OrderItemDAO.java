package dao;

import model.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderItemDAO extends AbstractCRUD<OrderItem>{

    @PersistenceContext(unitName = "pUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderItemDAO() {
        super(OrderItem.class);
    }
}
