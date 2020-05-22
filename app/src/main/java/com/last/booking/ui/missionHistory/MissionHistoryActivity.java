package com.last.booking.ui.missionHistory;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.last.booking.OnRecyclerItemClickListener;
import com.last.booking.R;
import com.last.booking.SlideInRightAnimation;
import com.last.booking.SpaceItemDecoration;
import com.last.booking.ui.missionHistory.adapter.MissionInfoAdapter;

import java.util.Date;

public class MissionHistoryActivity extends AppCompatActivity {


    private MissionHistoryViewModel viewModel;
    private RecyclerView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_history);

        viewModel = ViewModelProviders.of(this,new MissionHistoryViewModelFactory()).get(MissionHistoryViewModel.class);

        viewModel.getMissionInfo();

        list = findViewById(R.id.missionhistory_list);
        list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(MissionHistoryActivity.this,
                R.anim.layout_anim_fall_down));

        viewModel.getMissionInfoResult().observe(this, new Observer<MissionInfoResult>() {
            @Override
            public void onChanged(@Nullable final MissionInfoResult missionInfoResult) {
                if(missionInfoResult == null)
                    return;

                if(missionInfoResult.getError() != null)
                {
                    Toast.makeText(getApplicationContext(),missionInfoResult.getError(),Toast.LENGTH_SHORT).show();
                }

                if(missionInfoResult.getInfo() != null)
                {
                    MissionInfoAdapter adapter = new MissionInfoAdapter(missionInfoResult.getInfo());
                    adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                        @Override
                        public void onItemClick(View view) {
                            int pos = list.getChildAdapterPosition(view);
                            final int missionId = missionInfoResult.getInfo().get(pos).getId();
                            boolean isFuture =
                                    missionInfoResult.getInfo().get(pos).getMissionTime().after(new Date(System.currentTimeMillis()));

                            if(isFuture)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MissionHistoryActivity.this);
                                builder.setTitle("是否取消预约");
                                builder.setNegativeButton("取消",null);
                                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        viewModel.cancel(missionId);
                                    }
                                });
                                builder.create().show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"已经过了预约时间了",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    adapter.setAnimation(new SlideInRightAnimation());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    list.setLayoutManager(linearLayoutManager);
                    list.addItemDecoration(new SpaceItemDecoration(8));
                    list.setAdapter(adapter);

                }
            }
        });

        viewModel.getCancelResult().observe(this, new Observer<MissionResult>() {
            @Override
            public void onChanged(@Nullable MissionResult missionResult) {
                if(missionResult == null)
                    return;

                if(missionResult.getError() != null)
                {
                    Toast.makeText(getApplicationContext(),missionResult.getError(),Toast.LENGTH_SHORT).show();
                }

                if(missionResult.isSuccess())
                {
                    Toast.makeText(getApplicationContext(),"取消成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
    }
}
