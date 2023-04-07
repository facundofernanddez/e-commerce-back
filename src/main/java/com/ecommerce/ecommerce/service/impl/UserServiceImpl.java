package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.commons.GenericServiceImpl;
import com.ecommerce.ecommerce.model.user.User;
import com.ecommerce.ecommerce.model.user.dao.api.UserDaoAPI;
import com.ecommerce.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl extends GenericServiceImpl<User, String> implements UserDetailsService {

    private final UserDaoAPI userDaoAPI;
    private final UserRepository userRepository;

    @Override
    public CrudRepository<User, String> getDao() {
        return userDaoAPI;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
