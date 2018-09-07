package firstcup.facade;

import firstcup.entity.FirstCupUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FirstCupUserFacadeBean extends AbstractFacade<FirstCupUser>
        implements FirstCupUserFacade {

    @PersistenceContext(unitName = "firstcupclient")
    private EntityManager em;

    public FirstCupUserFacadeBean() {
        super(FirstCupUser.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public double findAverageAgeDifference() {
        Query query = em.createNamedQuery("findAverageAgeDifference");
        Double avgAgeDiff = (Double) query.getSingleResult();
        if (avgAgeDiff == null) {
            avgAgeDiff = 0.0;
        }
        return avgAgeDiff;
    }
}
