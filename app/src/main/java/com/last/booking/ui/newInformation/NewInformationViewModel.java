package com.last.booking.ui.newInformation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.last.booking.data.NewInformationRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.Userdata;
import com.last.booking.data.model.UserInfo;

public class NewInformationViewModel extends ViewModel {

    private MutableLiveData<NewPwdResult> newPwdResult = new MutableLiveData<>();
    private MutableLiveData<NewPwdResult> repeatPwdResult = new MutableLiveData<>();
    private MutableLiveData<OldPwdResult> oldPwdResult = new MutableLiveData<>();
    private MutableLiveData<ResetResult> resetResult = new MutableLiveData<>();

    private NewInformationRepository newInformationRepository;

    private String password;

    public NewInformationViewModel(NewInformationRepository repository)
    {
        this.newInformationRepository = repository;
    }

    public void oldPwd(String pwd)
    {
        if(pwd.equals(Userdata.getInstance().getPwd()))
        {
            oldPwdResult.postValue(new OldPwdResult(true));
        }
        else
            oldPwdResult.postValue(new OldPwdResult("请输入旧密码"));
    }

    public void newPwd(String pwd)
    {
        if(!isPasswordValid(pwd))
        {
            newPwdResult.postValue(new NewPwdResult("请输入6位或以上密码"));
            password = null;
            return;
        }

        newPwdResult.postValue(new NewPwdResult(true));
        password = pwd;

    }

    public void repeatPwd(String pwd)
    {
        if(password == null || password.isEmpty() || !pwd.equals(password))
        {
            repeatPwdResult.postValue(new NewPwdResult("两次输入的密码不相同"));
            return;
        }

        repeatPwdResult.postValue(new NewPwdResult(true));

    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    public void reset()
    {
        if(password == null || password.isEmpty())
        {
            resetResult.postValue(new ResetResult("密码不能为空"));
            return;
        }

        newInformationRepository.reset(Userdata.getInstance().getUserInfo().getUserId(),
                password, new RepositoryCallback<UserInfo>() {
                    @Override
                    public void success(UserInfo data) {
                        resetResult.postValue(new ResetResult(data));
                        Userdata.getInstance().setUserInfo(data);
                        Userdata.getInstance().setPwd(password);
                    }

                    @Override
                    public void failed(String msg) {
                        resetResult.postValue(new ResetResult(msg));
                    }
                });
    }

    public MutableLiveData<NewPwdResult> getNewPwdResult() {
        return newPwdResult;
    }

    public MutableLiveData<NewPwdResult> getRepeatPwdResult() {
        return repeatPwdResult;
    }

    public MutableLiveData<OldPwdResult> getOldPwdResult() {
        return oldPwdResult;
    }

    public MutableLiveData<ResetResult> getResetResult() {
        return resetResult;
    }
}
