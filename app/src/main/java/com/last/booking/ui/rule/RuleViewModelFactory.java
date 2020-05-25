package com.last.booking.ui.rule;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.last.booking.data.RuleRepository;
import com.last.booking.data.datasource.RuleDataSource;

public class RuleViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RuleViewModel.class)) {
            return (T) new RuleViewModel(
                    RuleRepository.getInstance(new RuleDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
