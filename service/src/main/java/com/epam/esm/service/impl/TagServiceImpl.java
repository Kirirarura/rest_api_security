package com.epam.esm.service.impl;

import com.epam.esm.dao.repository.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.request.TagCreateRequest;
import com.epam.esm.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

import static com.epam.esm.exception.ExceptionMessageKey.TAG_NOT_FOUND;

@Service
@Transactional
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    private static Supplier<NoSuchEntityException> getNoSuchGiftCertificateException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }


    @Override
    public Tag findById(Long id) {
        return tagDao.findById(id).orElseThrow(getNoSuchGiftCertificateException(id));
    }

    @Override
    public Tag create(TagCreateRequest request) {
        String tagName = request.getName();
        boolean isTagExist = tagDao.findByName(tagName).isPresent();
        if (isTagExist) {
            throw new DuplicateEntityException(ExceptionMessageKey.TAG_EXIST);
        }
        Tag tag = new Tag(null, request.getName());
        return tagDao.save(tag);
    }

    @Override
    public void deleteById(Long id) {
        Tag tag = tagDao.findById(id).orElseThrow(getNoSuchGiftCertificateException(id));
        tagDao.deleteById(tag.getId());
    }

    @Override
    public Tag getMostPopularTagOfUserWithHighestCostOfAllOrders(Long id) {
//        Optional<Tag> optionalTag = tagDao.findMostPopularTagOfUserWithHighestCostOfAllOrders(id);
//        if (!optionalTag.isPresent()) {
//            throw new NoSuchEntityException(TAG_NOT_FOUND);
//        }
//        return optionalTag.get();
//    }
        return null;
    }
}
