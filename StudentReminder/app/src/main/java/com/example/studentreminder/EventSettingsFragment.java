package com.example.studentreminder;


import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentreminder.models.CategoryItem;
import com.example.studentreminder.viewmodels.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EventSettingsFragment extends Fragment {
    public EventSettingsFragment() {
        super(R.layout.event_settings);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView settingsRecyclerView = view.findViewById(R.id.cat_rec_view);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CategoryViewModel categoryViewModel = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        ObservableArrayList<CategoryItem> categories = categoryViewModel.getCategories();

        EventSettingsAdapter adapter = new EventSettingsAdapter(categories, category -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, NewCategoryFragment.class, null);
        });

        categories.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<CategoryItem>>() {
                                                @Override
                                                public void onChanged(ObservableList<CategoryItem> sender) {
                                                    getActivity().runOnUiThread(() ->{
                                                        adapter.notifyDataSetChanged();
                                                    });
                                                }

                                                @Override
                                                public void onItemRangeChanged(ObservableList<CategoryItem> sender, int positionStart, int itemCount) {
                                                    getActivity().runOnUiThread(()->{
                                                        adapter.notifyItemRangeChanged(positionStart, itemCount);
                                                    });
                                                }

                                                @Override
                                                public void onItemRangeInserted(ObservableList<CategoryItem> sender, int positionStart, int itemCount) {
                                                    getActivity().runOnUiThread(()->{
                                                        adapter.notifyItemRangeInserted(positionStart, itemCount);
                                                    });
                                                }

                                                @Override
                                                public void onItemRangeMoved(ObservableList<CategoryItem> sender, int fromPosition, int toPosition, int itemCount) {
                                                    getActivity().runOnUiThread(()->{
                                                            adapter.notifyItemMoved(fromPosition, toPosition);
                                                    });
                                                }

                                                @Override
                                                public void onItemRangeRemoved(ObservableList<CategoryItem> sender, int positionStart, int itemCount) {
                                                    getActivity().runOnUiThread(()->{
                                                        adapter.notifyItemRangeRemoved(positionStart, itemCount);
                                                    });
                                                }
                                            }

        );
        settingsRecyclerView.setAdapter(adapter);
        FloatingActionButton button = view.findViewById(R.id.floatingActionButton);
        button.setOnClickListener(thing ->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, NewCategoryFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });
    }

}
