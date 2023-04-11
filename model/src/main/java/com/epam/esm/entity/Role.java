package com.epam.esm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;
}
