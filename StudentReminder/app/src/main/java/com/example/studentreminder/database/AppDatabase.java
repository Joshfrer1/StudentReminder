package com.example.studentreminder.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.studentreminder.models.CategoryItem;
import com.example.studentreminder.models.ToDoItem;

@Database(entities ={ToDoItem.class, CategoryItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ToDoItemDao getToDoItemDao();
    public abstract CategoryItemDao getCategoryItemDao();
}
