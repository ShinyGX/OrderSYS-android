package com.last.booking.ui.rebind;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.last.booking.data.RebindRepository;
import com.last.booking.data.datasource.SmsCodeDataSource;
import com.last.booking.data.datasource.UserDataSource;

public class RebindViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RebindViewModel.class)) {
            return (T) new RebindViewModel(RebindRepository.getInstance(new SmsCodeDataSource(),new UserDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
