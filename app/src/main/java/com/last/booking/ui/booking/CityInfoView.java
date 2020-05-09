package com.last.booking.ui.booking;

import com.last.booking.data.model.CityInfo;

import java.util.ArrayList;
import java.util.List;

public class CityInfoView {

    private List<CityInfo> cityInfoList;

    public CityInfoView(List<CityInfo> cityInfoList) {
        this.cityInfoList = cityInfoList;
    }

    public List<String> getCityList()
    {
        List<String> list = new ArrayList<>();
        for(CityInfo c: cityInfoList)
        {
            list.add(c.getCity_desc());
        }
        return list;
    }

    public int getCityId(int pos)
    {
        return cityInfoList.get(pos).getCity_id();
    }
}
