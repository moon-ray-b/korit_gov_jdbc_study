package com.korit.study2.dao;

import com.korit.study2.entity.User;
import com.korit.study2.util.ConnectionFactory;

import java.sql.*;
import java.util.Optional;

public class UserDao {
    private static UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }
    //회원가입
    public int add(User user) {
        String sql = // insert into 값을 대입을 한다
                //values
                "insert into user2_tb(user_id, username, password, email, create_dt) values(0,?,?,?,now())";
        try(
                Connection con = com.korit.study1.util.ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());

            int updateInt = ps.executeUpdate();
            try(ResultSet keys = ps.getGeneratedKeys()){//자동 생성키를 가져오는 메소드
                System.out.println(keys.toString());
                if (keys.next()){
                    int id = keys.getInt(1);
                    user.setUserId(id);
                }
            }
            return updateInt;

        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    //username 조회
    public Optional<User> findUserByUsername(String username) {
        String sql = "select * from user2_tb where username = ?";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(toUser(rs)) : Optional.empty();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    //email 조회
    public Optional<User> findUserByEmail(String email){
        String mail = "select * from user2_tb where email = ?";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(mail);
        ) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toUser(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    //user 추가
    //user 전체 조회

    //user username으로 조회

    public User toUser(ResultSet rs) throws SQLException {
        return User.builder()
                .userId(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();

    }
}