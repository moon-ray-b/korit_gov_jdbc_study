package com.korit.study2.service;

import com.korit.study2.dao.UserDao;
import com.korit.study2.dto.GetUserListRespDto;
import com.korit.study2.dto.SigninReqDto;
import com.korit.study2.dto.SignupReqDto;
import com.korit.study2.entity.User;
import com.korit.study2.util.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static UserService instance;
    private UserDao userDao;

    private UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService(UserDao.getInstance());
        }
        return instance;
    }

    // username 중복확인
    public boolean isDuplicatedUsername(String username) {
        Optional<User> foundUser = userDao.findUserByUsername(username);
        return foundUser.isPresent();
    }
    // email 중복확인
    public boolean isDuplicatedEmail(String email){
        Optional<User> foundEmail = userDao.findUserByUsername(email);
        return foundEmail.isPresent();
    }
    // 회원가입
    public void signup(SignupReqDto signupReqDto){
        userDao.add(signupReqDto.toEntity());
    }


    public boolean isEmpty(String str) {
        //isEmpty boolean
        //username, password 각각 null이거나 공백이 아닌지 확인 용도
        if (str == null || str.isBlank()){
            return true;
        }
        return false;
    }

    // 로그인
    public void signin(SigninReqDto signinReqDto){

        Optional<User> foundUser = userDao.findUserByUsername(signinReqDto.getUsername());
        if (foundUser.isEmpty()){
            System.out.println("사용자 정보를 다시 확인 하세요");
            return;
        }
        if (!PasswordEncoder.match(signinReqDto.getPassword(), foundUser.get().getPassword())){
            System.out.println("사용자 정보를 다시 확인하세요");
            return;
        }
        System.out.println("로그인 성공!");
        System.out.println(foundUser);

    }
    // 회원 전체 조회
    // 회원 검색
}