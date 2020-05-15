package com.last.booking.data;

import com.last.booking.data.datasource.OfficeDataSource;
import com.last.booking.data.model.CityInfo;
import com.last.booking.data.model.OfficeInfo;
import com.last.booking.network.ErrorCode;

import java.util.List;

public class BookingRepository {

    private static volatile BookingRepository instance;
    private OfficeDataSource bookingDataSource;

    private BookingRepository(OfficeDataSource dataSource)
    {
        this.bookingDataSource = dataSource;
    }

    private static final Object lock = new Object();
    public static BookingRepository getInstance(OfficeDataSource dataSource) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null)
                    instance = new BookingRepository(dataSource);
            }
        }
        return instance;
    }

    public void getCityInfo(final RepositoryCallback<List<CityInfo>> callback)
    {
        bookingDataSource.getCityInfo(new ResultCallback<List<CityInfo>>() {
                @Override
            public void result(Integer code, String msg, List<CityInfo> data) {
                sendMessage(code,msg, data,callback);
            }
        });
    }

    public void getCityOfficesInfo(int id, final RepositoryCallback<List<OfficeInfo>> callback)
    {
        bookingDataSource.getCityOfficesInfo(id, new ResultCallback<List<OfficeInfo>>() {
            @Override
            public void result(Integer code, String msg, List<OfficeInfo> data) {
                sendMessage(code,msg, data,callback);
            }
        });
    }

    private <T> void sendMessage(Integer code, String msg,T data,RepositoryCallback<T> callback)
    {
        if(code == ErrorCode.SUCCESS)
            callback.success(data);
        else
            callback.failed(msg);
    }
}
