package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from user";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("id"),
                        rs.getString("phone_number"),
                        rs.getTimestamp("created_at"),
                        rs.getString("nickname"),
                        rs.getBigDecimal("temperature"),
                        rs.getInt("area_id"),
                        rs.getString("password"))
                );
    }

    public List<GetUserRes> getUsersByPhone(String email){
        String getUsersByPhoneQuery = "select * from user where phone_number =?";
        String getUsersByPhoneParams = email;
        return this.jdbcTemplate.query(getUsersByPhoneQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("id"),
                        rs.getString("phone_number"),
                        rs.getTimestamp("created_at"),
                        rs.getString("nickname"),
                        rs.getBigDecimal("temperature"),
                        rs.getInt("area_id"),
                        rs.getString("password")),
                getUsersByPhoneParams);
    }

    public GetUserRes getUser(int id){
        String getUserQuery = "select * from user where id = ?";
        int getUserParams = id;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("id"),
                        rs.getString("phone_number"),
                        rs.getTimestamp("created_at"),
                        rs.getString("nickname"),
                        rs.getBigDecimal("temperature"),
                        rs.getInt("area_id"),
                        rs.getString("password")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into user (phone_number, nickname, password, area_id) VALUES (?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getPhone_number(), postUserReq.getNickname(), postUserReq.getPassword(), postUserReq.getArea_id()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
    // 존재하는 전화번호면 1 반환, 아니면 0 반환
    public int checkPhone(String phone){
        String checkPhoneQuery = "select exists(select phone_number from user where phone_number = ?)";
        String checkPhoneParams = phone;
        return this.jdbcTemplate.queryForObject(checkPhoneQuery,
                int.class,
                checkPhoneParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update user set nickname = ? where id = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getNickname(), patchUserReq.getId()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select * from user where phone_number = ?";
        String getPwdParams = postLoginReq.getPhone_number();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("id"),
                        rs.getString("phone_number"),
                        rs.getTimestamp("created_at"),
                        rs.getString("nickname"),
                        rs.getBigDecimal("temperature"),
                        rs.getInt("area_id"),
                        rs.getString("password")
                ),
                getPwdParams
                );

    }


}
