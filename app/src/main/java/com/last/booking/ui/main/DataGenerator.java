package com.last.booking.ui.main;

import android.support.v4.app.Fragment;

import com.last.booking.R;

public class DataGenerator {

    public static final int[] tabRes = new int[] {R.drawable.main_default,R.drawable.mine_default};
    public static final int[] tabResPressed = new int[]{R.drawable.main_focus,R.drawable.mine_focus};

    public static Fragment[] getFragmentList()
    {
        Fragment[] fragments = new Fragment[2];
        fragments[0] = new BusinessFragment();
        fragments[1] = new MineFragment();
        return fragments;

    }

}
