package com.tom.demo.service;

import com.tom.demo.dao.UserDao;
import com.tom.demo.dto.UserDto;
import com.tom.demo.model.User;
import com.tom.demo.util.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(UserDto userDto) {
        User user = Mapper.INSTANCE.toModel(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Date now = new Date();
        user.setCreateTime(now).setUpdateTime(now);
        return userDao.save(user);
    }

    public List<User> list() {
        List<User> list = userDao.findAll();
        return list;
    }

    public User getByUserName(String userName) {
        List<User> list = userDao.findByUserName(userName);
        return list == null ? null : list.get(0);
    }

    public User getById(Long id) {
        Optional<User> optionalUser = userDao.findById(id);
        return optionalUser.get();
    }

    public User updateUser(UserDto userDto) {
        User user = Mapper.INSTANCE.toModel(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Date now = new Date();
        user.setCreateTime(now).setUpdateTime(now);
        return userDao.save(user);
    }
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
