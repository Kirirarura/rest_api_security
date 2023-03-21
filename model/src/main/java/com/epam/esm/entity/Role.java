package com.epam.esm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = false)
@Audited
public class Role extends BaseEntity{

    @Column(name = "name")
    private String name;
}
