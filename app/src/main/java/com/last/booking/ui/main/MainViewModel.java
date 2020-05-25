package com.last.booking.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.app.Fragment;

import com.last.booking.data.MainRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.Userdata;
import com.last.booking.data.model.MissionNoticeInfo;
import com.last.booking.data.model.UserInfo;

import java.io.File;
import java.util.List;

public class MainViewModel extends ViewModel {

    private MainRepository mainRepository;

    private MutableLiveData<UploadResult> uploadResult = new MutableLiveData<>();
    private MutableLiveData<UserInfoResult> userInfoResult = new MutableLiveData<>();
    private MutableLiveData<NoticeResult> noticeResult = new MutableLiveData<>();


    public MainViewModel(MainRepository repository)
    {
        this.mainRepository = repository;
        mFragment = DataGenerator.getFragmentList();

    }

    private Fragment[] mFragment = null;
    private int userId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Fragment getFragment(int index)
    {
        return mFragment[index];
    }

    public void getUserInfo()
    {
        mainRepository.userInfo(Userdata.getInstance().getUserInfo().getUserId(),
                new RepositoryCallback<UserInfo>() {
                    @Override
                    public void success(UserInfo data) {
                        Userdata.getInstance().setUserInfo(data);
                        userInfoResult.postValue(new UserInfoResult(data));
                    }

                    @Override
                    public void failed(String msg) {
                        userInfoResult.postValue(new UserInfoResult(msg));
                    }
                });
    }

    public void getNotice()
    {
        mainRepository.getNotice(Userdata.getInstance().getUserInfo().getUserId(),
                new RepositoryCallback<List<MissionNoticeInfo>>() {
                    @Override
                    public void success(List<MissionNoticeInfo> data) {
                        noticeResult.postValue(new NoticeResult(data));
                    }

                    @Override
                    public void failed(String msg) {
                        noticeResult.postValue(new NoticeResult(msg));
                    }
                });
    }

    public void upload(int id, File file)
    {
        mainRepository.uploadIcon(id, file, new RepositoryCallback<String>() {
            @Override
            public void success(String data) {
                uploadResult.postValue(new UploadResult(true));
            }

            @Override
            public void failed(String msg) {
                uploadResult.postValue(new UploadResult(msg));
            }
        });
    }

    public MutableLiveData<NoticeResult> getNoticeResult() {
        return noticeResult;
    }

    public MutableLiveData<UserInfoResult> getUserInfoResult() {
        return userInfoResult;
    }

    public MutableLiveData<UploadResult> getUploadResult() {
        return uploadResult;
    }
}
