package com.example.studentreminder.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class CategoryItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String category;
    @ColumnInfo
    public String colour;
}
