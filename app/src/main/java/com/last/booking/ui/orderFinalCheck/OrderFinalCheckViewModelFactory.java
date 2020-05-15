package com.last.booking.ui.orderFinalCheck;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.last.booking.data.BookingDetailRepository;
import com.last.booking.data.BusinessRepository;
import com.last.booking.data.datasource.MissionDataSource;
import com.last.booking.data.datasource.BusinessDataSource;

public class OrderFinalCheckViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(OrderFinalCheckViewModel.class))
        {
            return (T) new OrderFinalCheckViewModel(
                    BusinessRepository.getInstance(new BusinessDataSource()),
                    BookingDetailRepository.getInstance(new MissionDataSource()));
        }else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}
