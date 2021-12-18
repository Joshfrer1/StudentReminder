package com.example.studentreminder.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class CategoryItem {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo
    public String category;
}
