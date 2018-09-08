package firstcup.facade;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    @SuppressWarnings("SameParameterValue")
    AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        EntityManager em = getEntityManager();
        em.remove(em.merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq)
                .setMaxResults(range[1] - range[0] + 1)
                .setFirstResult(range[0])
                .getResultList();
    }

    public int count() {
        CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(cq.from(entityClass)));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
