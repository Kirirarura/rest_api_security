package com.epam.esm.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity that represents Tag.
 */
@Entity
@Table(name = "tags")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tag implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

}
