package com.epam.esm.service;

import com.epam.esm.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User register(User user);

    Page<User> getAll(int page, int size);

    User findByUsername(String username);

    User findById(Long id);


}
