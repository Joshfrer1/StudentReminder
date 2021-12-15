package com.example.studentreminder;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentreminder.api.USUCanvasAPI;
import com.example.studentreminder.models.UpcomingEvent;

public class UpcomingEventsFragment {
    public UpcomingEventsFragment() {super(R.layout.upcoming_events);}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView eventRecyclerView = view.findViewById(R.id.event_recycler_view);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        USUCanvasAPI api = USUCanvasAPI.getInstance(getContext());


        api.getUpcomingEvents(new USUCanvasAPI.OnRequestCompleteListener<UpcomingEvent>() {
            @Override
            public void onComplete(UpcomingEvent[] result, String errorMessage) {
                eventRecyclerView.setAdapter(new UpcomingEventsAdapter(result));
            }
        });
    }
}
