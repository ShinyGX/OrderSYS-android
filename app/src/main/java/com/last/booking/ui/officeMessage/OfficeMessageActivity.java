package com.last.booking.ui.officeMessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.last.booking.R;
import com.last.booking.ui.businessDetail.BusinessDetailActivity;
import com.last.booking.ui.rule.RuleActivity;

public class OfficeMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_detail);

        final int userId = getIntent().getIntExtra("userId",-1);

        Button btn_business = findViewById(R.id.officedetail_business);
        btn_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfficeMessageActivity.this, BusinessDetailActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        Button btn_rule = findViewById(R.id.officedetail_rule);
        btn_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfficeMessageActivity.this, RuleActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
