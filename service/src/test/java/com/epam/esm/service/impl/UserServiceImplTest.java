package com.epam.esm.service.impl;

import com.epam.esm.dao.repository.RoleDao;
import com.epam.esm.dao.repository.UserDao;
import com.epam.esm.entity.Role;
import com.epam.esm.entity.Status;
import com.epam.esm.entity.User;
import com.epam.esm.request.UserRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserDao userDao;

    @Mock
    RoleDao roleDao;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    private static final LocalDateTime DATE = LocalDateTime.of(2023, 3, 3, 13, 52, 9);

    private User getUser() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return new User.UserBuilder()
                .setId(1L)
                .setUsername("UserName")
                .setFirstname("FirstName")
                .setLastname("Lastname")
                .setEmail("example@gmail.com")
                .setPassword("password")
                .setCreated(DATE)
                .setUpdated(DATE)
                .setRoles(role)
                .setStatus(Status.ACTIVE)
                .build();
    }

    @Test
    void register() {
        User expected = getUser();
        UserRegistrationRequest request = new UserRegistrationRequest(
                "UserName", "example@gmail.com", "password",
                "FirstName", "Lastname");
        when(userDao.save(any())).thenReturn(expected);

        User actual = userService.register(request);

        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        User expected = getUser();
        when(userDao.findById(1L)).thenReturn(Optional.ofNullable(expected));

        User actual = userService.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    void findByUserName(){
        User expected = getUser();
        when(userDao.findByUsername(expected.getUsername())).thenReturn(Optional.of(expected));

        User actual = userService.findByUsername(expected.getUsername());
        assertEquals(expected, actual);
    }
}