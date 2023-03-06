package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.config.TestDatabaseConfig;
import com.epam.esm.entity.Tag;
import org.hibernate.collection.internal.PersistentBag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestDatabaseConfig.class)
@ActiveProfiles("test")
@Transactional
class TagDaoImplTest {

    @Autowired
    TagDao tagDao;

    @Test
    void findById() {
        Tag expected = new Tag(1L, "tag1");
        Optional<Tag> actual = tagDao.findById(1L);
        Assertions.assertEquals(expected, actual.get());
    }

    @Test
    void removeById() {
        tagDao.removeById(2L);
        Tag actual = tagDao.findById(2L).orElse(null);
        Assertions.assertEquals(null, actual);

    }

    @Test
    void insert() {
        Tag tagToAdd = new Tag(null, "Tag to add");
        tagDao.insert(tagToAdd);

        Optional<Tag> actual = tagDao.findByName(tagToAdd.getName());
        Assertions.assertEquals(tagToAdd, actual.get());

    }

    @Test
    void findMostPopularTagOfUserWithHighestCostOfAllOrders() {
        List<Tag> expected = Arrays.asList(new Tag(2L, "tag2"));
        Optional<Tag> actual = tagDao.findMostPopularTagOfUserWithHighestCostOfAllOrders(2L);
        Assertions.assertEquals(expected, actual.get());
    }
}