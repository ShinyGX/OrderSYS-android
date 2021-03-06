package com.last.booking.data;

import com.last.booking.data.datasource.MissionDataSource;
import com.last.booking.data.model.BookInfo;
import com.last.booking.data.model.MissionAddResult;
import com.last.booking.network.ErrorCode;

import java.util.Date;
import java.util.List;

public class BookingDetailRepository {
    private static volatile BookingDetailRepository instance;
    private MissionDataSource bookingDetailDataSource;

    private BookingDetailRepository(MissionDataSource dataSource)
    {
        this.bookingDetailDataSource = dataSource;
    }

    private static final Object lock = new Object();
    public static BookingDetailRepository getInstance(MissionDataSource dataSource) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null)
                    instance = new BookingDetailRepository(dataSource);
            }
        }
        return instance;
    }

    public void add(int userId, int officeId, int businessId, Date time, final RepositoryCallback<MissionAddResult> callback)
    {
        bookingDetailDataSource.add(userId, officeId, businessId, time, new ResultCallback<MissionAddResult>() {
            @Override
            public void result(Integer code, String msg, MissionAddResult data) {
                sendMessage(code,msg,data,callback);
            }
        });
    }


    public void getUsefulInfo(int id, final RepositoryCallback<List<BookInfo>> callback)
    {
        bookingDetailDataSource.getUsefulInfo(id, new ResultCallback<List<BookInfo>>() {
            @Override
            public void result(Integer code, String msg, List<BookInfo> data) {
                sendMessage(code,msg,data,callback);
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
