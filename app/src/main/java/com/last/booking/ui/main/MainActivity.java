package com.last.booking.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.last.booking.R;
import com.last.booking.service.AlarmService;

public class MainActivity extends AppCompatActivity {


    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int userId = getIntent().getIntExtra("userId",-1);
        if(userId == -1)
            Toast.makeText(getApplicationContext(),"UserIdError",Toast.LENGTH_SHORT).show();

        mainViewModel = ViewModelProviders.of(this,new MainViewModelFactory())
                .get(MainViewModel.class);

        mainViewModel.setUserId(userId);
        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_navigation_layout);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                onItemSelect(menuItem.getItemId());
                return true;
            }
        });

        onItemSelect(R.id.tab_menu_main);
    }

    private void onItemSelect(int id)
    {
        Fragment fragment = null;
        switch (id)
        {
            case R.id.tab_menu_main:
                fragment = mainViewModel.getFragment(0);
                break;

            case R.id.tab_menu_mine:
                fragment = mainViewModel.getFragment(1);
                break;
        }

        if(fragment != null)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("userId",mainViewModel.getUserId());
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_home_container, fragment)
                    .commit();
        }


    }

}
