package com.last.booking.ui.bookDetail.adapter;

import com.last.booking.data.model.MissionDetail;

public interface OnExpandItemClick {
    void itemClick(int parentPos, int childPos, MissionDetail info,boolean isRelease);
}
