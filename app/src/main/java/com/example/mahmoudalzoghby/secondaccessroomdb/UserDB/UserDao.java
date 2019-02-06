package com.example.mahmoudalzoghby.secondaccessroomdb.UserDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long insert(UserEntity user);

    @Query("SELECT * FROM User")
    List<UserEntity> loadAllUsers();

}
