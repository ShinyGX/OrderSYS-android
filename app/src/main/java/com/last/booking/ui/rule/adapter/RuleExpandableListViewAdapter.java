package com.last.booking.ui.rule.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.last.booking.AnimatedExpandableListView;
import com.last.booking.R;
import com.last.booking.data.model.RuleEachOfficeInfo;
import com.last.booking.data.model.RuleInfo;

import java.util.List;

public class RuleExpandableListViewAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private List<RuleEachOfficeInfo> info;
    private Context context;

    private OnRuleItemClick onRuleItemClick;

    public RuleExpandableListViewAdapter(List<RuleEachOfficeInfo> info, Context context) {
        this.info = info;
        this.context = context;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rule_child,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.cl_item = convertView.findViewById(R.id.rule_item);
            childViewHolder.tv_name = convertView.findViewById(R.id.rule_name);
            convertView.setTag(childViewHolder);
        }
        else
            childViewHolder = (ChildViewHolder) convertView.getTag();


        childViewHolder.tv_name.setText(info.get(groupPosition).getOfficeRule().get(childPosition).getCom_rule_name());

        if(onRuleItemClick != null)
        {
            OnRuleItemClickImpl impl = new OnRuleItemClickImpl();
            impl.info = info.get(groupPosition).getOfficeRule().get(childPosition);
            impl.onRuleItemClick = this.onRuleItemClick;

            childViewHolder.cl_item.setOnClickListener(impl);
        }

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return info.get(groupPosition).getOfficeRule().size();
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
        return info.get(groupPosition).getOfficeRule().size();
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
        ParentViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rule_parent,parent,false);
            viewHolder = new ParentViewHolder();
            viewHolder.tv_title = convertView.findViewById(R.id.rule_officeName);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ParentViewHolder) convertView.getTag();


        viewHolder.tv_title.setText(info.get(groupPosition).getOfficeName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setOnRuleItemClick(OnRuleItemClick onRuleItemClick) {
        this.onRuleItemClick = onRuleItemClick;
    }

    static class ChildViewHolder
    {
        ConstraintLayout cl_item;
        TextView tv_name;
    }

    static class ParentViewHolder
    {
        TextView tv_title;
    }

    class OnRuleItemClickImpl implements View.OnClickListener{

        OnRuleItemClick onRuleItemClick;
        RuleInfo info;

        @Override
        public void onClick(View v) {
            onRuleItemClick.onClick(info);
        }
    }
}
