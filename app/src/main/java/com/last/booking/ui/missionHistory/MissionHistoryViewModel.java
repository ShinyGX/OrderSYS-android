package com.last.booking.ui.missionHistory;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.last.booking.data.MissionHistoryRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.Userdata;
import com.last.booking.data.model.MissionStatusInfo;

import java.util.List;

public class MissionHistoryViewModel extends ViewModel {

    private MissionHistoryRepository repository;

    private MutableLiveData<MissionInfoResult> missionInfoResult = new MutableLiveData<>();
    private MutableLiveData<MissionResult> cancelResult = new MutableLiveData<>();

    public MissionHistoryViewModel(MissionHistoryRepository repository) {
        this.repository = repository;
    }

    public void cancel(int id)
    {
        repository.cancel(id, new RepositoryCallback<String>() {
            @Override
            public void success(String data) {
                getMissionInfo();
                cancelResult.postValue(new MissionResult(true));
            }

            @Override
            public void failed(String msg) {
                cancelResult.postValue(new MissionResult(msg));
            }
        });
    }


    public void getMissionInfo()
    {
        repository.getMissionInfo(Userdata.getInstance().getUserInfo().getUserId(),
                new RepositoryCallback<List<MissionStatusInfo>>() {
                    @Override
                    public void success(List<MissionStatusInfo> data) {
                        missionInfoResult.postValue(new MissionInfoResult(data));
                    }

                    @Override
                    public void failed(String msg) {
                        missionInfoResult.postValue(new MissionInfoResult(msg));
                    }
                });
    }

    public MutableLiveData<MissionResult> getCancelResult() {
        return cancelResult;
    }

    public MutableLiveData<MissionInfoResult> getMissionInfoResult() {
        return missionInfoResult;
    }
}
