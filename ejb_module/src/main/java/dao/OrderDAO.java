package dao;

import model.Order;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Named
@RequestScoped
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
