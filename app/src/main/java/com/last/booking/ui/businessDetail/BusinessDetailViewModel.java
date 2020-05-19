package com.last.booking.ui.businessDetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.last.booking.data.BusinessDetailRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.model.BusinessInfo;

import java.util.List;

public class BusinessDetailViewModel extends ViewModel {


    private MutableLiveData<BusinessInfoResult> businessInfoResult = new MutableLiveData<>();

    private BusinessDetailRepository repository;
    public BusinessDetailViewModel(BusinessDetailRepository repository)
    {
        this.repository = repository;
    }


    public void getAllBusiness()
    {
        repository.getAllBusinessInfo(new RepositoryCallback<List<BusinessInfo>>() {
            @Override
            public void success(List<BusinessInfo> data) {
                businessInfoResult.postValue(new BusinessInfoResult(data));
            }

            @Override
            public void failed(String msg) {
                businessInfoResult.postValue(new BusinessInfoResult(msg));
            }
        });
    }

    public MutableLiveData<BusinessInfoResult> getBusinessInfoResult() {
        return businessInfoResult;
    }
}
