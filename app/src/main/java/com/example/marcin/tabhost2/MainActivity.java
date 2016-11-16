package com.example.marcin.tabhost2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;
import java.util.Vector;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener {

    MaterialTabHost tabHost;
    ViewPager viewPager;
    ViewPagerAdapter androidAdapter;
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //android toolbar
        toolBar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolBar);
        this.setSupportActionBar(toolBar);

        //tab host
        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);


        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, AndroidFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, AndroidFragment2.class.getName()));

        List<String> fragments_title = new Vector<String>();
        fragments_title.add("Fragment 1");
        fragments_title.add("Fragment 2");
        //adapter view
        androidAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments,fragments_title);
        viewPager.setAdapter(androidAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int tabposition) {
                tabHost.setSelectedNavigationItem(tabposition);
            }
        });

        //for tab position
        for (int i = 0; i < androidAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(androidAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }
    }

    //tab on selected
    @Override
    public void onTabSelected(MaterialTab materialTab) {

        viewPager.setCurrentItem(materialTab.getPosition());
    }

    //tab on reselected
    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    //tab on unselected
    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    // view pager adapter
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragments;
        private List<String> mTitles;


        public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments, List<String> titles) {
            super(fragmentManager);
            mFragments = fragments;
            mTitles = titles;
        }

        public Fragment getItem(int num) {
            return mFragments.get(num);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int tabposition) {
            return mTitles.get(tabposition);
        }
    }
}