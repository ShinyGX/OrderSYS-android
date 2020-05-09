package com.last.booking.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.last.booking.R;
import com.last.booking.data.AccessTokenKeeper;
import com.last.booking.data.model.WeiboShow;
import com.last.booking.network.Weibo;
import com.last.booking.ui.main.MainActivity;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);


        final WeiboAuth weiboAuth = new WeiboAuth(this,
                Weibo.APP_KEY,Weibo.REDIRECT_URL,Weibo.SCOPE);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        ImageButton weiboLogin = findViewById(R.id.weibo_login);
        weiboLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weiboAuth.anthorize(new WeiboAuthListener() {
                    @Override
                    public void onComplete(Bundle bundle) {
                        AccessTokenKeeper.writeAccessToken(getApplicationContext(),
                                Oauth2AccessToken.parseAccessToken(bundle));

                        Toast.makeText(getApplicationContext(),
                                AccessTokenKeeper.readAccessToken(getApplicationContext()).getUid(),
                                Toast.LENGTH_SHORT).show();

                        loginViewModel.loginByWeibo(
                                AccessTokenKeeper.readAccessToken(getApplicationContext()).getUid());
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(),
                                "取消了登陆",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        loginViewModel.getWeiboLoginResult().observe(this, new Observer<WeiboLoginResult>() {
            @Override
            public void onChanged(@Nullable WeiboLoginResult weiboLoginResult) {
                if(weiboLoginResult == null)
                    return;
                if(weiboLoginResult.getErrMsg() != null)
                    Toast.makeText(
                            getApplicationContext(),
                            weiboLoginResult.getErrMsg(),
                            Toast.LENGTH_SHORT).show();


                if(weiboLoginResult.getUserInfo() != null)
                    loginViewModel.getWeiboShow(getApplicationContext());

                if(weiboLoginResult.getWeiboShow() != null)
                {
                    WeiboShow weiboShow = weiboLoginResult.getWeiboShow();
                    HashMap<String,String> map = new HashMap<>();
                    map.put("name",weiboShow.getScreen_name());
                    map.put("icon",weiboShow.getProfile_image_url());
                    loginViewModel.reset(map);
                }
            }
        });

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getErrorMsg() != null) {
                    showLoginFailedString(loginResult.getErrorMsg());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                    //Complete and destroy login activity once successful
                    Intent intent = new Intent();
                    intent.putExtra("userId",loginResult.getSuccess().getUserId());
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                //setResult(Activity.RESULT_OK);
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());


            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }


    private void showLoginFailedString(String str)
    {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }
}
