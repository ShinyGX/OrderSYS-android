package com.last.booking.ui.orderFinalCheck;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.last.booking.R;
import com.last.booking.service.AlarmService;
import com.last.booking.ui.login.LoginActivity;
import com.last.booking.ui.main.MainActivity;
import com.last.booking.uitl.NotificationUtil;
import com.last.booking.uitl.NotifyObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderFinalCheckActivity extends AppCompatActivity {

    private OrderFinalCheckViewModel orderFinalCheckViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_final_check);

        orderFinalCheckViewModel = ViewModelProviders.of(this,
                new OrderFinalCheckViewModelFactory()).get(OrderFinalCheckViewModel.class);

        final int userId = getIntent().getIntExtra("userId",-1);
        final int officeId = getIntent().getIntExtra("officeId",-1);
        final int businessId = getIntent().getIntExtra("businessId",-1);
        final long time = getIntent().getLongExtra("time",-1);

        orderFinalCheckViewModel.getBusinessInfo(businessId);

        final TextView tv_businessName = findViewById(R.id.finalcheck_business_id);
        final TextView tv_businessDesc = findViewById(R.id.finalcheck_business_desc);
        TextView tv_time = findViewById(R.id.finalcheck_order_time);
        Button btn_order = findViewById(R.id.finalcheck_commit);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 a HH:mm");
        tv_time.setText(simpleDateFormat.format(new Date(time)));

        orderFinalCheckViewModel.getBusinessInfoResult().observe(this, new Observer<BusinessInfoResult>() {
            @Override
            public void onChanged(@Nullable BusinessInfoResult businessInfoResult) {
                if(businessInfoResult == null)
                    return;

                if(businessInfoResult.getErrorMsg() != null)
                {
                    Toast.makeText(getApplicationContext(),
                            businessInfoResult.getErrorMsg(),Toast.LENGTH_SHORT).show();
                }

                if(businessInfoResult.getBusinessInfo() != null)
                {
                    tv_businessDesc.setText(businessInfoResult.getBusinessInfo().getBusiness_detail());
                    tv_businessName.setText(businessInfoResult.getBusinessInfo().getBusiness_desc());
                }
            }
        });

        orderFinalCheckViewModel.getMissionResult().observe(this, new Observer<MissionResult>() {
            @Override
            public void onChanged(@Nullable MissionResult missionResult) {
                if(missionResult == null)
                    return;

                if(missionResult.getError() != null)
                {
                    Toast.makeText(getApplicationContext(),
                            "Network Error",Toast.LENGTH_SHORT).show();
                }

                if(missionResult.getData() != null)
                {

                    long now = System.currentTimeMillis();
                    int id = missionResult.getData();
                    NotifyObject obj = new NotifyObject();
                    obj.type = id;
                    obj.title = "预约成功";
                    obj.subText = "提醒流程";
                    obj.content = "类型";
                    obj.firstTime = now + 10;
                    obj.activityClass = LoginActivity.class;
                    obj.param = "";
                    obj.icon = R.drawable.icon;
                    obj.times = null;

                    @SuppressLint("UseSparseArrays") Map<Integer,NotifyObject> map = new HashMap<>();
                    map.put(obj.type,obj);

                    NotificationUtil.notifyByAlarm(getApplicationContext(),map);
                    OrderFinalCheckActivity.this.finish();
                }
            }
        });

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderFinalCheckViewModel.add(userId,officeId,businessId,new Date(time));
            }
        });


    }
}
