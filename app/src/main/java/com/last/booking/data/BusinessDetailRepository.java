package com.last.booking.data;

import com.last.booking.data.datasource.BusinessDataSource;
import com.last.booking.data.model.BusinessInfo;
import com.last.booking.network.ErrorCode;

import java.util.List;

public class BusinessDetailRepository {

    private static volatile BusinessDetailRepository mInstance;
    private BusinessDataSource dataSource;
    private BusinessDetailRepository(BusinessDataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    public static final Object lock = new Object();
    public static BusinessDetailRepository getInstance(BusinessDataSource dataSource)
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new BusinessDetailRepository(dataSource);
            }
        }

        return mInstance;
    }

    public void getAllBusinessInfo(final RepositoryCallback<List<BusinessInfo>> callback)
    {
        dataSource.getAllBusinessInfo(new ResultCallback<List<BusinessInfo>>() {
            @Override
            public void result(Integer code, String msg, List<BusinessInfo> data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }

}
