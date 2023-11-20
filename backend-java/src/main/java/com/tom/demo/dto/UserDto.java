package com.tom.demo.dto;

import com.tom.demo.validate.UserAddGroup;
import com.tom.demo.validate.UserUpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UserDto {

    @NotNull(message = "user id could not be empty", groups = {UserAddGroup.class, UserUpdateGroup.class})
    private Long id;

    @Schema(name="userName", example = "")
    private String userName;

    @Schema(name="email", example = "tom@abc.com")
    private String email;

    @Schema(name="password")
    private String password;
}
