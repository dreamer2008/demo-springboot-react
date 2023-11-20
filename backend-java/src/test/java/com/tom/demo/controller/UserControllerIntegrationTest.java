package com.tom.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.demo.dto.UserDto;
import com.tom.demo.model.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class UserControllerIntegrationTest extends BaseControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private UserDto userDTO = new UserDto();

    @BeforeEach
    void setup() {
        // Setup
        userDTO.setUserName("user1");
        userDTO.setEmail("email1");
        userDTO.setPassword("passwd1");
    }

    @Test
    @Order(1)
    @SneakyThrows
    void testSave() {
        final MockHttpServletResponse response = mockMvc.perform(post(BASE_URL + "/save")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        User actualResult = objectMapper.readValue(response.getContentAsByteArray(), User.class);
        assertThat(actualResult.getUserName()).isEqualTo("user1");
    }

    @Test
    @Order(2)
    @SneakyThrows
    void testList() {
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(BASE_URL + "/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List actualResult = objectMapper.readValue(response.getContentAsByteArray(), List.class);
        assertThat(actualResult.size()).isGreaterThanOrEqualTo(1);
    }


}

