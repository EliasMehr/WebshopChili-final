package dao;

import model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
// @EJB                         @Inject
// ejb = stateless, ELLER CDI = @RequsetScoped /ApllicationScoped
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
