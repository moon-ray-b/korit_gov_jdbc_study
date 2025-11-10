package com.korit;

import com.korit.dao.UserDao;
import com.korit.entity.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();

//        //insert
//        User user = User.builder()
//                .username("bae9842")
//                .password("1q2w3e4r")
//                .age(23)
//                .build();
//        int count = userDao.addUser(user);
//        System.out.println("추가된 행 개수: " + count );
//        System.out.println("추가된 유저 개수: " + user);

        //단건조회
        User foundUser = userDao.findUserByUsername("bae9842");
        System.out.println("foundUser = " + foundUser);

        //전체 조회
        List<User> userList = userDao.getUserAllList();
        System.out.println(userList);
        userList.forEach(System.out::println);

        //username 키워드 검색
        List<User> usersList11 = userDao.getUserListByKeyword("bae");
        userList.forEach(System.out::println);
    }
}
