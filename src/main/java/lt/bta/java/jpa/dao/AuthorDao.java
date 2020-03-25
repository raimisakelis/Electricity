package lt.bta.java.jpa.dao;

import lt.bta.java.entities.Author;

import javax.persistence.EntityManager;

public class AuthorDao extends DaoImp<Author> {


    public AuthorDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void update(Object pk, Author author) {
        executeInsideTransaction(em -> {
            Author authorOrg = em.find(Author.class, pk);
            authorOrg.setName(author.getName());
            authorOrg.setSurname(author.getSurname());
            em.persist(authorOrg);
        });
    }



}
