package com.last.booking.ui.rebind;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.last.booking.R;
import com.last.booking.uitl.ITimer;
import com.last.booking.uitl.TimeCount;

public class RebindActivity extends AppCompatActivity {

    private RebindViewModel rebindViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_phone);

        rebindViewModel = ViewModelProviders.of(this,new RebindViewModelFactory()).get(RebindViewModel.class);

        final EditText et_phone = findViewById(R.id.newphone_number);
        final EditText et_code = findViewById(R.id.newphone_code);

        final Button btn_getCode = findViewById(R.id.newphone_getCode);
        final Button btn_commit = findViewById(R.id.newphone_commit);

        final TimeCount timeCount = new TimeCount(60000,1000);
        timeCount.setiTimer(new ITimer() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                btn_getCode.setClickable(false);
                btn_getCode.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
            }

            @Override
            public void onFinish() {
                btn_getCode.setClickable(true);
                btn_getCode.setText(getString(R.string.get_code));
            }
        });

        rebindViewModel.getRebindResult().observe(this, new Observer<RebindResult>() {
            @Override
            public void onChanged(@Nullable RebindResult rebindResult) {
                if(rebindResult == null)
                    return;

                if(rebindResult.getError() != null)
                {
                    Toast.makeText(getApplicationContext(),
                            rebindResult.getError(),Toast.LENGTH_SHORT).show();
                }

                if(rebindResult.getUserInfo() != null)
                {
                    Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        rebindViewModel.getCodeResult().observe(this, new Observer<RebindResult>() {
            @Override
            public void onChanged(@Nullable RebindResult rebindResult) {
                if(rebindResult == null)
                    return;

                if(rebindResult.getError() != null)
                {
                    et_code.setError(rebindResult.getError());
                }

                btn_commit.setEnabled(rebindResult.isValid());
            }
        });

        rebindViewModel.getPhoneResult().observe(this, new Observer<RebindResult>() {
            @Override
            public void onChanged(@Nullable RebindResult rebindResult) {
                if(rebindResult == null)
                    return;


                if(rebindResult.getError() != null)
                {
                    et_phone.setError(rebindResult.getError());
                }

                btn_getCode.setEnabled(rebindResult.isValid());
            }
        });

        btn_getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rebindViewModel.getCode();
                timeCount.start();
            }
        });

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rebindViewModel.rebind();
            }
        });

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rebindViewModel.setPhone(s.toString());
            }
        });

        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rebindViewModel.setCode(s.toString());
            }
        });

    }
}
