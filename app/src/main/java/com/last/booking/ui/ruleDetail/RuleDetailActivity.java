package com.last.booking.ui.ruleDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.last.booking.R;

public class RuleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_detail);

        String name = getIntent().getStringExtra("rule_name");
        String text = getIntent().getStringExtra("rule_text");

        TextView tv_name = findViewById(R.id.ruledetail_title);
        tv_name.setText(name);

        TextView tv_text = findViewById(R.id.ruledetail_text);
        tv_text.setText(text);

        Button btn_commit = findViewById(R.id.ruledetail_commit);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
