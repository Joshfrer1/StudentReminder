package com.example.studentreminder.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.studentreminder.models.CategoryItem;

public class CategoryViewModel extends AndroidViewModel {
    public CategoryViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveCategory(String title, String colour){
        CategoryItem category = new CategoryItem();
        category.category = title;
        category.colour = colour;

    }
}
