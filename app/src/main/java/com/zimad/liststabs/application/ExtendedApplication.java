package com.zimad.liststabs.application;

import android.app.Application;

import com.zimad.liststabs.fragments.DetailsFragment;
import com.zimad.liststabs.fragments.Fragments;
import com.zimad.liststabs.fragments.TabFragment;
import com.zimad.liststabs.retrofit.ItemData;
import com.zimad.liststabs.retrofit.RetrofitApi;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zimad.liststabs.fragments.Fragments.TAB_1;

/**
 * Created by igor on 09.03.18.
 */

public class ExtendedApplication extends Application {

    private static RetrofitApi retrofitApi;
    private TabFragment tab1Fragment;
    private TabFragment tab2Fragment;
    private DetailsFragment detailsFragment;
    private Fragments selectedTab = TAB_1;
    private ConcurrentHashMap<String, List<ItemData>> dataStorage = new ConcurrentHashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://kot3.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitApi = retrofit.create(RetrofitApi.class);
    }

    public static RetrofitApi getApi() {
        return retrofitApi;
    }

    public TabFragment getTab1Fragment() {
        return tab1Fragment;
    }

    public void setTab1Fragment(TabFragment tab1Fragment) {
        this.tab1Fragment = tab1Fragment;
    }

    public TabFragment getTab2Fragment() {
        return tab2Fragment;
    }

    public void setTab2Fragment(TabFragment tab2Fragment) {
        this.tab2Fragment = tab2Fragment;
    }

    public DetailsFragment getDetailsFragment() {
        return detailsFragment;
    }

    public void setDetailsFragment(DetailsFragment detailsFragment) {
        this.detailsFragment = detailsFragment;
    }

    public Fragments getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(Fragments selectedTab) {
        this.selectedTab = selectedTab;
    }

    public void dataStoragePut(String key, List<ItemData> value) {
        dataStorage.put(key, value);
    }

    public List<ItemData> dataStorageGet(String key) {
        return dataStorage.containsKey(key) ? dataStorage.get(key) : null;
    }
}
