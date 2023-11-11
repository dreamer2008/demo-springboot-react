package com.tom.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserDto {

    @Schema(name="userName", example = "")
    private String userName;

    @Schema(name="email", example = "tom@abc.com")
    private String email;

    @Schema(name="password")
    private String password;
}
