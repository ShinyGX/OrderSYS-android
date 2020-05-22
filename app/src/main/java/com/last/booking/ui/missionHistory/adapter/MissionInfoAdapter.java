package com.last.booking.ui.missionHistory.adapter;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.last.booking.BaseRecycleAnimation;
import com.last.booking.OnRecyclerItemClickListener;
import com.last.booking.R;
import com.last.booking.data.model.MissionStatusInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;

public class MissionInfoAdapter extends RecyclerView.Adapter<MissionInfoAdapter.ViewHolder>  {


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

        viewHolder.tv_officeName.setText(info.getOfficeName());
        viewHolder.tv_officeAddress.setText(info.getOfficeAddress());


        if(animEnable)
        {
            addAnimation(viewHolder);
        }

    }

    private int lastPosition = -1;
    private int pos = 0;
    private boolean animEnable = false;
    private BaseRecycleAnimation animation;
    public void addAnimation(ViewHolder vh)
    {
        if(animEnable)
        {
            if(vh.getLayoutPosition() > lastPosition)
            {
                for(Animator animator : animation.getAnimators(vh.itemView))
                {
                    startAnim(animator);
                }
            }
            lastPosition = vh.getLayoutPosition();
        }
    }

    private void startAnim(Animator animator)
    {
        animator.setDuration(300).start();
        animator.setInterpolator(new LinearInterpolator());
    }

    public void setAnimation(BaseRecycleAnimation animation)
    {
        animEnable = true;
        this.animation = animation;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder //implements AnimateViewHolder
    {

        TextView tv_name;
        TextView tv_time;
        TextView tv_status;
        TextView tv_officeName;
        TextView tv_officeAddress;
        ConstraintLayout cl_item;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            cl_item = itemView.findViewById(R.id.missionhistory_item);
            tv_name = itemView.findViewById(R.id.missionhistory_business_name);
            tv_time = itemView.findViewById(R.id.missionhistory_mission_time);
            tv_officeAddress = itemView.findViewById(R.id.missionhistory_office_address);
            tv_officeName = itemView.findViewById(R.id.missionhistory_office_name);
            tv_status = itemView.findViewById(R.id.missionhistory_status);

        }

//        @Override
//        public void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
//            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
//            ViewCompat.setAlpha(itemView, 0);
//        }
//
//        @Override
//        public void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
//
//        }
//
//        @Override
//        public void animateAddImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
//            ViewCompat.animate(itemView)
//                    .translationY(-itemView.getHeight() * 0.3f)
//                    .alpha(0)
//                    .setDuration(300)
//                    .setListener(listener)
//                    .start();
//        }
//
//        @Override
//        public void animateRemoveImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
//            ViewCompat.animate(itemView)
//                    .translationY(-itemView.getHeight() * 0.3f)
//                    .alpha(0)
//                    .setDuration(300)
//                    .setListener(listener)
//                    .start();
//        }
    }
}
