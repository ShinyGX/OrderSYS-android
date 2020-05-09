package com.last.booking.ui.booking;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.last.booking.data.BookingRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.model.CityInfo;
import com.last.booking.data.model.OfficeInfo;

import java.util.List;

public class BookingViewModel extends ViewModel {

    private MutableLiveData<BookingResult> cityInfo = new MutableLiveData<>();
    private MutableLiveData<BookingResult> officeInfo = new MutableLiveData<>();

    private BookingRepository bookingRepository;
    private int userId;

    BookingViewModel(BookingRepository repository)
    {
        bookingRepository = repository;
    }

    public void getCity()
    {
        bookingRepository.getCityInfo(new RepositoryCallback<List<CityInfo>>() {
            @Override
            public void success(List<CityInfo> data) {
                cityInfo.postValue(new BookingResult(new CityInfoView(data)));
            }

            @Override
            public void failed(String msg) {
                cityInfo.postValue(new BookingResult(msg));
            }
        });
    }

    public void getArea(int cityId)
    {
        bookingRepository.getCityOfficesInfo(cityId, new RepositoryCallback<List<OfficeInfo>>() {
            @Override
            public void success(List<OfficeInfo> data) {
                officeInfo.postValue(new BookingResult(new AreaInfoView(data)));
            }

            @Override
            public void failed(String msg) {
                officeInfo.postValue(new BookingResult(msg));
            }
        });
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public MutableLiveData<BookingResult> getOfficeInfo() {
        return officeInfo;
    }

    public MutableLiveData<BookingResult> getCityInfo() {
        return cityInfo;
    }
}
