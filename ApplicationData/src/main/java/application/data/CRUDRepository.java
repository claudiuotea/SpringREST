package application.data;

import model.Entity;

public interface CRUDRepository<ID,E extends Entity<ID>> {
    int size();

    void save(E entity);

    void update(ID id, E entity);

    void delete(ID id) throws Exception;

    E findOne(ID id);

    E[] findAll();
}
