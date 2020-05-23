package com.last.booking.ui.bookDetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.last.booking.data.BookingDetailRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.model.BookInfo;
import com.last.booking.data.model.MissionDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDetailViewModel extends ViewModel {


    private MutableLiveData<BookDetailResult> bookInfo = new MutableLiveData<>();
    private MutableLiveData<BookDetailResult> bookSuccessInfo = new MutableLiveData<>();

    private BookingDetailRepository bookingDetailRepository;
    private int officeId;
    private int userId;

    public BookDetailViewModel(BookingDetailRepository bookingDetailRepository) {
        this.bookingDetailRepository = bookingDetailRepository;
    }

    void add(int businessId, Date time)
    {
        bookingDetailRepository.add(userId, officeId, businessId, time, new RepositoryCallback<Integer>() {
            @Override
            public void success(Integer data) {
                bookSuccessInfo.postValue(new BookDetailResult(new BookDetailSuccessView()));
            }

            @Override
            public void failed(String msg) {
                bookSuccessInfo.postValue(new BookDetailResult(msg));
            }
        });
    }

    void getUsefulInfo()
    {
        bookingDetailRepository.getUsefulInfo(officeId, new RepositoryCallback<List<BookInfo>>() {
            @Override
            public void success(List<BookInfo> data) {
                List<MissionDetail> missionDetails = new ArrayList<>();
                for(BookInfo bookInfo : data)
                {
                    missionDetails.add(new MissionDetail(
                            bookInfo.getBusinessId(),
                            bookInfo.getBusinessName(),
                            bookInfo.getBusinessDesc(),
                            bookInfo.getUsefulTime()));
                }
                bookInfo.postValue(new BookDetailResult(new BookDetailView(missionDetails)));
            }

            @Override
            public void failed(String msg) {
                bookInfo.postValue(new BookDetailResult(msg));
            }
        });
    }

    public MutableLiveData<BookDetailResult> getBookSuccessInfo() {
        return bookSuccessInfo;
    }

    public MutableLiveData<BookDetailResult> getBookInfo() {
        return bookInfo;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
