package com.last.booking.ui.bookDetail.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.last.booking.AnimatedExpandableListView;
import com.last.booking.data.model.MissionDetail;

import java.util.List;

public class MissionDetailExpandableAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private List<MissionDetail> info;

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return info.get(groupPosition).getTime().size();
    }

    @Override
    public int getGroupCount() {
        return info.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return info.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return info.get(groupPosition).getTime().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class ParentViewHolder
    {
        TextView tv_title;
    }

    static class ChildViewHolder
    {
        TextView tv_meaage;
    }

}


