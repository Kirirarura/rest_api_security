package com.epam.esm.controllers.additional;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.hateoas.GiftCertificateHateoas;
import com.epam.esm.hateoas.TagHateoas;
import com.epam.esm.request.GiftCertificateCreateRequest;
import com.epam.esm.request.GiftCertificatePriceUpdateRequest;
import com.epam.esm.request.GiftCertificateUpdateRequest;
import com.epam.esm.request.TagCreateRequest;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminController {
    private final GiftCertificateService giftCertificateService;
    private final GiftCertificateHateoas giftCertificateHateoas;
    private final TagService tagService;
    private final TagHateoas tagHateoas;

    @PostMapping("/certificate")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GiftCertificateDto> createGiftCertificate(
            @Valid @RequestBody GiftCertificateCreateRequest request) throws DuplicateEntityException {

        GiftCertificateDto giftCertificateDto = giftCertificateHateoas.toModel(giftCertificateService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION,
                        giftCertificateDto.getLink("self").orElseThrow().toUri().toString())
                .body(giftCertificateDto);
    }

    @DeleteMapping("/certificate/{id}")
    public ResponseEntity<GiftCertificateDto> deleteGiftCertificate(@PathVariable Long id) {
        giftCertificateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/certificate/{id}")
    public ResponseEntity<GiftCertificateDto> updateGiftCertificate(
            @PathVariable Long id,
            @Valid @RequestBody GiftCertificateUpdateRequest request) {
        GiftCertificateDto giftCertificateDTO = giftCertificateHateoas.toModel(
                giftCertificateService.update(id, request));
        return ResponseEntity.ok(giftCertificateDTO);
    }

    @PatchMapping("/certificate/price/{id}")
    public ResponseEntity<GiftCertificateDto> updateGiftCertificatePrice(
            @PathVariable Long id,
            @Valid @RequestBody GiftCertificatePriceUpdateRequest request) {
        GiftCertificateDto giftCertificateDTO = giftCertificateHateoas.toModel(
                giftCertificateService.update(id, request));
        return ResponseEntity.ok(giftCertificateDTO);
    }

    @PostMapping("/tag")
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagCreateRequest request) {
        TagDto tagDTO = tagHateoas.toModel(tagService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, tagDTO.getLink("self").orElseThrow().toUri().toString())
                .body(tagDTO);
    }

    @DeleteMapping("/tag/{id}")
    public ResponseEntity<TagDto> deleteTag(@PathVariable Long id) {
        tagService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
