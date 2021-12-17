package com.example.studentreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentreminder.databinding.FragmentEventBinding;


public class EventFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentEventBinding binding = FragmentEventBinding.inflate(inflater, container, false);

        binding.fab.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, EditEventFragment.class, null)
                    .addToBackStack(null)
                    .commit();
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}