package com.epam.esm;

import com.epam.esm.dao.repository.UserDao;
import com.epam.esm.entity.Status;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserDaoImplTest {

    @Autowired
    UserDao repository;

    @Test
    void getById() {
        User actual = repository.findById(1L).orElse(null);
        Assertions.assertNotNull(actual);
    }

    @Test
    void getAll() {
        List<User> actual = repository.findAll();
        assertThat(actual).hasSize(3);
    }

    @Test
    void register() {
        User expected = new User.UserBuilder()
                .setUsername("user5")
                .setEmail("emailOfUser5gmail.com")
                .setPassword("$2a$04$pdPdz98.SMXrDE2/0NZD7OWwOzzb/d0w75KqJRTxNBX5El8oQdhP6")
                .setFirstname("first_name")
                .setLastname("last_name")
                .setStatus(Status.ACTIVE)
                .setCreated(LocalDateTime.parse("2020-08-29T06:12:15.156"))
                .setUpdated(LocalDateTime.parse("2020-08-29T06:12:15.156"))
                .build();
        repository.save(expected);

        User actual = repository.findByUsername("user5").orElse(null);

        Assertions.assertEquals(expected, actual);
    }
}
