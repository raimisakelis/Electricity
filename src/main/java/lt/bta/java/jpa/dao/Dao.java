package lt.bta.java.jpa.dao;

import java.util.List;

public interface Dao<T> {

    T get(Class<T> entityClass, Object pk);

    T get(String graphName, Class<T> entityClass, Object pk);

    void save(T t);

    void update(Object pk, T t);

    void delete(Class<T> entityClass, Object pk);

    List<T> getPage(Class<T> entityClass, int pageSize, int skip);

}

