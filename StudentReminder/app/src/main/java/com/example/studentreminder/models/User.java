package com.example.studentreminder.models;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo
    public int canvasId;
    @ColumnInfo
    public String name;
    @ColumnInfo
    public String token;

}
