package com.example.studentreminder.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.studentreminder.models.ToDoItem;

public class ToDoViewModel extends AndroidViewModel {
    public ToDoViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveToDoItem(String title, long dueDate, long remindDate, String reoccur, boolean isCanvasItem){
        ToDoItem item = new ToDoItem();
        item.title = title;
        item.dueDate = dueDate;
        item.remindDate = remindDate;
        item.reoccur = reoccur;
        item.isCanvasItem = isCanvasItem;
    }
    public void saveToDoItem(String title, long dueDate, long remindDate, String reoccur, boolean isCanvasItem, int categoryId){
        ToDoItem item = new ToDoItem();
        item.title = title;
        item.dueDate = dueDate;
        item.remindDate = remindDate;
        item.reoccur = reoccur;
        item.categoryId = categoryId;
        item.isCanvasItem = isCanvasItem;
    }
}
