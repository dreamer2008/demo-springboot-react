package com.tom.demo.service;

import com.tom.demo.dao.UserDao;
import com.tom.demo.dto.CreateUserDto;
import com.tom.demo.model.User;
import com.tom.demo.util.UserTestUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @ParameterizedTest
    @Order(1)
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void testSave(String userName, String email, String password) {
        User user = new User().setUserName(userName).setEmail(email).setPassword(password);
        CreateUserDto createUserDto = new CreateUserDto().setUserName(userName).setEmail(email).setPassword(password);
        when(userDao.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User entitySaved = userService.save(createUserDto);
        String userNameSaved = entitySaved.getUserName();
        assertThat("Tom".equals(userNameSaved) || "Mary".equals(userNameSaved));
    }

    @Test
    void testList() {
        when(userDao.findAll()).thenReturn(UserTestUtil.createUserList());
        List<User> list = userService.list();
        assertThat(list.size()).isEqualTo(5);
        assertThat(list.stream().filter(it -> it.getUserName().startsWith("user")).count()).isEqualTo(5);
    }

    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = {"user-1", "user-2"})
    void testListByUserName(String userName) {
        List<User> userList = UserTestUtil.createUserList().stream().filter(it -> userName.equals(it.getUserName())).collect(Collectors.toList());
        when(userDao.findByUserName(userName)).thenReturn(userList);
        List<User> list = userService.listByUserName(userName);
        assertThat(list.size()).isEqualTo(userList.size());
        assertThat(list.stream().filter(it -> userName.equals(it.getUserName())).count()).isEqualTo(1L);
    }


}