package com.last.booking.ui.bookDetail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.last.booking.AnimatedExpandableListView;
import com.last.booking.R;
import com.last.booking.data.model.MissionDetail;

import java.text.SimpleDateFormat;
import java.util.List;

public class MissionDetailExpandableAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private List<MissionDetail> info;
    private Context context;
    private SimpleDateFormat sdf;
    private OnExpandItemClick onItemClick;

    public MissionDetailExpandableAdapter(List<MissionDetail> info, Context context) {
        this.info = info;
        this.context = context;

        sdf = new SimpleDateFormat("MM月dd日 a");
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book_time_list,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tv_message = convertView.findViewById(R.id.bookdetail_text);
            childViewHolder.ib_next = convertView.findViewById(R.id.bookdetail_next);
            convertView.setTag(childViewHolder);
        }
        else
        {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }


        String text = sdf.format(info.get(groupPosition).getOrderList().get(childPosition));
        Integer person = info.get(groupPosition).getTime().get(info.get(groupPosition).getOrderList().get(childPosition));

        if(person == null || person == -1)
        {
            text += "(今日休息)";
            childViewHolder.tv_message.setTextColor(
                    childViewHolder.tv_message.getResources().getColor(R.color.dimgrey));
        }
        else
        {
            text += "(当前预约人数：" + person + ")";
            if(person < 10)
                childViewHolder.tv_message.setTextColor(
                        childViewHolder.tv_message.getResources().getColor(R.color.forestgreen));
            else if(person < 50)
                childViewHolder.tv_message.setTextColor(
                        childViewHolder.tv_message.getResources().getColor(R.color.darkorange));
            else
                childViewHolder.tv_message.setTextColor(
                        childViewHolder.tv_message.getResources().getColor(R.color.orangered));
        }

        childViewHolder.tv_message.setText(text);

        if(onItemClick != null) {
            ExpandableItemClickListener listener = new ExpandableItemClickListener();
            listener.onItemClick = this.onItemClick;
            listener.childPos = childPosition;
            listener.parentPos = groupPosition;
            listener.missionDetail = info.get(groupPosition);

            childViewHolder.ib_next.setOnClickListener(listener);
        }



        return convertView;
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
        ParentViewHolder parentViewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book_detail_parent,parent,false);
            parentViewHolder = new ParentViewHolder();
            parentViewHolder.tv_title = convertView.findViewById(R.id.bookdetail_title);
            convertView.setTag(parentViewHolder);
        }
        else
        {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }

        parentViewHolder.tv_title.setText(info.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setOnItemClick(OnExpandItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    static class ParentViewHolder
    {
        TextView tv_title;
    }

    static class ChildViewHolder
    {
        TextView tv_message;
        ImageButton ib_next;
    }



    class ExpandableItemClickListener implements View.OnClickListener
    {

        int parentPos,childPos;
        OnExpandItemClick onItemClick;
        MissionDetail missionDetail;


        @Override
        public void onClick(View v) {
            boolean isRelease = false;
            if(missionDetail.getTime() != null)
            {
                Integer i = missionDetail.getTime().get(missionDetail.getOrderList().get(childPos));
                if( i == null || i == -1)
                    isRelease = true;
            }

            onItemClick.itemClick(parentPos,childPos,missionDetail,isRelease);
        }
    }
}


