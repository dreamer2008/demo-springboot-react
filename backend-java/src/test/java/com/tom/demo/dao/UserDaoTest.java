package com.tom.demo.dao;

import com.tom.demo.model.User;
import com.tom.demo.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class UserDaoTest {

    @Resource
    private TestEntityManager testEntityManager;

    @Resource
    private UserDao userDao;

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void testSave(String userName, String email, String password) {
        Date now = new Date();
        User user = userDao.save(new User().setUserName(userName).setEmail(email).setPassword(password).setCreateTime(now).setUpdateTime(now));
        assertThat(user.getId()).isGreaterThan(0L);
    }

    @Test
    void testFindAll() {
        List<User> userList = UserTestUtil.createUserList();
        userList.forEach(it -> testEntityManager.persist(it));
        List<User> list = userDao.findAll();
        assertThat(list.size()).isGreaterThanOrEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(strings = "user-1")
    void findByUserName(String userName) {
        List<User> userList = UserTestUtil.createUserList();
        userList.forEach(it -> testEntityManager.persist(it));
        List<User> users = userDao.findByUserName(userName);
        assertThat(users.size()).isEqualTo(1);
    }
}