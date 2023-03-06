package com.epam.esm.entity;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity that represents user.
 */
@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
@Audited
public class User extends BaseEntity implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
