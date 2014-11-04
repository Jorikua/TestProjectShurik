package ru.vsevolodkaganovych.testprojectshurik;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

public class PagerAdapter extends FragmentPagerAdapter{

    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return new FragmentEven();
            case 1: return new FragmentOdd();
        }
        return new FragmentEven();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0: return mContext.getString(R.string.even).toUpperCase(l);
            case 1: return mContext.getString(R.string.odd).toUpperCase(l);
        }
        return null;
    }
}
