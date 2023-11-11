package com.tom.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.demo.dto.CreateUserDto;
import com.tom.demo.model.User;
import com.tom.demo.service.UserService;
import com.tom.demo.util.UserTestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@WebMvcTest(ControlTowerController.class)
public class UserControllerTest extends BaseControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final User User = new User();

    static List<User> list = new ArrayList<>();

    private final CreateUserDto createUserDTO = new CreateUserDto();

    @MockBean
    private UserService UserService;

    @BeforeEach
    void setup() {
        // Setup
        createUserDTO.setUserName("user1");
        createUserDTO.setEmail("email1");
        createUserDTO.setPassword("passwd1");
    }

    @Test
    @SneakyThrows
    void testSave() {
        // Setup
        when(UserService.save(createUserDTO)).thenReturn(UserTestUtil.createUserList().get(0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(BASE_URL + "/save")
                        .content(objectMapper.writeValueAsString(createUserDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        User user = objectMapper.readValue(response.getContentAsByteArray(), User.class);
        assertThat(user.getUserName()).isEqualTo("user-1");
    }

    @Test
    @SneakyThrows
    void testList() {
        // Setup
        when(UserService.list()).thenReturn(UserTestUtil.createUserList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(BASE_URL + "/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<User> list = objectMapper.readValue(response.getContentAsByteArray(), List.class);
        assertThat(list.size()).isEqualTo(5);
    }


}

