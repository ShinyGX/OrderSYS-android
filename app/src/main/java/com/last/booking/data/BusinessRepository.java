package com.last.booking.data;

import com.last.booking.data.datasource.BusinessDataSource;
import com.last.booking.data.model.BusinessInfo;
import com.last.booking.network.ErrorCode;

public class BusinessRepository {

    private static volatile BusinessRepository instance;
    private BusinessDataSource dataSource;
    private BusinessRepository(BusinessDataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    private final static Object lock = new Object();
    public static BusinessRepository getInstance(BusinessDataSource dataSource)
    {
        if(instance == null)
        {
            synchronized (lock)
            {
                if(instance == null)
                    instance = new BusinessRepository(dataSource);
            }
        }
        return instance;
    }

    public void business(int id, final RepositoryCallback<BusinessInfo> callback)
    {
        dataSource.getBusinessInfo(id, new ResultCallback<BusinessInfo>() {
            @Override
            public void result(Integer code, String msg, BusinessInfo data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }
}
