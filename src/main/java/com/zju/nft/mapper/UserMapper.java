package com.zju.nft.mapper;

import com.zju.nft.entity.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findUserById(int id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Update("update user set password = #{newPassword},salt = #{salt} WHERE id = #{id}")
    void updatePassword(String newPassword, String salt, Integer id);

    @Insert("insert into user (id, username,password,salt) values(#{id}, #{username},#{password},#{salt})")
    void registerUser(Integer id, String username, String password, String salt);

    @Select("select count(*) from user")
    Integer sumUser();
}
