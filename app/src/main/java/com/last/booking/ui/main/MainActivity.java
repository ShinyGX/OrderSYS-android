package com.last.booking.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.last.booking.R;
import com.last.booking.data.dto.MissionNoticeObj;
import com.last.booking.data.model.MissionNoticeInfo;
import com.last.booking.service.AlarmService;

import java.text.SimpleDateFormat;
import java.util.List;

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

        mainViewModel.getNotice();

        mainViewModel.getNoticeResult().observe(this, new Observer<NoticeResult>() {
            @Override
            public void onChanged(@Nullable NoticeResult noticeResult) {
                if(noticeResult == null)
                    return;

                if(noticeResult.getErrorMsg() != null)
                {
                    Toast.makeText(getApplicationContext(),noticeResult.getErrorMsg(),Toast.LENGTH_SHORT).show();
                }

                if(noticeResult.getNoticeInfoList() != null)
                {
                    List<MissionNoticeInfo> infoList = noticeResult.getNoticeInfoList();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-HH-dd hh时mm分");
                    if(infoList.size() > 0)
                    {
                        for(MissionNoticeInfo info : infoList)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("预约提醒");
                            builder.setMessage("您预约的" + sdf.format(info.getOrderTime()) + "的" + info.getBusinessName() +
                                    "业务(地址" + info.getOfficeAddress() + ")" +
                                    "即将开始业务" + "，您的预约号为(" + info.getMissionRegisterId() + ")请及时到达营业厅，若已过号请咨询前台");

                            builder.setNegativeButton("确定",null);
                            builder.create().show();
                        }
                    }
                }
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
