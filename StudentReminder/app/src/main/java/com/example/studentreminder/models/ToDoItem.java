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
    @ColumnInfo(name = "due_date")
    public String dueDate;
    @ColumnInfo(name = "remind_date")
    public String remindDate;
    @ColumnInfo
    public String reoccur;
    @ColumnInfo(name = "category_id")
    public int categoryId;
    @ColumnInfo(name = "canvas_id")
    public int canvasId;
    @ColumnInfo(name = "is_completed")
    public boolean isCompleted;
}
