package dao;

import model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
@RequestScoped
public class UserDAO extends AbstractCRUD<User> {

    @PersistenceContext(unitName = "pUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserDAO() {
        super(User.class);
    }

    public User findByEmail(String inputEmail) {
        TypedQuery<User> typedQuery = em.createNamedQuery("getUserByEmail" , User.class);
        typedQuery.setParameter("email", inputEmail);
        return typedQuery.getSingleResult();
    }

    public List<User> findAllCustomers() {
        TypedQuery<User> typedQuery = em.createNamedQuery("User.getCustomers", User.class);
        return typedQuery.getResultList();
    }


}
