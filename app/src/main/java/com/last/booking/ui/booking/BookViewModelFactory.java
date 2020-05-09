package com.last.booking.ui.booking;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.last.booking.data.datasource.BookingDataSource;
import com.last.booking.data.BookingRepository;

public class BookViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(BookingViewModel.class))
        {
            return (T) new BookingViewModel(BookingRepository.getInstance(new BookingDataSource()));
        }
        else
            throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
