package com.last.booking.ui.bookDetail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.last.booking.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MissionTimeListViewAdapter extends BaseAdapter {

    private List<Date> timeList;
    private Context context;
    private DateFormat formatTo;

    public MissionTimeListViewAdapter(List<Date> timeList, Context context) {
        this.timeList = timeList;
        this.context = context;

        formatTo = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
    }

    @Override
    public int getCount() {
        return timeList.size();
    }

    @Override
    public Object getItem(int position) {
        return timeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder childViewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book_time_list,parent,false);
            childViewHolder = new ViewHolder();
            childViewHolder.desc = convertView.findViewById(R.id.book_time_list_);
            convertView.setTag(childViewHolder);
        }
        else
            childViewHolder = (ViewHolder) convertView.getTag();

        childViewHolder.desc.setText(formatTo.format(timeList.get(position)));
        return convertView;
    }

    static class ViewHolder
    {
        TextView desc;
    }
}
