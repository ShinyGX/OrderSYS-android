package com.last.booking.ui.register;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.last.booking.data.SmsRepository;
import com.last.booking.data.datasource.SmsCodeDataSource;

public class RegisterViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(
                    SmsRepository.getInstance(new SmsCodeDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}

