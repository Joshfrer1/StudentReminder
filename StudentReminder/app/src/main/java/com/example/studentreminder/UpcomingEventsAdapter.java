package com.example.studentreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentreminder.models.UpcomingEvent;

import java.util.ArrayList;

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.ViewHolder> {

    private UpcomingEvent[] upcomingEvents;
    public UpcomingEventsAdapter(UpcomingEvent[] upcomingEvents) {
        this.upcomingEvents = upcomingEvents;
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
        UpcomingEvent upcomingEvent = upcomingEvents[position];
        TextView title = holder.itemView.findViewById(R.id.event_title);
        title.setText(upcomingEvent.title);
    }

    @Override
    public int getItemCount() {
        return upcomingEvents.length;
    }
}
