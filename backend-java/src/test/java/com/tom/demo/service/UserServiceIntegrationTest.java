package com.tom.demo.service;

import com.tom.demo.dto.UserDto;
import com.tom.demo.model.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceIntegrationTest {

    @Resource
    private UserService userService;

    @ParameterizedTest
    @Order(1)
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void testSave(String userName, String email, String password) {
        UserDto userDto = new UserDto().setUserName(userName).setEmail(email).setPassword(password);
        User user = userService.save(userDto);
        assertThat(user.getId()).isGreaterThan(0L);
    }

    @Test
    @Order(2)
    void testList() {
        List<User> list = userService.list();
        assertThat(list.size()).isGreaterThanOrEqualTo(1);
    }

    @ParameterizedTest
    @Order(3)
//    @ValueSource(strings = {"Tom","Mary"})
    @MethodSource("userNames")
    void testListByUserName(String userName) {
        User user = userService.getByUserName(userName);
        assertThat(user.getId()).isGreaterThanOrEqualTo(1);
    }

    private static String[] userNames() {
        return new String[] {"Tom","Mary"};
    }
}