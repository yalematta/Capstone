package com.yalematta.earcasts.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yalematta.earcasts.ui.main.featured.FeaturedPresenterImpl;
import com.yalematta.earcasts.ui.main.featured.FeaturedTabFragment;

/**
 * Created by yalematta on 6/16/18.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_NUMBER = 2;

    private FeaturedPresenterImpl mFeaturedPresenter;

    public TabPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch ( position ) {
            case 0: {
                FeaturedTabFragment featuredTabFragment = FeaturedTabFragment.newInstance();
                mFeaturedPresenter = new FeaturedPresenterImpl(featuredTabFragment);

                return featuredTabFragment;
            }

            case 1: {
                FeaturedTabFragment featuredTabFragment = FeaturedTabFragment.newInstance();
                mFeaturedPresenter = new FeaturedPresenterImpl(featuredTabFragment);

                return featuredTabFragment;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch ( position ) {
            case 0:
                return "Featured";
            case 1:
                return "Categories";
            default:
                return "Default";
        }
    }
}
