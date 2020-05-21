package com.last.booking.ui.missionHistory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.last.booking.data.MissionHistoryRepository;
import com.last.booking.data.datasource.MissionDataSource;

public class MissionHistoryViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MissionHistoryViewModel.class))
        {
            return (T) new MissionHistoryViewModel(
                    MissionHistoryRepository.getInstance(new MissionDataSource()));
        }else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
