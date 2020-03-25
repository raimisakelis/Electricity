package lt.bta.java.api.impl;

import lt.bta.java.api.service.AuthorService;
import lt.bta.java.entities.Author;
import lt.bta.java.entities.AuthorPK;
import lt.bta.java.jpa.PersistenceUtil;
import lt.bta.java.jpa.dao.AuthorDao;
import lt.bta.java.jpa.dao.DaoImp;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * AuthorServiceImpl klase skirta author objekto duomenimu
 * mainams tarp aplikacijos ir duomenu bazes.
 *
 */
@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorServiceImpl implements AuthorService {

    /**
     * Metodas, skirtas gauti Author objekta is duomenu bazes.
     * @param id unikalus Author objekto identifikatorius.
     * @return Author tipo objekta.
     */
    @Override
    @GET
    @Path("/{id}")
    public Author get (@PathParam("id") int id) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        AuthorDao authorDao = new AuthorDao(em);
        Author a = authorDao.get(Author.class, id);
        a.getWorks().size();//it's needed to initialize session
        em.close();
        return a;
    }


//    @Override
//    @POST
//    @Path("/auth")
//    public Author getAuthor(@QueryParam("name") String name, @QueryParam("surname") String surname) {
//        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
//        AuthorDao authorDao = new AuthorDao(em);
//        Author a = authorDao.get(Author.class, new AuthorPK(name, surname));
////        a.getWorks().size();//it's needed to initialize session
////        em.close();
//        return a;
//    }



    /**
     * Metodas, skirtas nauja Author objekta irasyti
     * i duomenu baze.
     * @param author Author objektas.
     */
    @Override
    @POST
    @Path("/save")
    public void save(Author author) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        DaoImp dao = new DaoImp(em);
        dao.save(author);
    }

    /**
     * Metodas, skirtas pasalinti Author objekta
     * is duomenu bazes.
     * @param id unikalus Author objekto identifikatorius.
     */
    @Override
    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") int id) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        DaoImp dao = new DaoImp(em);
        dao.delete(Author.class, id);
    }

    /**
     * Metodas, skirtas atnaujinti Author objekto laukus
     * duomenu bazeje.
     * @param id unikalus Author objekto identifikatorius.
     * @param author modifikuotas Author objektas.
     */
    @Override
    @PUT
    @Path("/update/{id}")
    public void update(@PathParam("id") int id, Author author) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        AuthorDao authorDao = new AuthorDao(em);
        authorDao.update(id,author);
    }

    /**
     * Metodas, skirtas gauti Author objektu sarasa is
     * duomenu bazes.
     * @param size saraso dydis.
     * @param skip praleistas saraso elementu kiekis.
     * @return Author objektu sarasas.
     */
    @Override
    @GET
    @Path("/list")
    public List<Author> authorList(@QueryParam("size") int size, @QueryParam("skip") int skip) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        DaoImp dao = new DaoImp(em);
        List<Author> authorList = dao.getPage(Author.class, size, skip);
        return authorList;
    }


//    @Override
//    @GET
//    @Path("/list/{size}/{skip}")
//    public List<Author> authorList(@PathParam("size") int size, @PathParam("skip") int skip) {
//        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
//        DaoImp dao = new DaoImp(em);
//        List<Author> authorList = dao.getPage(Author.class, size, skip);
//        return authorList;
//    }





}
