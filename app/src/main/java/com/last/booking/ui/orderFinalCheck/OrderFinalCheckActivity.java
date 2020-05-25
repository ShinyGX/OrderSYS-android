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
import com.last.booking.SmsManager;
import com.last.booking.data.model.MissionAddResult;
import com.last.booking.ui.login.LoginActivity;
import com.last.booking.ui.missionHistory.MissionHistoryActivity;
import com.last.booking.uitl.NotificationUtil;
import com.last.booking.uitl.NotifyObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
                    MissionAddResult info = missionResult.getData();
                    String text = "亲爱的用户" + info.getUserName() + ",您成功预约了" + info.getOfficeName() +
                            "(" + info.getOfficeAddress() + ")的" + info.getBusinessName() + "业务,预约时间为:" + info.getOrderTime() +
                            "预约号为" + info.getMissionRegisterId() + ",届时请带齐办理业务所需物品前往";
                    NotifyObject obj = new NotifyObject();
                    obj.type = info.getMissionId() ;
                    obj.title = "预约成功";
                    obj.subText = "预约提醒";
                    obj.content = text;
                    obj.firstTime = now + 10;
                    obj.activityClass = MissionHistoryActivity.class;
                    obj.param = "";
                    obj.icon = R.drawable.icon;
                    obj.times = null;

                    @SuppressLint("UseSparseArrays") Map<Integer,NotifyObject> map = new HashMap<>();
                    map.put(obj.type,obj);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date(time));
                    calendar.add(Calendar.HOUR_OF_DAY,-1);
                    NotifyObject obj2 = new NotifyObject();
                    obj2.type = info.getMissionId() * 1000;
                    obj2.title = "您预约的" + info.getBusinessName() + "即将开始";
                    obj2.subText = "预约提醒";
                    obj2.content = "您预约的" + info.getBusinessName() + "(预约号:" + info.getMissionRegisterId() + ")" +
                            ",地址为" + info.getOfficeAddress() + ",即将开始业务，请届时准时到达，若到达后以过号，可向前台咨询";
                    obj2.firstTime = calendar.getTimeInMillis();
                    obj2.activityClass = LoginActivity.class;
                    obj2.param = "";
                    obj2.icon = R.drawable.icon;
                    obj2.times = null;

                    map.put(obj2.type,obj2);

                    @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    NotificationUtil.notifyByAlarm(getApplicationContext(),map);
                    SmsManager.getInstance().sendMessage("[OrderSYS] " + text);
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
