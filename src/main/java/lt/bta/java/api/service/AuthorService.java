package lt.bta.java.api.service;

import lt.bta.java.entities.Author;

import java.util.List;


public interface AuthorService {


    Author get(int id);

    //Author getAuthor(String name, String surname);

    void save(Author author);

    void delete(int id);

    void update(int id, Author author);

    List<Author> authorList(int size, int skip);


}
