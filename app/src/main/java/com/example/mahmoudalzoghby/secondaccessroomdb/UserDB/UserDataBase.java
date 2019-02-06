package com.example.mahmoudalzoghby.secondaccessroomdb.UserDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {UserEntity.class} , version = 1 , exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();

    private static UserDataBase UserDB;

    public static UserDataBase getInstance(Context context){
        if (UserDB == null){
            UserDB = buildDatabaseInstance(context);
        }
        return UserDB;

    }

    private static UserDataBase buildDatabaseInstance(Context context){

        return Room.databaseBuilder(context , UserDataBase.class , "UserDB.db").allowMainThreadQueries().build();

    }

    public void cleanup(){UserDB = null;}

}
