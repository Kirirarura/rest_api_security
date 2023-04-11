package com.epam.esm.entity;

import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity that represents user.
 */
@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
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

    //Builder Class
    public static class UserBuilder{
        private User newUser;
        public UserBuilder() {
            newUser = new User();
        }

        public UserBuilder setId(Long id){
            newUser.setId(id);
            return this;
        }

        public UserBuilder setUsername(String username){
            newUser.setUsername(username);
            return this;
        }

        public UserBuilder setFirstname(String firstName){
            newUser.setFirstName(firstName);
            return this;
        }

        public UserBuilder setLastname(String lastName){
            newUser.setLastName(lastName);
            return this;
        }
        public UserBuilder setEmail(String email){
            newUser.setEmail(email);
            return this;
        }
        public UserBuilder setPassword(String password){
            newUser.setPassword(password);
            return this;
        }
        public UserBuilder setRoles(Role... roles){
            newUser.setRoles(List.of(roles));
            return this;
        }
        public UserBuilder setCreated(LocalDateTime dateTime){
            newUser.setCreated(dateTime);
            return this;
        }
        public UserBuilder setUpdated(LocalDateTime dateTime){
            newUser.setUpdated(dateTime);
            return this;
        }
        public UserBuilder setStatus(Status status){
            newUser.setStatus(status);
            return this;
        }
        public User build(){
            return newUser;
        }
    }
}
