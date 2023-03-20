package com.epam.esm.service;

import com.epam.esm.entity.User;
import com.epam.esm.request.UserRegistrationRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User register(UserRegistrationRequest request);

    Page<User> getAll(int page, int size);

    User findByUsername(String username);

    User findById(Long id);


}
