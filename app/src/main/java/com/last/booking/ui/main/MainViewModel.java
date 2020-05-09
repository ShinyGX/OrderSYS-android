package com.last.booking.ui.main;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.Fragment;

public class MainViewModel extends ViewModel {

    private Fragment[] mFragment = null;
    private int userId;

    MainViewModel()
    {
        mFragment = DataGenerator.getFragmentList();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Fragment getFragment(int index)
    {
        return mFragment[index];
    }
}
