package com.project.usmansh.firstactivity.TabActivityWithFragments.ui;

import com.project.usmansh.firstactivity.ContainerActivityWithFragments.Fragment1;
import com.project.usmansh.firstactivity.ContainerActivityWithFragments.Fragment2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new Fragment1();
        }
        else if (position == 1)
        {
            fragment = new Fragment2();

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Chats";
        }
        else if (position == 1)
        {
            title = "Status";
        }

        return title;
    }
}