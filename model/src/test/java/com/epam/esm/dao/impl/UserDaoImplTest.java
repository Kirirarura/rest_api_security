package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.config.TestDatabaseConfig;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestDatabaseConfig.class)
@ActiveProfiles("test")
@Transactional
class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Test
    void getById(){
        User expected = new User(1L, "Marry", "Rose");
        Optional<User> actual = userDao.findById(expected.getId());
        Assertions.assertEquals(actual.get(), expected);
    }

    @Test
    void getAll(){
        List<User> expected = Arrays.asList(
                new User(1L, "Marry", "Rose"),
                new User(2L,"Snoop", "Dog")
        );
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<User> actual = userDao.findAll(pageRequest);

        Assertions.assertEquals(expected, actual);
    }
}
