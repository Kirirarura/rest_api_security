package com.epam.esm.controllers;

import com.epam.esm.dto.TagDto;
import com.epam.esm.hateoas.TagHateoas;
import com.epam.esm.request.TagCreateRequest;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller responsible for all operations with tags.
 */
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagService tagService;
    private final TagHateoas tagHateoas;

    @GetMapping("/{id}")
    public TagDto getById(@PathVariable Long id) {
        return tagHateoas.toModel(tagService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagCreateRequest request) {
        TagDto tagDTO = tagHateoas.toModel(tagService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, tagDTO.getLink("self").orElseThrow().toUri().toString())
                .body(tagDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TagDto> deleteTag(@PathVariable Long id) {
        tagService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/popular/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto mostPopularTagOfUserWithHighestCostOfAllOrders(@PathVariable Long id) {
        return tagHateoas.toModel(tagService.getMostPopularTagOfUserWithHighestCostOfAllOrders(id));
    }
}
