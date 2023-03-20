package com.epam.esm.service.impl;

import com.epam.esm.dao.repository.GiftCertificateDao;
import com.epam.esm.dao.repository.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.*;
import com.epam.esm.request.GiftCertificateCreateRequest;
import com.epam.esm.request.GiftCertificatePriceUpdateRequest;
import com.epam.esm.request.GiftCertificateUpdateRequest;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.impl.util.PaginationHelper;
import com.epam.esm.validation.FilterParamsValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final TagDao tagDao;

    private static Supplier<NoSuchEntityException> getNoSuchGiftCertificateException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }

    @Override
    public GiftCertificate getById(Long id) {
        return giftCertificateDao.findById(id).orElseThrow(getNoSuchGiftCertificateException(id));
    }


    @Override
    @Transactional
    public GiftCertificate create(GiftCertificateCreateRequest request) {
        String giftCertificateName = request.getName();
        boolean isGiftCertificateExist = giftCertificateDao.findByName(giftCertificateName).isPresent();
        if (isGiftCertificateExist) {
            throw new DuplicateEntityException(ExceptionMessageKey.GIFT_CERTIFICATE_EXIST);
        }

        List<Tag> tags = checkRequestedTags(request.getTags());
        GiftCertificate giftCertificate = new GiftCertificate(null, request.getName(),
                request.getDescription(), request.getPrice(), request.getDuration(),
                String.valueOf(Timestamp.from(Instant.now())),
                String.valueOf(Timestamp.from(Instant.now())), tags);

        return giftCertificateDao.save(giftCertificate);
    }

    @Override
    @Transactional
    public void fillData() {
        for (int i = 1; i <= 10000; i++) {
            List<Tag> tags;
            if (i > 1000) {
                tags = Arrays.asList(new Tag(null, "Tag " + (i % 1000)));
            } else {
                tags = Arrays.asList(new Tag(null, "Tag " + i));
            }
            List<Tag> checkedTags = checkRequestedTags(tags);
            GiftCertificate giftCertificate = new GiftCertificate(
                    null, "Gift certificate " + i, "Description " + i,
                    BigDecimal.valueOf(Math.random()),
                    (int) Math.round(Math.random()),
                    String.valueOf(Timestamp.from(Instant.now())),
                    String.valueOf(Timestamp.from(Instant.now())), checkedTags);
            giftCertificateDao.save(giftCertificate);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        giftCertificateDao.findById(id).orElseThrow(getNoSuchGiftCertificateException(id));
        giftCertificateDao.deleteById(id);
    }

    @Override
    @Transactional
    public GiftCertificate update(Long id, GiftCertificateUpdateRequest updateRequest) throws NoSuchEntityException {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id)
                .orElseThrow(getNoSuchGiftCertificateException(id));

        handleUpdateRequest(giftCertificate, updateRequest);
        giftCertificate.setLastUpdateDate(String.valueOf(Timestamp.from(Instant.now())));

        return giftCertificateDao.save(giftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificate update(Long id, GiftCertificatePriceUpdateRequest updateRequest) {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id)
                .orElseThrow(getNoSuchGiftCertificateException(id));

        giftCertificate.setPrice(updateRequest.getPrice());
        giftCertificate.setLastUpdateDate(String.valueOf(Timestamp.from(Instant.now())));

        return giftCertificateDao.save(giftCertificate);
    }

    @Override
    public List<GiftCertificate> doFilter(MultiValueMap<String, String> requestParams, int page, int size) {
        ExceptionResult exceptionResult = new ExceptionResult();

        FilterParamsValidator.validateFilterParams(requestParams, exceptionResult);

        if (!exceptionResult.getExceptionMessages().isEmpty()) {
            throw new IncorrectParameterException(exceptionResult);
        }

        Pageable pageRequest = PaginationHelper.createPageRequest(page, size);
        return giftCertificateDao.findWithFilters(requestParams, pageRequest);
    }


    private List<Tag> checkRequestedTags(List<Tag> requestedTags) {
        List<Tag> tagsToPersist = new ArrayList<>();
        List<Tag> requestedTagsWithoutDuplicates = removeDuplicateTags(requestedTags);

        for (Tag tag : requestedTagsWithoutDuplicates) {
            Optional<Tag> tagOptional = tagDao.findByName(tag.getName());
            if (tagOptional.isPresent()) {
                tagsToPersist.add(tagOptional.get());
            } else {
                tagsToPersist.add(tag);
            }
        }
        return tagsToPersist;
    }

    private List<Tag> removeDuplicateTags(List<Tag> tags) {
        return tags.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private void handleUpdateRequest(GiftCertificate giftCertificate, GiftCertificateUpdateRequest updateRequest) {

        if (updateRequest.isNamePresent()) {
            giftCertificate.setName(updateRequest.getName());
        }

        if (updateRequest.isDescriptionPresent()) {
            giftCertificate.setDescription(updateRequest.getDescription());
        }

        if (updateRequest.isDurationPresent()) {
            giftCertificate.setDuration(updateRequest.getDuration());
        }

        if (updateRequest.isPricePresent()) {
            giftCertificate.setPrice(updateRequest.getPrice());
        }

        if (updateRequest.isTagsPresent()) {
            List<Tag> tagsToUpdate = checkRequestedTags(updateRequest.getTags());
            giftCertificate.getTags().addAll(tagsToUpdate);
            giftCertificate.setTags(removeDuplicateTags(giftCertificate.getTags()));
        }
    }
}
