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

// @EJB                         @Inject
// ejb = stateless, ELLER CDI = @RequsetScoped /ApllicationScoped
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

    public List<Product> getAllProducts() {
        TypedQuery<Product> typedQuery = em.createQuery("" +
                "select p from Product p", Product.class);
        return typedQuery.getResultList();
    }

}
