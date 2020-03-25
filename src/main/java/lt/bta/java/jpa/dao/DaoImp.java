package lt.bta.java.jpa.dao;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DaoImp<T> implements Dao<T> {

    private final EntityManager entityManager;

    public DaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T get(Class<T> entityClass, Object pk) {
        return get(null, entityClass, pk);
    }

    @Override
    public T get(String graphName, Class<T> entityClass, Object pk) {
        Map<String, Object> hints = null;
        if (graphName != null) {
            hints = new HashMap<>();
            EntityGraph graph = entityManager.getEntityGraph(graphName);
            hints.put("javax.persistence.fetchgraph", graph);
        }
        return entityManager.find(entityClass, pk, hints);
    }

    @Override
    public void save(T t) {
        executeInsideTransaction(entityManager -> entityManager.persist(t));
    }

    @Override
    public void update(Object pk, T t) {
        throw new RuntimeException("Reikia implementuoti!!!");
    }

    @Override
    public void delete(Class<T> entityClass, Object pk) {
        executeInsideTransaction(entityManager -> {
            T t = get(entityClass, pk);
            entityManager.remove(t);
        });
    }

    @Override
    public List<T> getPage(Class<T> entityClass, int pageSize, int skip) {
        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entityClass);
        Root<T> from = criteriaQuery.from(entityClass);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        TypedQuery<T> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(skip);
        typedQuery.setMaxResults(pageSize);
        List<T> result = typedQuery.getResultList();
        return result;
    }

    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
