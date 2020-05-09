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

import java.text.SimpleDateFormat;
import java.util.Date;

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

        final TextView tv_businessName = findViewById(R.id.business_id);
        final TextView tv_businessDesc = findViewById(R.id.business_desc);
        TextView tv_time = findViewById(R.id.order_time);
        Button btn_order = findViewById(R.id.make_sure);
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
                    Toast.makeText(getApplicationContext(),
                            missionResult.getData(),Toast.LENGTH_SHORT).show();

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
