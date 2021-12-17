package com.example.studentreminder;

import android.os.Bundle;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentreminder.databinding.FragmentEditEventBinding;
import com.example.studentreminder.viewmodels.ToDoViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;


public class EditEventFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ToDoViewModel viewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        FragmentEditEventBinding binding = FragmentEditEventBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        binding.save.setOnClickListener(view -> {

        });
        binding.date.setOnClickListener(view -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            datePicker.show(requireActivity().getSupportFragmentManager(), "tag");
        });

        return binding.getRoot();
    }
}