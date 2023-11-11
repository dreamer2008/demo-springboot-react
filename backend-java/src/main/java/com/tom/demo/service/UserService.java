package com.tom.demo.service;

import com.tom.demo.dao.UserDao;
import com.tom.demo.dto.CreateUserDto;
import com.tom.demo.model.User;
import com.tom.demo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User save(CreateUserDto createUserDto) {
        User user = Mapper.INSTANCE.toModel(createUserDto);
        Date now = new Date();
        user.setCreateTime(now).setUpdateTime(now);
        return userDao.save(user);
    }

    public List<User> list() {
        List<User> list = userDao.findAll();
        return list;
    }

    public List<User> listByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}
