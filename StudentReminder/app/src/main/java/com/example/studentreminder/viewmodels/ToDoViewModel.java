package com.example.studentreminder.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.studentreminder.R;
import com.example.studentreminder.database.AppDatabase;
import com.example.studentreminder.models.CategoryItem;
import com.example.studentreminder.models.ToDoItem;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class ToDoViewModel extends AndroidViewModel {

    private AppDatabase database;
    private Handler handler;
    private MutableLiveData<ToDoItem> currentItem = new MutableLiveData<>();
    private ObservableArrayList<ToDoItem> toDoList = new ObservableArrayList<>();
    private boolean loaded = false;



    public ToDoViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, AppDatabase.class, application.getString(R.string.db_name)).build();
        handler = new Handler();
    }

    public void loadTodos() {
        if (!loaded) {
            loaded = true;
            new Thread(() -> {
                List<ToDoItem> todoList = database.getToDoItemDao().getAll();
                handler.post(() -> {
                    todoList.addAll(todoList);
                });
            }).start();
        }

    }

    public void removeTodoCommand(ToDoItem todo) {

    }

    public ObservableArrayList<ToDoItem> getTodos() {return toDoList;}

    public void setCurrentTodo(ToDoItem todo){this.currentItem.setValue(todo);}

    public MutableLiveData<ToDoItem> getCurrentItem() {return currentItem;}

    public void markTodoCommand(ToDoItem todo, boolean isDone) {
        new Thread(() -> {
            todo.isCompleted = isDone;
            database.getToDoItemDao().update(todo);
            int index = toDoList.indexOf(todo);
            handler.post(() -> {
                toDoList.set(index, todo);
            });
        }).start();

    }


    public void saveNewToDoItem(String title){
        new Thread(() -> {
            if (currentItem.getValue() != null) {
                // updating
                currentItem.getValue().title = title;
                database.getToDoItemDao().update(currentItem.getValue());
                int index = toDoList.indexOf(currentItem.getValue());
                handler.post(() -> {
                    toDoList.set(index, currentItem.getValue());
                });
            } else {
                // create
                ToDoItem newTodo = new ToDoItem();
                newTodo.title = title;
                newTodo.id = database.getToDoItemDao().insert(newTodo);
                // TODO add to database
                handler.post(() -> {
                    toDoList.add(newTodo);
                });
            }
        }).start();

    }

    public void updateTodoCommand(ToDoItem todo, String title) {
        new Thread(() -> {
            todo.title = title;
            database.getToDoItemDao().update(todo);
            int index = toDoList.indexOf(todo);
            handler.post(() -> {
                toDoList.set(index, todo);
            });
        }).start();
    }

    public void deleteToDoItem(ToDoItem item){
        new Thread(() -> {
            database.getToDoItemDao().deleteItem(item);
            toDoList.remove(item);
        }).start();
    }

    private void getToDoList(){
        new Thread(() -> {
            ArrayList<ToDoItem> toDos = new ArrayList<>();
            toDos = (ArrayList<ToDoItem>) database.getToDoItemDao().getAll();
            toDoList.addAll(toDos);
        }).start();
    }


    public void deleteCurrentItem(){
        deleteToDoItem(currentItem.getValue());
        currentItem.setValue(null);
    }

    public ObservableArrayList<ToDoItem> getFullList(){
        getToDoList();
        return toDoList;
    }
}
