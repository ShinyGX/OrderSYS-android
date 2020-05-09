package com.last.booking.ui.bookDetail;

import com.last.booking.data.model.MissionDetail;

import java.util.List;

public class BookDetailView {
    private List<MissionDetail> missionDetailList;

    public BookDetailView(List<MissionDetail> missionDetailList) {
        this.missionDetailList = missionDetailList;
    }

    public List<MissionDetail> getMissionDetailList() {
        return missionDetailList;
    }
}
