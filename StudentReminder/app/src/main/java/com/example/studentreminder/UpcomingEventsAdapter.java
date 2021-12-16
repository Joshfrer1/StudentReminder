package com.example.studentreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentreminder.models.ToDoItem;
import com.example.studentreminder.models.UpcomingEvent;


public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.ViewHolder> {

    private ObservableArrayList<ToDoItem> items;
    public UpcomingEventsAdapter(ObservableArrayList<ToDoItem> items) {
        this.items = items;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    @NonNull
    @Override
    public UpcomingEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_events_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingEventsAdapter.ViewHolder holder, int position) {
        ToDoItem item = items.get(position);
        TextView title = holder.itemView.findViewById(R.id.event_title);
        title.setText(item.title);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
