package com.example.studentreminder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentreminder.models.CategoryItem;
import com.example.studentreminder.models.ToDoItem;

public class EventSettingsAdapter extends RecyclerView.Adapter<EventSettingsAdapter.ViewHolder> {

    private ObservableArrayList<CategoryItem> categories;
    public EventSettingsAdapter(ObservableArrayList<CategoryItem> categories){
        this.categories = categories;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
    @NonNull
    @Override
    public EventSettingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventSettingsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
