package lt.bta.java.api.impl;

import lt.bta.java.api.service.WorkService;
import lt.bta.java.entities.Work;
import lt.bta.java.jpa.PersistenceUtil;
import lt.bta.java.jpa.dao.DaoImp;
import lt.bta.java.jpa.dao.WorkDao;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * AuthorServiceImpl klase skirta author objekto duomenu
 * mainams tarp aplikacijos ir duomenu bazes.
 *
 */
@Path("/work")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkServiceImpl implements WorkService {

    /**
     * Metodas, skirtas gauti Work objekta is duomenu bazes.
     * @param id unikalus Work objekto identifikatorius.
     * @return Work tipo objekta.
     */
    @Override
    @GET
    @Path("/{id}")
    public Work get (@PathParam("id") int id) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        WorkDao workDao = new WorkDao(em);
        Work w = workDao.get(Work.class, id);
        em.close();
        return w;
    }

    /**
     * Metodas, skirtas nauja Work objekta irasyti
     * i duomenu baze.
     * @param work Work objektas.
     */
    @Override
    @POST
    @Path("/save")
    public void save(Work work) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        DaoImp dao = new DaoImp(em);
        dao.save(work);
    }

    /**
     * Metodas, skirtas pasalinti Work objekta
     * is duomenu bazes.
     * @param id unikalus Work objekto identifikatorius.
     */
    @Override
    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") int id) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        DaoImp dao = new DaoImp(em);
        dao.delete(Work.class, id);
    }

    /**
     * Metodas, skirtas atnaujinti Work objekto laukus
     * duomenu bazeje.
     * @param id unikalus Work objekto identifikatorius.
     * @param work modifikuotas Work objektas.
     */
    @Override
    @PUT
    @Path("/update/{id}")
    public void update(@PathParam("id") int id, Work work) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        WorkDao workDao = new WorkDao(em);
        workDao.update(id, work);
    }

    /**
     * Metodas, skirtas gauti Work objektu sarasa is
     * duomenu bazes.
     * @param size saraso dydis.
     * @param skip praleistas saraso elementu kiekis.
     * @return Work objektu sarasas.
     */
    @Override
    @GET
    @Path("/list")
    public List<Work> workList(@QueryParam("size") int size,@QueryParam("skip") int skip) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        DaoImp dao = new DaoImp(em);
        List<Work> workList = dao.getPage(Work.class, size, skip);
        return workList;
    }
}
