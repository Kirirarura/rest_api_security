package com.epam.esm.controllers;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.hateoas.UserHateoas;
import com.epam.esm.request.UserRegistrationRequest;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for all operations with users.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final UserHateoas userHateoas;

    @GetMapping("/{id}")
    public UserDto userById(@PathVariable Long id) {
        return userHateoas.toModel(userService.findById(id));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserRegistrationRequest request){
        return userHateoas.toModel(userService.register(request));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<User> allUsers(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                               @RequestParam(value = "size", defaultValue = "5", required = false) int size) {
        return userService.getAll(page, size);
    }

}
