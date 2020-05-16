package com.last.booking.ui.newInformation;

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

public class NewInformationActivity extends AppCompatActivity {

    private NewInformationViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_information);

        viewModel = ViewModelProviders.of(this,new NewInformationViewModelFactory())
                .get(NewInformationViewModel.class);


        final EditText et_oldPwd = findViewById(R.id.newinformation_oldPwd);
        final EditText et_newPwd = findViewById(R.id.newinformation_newPwd);
        final EditText et_repeatPwd = findViewById(R.id.newinformation_newPwdRepeat);

        final Button btn_commit = findViewById(R.id.newinformation_commit);

        viewModel.getResetResult().observe(this, new Observer<ResetResult>() {
            @Override
            public void onChanged(@Nullable ResetResult resetResult) {
                if(resetResult == null)
                    return;

                if(resetResult.getErrorMsg() != null)
                {
                    Toast.makeText(getApplicationContext(),resetResult.getErrorMsg(),Toast.LENGTH_SHORT).show();
                }

                if(resetResult.getUserdata() != null)
                {
                    finish();
                }
            }
        });


        viewModel.getRepeatPwdResult().observe(this, new Observer<NewPwdResult>() {
            @Override
            public void onChanged(@Nullable NewPwdResult newPwdResult) {
                if(newPwdResult == null)
                    return;

                if(newPwdResult.getError() != null)
                {
                    et_repeatPwd.setError(newPwdResult.getError());
                }

                btn_commit.setEnabled(newPwdResult.isDataValid());
            }
        });

        viewModel.getNewPwdResult().observe(this, new Observer<NewPwdResult>() {
            @Override
            public void onChanged(@Nullable NewPwdResult newPwdResult) {
                if(newPwdResult == null)
                    return;

                if(newPwdResult.getError() != null)
                {
                    et_newPwd.setError(newPwdResult.getError());
                }
            }
        });

        viewModel.getOldPwdResult().observe(this, new Observer<OldPwdResult>() {
            @Override
            public void onChanged(@Nullable OldPwdResult oldPwdResult) {
                if(oldPwdResult == null)
                    return;

                if(oldPwdResult.getError() != null)
                {
                    et_oldPwd.setError(oldPwdResult.getError());
                }
            }
        });

        et_oldPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.oldPwd(s.toString());
            }
        });

        et_newPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.newPwd(s.toString());
            }
        });

        et_repeatPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.repeatPwd(s.toString());
            }
        });

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.reset();
            }
        });

    }
}
