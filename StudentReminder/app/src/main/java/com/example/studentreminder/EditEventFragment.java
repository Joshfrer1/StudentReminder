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
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class EditEventFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ToDoViewModel viewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        FragmentEditEventBinding binding = FragmentEditEventBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment

        viewModel.getCurrentItem().observe(getViewLifecycleOwner(), (todo) -> {
            if (todo != null) {
                binding.getEvent.setText(todo.title);
            }
        });


        binding.date.setOnClickListener(view -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Reminder Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            datePicker.show(requireActivity().getSupportFragmentManager(), "date");
        });
        binding.time.setOnClickListener(view -> {
           MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                   .setTimeFormat(TimeFormat.CLOCK_12H)
                   .setHour(12)
                   .setMinute(00)
                   .setTitleText("Select Reminder Time")
                   .build();
           timePicker.show(requireActivity().getSupportFragmentManager(), "time");
        });
        binding.getRoot().setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });

        binding.save.setOnClickListener(view -> {
            viewModel.saveNewToDoItem(binding.getEvent.getText().toString());
            getActivity().getSupportFragmentManager().popBackStack();
        });
        return binding.getRoot();
    }
}