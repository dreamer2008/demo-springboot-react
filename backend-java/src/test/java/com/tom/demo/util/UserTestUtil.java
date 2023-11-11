package com.tom.demo.util;

import com.tom.demo.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTestUtil {

    public static List<User> createUserList() {
        List<User> list = new ArrayList<>();
        for (int i = 1 ; i <= 5; i++) {
            Date now = new Date();
            User user= new User().setUserName("user-" + i).setEmail("email-" + i).setPassword("passwd-" + i).setCreateTime(now).setUpdateTime(now);
            list.add(user);
        }
        return list;
    }
}
