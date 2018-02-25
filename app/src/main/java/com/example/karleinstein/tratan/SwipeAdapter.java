package com.example.karleinstein.tratan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by karleinstein on 1/26/2018.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=new BlankFragment();
        Bundle bundle=new Bundle();
            bundle.putInt("key_color",position);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
