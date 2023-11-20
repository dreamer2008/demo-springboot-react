package com.tom.demo.controller;

import com.tom.demo.dto.UserDto;
import com.tom.demo.model.User;
import com.tom.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API")
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "create a user")
    @PostMapping
    public User save(@RequestBody UserDto userDto) {
        log.info("user creation param: " + userDto);
        User user = userService.save(userDto);
        return user;
    }

    @Operation(summary = "list all the user data")
    @GetMapping
    public List<User> list() {
        return userService.list();
    }

    @Operation(summary = "find a user by id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "update a user")
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        userService.updateUser(userDto);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "success";
    }

}
