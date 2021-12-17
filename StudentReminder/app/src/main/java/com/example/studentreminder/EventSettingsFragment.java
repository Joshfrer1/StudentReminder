package com.example.studentreminder;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentreminder.models.CategoryItem;
import com.example.studentreminder.viewmodels.CategoryViewModel;

public class EventSettingsFragment extends Fragment {
    public EventSettingsFragment() {
        super(R.layout.upcoming_events);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView settingsRecyclerView = view.findViewById(R.id.cat_rec_view);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CategoryViewModel categoryViewModel = new CategoryViewModel(getActivity().getApplication());
        ObservableArrayList<CategoryItem> categories = categoryViewModel.getCategories();
        
        settingsRecyclerView.setAdapter(new EventSettingsAdapter(categories));
    }
}
