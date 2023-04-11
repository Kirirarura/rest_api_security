package com.epam.esm.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Entity that represents gift certificate.
 */
@Entity
@Table(name = "gift_certificates")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GiftCertificate implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "duration")
    private int duration;

    @Column(name = "created")
    private String createDate;

    @Column(name = "updated")
    private String lastUpdateDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "gift_certificate_has_tag",
            joinColumns = {
                    @JoinColumn(
                            name = "gift_certificate_id",
                            referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "tag_id",
                            referencedColumnName = "id")
            }
    )
    private List<Tag> tags;
}
