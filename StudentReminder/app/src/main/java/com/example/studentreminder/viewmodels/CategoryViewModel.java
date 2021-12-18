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

import java.util.ArrayList;

public class CategoryViewModel extends AndroidViewModel {

    private AppDatabase database;
    private ObservableArrayList<CategoryItem> categories = new ObservableArrayList<>();
    private MutableLiveData<Boolean> saving = new MutableLiveData<>();
    private MutableLiveData<CategoryItem> currentCategory = new MutableLiveData<>();

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, AppDatabase.class, application.getString(R.string.db_name)).build();
        saving.setValue(false);
        setCategories();
    }

    public MutableLiveData<Boolean> getSaving() {
        return saving;
    }

    public void saveNewCategory(String title){
        saving.setValue(true);
        new Thread(() ->{
            CategoryItem category = new CategoryItem();
            category.category = title;
            category.id = database.getCategoryItemDao().insert(category);
            categories.add(category);
            saving.postValue(false);
        }).start();
    }

    public void updateCategory(CategoryItem item){
        saving.setValue(true);
        new Thread(() ->{
            database.getCategoryItemDao().update(item);
            saving.postValue(false);
        }).start();
    }

    public void deleteCategory(CategoryItem item){
        new Thread(() ->{
            database.getCategoryItemDao().delete(item);
            categories.remove(item);
        }).start();
    }

    private void setCategories() {
        new Thread(() -> {
            ArrayList<CategoryItem> catList;
            catList = (ArrayList<CategoryItem>) database.getCategoryItemDao().getCategories();
            categories.addAll(catList);
        }).start();
    }

    public MutableLiveData<CategoryItem> getCurrentCategory() {
        return currentCategory;
    }

    public ObservableArrayList<CategoryItem> getCategories(){
        return categories;
    }
}
