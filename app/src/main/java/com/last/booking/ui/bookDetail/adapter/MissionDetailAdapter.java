package com.last.booking.ui.bookDetail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.last.booking.R;
import com.last.booking.data.model.MissionDetail;

import java.util.List;

public class MissionDetailAdapter extends RecyclerView.Adapter<MissionDetailAdapter.ViewHolder> {

    private List<MissionDetail> missionDetail;
    private Context context;
    private OnItemClick onItemClick;


    public MissionDetailAdapter(List<MissionDetail> missionDetail)
    {
        this.missionDetail = missionDetail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_book_detail,viewGroup,false);
        context = v.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(missionDetail.get(i).getName());
        viewHolder.desc.setText(missionDetail.get(i).getDesc());
        viewHolder.timeList.setAdapter(new MissionTimeListViewAdapter(missionDetail.get(i).getTime(),context));
        final int index = i;
        viewHolder.timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onItemClick != null)
                    onItemClick.itemClick(index,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return missionDetail.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView desc;
        ListView timeList;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.mission_name);
            desc = itemView.findViewById(R.id.mission_desc);
            timeList = itemView.findViewById(R.id.mission_time_list);
        }
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
