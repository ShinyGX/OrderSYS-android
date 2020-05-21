package com.last.booking.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.last.booking.R;
import com.last.booking.ui.booking.BookingActivity;
import com.last.booking.ui.businessDetail.BusinessDetailActivity;
import com.last.booking.ui.missionHistory.MissionHistoryActivity;

public class BusinessFragment extends Fragment {

    int userId = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,null);

        userId = getArguments() != null ? getArguments().getInt("userId", -1) : -1;

        ImageButton ib_bookingOnline = view.findViewById(R.id.booking_online);
        ib_bookingOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpOtherActivity(BookingActivity.class);
            }
        });

        ImageButton ib_businessCheck = view.findViewById(R.id.business_check);
        ib_businessCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpOtherActivity(BusinessDetailActivity.class);
            }
        });
        ImageButton ib_messageRealtime = view.findViewById(R.id.mine_message_realtime);
        ib_messageRealtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpOtherActivity(MissionHistoryActivity.class);
            }
        });
        return view;
    }

    private void jumpOtherActivity(Class<? extends Activity> cls)
    {
        Intent intent = new Intent(getActivity(),cls);
        intent.putExtra("userId",userId);
        startActivity(intent);
    }
}
