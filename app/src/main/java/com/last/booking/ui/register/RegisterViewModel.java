package com.last.booking.ui.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.SmsRepository;
import com.last.booking.data.model.SmsCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterViewModel extends ViewModel {

    private SmsRepository smsRepository;

    private MutableLiveData<PhoneFormState> phoneState = new MutableLiveData<>();
    private MutableLiveData<PasswordFormState> pwdState = new MutableLiveData<>();
    private MutableLiveData<CodeFormState> codeState = new MutableLiveData<>();
    private MutableLiveData<RepeatPwdFormState> repeatPwdState = new MutableLiveData<>();
    private MutableLiveData<RegisterViewModel> registerState = new MutableLiveData<>();
    private MutableLiveData<UsernameFormState> usernameState = new MutableLiveData<>();

    private int code;
    private boolean hasCode = false;
    private String password;
    private String phone;
    private String username;

    public RegisterViewModel(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    public void phoneDataChange(String phone) {
        if (isPhone(phone))
        {
            phoneState.postValue(new PhoneFormState(true));
            this.phone = phone;
        }
        else
        {
            phoneState.postValue(new PhoneFormState("请输入手机号码"));
            this.phone = null;
        }
    }

    public void usernameDataChange(String name)
    {
        if(name == null || name.isEmpty())
            return;

        if(isSpecialChar(name))
        {
            usernameState.postValue(new UsernameFormState("用户名含有非法字符"));
            username = null;
        }
        else
        {
            usernameState.postValue(new UsernameFormState(true));
            username = name;
        }
    }


    public void repeatPwdDataChange(String repeatPwd)
    {
        if(password != null && password.equals(repeatPwd))
        {
            this.repeatPwdState.postValue(new RepeatPwdFormState(true));
        }
        else
        {
            this.repeatPwdState.postValue(new RepeatPwdFormState("两次输入密码不一致"));
        }
    }

    public void pwdDataChange(String pwd) {
        if (isPasswordValid(pwd))
        {
            pwdState.postValue(new PasswordFormState(true));
            password = pwd;
        }
        else
        {
            pwdState.postValue(new PasswordFormState("密码长度需要大于6位"));
            password = null;
        }

    }

    public void codeDataChange(String str)
    {
        if(str == null || str.isEmpty())
            return;
        if(isInteger(str))
        {
            int c = Integer.valueOf(str);
            if (c == code)
            {
                codeState.postValue(new CodeFormState(true));
                return;
            }
        }

        codeState.postValue(new CodeFormState("验证码错误"));
    }


    public void getCode()
    {
        if(phone == null)
        {
            phoneState.postValue(new PhoneFormState("手机号错误"));
            return;
        }

        hasCode = false;
        code = -1;

        smsRepository.getCode(phone, new RepositoryCallback<SmsCode>() {
            @Override
            public void success(SmsCode data) {
                hasCode = true;
                code = data.getCode();
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    private boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    public boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private boolean isPhone(String phone) {
        if (phone.length() != 11) {
            return false;
        } else {

            //移动号段正则表达式
            String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";

            //联通号段正则表达式
            String pat2 = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";

             //电信号段正则表达式
            String pat3 = "^((133)|(153)|(177)|(18[0,1,9])|(149))\\d{8}$";

             //虚拟运营商正则表达式
            String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

            Pattern pattern1 = Pattern.compile(pat1);
            Matcher match1 = pattern1.matcher(phone);
            boolean isMatch1 = match1.matches();
            if (isMatch1) {
                return true;
            }
            Pattern pattern2 = Pattern.compile(pat2);
            Matcher match2 = pattern2.matcher(phone);
            boolean isMatch2 = match2.matches();
            if (isMatch2) {
                return true;
            }
            Pattern pattern3 = Pattern.compile(pat3);
            Matcher match3 = pattern3.matcher(phone);
            boolean isMatch3 = match3.matches();
            if (isMatch3) {
                return true;
            }
            Pattern pattern4 = Pattern.compile(pat4);
            Matcher match4 = pattern4.matcher(phone);
            return match4.matches();


        }
    }

    public MutableLiveData<RegisterViewModel> getRegisterState() {
        return registerState;
    }

    public MutableLiveData<UsernameFormState> getUsernameState() {
        return usernameState;
    }

    public MutableLiveData<PhoneFormState> getPhoneState() {
        return phoneState;
    }

    public MutableLiveData<PasswordFormState> getPwdState() {
        return pwdState;
    }

    public MutableLiveData<CodeFormState> getCodeState() {
        return codeState;
    }

    public MutableLiveData<RepeatPwdFormState> getRepeatPwdState() {
        return repeatPwdState;
    }

    public boolean isHasCode() {
        return hasCode;
    }
}
