package com.last.booking.ui.register;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.last.booking.ui.main.MainActivity;
import com.last.booking.uitl.ITimer;
import com.last.booking.uitl.TimeCount;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerViewModel = ViewModelProviders.of(this,new RegisterViewModelFactory())
                .get(RegisterViewModel.class);

        final EditText et_phone = findViewById(R.id.register_phone);
        final EditText et_pwd = findViewById(R.id.register_password);
        final EditText et_repeat_pwd = findViewById(R.id.register_repeat_pwd);
        final EditText et_code = findViewById(R.id.register_code);
        final EditText et_username = findViewById(R.id.register_username);

        final Button btn_getCode = findViewById(R.id.register_get_code);
        final Button btn_commit = findViewById(R.id.register_commit);

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

        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.usernameDataChange(s.toString());
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
                registerViewModel.codeDataChange(s.toString());
            }
        });

        et_repeat_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.repeatPwdDataChange(s.toString());
            }
        });

        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.pwdDataChange(s.toString());
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
                registerViewModel.phoneDataChange(s.toString());
            }
        });

        btn_getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerViewModel.getCode();
                timeCount.start();
            }
        });

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerViewModel.register();
            }
        });

        registerViewModel.getRegisterResult().observe(this, new Observer<RegisterResult>() {
            @Override
            public void onChanged(@Nullable RegisterResult registerResult) {
                if(registerResult == null)
                    return;

                if(registerResult.getError() != null)
                {
                    Toast.makeText(getApplicationContext(),
                            registerResult.getError(),Toast.LENGTH_SHORT).show();
                }

                if(registerResult.getUserInfo() != null)
                {
                    Intent intent = new Intent();
                    intent.putExtra("userId",registerResult.getUserInfo().getUserId());
                    intent.setClass(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        registerViewModel.getUsernameState().observe(this, new Observer<UsernameFormState>() {
            @Override
            public void onChanged(@Nullable UsernameFormState usernameFormState) {
                if(usernameFormState == null)
                    return;

                if(usernameFormState.getUsernameError() != null)
                {
                    et_username.setError(usernameFormState.getUsernameError());
                }
            }
        });


        registerViewModel.getCodeState().observe(this, new Observer<CodeFormState>() {
            @Override
            public void onChanged(@Nullable CodeFormState codeFormState) {
                if(codeFormState == null)
                    return;

                if(codeFormState.getCodeError() != null)
                {
                    et_code.setError(codeFormState.getCodeError());
                }

                btn_commit.setEnabled(codeFormState.isDataValid());
            }
        });

        registerViewModel.getPhoneState().observe(this, new Observer<PhoneFormState>() {
            @Override
            public void onChanged(@Nullable PhoneFormState phoneFormState) {
                if(phoneFormState == null)
                    return;

                if(phoneFormState.getPhoneError() != null)
                {
                    et_phone.setError(phoneFormState.getPhoneError());
                }

                btn_getCode.setEnabled(phoneFormState.isDataValid());
            }
        });

        registerViewModel.getPwdState().observe(this, new Observer<PasswordFormState>() {
            @Override
            public void onChanged(@Nullable PasswordFormState passwordFormState) {
                if (passwordFormState == null)
                    return;

                if(passwordFormState.getPwdError() != null)
                {
                    et_pwd.setError(passwordFormState.getPwdError());
                }
            }
        });

        registerViewModel.getRepeatPwdState().observe(this, new Observer<RepeatPwdFormState>() {
            @Override
            public void onChanged(@Nullable RepeatPwdFormState repeatPwdFormState) {
                if (repeatPwdFormState == null)
                    return;

                if(repeatPwdFormState.getRepeatPwdError() != null)
                {
                    et_repeat_pwd.setError(repeatPwdFormState.getRepeatPwdError());
                }
            }
        });

    }


}
