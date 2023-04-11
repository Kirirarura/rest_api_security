package com.epam.esm;

import com.epam.esm.dao.repository.TagDao;

import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
class TagDaoImplTest {

    @Autowired
    TagDao repository;

    @Test
    void findById() {
        Tag tag = repository.findById(1L).orElse(null);
        Assertions.assertNotNull(tag);
    }

    @Test
    void findAll(){
        List<Tag> tags = repository.findAll();
        assertThat(tags).hasSize(3);
    }

    @Test
    void removeById() {
        repository.deleteById(2L);
        Tag actual = repository.findById(2L).orElse(null);
        Assertions.assertNull(actual);
    }

    @Test
    void insert() {
        Tag tagToAdd = new Tag(null, "Tag to add");
        repository.save(tagToAdd);

        Tag actual = repository.findByName(tagToAdd.getName()).orElse(null);
        Assertions.assertNotNull(actual);
    }
}