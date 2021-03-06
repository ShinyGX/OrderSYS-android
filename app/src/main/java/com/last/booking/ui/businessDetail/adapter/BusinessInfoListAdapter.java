package com.last.booking.ui.businessDetail.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.last.booking.OnRecyclerItemClickListener;
import com.last.booking.R;
import com.last.booking.data.model.BusinessInfo;

import java.util.List;

public class BusinessInfoListAdapter extends RecyclerView.Adapter<BusinessInfoListAdapter.ViewHolder> {


    private List<BusinessInfo> infoList;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public BusinessInfoListAdapter(List<BusinessInfo> infoList) {
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_business_detail,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.cl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecyclerItemClickListener != null)
                    onRecyclerItemClickListener.onItemClick(v);
            }
        });
        viewHolder.btn_more.setClickable(false);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_id.setText(infoList.get(i).getBusiness_id() + "");
        viewHolder.tv_name.setText(infoList.get(i).getBusiness_desc());
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        Button btn_more;
        ConstraintLayout cl_item;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.businessdeatil_id);
            tv_name = itemView.findViewById(R.id.businessdetail_name);
            btn_more = itemView.findViewById(R.id.businessdetail_more);
            cl_item = itemView.findViewById(R.id.businessdetail_item);
        }
    }
}
