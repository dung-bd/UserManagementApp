package com.example.usermanagementapp.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.usermanagementapp.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();

    @Query("SELECT * FROM user where name= :name")
    List<User> checkUser(String name);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM user")
    void deleteAllUser();

    @Query("SELECT * FROM user WHERE name LIKE '%' || :name || '%'")
    List<User> searchUser(String name);
}