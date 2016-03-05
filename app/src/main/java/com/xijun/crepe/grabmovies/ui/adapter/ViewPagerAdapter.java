package com.xijun.crepe.grabmovies.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xijun.crepe.grabmovies.fragments.FavoritesFragment;
import com.xijun.crepe.grabmovies.fragments.NowPlayingFragment;
import com.xijun.crepe.grabmovies.fragments.TopRatedFragment;

/**
 * Created by LiXijun on 2016/3/4.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public final static int NUM_OF_TABS = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NowPlayingFragment nowPlayingFragment = new NowPlayingFragment();
                return nowPlayingFragment;
            case 1:
                TopRatedFragment topRatedFragment = new TopRatedFragment();
                return topRatedFragment;
            case 2:
                FavoritesFragment favoritesFragment = new FavoritesFragment();
                return favoritesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_OF_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Now Playing";
            case 1:
                return "Top Rated";
            case 2:
                return "Favourites";
            default:
                return null;
        }
    }
}
