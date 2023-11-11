package com.tom.demo.controller;

import com.tom.demo.dto.CreateUserDto;
import com.tom.demo.model.User;
import com.tom.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
@Tag(name = "User API")
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "save")
    @PostMapping("/save")
    public User save(@RequestBody CreateUserDto createUserDto){
        log.info("user creation param: " + createUserDto);
        User user = userService.save(createUserDto);
        return user;
    }

    @Operation(summary = "list all the user data")
    @GetMapping("/list")
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(userService.list());
    }

}
