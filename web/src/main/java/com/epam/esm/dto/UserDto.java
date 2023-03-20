package com.epam.esm.dto;

import com.epam.esm.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Relation(collectionRelation = "users", itemRelation = "user")
public class UserDto extends RepresentationModel<UserDto> {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;
}
