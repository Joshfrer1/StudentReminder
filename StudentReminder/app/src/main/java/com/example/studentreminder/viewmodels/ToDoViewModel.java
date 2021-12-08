package com.example.studentreminder.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ToDoViewModel extends AndroidViewModel {
    public ToDoViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveToDoItem(String title, long dueDate, long remindDate, String reoccur){

    }
    public void saveToDoItem(String title, long dueDate, long remindDate, String reoccur, int categoryId){

    }
}
