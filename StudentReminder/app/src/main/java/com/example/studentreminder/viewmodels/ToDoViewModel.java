package com.example.studentreminder.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.studentreminder.R;
import com.example.studentreminder.database.AppDatabase;
import com.example.studentreminder.models.CategoryItem;
import com.example.studentreminder.models.ToDoItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ToDoViewModel extends AndroidViewModel {

    private AppDatabase database;
    private MutableLiveData<Boolean> saving = new MutableLiveData<Boolean>();
    private MutableLiveData<ToDoItem> currentItem = new MutableLiveData<>();
    private ObservableArrayList<ToDoItem> toDoList = new ObservableArrayList<>();



    public ToDoViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, AppDatabase.class, application.getString(R.string.db_name)).build();
        saving.setValue(false);
    }

    public MutableLiveData<Boolean> getSaving() {
        return saving;
    }

    public void saveNewToDoItem(String title, String dueDate, String remindDate, String reoccur, int canvasId){
        saving.setValue(true);
        new Thread(() ->{
            ToDoItem item = new ToDoItem();
            item.title = title;
            item.dueDate = dueDate;
            item.remindDate = remindDate;
            item.reoccur = reoccur;
            item.canvasId = canvasId;
            item.isCompleted = false;
            item.id = database.getToDoItemDao().insert(item);
            toDoList.add(item);
            saving.postValue(false);
        }).start();
    }
    public void saveNewToDoItem(String title, String dueDate, String remindDate, String reoccur, int canvasId, int categoryId){
        saving.setValue(true);
        new Thread(() ->{
            ToDoItem item = new ToDoItem();
            item.title = title;
            item.dueDate = dueDate;
            item.remindDate = remindDate;
            item.reoccur = reoccur;
            item.categoryId = categoryId;
            item.canvasId = canvasId;
            item.isCompleted = false;
            item.id = database.getToDoItemDao().insert(item);
            toDoList.add(item);
            saving.postValue(false);
        }).start();

    }

    public void updateToDoItem(ToDoItem item){
        saving.setValue(true);
        new Thread(() -> {
            database.getToDoItemDao().update(item);
            saving.postValue(false);
        }).start();
    }

    public void deleteToDoItem(ToDoItem item){
        new Thread(() -> {
            database.getToDoItemDao().deleteItem(item);
            toDoList.remove(item);
        }).start();
    }

    public void getToDoList(){
        new Thread(() -> {
            ArrayList<ToDoItem> toDos = new ArrayList<>();
            toDos = (ArrayList<ToDoItem>) database.getToDoItemDao().getAll();
            toDoList.addAll(toDos);
        }).start();
    }

    public void deleteCanvasItems(){
        saving.setValue(true);
        new Thread(() -> {
            ArrayList<ToDoItem> items = (ArrayList<ToDoItem>) database.getToDoItemDao().getCanvasItems();
            for(ToDoItem item: items){
                database.getToDoItemDao().deleteItem(item);
                toDoList.remove(item);
            }
            saving.postValue(false);
        }).start();
    }
}
