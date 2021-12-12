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
    public long dueDate;
    @ColumnInfo(name = "remind_date")
    public long remindDate;
    @ColumnInfo
    public String reoccur;
    @ColumnInfo(name = "category_id")
    public int categoryId;
    @ColumnInfo(name = "is_canvas_item")
    public boolean isCanvasItem;
    @ColumnInfo(name = "is_completed")
    public boolean isCompleted;
}
