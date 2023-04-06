package com.ecommerce.ecommerce.commons;

import java.io.Serializable;
import java.util.List;

public interface GenericServiceApi<User, ID extends Serializable>{

    T save(T Entity);

    void delete(ID id);

    T get(ID id);

    List<T> getAll();
}
