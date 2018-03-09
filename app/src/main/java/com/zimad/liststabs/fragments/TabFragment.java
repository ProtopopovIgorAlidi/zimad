package com.zimad.liststabs.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zimad.liststabs.R;
import com.zimad.liststabs.application.ExtendedApplication;
import com.zimad.liststabs.list.OnListItemClick;
import com.zimad.liststabs.list.TabListAdapter;
import com.zimad.liststabs.retrofit.ItemData;
import com.zimad.liststabs.retrofit.QueryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by igor on 09.03.18.
 */

public class TabFragment extends Fragment {

    public static String CATEGORY_TAG = "category_tag";
    private String category;
    private TabListAdapter tabAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, null);
        initList(rootView);
        return rootView;
    }

    private void initList(View rootView) {
        RecyclerView rvTabList = (RecyclerView)rootView.findViewById(R.id.rvTabList);
        rvTabList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rvTabList.setLayoutManager(layoutManager);
        tabAdapter = new TabListAdapter(new OnListItemClick(){
            @Override
            public void onClick(int position, String url, String title) {
                Activity activity = getActivity();
                if (activity != null && !activity.isFinishing() &&
                        activity instanceof OnListItemClick) {
                    ((OnListItemClick) activity).onClick(position, url, title);
                }
            }
        });
        rvTabList.setAdapter(tabAdapter);

        if (getData() != null) {
            updateList();
        } else {
            ExtendedApplication.getApi().getData(category).enqueue(
                    new Callback<QueryModel>() {
                        @Override
                        public void onResponse(Call<QueryModel> call, Response<QueryModel> response) {
                            if (response == null || response.body() == null) {
                                return;
                            }
                            Activity act = getActivity();
                            if (act != null && !act.isFinishing() ) {
                                ((ExtendedApplication)act.getApplication()).dataStoragePut(category, response.body().getData());
                                act.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateList();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<QueryModel> call, Throwable t) {
                        }
                    }
            );
        }
    }

    private void updateList() {
        tabAdapter.updateList(getData());
    }

    private List<ItemData> getData() {
        Activity act = getActivity();
        if (act == null || act.isFinishing()) {
            return null;
        }
        return ((ExtendedApplication)act.getApplication()).dataStorageGet(category);
    }
}
