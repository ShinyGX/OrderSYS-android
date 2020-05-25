package com.last.booking.ui.booking;

import android.annotation.SuppressLint;

import com.last.booking.data.model.OfficeDetail;
import com.last.booking.data.model.OfficeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaInfoView {


    private List<OfficeInfo> officeInfoList;
    private Map<Integer,List<OfficeDetail>> officeDesc;
    private Map<Integer,String> areaDesc;

    @SuppressLint("UseSparseArrays")
    public AreaInfoView(List<OfficeInfo> officeInfoList) {
        this.officeInfoList = officeInfoList;
        officeDesc = new HashMap<>();
        areaDesc = new HashMap<>();
        for(OfficeInfo obj:officeInfoList)
        {
            if(officeDesc.containsKey(obj.getAreaId()))
            {
                officeDesc.get(obj.getAreaId()).add(
                        new OfficeDetail(
                                obj.getOfficeId(),
                                obj.getOfficeDesc(),
                                obj.getCityDecs() + obj.getAreaDesc() + obj.getOfficeAddressDesc()));
            }
            else
            {
                areaDesc.put(obj.getAreaId(),obj.getAreaDesc());
                officeDesc.put(obj.getAreaId(),new ArrayList<OfficeDetail>());
                officeDesc.get(obj.getAreaId()).add(
                        new OfficeDetail(
                                obj.getOfficeId(),
                                obj.getOfficeDesc(),
                                obj.getCityDecs() + obj.getAreaDesc() + obj.getOfficeAddressDesc()));
            }
        }
    }

    public int getOfficeId(int pos)
    {
        return officeInfoList.get(pos).getOfficeId();
    }

    public List<String> getAreas()
    {
        return new ArrayList<>(areaDesc.values());
    }

    public List<List<OfficeDetail>> getOffices()
    {
        return new ArrayList<>(officeDesc.values());
    }


}
