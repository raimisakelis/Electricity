package lt.bta.java.jpa.dao;

import lt.bta.java.entities.Work;

import javax.persistence.EntityManager;

public class WorkDao extends DaoImp<Work> {

    public WorkDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void update(Object pk, Work work) {
        executeInsideTransaction(em -> {
            Work workOrg = em.find(Work.class, pk);
            workOrg.setAuthorId(work.getAuthorId());
            workOrg.setYearOfWork(work.getYearOfWork());
            workOrg.setContentOfTheWork(work.getContentOfTheWork());
            em.persist(workOrg);
        });
    }
}