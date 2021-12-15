package com.example.studentreminder.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentreminder.models.ToDoItem;
import com.example.studentreminder.models.User;

@Dao
public interface UserDao {
    @Insert
    public long insert(User user);
    @Query("select * from user")
    public User getUser();
    @Update
    public void update(User user);
    @Delete
    public void deleteUser(User user);
}
