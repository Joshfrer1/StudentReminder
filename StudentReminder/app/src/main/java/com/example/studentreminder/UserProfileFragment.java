package com.example.studentreminder;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentreminder.api.USUCanvasAPI;
import com.example.studentreminder.models.UpcomingEvent;
import com.example.studentreminder.models.User;

import java.util.ArrayList;

public class UserProfileFragment extends Fragment {
    public UserProfileFragment() {
        super(R.layout.user_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        USUCanvasAPI api = USUCanvasAPI.getInstance(getContext());

        api.getUser(new USUCanvasAPI.OnRequestCompleteListener<User>() {
            @Override
            public void onComplete(User[] result, String errorMessage) {
                TextView userName = view.findViewById(R.id.userName);
                userName.setText(result[0].name);
            }
        });
    }
}
