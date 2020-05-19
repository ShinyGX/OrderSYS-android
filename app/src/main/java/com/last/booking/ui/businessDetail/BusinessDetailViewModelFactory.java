package com.last.booking.ui.businessDetail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.last.booking.data.BusinessDetailRepository;
import com.last.booking.data.datasource.BusinessDataSource;

public class BusinessDetailViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BusinessDetailViewModel.class)) {
            return (T) new BusinessDetailViewModel(
                    BusinessDetailRepository.getInstance(new BusinessDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
