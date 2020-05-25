package com.last.booking.data;

import com.last.booking.data.datasource.RuleDataSource;
import com.last.booking.data.model.RuleEachOfficeInfo;
import com.last.booking.network.ErrorCode;

import java.util.List;

public class RuleRepository {

    private static volatile RuleRepository mInstance;
    private RuleDataSource dataSource;
    private RuleRepository(RuleDataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    private static final Object lock = new Object();
    public static RuleRepository getInstance(RuleDataSource dataSource)
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new RuleRepository(dataSource);
            }
        }

        return mInstance;
    }


    public void getAll(final RepositoryCallback<List<RuleEachOfficeInfo>> callback)
    {
        dataSource.getAll(new ResultCallback<List<RuleEachOfficeInfo>>() {
            @Override
            public void result(Integer code, String msg, List<RuleEachOfficeInfo> data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }

}
