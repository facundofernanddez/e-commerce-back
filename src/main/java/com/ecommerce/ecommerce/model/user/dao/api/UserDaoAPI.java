package com.ecommerce.ecommerce.model.user.dao.api;

import com.ecommerce.ecommerce.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDaoAPI extends CrudRepository<User, Long> {
}
