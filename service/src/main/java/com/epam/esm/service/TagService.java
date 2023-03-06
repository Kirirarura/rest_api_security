package com.epam.esm.service;


import com.epam.esm.entity.Tag;
import com.epam.esm.request.TagCreateRequest;

public interface TagService{
    Tag create(TagCreateRequest request);
    Tag getById(Long id);
    void deleteById(Long id);
    Tag getMostPopularTagOfUserWithHighestCostOfAllOrders(Long id);
}
