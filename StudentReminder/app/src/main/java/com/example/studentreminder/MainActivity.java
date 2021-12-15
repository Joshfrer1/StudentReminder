package com.example.studentreminder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuInflater;
import android.widget.TextView;

import com.example.studentreminder.api.USUCanvasAPI;
import com.example.studentreminder.models.Course;
import com.example.studentreminder.models.UpcomingEvent;
import com.example.studentreminder.models.User;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        NavigationView navigationView = findViewById(R.id.navigation_view);



        toolbar.setNavigationOnClickListener(view -> {
            drawerLayout.open();
        });



        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            if (menuItem.getItemId() == R.id.event_settings) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, EventSettingsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
            if (menuItem.getItemId() == R.id.profile_item) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, UserProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
            return true;
        });
    }
}