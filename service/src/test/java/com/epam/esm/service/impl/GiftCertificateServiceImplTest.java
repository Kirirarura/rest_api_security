package com.epam.esm.service.impl;

import com.epam.esm.dao.repository.GiftCertificateDao;
import com.epam.esm.dao.repository.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.IncorrectParameterException;
import com.epam.esm.request.GiftCertificateCreateRequest;
import com.epam.esm.request.GiftCertificatePriceUpdateRequest;
import com.epam.esm.request.GiftCertificateUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {
    @Mock
    TagDao tagDao;

    @Mock
    GiftCertificateDao giftCertificateDao;

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    private static final Tag TAG_2 = new Tag(2L, "tagName3");

    private static final String SORT_PARAMETER = "DESC";
    private static final int PAGE = 0;
    private static final int SIZE = 5;

    private GiftCertificate getGiftCertificate() {
        return new GiftCertificate(1L, "giftCertificate1", "description1", BigDecimal.valueOf(10.1),
                10, String.valueOf(Instant.now()), String.valueOf(Instant.now()), getTags());
    }

    private List<GiftCertificate> getGiftCertificates() {
        return List.of(
                new GiftCertificate(1L, "giftCertificate1", "description1", BigDecimal.valueOf(10.1),
                        10, String.valueOf(Instant.now()), String.valueOf(Instant.now()), getTags()),
                new GiftCertificate(2L, "giftCertificate2", "description2", BigDecimal.valueOf(20.1),
                        20, String.valueOf(Instant.now()),String.valueOf(Instant.now()), getTags()));
    }

    private List<Tag> getTags() {
        return List.of(new Tag(1L, "tag1"), new Tag(2L, "tag2"));
    }

    private GiftCertificateCreateRequest getCreateRequest(GiftCertificate giftCertificate) {
        return new GiftCertificateCreateRequest(giftCertificate.getName(), giftCertificate.getName(),
                giftCertificate.getPrice(), giftCertificate.getDuration(), getTags());
    }


    @Test
    void getById() {
        GiftCertificate giftCertificate = getGiftCertificate();
        when(giftCertificateDao.findById(giftCertificate.getId())).thenReturn(Optional.of(giftCertificate));
        GiftCertificate actual = giftCertificateService.getById(giftCertificate.getId());
        assertEquals(giftCertificate, actual);
    }

    @Test
    void createSuccess() {
        GiftCertificate giftCertificate = getGiftCertificate();
        GiftCertificateCreateRequest createRequest = getCreateRequest(giftCertificate);

        for (Tag tag : giftCertificate.getTags()) {
            when(tagDao.findByName(tag.getName())).thenReturn(Optional.of(tag));
        }
        when(giftCertificateDao.save(any())).thenReturn(giftCertificate);

        GiftCertificate actual = giftCertificateService.create(createRequest);

        assertEquals(giftCertificate, actual);
    }

    @Test
    void createThrowException() {
        GiftCertificate giftCertificate = getGiftCertificate();
        GiftCertificateCreateRequest createRequest = getCreateRequest(giftCertificate);

        for (Tag tag : giftCertificate.getTags()) {
            when(tagDao.findByName(tag.getName())).thenReturn(Optional.of(tag));
        }
        when(giftCertificateDao.save(any())).thenThrow(DuplicateEntityException.class);

        assertThrows(DuplicateEntityException.class, ()-> giftCertificateService.create(createRequest));
    }

    @Test
    void deleteById() {
        GiftCertificate giftCertificate = getGiftCertificate();
        when(giftCertificateDao.findById(giftCertificate.getId())).thenReturn(Optional.of(giftCertificate));

        giftCertificateService.deleteById(giftCertificate.getId());
        verify(giftCertificateDao, times(1)).findById(giftCertificate.getId());
    }

    @Test
    void updateWithGiftCertificateUpdateRequest() {
        GiftCertificate giftCertificate = getGiftCertificate();
        GiftCertificateUpdateRequest updateRequest = new GiftCertificateUpdateRequest("New name",
                null, null, 20, null);

        giftCertificate.setName(updateRequest.getName());
        giftCertificate.setDuration(updateRequest.getDuration());

        when(giftCertificateDao.findById(giftCertificate.getId()))
                .thenReturn(Optional.of(giftCertificate));

        when(giftCertificateDao.save(giftCertificate)).thenAnswer(returnsFirstArg());
        assertEquals(updateRequest.getName(),
                giftCertificateService.update(giftCertificate.getId(), updateRequest).getName());
    }

    @Test
    void updateWithGiftCertificatePriceUpdateRequest() {
        GiftCertificate giftCertificateOriginal = getGiftCertificate();
        GiftCertificatePriceUpdateRequest updateRequest = new GiftCertificatePriceUpdateRequest(BigDecimal.valueOf(10.1));

        GiftCertificate giftCertificateModified = getGiftCertificate();
        giftCertificateModified.setPrice(updateRequest.getPrice());

        when(giftCertificateDao.findById(giftCertificateOriginal.getId()))
                .thenReturn(Optional.of(giftCertificateOriginal));

        when(giftCertificateDao.save(notNull())).thenAnswer(returnsFirstArg());
        assertEquals(updateRequest.getPrice(),
                giftCertificateService.update(giftCertificateOriginal.getId(), updateRequest).getPrice());
    }

    @Test
    void doFilterWithSeveralRequestParams() {
        List<GiftCertificate> giftCertificates = getGiftCertificates();
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("name", TAG_2.getName());
        requestParams.add("tagName", TAG_2.getName());
        requestParams.add("sortByName", SORT_PARAMETER);
        Pageable pageRequest = PageRequest.of(PAGE, SIZE);
        when(giftCertificateDao.findWithFilters(requestParams, pageRequest))
                .thenReturn(giftCertificates);

        List<GiftCertificate> actual = giftCertificateService.doFilter(requestParams, PAGE, SIZE);

        assertEquals(giftCertificates, actual);
    }

    @Test
    void doFilterWithAllRequestParams() {
        List<GiftCertificate> giftCertificates = getGiftCertificates();
        List<GiftCertificate> expected = new ArrayList<>(Collections.singleton(giftCertificates.get(0)));
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("name", TAG_2.getName());
        requestParams.add("partOfName", giftCertificates.get(0).getName());
        requestParams.add("partOfDescription", giftCertificates.get(0).getDescription());
        requestParams.add("tagName", TAG_2.getName());
        requestParams.add("sortByName", SORT_PARAMETER);
        requestParams.add("sortByCreateDate", SORT_PARAMETER);

        Pageable pageRequest = PageRequest.of(PAGE, SIZE);
        when(giftCertificateDao.findWithFilters(requestParams, pageRequest))
                .thenReturn(expected);

        List<GiftCertificate> actual = giftCertificateService.doFilter(requestParams, PAGE, SIZE);

        assertEquals(expected, actual);
    }

    @Test
    void doFilterWithNoRequestParams() {
        List<GiftCertificate> giftCertificates = getGiftCertificates();
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();

        Pageable pageRequest = PageRequest.of(PAGE, SIZE);
        when(giftCertificateDao.findWithFilters(requestParams, pageRequest))
                .thenReturn(giftCertificates);

        List<GiftCertificate> actual = giftCertificateService.doFilter(requestParams, PAGE, SIZE);

        assertEquals(giftCertificates, actual);
    }

    @Test
    void doFilterThrowException() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();

        Pageable pageRequest = PageRequest.of(PAGE, SIZE);
        when(giftCertificateDao.findWithFilters(requestParams, pageRequest))
                .thenThrow(IncorrectParameterException.class);

        assertThrows(
                IncorrectParameterException.class, () -> giftCertificateService.doFilter(requestParams, PAGE, SIZE));
    }
}