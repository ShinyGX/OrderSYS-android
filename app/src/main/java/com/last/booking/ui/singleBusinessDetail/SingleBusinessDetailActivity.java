package com.last.booking.ui.singleBusinessDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.last.booking.R;

public class SingleBusinessDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_single_business_detail);


        String name = getIntent().getStringExtra("name");
        String detail = getIntent().getStringExtra("detail");

        TextView tv_name = findViewById(R.id.singlebusiness_detail_name);
        tv_name.setText(name);

        TextView tv_detail = findViewById(R.id.singlebusiness_detail_desc);
        tv_detail.setText(detail);

        Button btn_commit = findViewById(R.id.singlebusiness_detail_commit);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
