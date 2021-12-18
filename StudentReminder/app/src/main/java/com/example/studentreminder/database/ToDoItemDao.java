package com.example.studentreminder.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentreminder.models.ToDoItem;

import java.util.List;

@Dao
public interface ToDoItemDao {
    @Insert
    public long insert(ToDoItem item);

    @Query("SELECT * FROM ToDoItem")
    public List<ToDoItem> getAll();

    @Query("SELECT * FROM ToDoItem WHERE id = :id")
    public ToDoItem getOne(long id);

    @Update
    public void update(ToDoItem item);
    @Delete
    public void deleteItem(ToDoItem item);

}
