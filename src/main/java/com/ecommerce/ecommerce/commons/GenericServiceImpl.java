package com.ecommerce.ecommerce.commons;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID> {
    @Override
    public T save(T Entity) {
        return getDao().save(Entity);
    }

    @Override
    public void delete(ID id) {
        getDao().deleteById(id);
    }

    @Override
    public T get(ID id) {
        Optional<T> obj = getDao().findById(id);
        return obj.orElse(null);
    }

    @Override
    public List<T> getAll() {
        List<T> returnList = new ArrayList<>();
        getDao().findAll().forEach(returnList::add);
        return returnList;
    }

    public abstract CrudRepository<T, ID> getDao();
}
