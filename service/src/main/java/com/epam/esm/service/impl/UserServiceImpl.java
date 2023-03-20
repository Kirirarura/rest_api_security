package com.epam.esm.service.impl;

import com.epam.esm.dao.repository.RoleDao;
import com.epam.esm.dao.repository.UserDao;
import com.epam.esm.entity.Role;
import com.epam.esm.entity.Status;
import com.epam.esm.entity.User;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.request.UserRegistrationRequest;
import com.epam.esm.service.UserService;
import com.epam.esm.service.impl.util.PaginationHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.exception.ExceptionMessageKey.NO_ENTITY;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(UserRegistrationRequest request) {

        Role roleUser = roleDao.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userDao.save(user);

        log.info("IN register - user: {}, successfully registered.", registeredUser);
        return registeredUser;
    }

    @Override
    public Page<User> getAll(int page, int size) {
        Page<User> result = userDao.findAll(PaginationHelper.createPageRequest(page, size));

        log.info("IN getAll - {} users found", result.getTotalElements());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> result = userDao.findByUsername(username);
        if (!result.isPresent()){
            throw new NoSuchEntityException(NO_ENTITY);
        }

        User user = result.get();

        log.info("IN findByUsername - user: {}, was found by username: {}", user, user.getUsername() );
        return user;
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = userDao.findById(id);
        if (!result.isPresent()){
            throw new NoSuchEntityException(NO_ENTITY);
        }

        User user = result.get();

        log.info("IN findById - user: {}, was found by id: {}", user, user.getId() );
        return user;
    }
}
