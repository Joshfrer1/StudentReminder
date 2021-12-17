package com.example.studentreminder;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentreminder.api.USUCanvasAPI;
import com.example.studentreminder.models.ToDoItem;
import com.example.studentreminder.models.UpcomingEvent;
import com.example.studentreminder.models.User;
import com.example.studentreminder.viewmodels.ToDoViewModel;
import com.example.studentreminder.viewmodels.UserViewModel;

import java.util.ArrayList;

public class UpcomingEventsFragment extends Fragment {
    public UpcomingEventsFragment() {super(R.layout.upcoming_events);}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView eventRecyclerView = view.findViewById(R.id.event_recycler_view);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ToDoViewModel toDoViewModel = new ToDoViewModel(getActivity().getApplication());
        UserViewModel userViewModel = new UserViewModel(getActivity().getApplication());

        userViewModel.getUser().observe(getViewLifecycleOwner(), (currentUser) ->{

            if(currentUser != null) {
                USUCanvasAPI api = USUCanvasAPI.getInstance(getContext());
                ArrayList<ToDoItem> canvasImports = new ArrayList<>();
                api.getUpcomingEvents(new USUCanvasAPI.OnRequestCompleteListener<ToDoItem>() {
                    @Override
                    public void onComplete(ToDoItem[] result, String errorMessage) {
                        for (ToDoItem item : result) {
                            canvasImports.add(item);
                        }
                    }
                });
                for(ToDoItem item: canvasImports){
                    toDoViewModel.saveNewToDoItem(
                            item.title,
                            item.dueDate,
                            item.remindDate,
                            item.reoccur,
                            item.canvasId
                    );
                }
            }
        });

        ObservableArrayList<ToDoItem> toDoList = toDoViewModel.getFullList();
        eventRecyclerView.setAdapter(new UpcomingEventsAdapter(toDoList));
    }
}
