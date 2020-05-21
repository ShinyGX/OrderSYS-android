package com.last.booking.ui.missionHistory.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.last.booking.OnRecyclerItemClickListener;
import com.last.booking.R;
import com.last.booking.data.model.MissionStatusInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MissionInfoAdapter extends RecyclerView.Adapter<MissionInfoAdapter.ViewHolder> {


    private List<MissionStatusInfo> infoList;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public MissionInfoAdapter(List<MissionStatusInfo> infoList) {
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mission_history,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.cl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecyclerItemClickListener != null)
                    onRecyclerItemClickListener.onItemClick(v);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MissionStatusInfo info = infoList.get(i);

        viewHolder.tv_name.setText(info.getBusinessName());
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 a HH:mm");
        viewHolder.tv_time.setText(simpleDateFormat.format( info.getMissionTime()));
        boolean isFuture = info.getMissionTime().after(new Date(System.currentTimeMillis()));
        boolean isDone = info.getIsDone() == 1;

        if(isFuture)
            viewHolder.tv_status.setTextColor(viewHolder.tv_status.getResources().getColor(R.color.limegreen));
        else
            viewHolder.tv_status.setTextColor(viewHolder.tv_status.getResources().getColor(R.color.sienna));

        if(!isDone)
            viewHolder.tv_status.setTextColor(viewHolder.tv_status.getResources().getColor(R.color.colorBlue));
        viewHolder.tv_status.setText(isFuture ? "已预约" : isDone? "已超时" : "已处理");

    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv_name;
        TextView tv_time;
        TextView tv_status;
        ConstraintLayout cl_item;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            cl_item = itemView.findViewById(R.id.missionhistory_item);
            tv_name = itemView.findViewById(R.id.missionhistory_business_name);
            tv_time = itemView.findViewById(R.id.missionhistory_mission_time);
            tv_status = itemView.findViewById(R.id.missionhistory_status);

        }
    }
}
