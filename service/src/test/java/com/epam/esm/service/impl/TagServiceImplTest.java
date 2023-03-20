package com.epam.esm.service.impl;

import com.epam.esm.dao.repository.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.request.TagCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer1;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    TagDao tagDao;

    @InjectMocks
    TagServiceImpl tagService;

    private Tag getTag() {
        return new Tag(1L, "tag 1");
    }

    private static Answer1<Tag, Tag> getFakeSave(Long id) {
        return tag -> {
            tag.setId(id);
            return tag;
        };
    }

    @Test
    void getById() {
        Tag preparedTag = getTag();

        when(tagDao.findById(preparedTag.getId())).thenReturn(Optional.of(preparedTag));

        assertNotNull(tagService.findById(1L));
    }

    @Test
    void createSuccess() {
        Tag preparedTag = getTag();
        TagCreateRequest tagCreateRequest = new TagCreateRequest(preparedTag.getName());

        when(tagDao.save(argThat(tag -> tag.getId() == null))).thenAnswer(answer(getFakeSave(preparedTag.getId())));
        Tag actual = tagService.create(tagCreateRequest);

        assertEquals(actual, preparedTag);
    }

    @Test
    void createThrowException() {
        Tag preparedTag = getTag();
        TagCreateRequest tagCreateRequest = new TagCreateRequest(preparedTag.getName());

        when(tagDao.save(argThat(tag -> tag.getId() == null))).thenThrow(DuplicateEntityException.class);

        assertThrows(DuplicateEntityException.class, ()-> tagService.create(tagCreateRequest));
    }



    @Test
    void deleteById() {
        Tag tag = getTag();
        when(tagDao.findById(tag.getId())).thenReturn(Optional.of(tag));

        tagService.deleteById(tag.getId());
        verify(tagDao, times(1)).findById(tag.getId());
    }

}