package com.example.studentreminder.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;

import com.example.studentreminder.database.AppDatabase;
import com.example.studentreminder.models.CategoryItem;
import com.example.studentreminder.models.ToDoItem;

public class ViewModel extends AndroidViewModel {

    private AppDatabase database;

    public ViewModel(@NonNull Application application) {

        super(application);
        database = Room.databaseBuilder(application, AppDatabase.class, "appdb").build();
    }

    public void saveToDoItem(String title, long dueDate, long remindDate, String reoccur, boolean isCanvasItem){
        new Thread(() ->{
            ToDoItem item = new ToDoItem();
            item.title = title;
            item.dueDate = dueDate;
            item.remindDate = remindDate;
            item.reoccur = reoccur;
            item.isCanvasItem = isCanvasItem;
            item.isCompleted = false;
            item.id = database.getToDoItemDao().insert(item);
        }).start();
    }
    public void saveToDoItem(String title, long dueDate, long remindDate, String reoccur, boolean isCanvasItem, int categoryId){
        new Thread(() ->{
            ToDoItem item = new ToDoItem();
            item.title = title;
            item.dueDate = dueDate;
            item.remindDate = remindDate;
            item.reoccur = reoccur;
            item.categoryId = categoryId;
            item.isCanvasItem = isCanvasItem;
            item.isCompleted = false;
            item.id = database.getToDoItemDao().insert(item);
        }).start();

    }

    public void saveCategory(String title, String colour){
        new Thread(() ->{
            CategoryItem category = new CategoryItem();
            category.category = title;
            category.colour = colour;
            category.id = database.getCategoryItemDao().insert(category);
        }).start();
    }
}
