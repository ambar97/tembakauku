package com.example.westo.Adapter;

import com.example.westo.Fragment.Panen;
import com.example.westo.Fragment.Pembibitan;
import com.example.westo.Fragment.MasaTanam;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TablayoutBudidaya extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    public TablayoutBudidaya(@NonNull FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Pembibitan();
            case 1:
                return new MasaTanam();
            case 2:
                return new Panen();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
