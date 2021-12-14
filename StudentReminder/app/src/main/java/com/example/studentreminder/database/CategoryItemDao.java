package com.example.studentreminder.database;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentreminder.models.CategoryItem;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CategoryItemDao {
    @Insert
    public long insert(CategoryItem item);
    @Query("select * from categoryitem")
    public List<CategoryItem> getCategories();
    @Query("select * from categoryitem where id = :id")
    public CategoryItem findById(long id);
    @Update
    public void update(CategoryItem item);
    @Delete
    public void delete(CategoryItem item);
}
