package com.last.booking.ui.main;

import com.last.booking.data.model.MissionNoticeInfo;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NoticeResult {

    @Nullable
    private List<MissionNoticeInfo> noticeInfoList;

    @Nullable
    private String errorMsg;

    public NoticeResult(@Nullable List<MissionNoticeInfo> noticeInfoList) {
        this.noticeInfoList = noticeInfoList;
    }

    public NoticeResult(@Nullable String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Nullable
    public List<MissionNoticeInfo> getNoticeInfoList() {
        return noticeInfoList;
    }

    @Nullable
    public String getErrorMsg() {
        return errorMsg;
    }
}
