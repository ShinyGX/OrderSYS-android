package com.last.booking.ui.newInformation;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.last.booking.data.NewInformationRepository;
import com.last.booking.data.datasource.UserDataSource;

public class NewInformationViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewInformationViewModel.class)) {
            return (T) new NewInformationViewModel(
                    NewInformationRepository.getInstance(new UserDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
