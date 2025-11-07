package com.korit;

import com.korit.dao.UserDao;
import com.korit.entity.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();

        //insert
        User user = User.builder()
                .username("bae9842")
                .password("1q2w3e4r")
                .age(23)
                .build();
        int count = userDao.addUser(user);
        System.out.println("추가된 행 개수: " + count );
        System.out.println("추가된 유저 개수: " + user);
    }
}
