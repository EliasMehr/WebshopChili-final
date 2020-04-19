package dao;

import model.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
@Named
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
