package com.last.booking.ui.rebind;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.last.booking.data.RebindRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.Userdata;
import com.last.booking.data.model.SmsCode;
import com.last.booking.data.model.UserInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RebindViewModel extends ViewModel {


    private MutableLiveData<RebindResult> phoneResult = new MutableLiveData<>();
    private MutableLiveData<RebindResult> codeResult = new MutableLiveData<>();
    private MutableLiveData<RebindResult> rebindResult = new MutableLiveData<>();


    private RebindRepository rebindRepository;

    public RebindViewModel(RebindRepository rebindRepository)
    {
        this.rebindRepository = rebindRepository;
    }

    private int code;
    private String phone;

    public void rebind()
    {
        if(phone == null || phone.isEmpty())
        {
            rebindResult.postValue(new RebindResult("请输入正确手机号"));
            return;
        }

        rebindRepository.reset(Userdata.getInstance().getUserInfo().getUserId(), phone,
                new RepositoryCallback<UserInfo>() {
                    @Override
                    public void success(UserInfo data) {
                        rebindResult.postValue(new RebindResult(data));
                        Userdata.getInstance().setUserInfo(data);
                    }

                    @Override
                    public void failed(String msg) {
                        rebindResult.postValue(new RebindResult(msg));
                    }
                });

    }

    public void setCode(String code)
    {
        if(code == null || code.isEmpty())
            return;
        if(isInteger(code))
        {
            int c = Integer.valueOf(code);
            if (this.code == c)
            {
                codeResult.postValue(new RebindResult(true));
                return;
            }
        }

        codeResult.postValue(new RebindResult("验证码错误"));
    }


    public boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public void getCode()
    {
        if(phone == null || phone.isEmpty())
        {
            codeResult.postValue(new RebindResult("请输入手机号"));
            return;
        }


        rebindRepository.getCode(phone, new RepositoryCallback<SmsCode>() {
            @Override
            public void success(SmsCode data) {
                code = data.getCode();
                codeResult.postValue(new RebindResult(true));
            }

            @Override
            public void failed(String msg) {
                codeResult.postValue(new RebindResult(msg));
            }
        });
    }

    public void setPhone(String phone)
    {
        if(isPhone(phone))
        {
            this.phone = phone;
            phoneResult.postValue(new RebindResult(true));
        }
        else
        {
            this.phone = null;
            phoneResult.postValue(new RebindResult("请输入手机号"));
        }
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

    public MutableLiveData<RebindResult> getPhoneResult() {
        return phoneResult;
    }

    public MutableLiveData<RebindResult> getCodeResult() {
        return codeResult;
    }

    public MutableLiveData<RebindResult> getRebindResult() {
        return rebindResult;
    }
}
