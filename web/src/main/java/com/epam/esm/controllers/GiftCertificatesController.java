package com.epam.esm.controllers;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.hateoas.GiftCertificateHateoas;
import com.epam.esm.request.GiftCertificateCreateRequest;
import com.epam.esm.request.GiftCertificatePriceUpdateRequest;
import com.epam.esm.request.GiftCertificateUpdateRequest;
import com.epam.esm.service.GiftCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Controller responsible for all operations with gift certificates.
 */
@RestController
@RequestMapping(path = "/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GiftCertificatesController {

    private final GiftCertificateService giftCertificateService;
    private final GiftCertificateHateoas giftCertificateHateoas;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto giftCertificateById(@PathVariable Long id) {
        return giftCertificateHateoas.toModel(giftCertificateService.getById(id));
    }

    @PostMapping("/fillData")
    @ResponseStatus(HttpStatus.CREATED)
    public void createGiftCertificates(){
        giftCertificateService.fillData();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GiftCertificateDto> createGiftCertificate(
            @Valid @RequestBody GiftCertificateCreateRequest request) throws DuplicateEntityException {

        GiftCertificateDto giftCertificateDto = giftCertificateHateoas.toModel(giftCertificateService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION,
                        giftCertificateDto.getLink("self").orElseThrow().toUri().toString())
                .body(giftCertificateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GiftCertificateDto> deleteGiftCertificate(@PathVariable Long id) {
        giftCertificateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GiftCertificateDto> updateGiftCertificate(
            @PathVariable Long id,
            @Valid @RequestBody GiftCertificateUpdateRequest request) {
        GiftCertificateDto giftCertificateDTO = giftCertificateHateoas.toModel(
                giftCertificateService.update(id, request));
        return ResponseEntity.ok(giftCertificateDTO);
    }

    @PatchMapping("/price/{id}")
    public ResponseEntity<GiftCertificateDto> updateGiftCertificatePrice(
            @PathVariable Long id,
            @Valid @RequestBody GiftCertificatePriceUpdateRequest request) {
        GiftCertificateDto giftCertificateDTO = giftCertificateHateoas.toModel(
                giftCertificateService.update(id, request));
        return ResponseEntity.ok(giftCertificateDTO);
    }

    @GetMapping("/pageableWithFilters")
    public CollectionModel<GiftCertificateDto> allGiftCertificates(
            @RequestParam MultiValueMap<String, String> allRequestParams,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "5", required = false) int size) {

        List<GiftCertificate> giftCertificates = giftCertificateService.doFilter(allRequestParams, page, size);
        return giftCertificateHateoas.toCollectionModel(giftCertificates);
    }

}
