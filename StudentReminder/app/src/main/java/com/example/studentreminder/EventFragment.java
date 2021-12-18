package com.example.studentreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentreminder.databinding.FragmentEventBinding;
import com.example.studentreminder.viewmodels.ToDoViewModel;


public class EventFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentEventBinding binding = FragmentEventBinding.inflate(inflater, container, false);

        ToDoViewModel viewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        viewModel.loadTodos();

        binding.fab.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, EditEventFragment.class, null)
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
        });

        binding.recyclerView.setAdapter(new EventAdapter(viewModel, todo -> {
            viewModel.setCurrentTodo(todo);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, EditEventFragment.class, null)
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
        }));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//         Inflate the layout for this fragment
        return binding.getRoot();
    }
}