package the.best.dao;

import java.util.List;

public interface EntityDao<T, E> {

    T getById(E id);

    List<T> getAll();

    boolean create(T entity);

    boolean update(T entity);

    boolean remove(T entity);
}
