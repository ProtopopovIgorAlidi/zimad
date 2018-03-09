package com.zimad.liststabs.activities;

import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zimad.liststabs.R;
import com.zimad.liststabs.application.ExtendedApplication;
import com.zimad.liststabs.fragments.DetailsFragment;
import com.zimad.liststabs.fragments.Fragments;
import com.zimad.liststabs.fragments.TabFragment;
import com.zimad.liststabs.list.OnListItemClick;

import static com.zimad.liststabs.fragments.Fragments.TAB_1;
import static com.zimad.liststabs.fragments.Fragments.TAB_2;
import static com.zimad.liststabs.fragments.Fragments.TAB_DETAILS;

public class MainActivity extends AppCompatActivity implements OnListItemClick {

    public static String TAB_1_CATEGORY = "cat";
    public static String TAB_2_CATEGORY = "dog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        if (((ExtendedApplication)getApplication()).getTab1Fragment() == null) {
            Bundle bundleTab1 = new Bundle();
            bundleTab1.putString(TabFragment.CATEGORY_TAG, TAB_1_CATEGORY);
            TabFragment tab1Fragment = new TabFragment();
            tab1Fragment.setArguments(bundleTab1);
            ((ExtendedApplication)getApplication()).setTab1Fragment(tab1Fragment);
        }

        if (((ExtendedApplication)getApplication()).getTab2Fragment() == null) {
            Bundle bundleTab2 = new Bundle();
            bundleTab2.putString(TabFragment.CATEGORY_TAG, TAB_2_CATEGORY);
            TabFragment tab2Fragment = new TabFragment();
            tab2Fragment.setArguments(bundleTab2);
            ((ExtendedApplication)getApplication()).setTab2Fragment(tab2Fragment);
        }

        TabLayout tlTabs = findViewById(R.id.tl_tabs);
        TabLayout.Tab tab = tlTabs.getTabAt(((ExtendedApplication) getApplication()).getSelectedTab() == TAB_1 ? 0 : 1);
        tab.select();

        ((TabLayout)findViewById(R.id.tl_tabs)).addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        onOpenTab(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                }
        );

        if (((ExtendedApplication)getApplication()).getDetailsFragment() != null) {
            showFragment(TAB_DETAILS);
        } else {
            showFragment(((ExtendedApplication) getApplication()).getSelectedTab());
        }
    }

    private void onOpenTab(int position) {
        switch (position) {
            case 0:
                ((ExtendedApplication)getApplication()).setSelectedTab(TAB_1);
                showFragment(TAB_1);
                break;
            case 1:
                ((ExtendedApplication)getApplication()).setSelectedTab(TAB_2);
                showFragment(TAB_2);
                break;
            default:
                return;
        }
    }

    private void showFragment(Fragments enumItem) {
        FragmentTransaction ftTransaction = getFragmentManager().beginTransaction();
        switch (enumItem) {
            case TAB_1:
                ftTransaction.replace(R.id.flListsContainer, ((ExtendedApplication)getApplication()).getTab1Fragment());
                showTabs(true);
                break;
            case TAB_2:
                ftTransaction.replace(R.id.flListsContainer, ((ExtendedApplication)getApplication()).getTab2Fragment());
                showTabs(true);
                break;
            case TAB_DETAILS:
                ftTransaction.replace(R.id.flListsContainer, ((ExtendedApplication)getApplication()).getDetailsFragment());
                showTabs(false);
                break;
            default:
                return;
        }
        ftTransaction.commit();
    }

    @Override
    public void onClick(int position, String url, String title) {
        showDetails(position, url, title);
    }

    private void showDetails(int position, String url, String title) {
        Bundle bundleDetails = new Bundle();
        bundleDetails.putInt(DetailsFragment.POSITION_TAG, position);
        bundleDetails.putString(DetailsFragment.URL_TAG, url);
        bundleDetails.putString(DetailsFragment.TITLE_TAG, title);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundleDetails);
        ((ExtendedApplication)getApplication()).setDetailsFragment(detailsFragment);

        showFragment(TAB_DETAILS);
    }

    private void hideDetails() {
        showFragment(((ExtendedApplication) getApplication()).getSelectedTab());
        ((ExtendedApplication)getApplication()).setDetailsFragment(null);
    }

    public void showTabs(boolean show) {
        findViewById(R.id.tl_tabs).setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (((ExtendedApplication)getApplication()).getDetailsFragment() != null) {
            hideDetails();
        } else {
            super.onBackPressed();
        }
    }
}
