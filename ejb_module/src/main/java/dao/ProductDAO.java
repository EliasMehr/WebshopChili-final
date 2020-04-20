package dao;

import model.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
@RequestScoped
public class ProductDAO extends AbstractCRUD<Product>{

    @PersistenceContext(unitName = "pUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductDAO() {
        super(Product.class);
    }

}
