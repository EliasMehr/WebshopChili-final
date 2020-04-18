package dao;

import model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
@Named
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

    public List<User> findByEmail(String inputEmail) {
        TypedQuery<User> typedQuery = em.createNamedQuery("getUserByEmail" , User.class);
        typedQuery.setParameter("email", inputEmail);
        try {
            return typedQuery.getResultList();
        }
        catch (Exception e) {
            System.out.println("CAUGHT ERRORRRRRR");
        }
        return null;

    }


}
