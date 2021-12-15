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

    @Query("select * from todoitem where is_completed = 0 order by remind_date, due_date")
    public List<ToDoItem> getAll();
    @Query("select * from todoitem where id = :id")
    public ToDoItem findById(long id);
    @Query("select * from todoitem where canvas_id <> null")
    public List<ToDoItem> getCanvasItems();


    @Update
    public void update(ToDoItem item);
    @Delete
    public void deleteItem(ToDoItem item);

}
