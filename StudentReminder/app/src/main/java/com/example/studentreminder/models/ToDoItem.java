package com.example.studentreminder.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ToDoItem {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo
    public String title;
    @ColumnInfo(name = "is_completed")
    public boolean isCompleted = false;

}
