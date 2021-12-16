package com.example.studentreminder.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.studentreminder.R;
import com.example.studentreminder.database.AppDatabase;
import com.example.studentreminder.models.User;

public class UserViewModel extends AndroidViewModel {

   private AppDatabase database;
   private MutableLiveData<User> currentUser = new MutableLiveData<>();
   private MutableLiveData<Boolean> saving = new MutableLiveData<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, AppDatabase.class, application.getString(R.string.db_name)).build();
        getUser();
    }

    public MutableLiveData<Boolean> getSaving(){
        return saving;
    }
    public void saveNewUser(int canvasId, String name, String token){
        saving.setValue(true);
        new Thread(() ->{
            User user = new User();
            user.canvasId = canvasId;
            user.name = name;
            user.token = token;
            user.id = database.getUserDao().insert(user);
            saving.postValue(false);
        }).start();
    }

    public void updateUser(User user){
        saving.setValue(true);
        new Thread(() ->{
            database.getUserDao().update(user);
            saving.postValue(false);
        }).start();
    }

    public void deleteUser(User user){
        saving.setValue(true);
        new Thread(() ->{
            database.getUserDao().deleteUser(user);
            saving.postValue(false);
        }).start();
    }

    private void setUser(){
        saving.setValue(true);
        new Thread(() ->{
            User user = database.getUserDao().getUser();
            currentUser.postValue(user);
            saving.postValue(false);
        }).start();
    }

    public MutableLiveData<User> getUser(){
        setUser();
        return currentUser;
    }


}
