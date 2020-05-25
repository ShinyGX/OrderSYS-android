package com.last.booking.ui.booking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.last.booking.AnimatedExpandableListView;
import com.last.booking.R;
import com.last.booking.data.model.OfficeDetail;

import java.util.List;

public class OfficeInfoExpandableListViewAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {


    private List<String> group;
    private List<List<OfficeDetail>> child;
    private Context context;

    public OfficeInfoExpandableListViewAdapter(List<String> group, List<List<OfficeDetail>> child, Context context) {
        this.group = group;
        this.child = child;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return child.get(groupPosition).size();
//    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
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
        GroupViewHolder groupViewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_office_area_info,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tv_title = convertView.findViewById(R.id.office_area_);
            //groupViewHolder.iv_arrow = convertView.findViewById(R.id.item_area_arrow_);
            convertView.setTag(groupViewHolder);
        }
        else
        {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.tv_title.setText(group.get(groupPosition));

//        if(isExpanded)
//            groupViewHolder.iv_arrow.setImageResource(R.drawable.arrow_down);
//        else
//            groupViewHolder.iv_arrow.setImageResource(R.drawable.arrow_left);

        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_offcie_detail_info,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tv_title = convertView.findViewById(R.id.office_detail);
            childViewHolder.tv_address = convertView.findViewById(R.id.office_address);
            convertView.setTag(childViewHolder);
        }
        else
            childViewHolder = (ChildViewHolder) convertView.getTag();


        childViewHolder.tv_title.setText(child.get(groupPosition).get(childPosition).getDesc());
        childViewHolder.tv_address.setText(child.get(groupPosition).get(childPosition).getAddress());
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        ChildViewHolder childViewHolder;
//        if(convertView == null)
//        {
//            convertView = LayoutInflater.from(context).inflate(R.layout.item_offcie_detail_info,parent,false);
//            childViewHolder = new ChildViewHolder();
//            childViewHolder.tv_title = convertView.findViewById(R.id.office_detail);
//            childViewHolder.tv_address = convertView.findViewById(R.id.office_address);
//            convertView.setTag(childViewHolder);
//        }
//        else
//            childViewHolder = (ChildViewHolder) convertView.getTag();
//
//
//        childViewHolder.tv_title.setText(child.get(groupPosition).get(childPosition).getDesc());
//        childViewHolder.tv_address.setText(child.get(groupPosition).get(childPosition).getAddress());
//        return convertView;
//    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder
    {
        TextView tv_title;
        ImageView iv_arrow;
    }

    static class ChildViewHolder
    {
        TextView tv_title;
        TextView tv_address;
    }


}
