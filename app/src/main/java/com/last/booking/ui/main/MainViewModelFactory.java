package com.last.booking.ui.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.last.booking.data.MainRepository;
import com.last.booking.data.datasource.FileDataSource;
import com.last.booking.data.datasource.UserDataSource;

public class MainViewModelFactory implements ViewModelProvider.Factory {


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MainViewModel.class))
        {
            return (T) new MainViewModel(MainRepository.getInstance(
                    new FileDataSource(),new UserDataSource()));
        }else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
