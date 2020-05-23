package com.last.booking.ui.orderFinalCheck;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.last.booking.data.BookingDetailRepository;
import com.last.booking.data.BusinessRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.model.BusinessInfo;

import java.util.Date;

public class OrderFinalCheckViewModel extends ViewModel {

    private MutableLiveData<BusinessInfoResult> businessInfoResult = new MutableLiveData<>();
    private MutableLiveData<MissionResult> missionResult = new MutableLiveData<>();

    private BusinessRepository businessRepository;
    private BookingDetailRepository bookingDetailRepository;

    public OrderFinalCheckViewModel(BusinessRepository businessRepository, BookingDetailRepository bookingDetailRepository) {
        this.businessRepository = businessRepository;
        this.bookingDetailRepository = bookingDetailRepository;
    }

    public void getBusinessInfo(int id)
    {
        businessRepository.business(id, new RepositoryCallback<BusinessInfo>() {
            @Override
            public void success(BusinessInfo data) {
                businessInfoResult.postValue(new BusinessInfoResult(data));
            }

            @Override
            public void failed(String msg) {
                businessInfoResult.postValue(new BusinessInfoResult(msg));
            }
        });
    }

    public void add(int userId, int officeId, int businessId, Date time)
    {
        bookingDetailRepository.add(userId, officeId, businessId, time, new RepositoryCallback<Integer>() {
            @Override
            public void success(Integer data) {
                missionResult.postValue(new MissionResult(data));
            }

            @Override
            public void failed(String msg) {
                missionResult.postValue(new MissionResult(msg));
            }
        });
    }

    public MutableLiveData<MissionResult> getMissionResult() {
        return missionResult;
    }

    public MutableLiveData<BusinessInfoResult> getBusinessInfoResult() {
        return businessInfoResult;
    }
}
