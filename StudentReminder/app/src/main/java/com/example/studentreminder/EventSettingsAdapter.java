package com.example.studentreminder;

import android.graphics.Color;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentreminder.models.CategoryItem;
import com.example.studentreminder.models.ToDoItem;

public class EventSettingsAdapter extends RecyclerView.Adapter<EventSettingsAdapter.ViewHolder> {

    private ObservableArrayList<CategoryItem> categories;
    public OnCategoryItemClicked listner;

    public interface OnCategoryItemClicked{
        public void onClick(CategoryItem category);
    }

    public EventSettingsAdapter(ObservableArrayList<CategoryItem> categories, OnCategoryItemClicked listner){
        this.categories = categories;
        this.listner = listner;
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
        CategoryItem category = categories.get(position);
        TextView title = holder.itemView.findViewById(R.id.cat_title);
        title.setText(category.category);

        ImageView colourBox = holder.itemView.findViewById(R.id.cat_colour);
        int colour = Color.parseColor(category.colour);
        colourBox.setColorFilter(colour);

        Button button = holder.itemView.findViewById(R.id.cat_edit_button);
        button.setOnClickListener(view ->{
            listner.onClick(category);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
