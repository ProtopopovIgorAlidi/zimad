package com.zimad.liststabs.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zimad.liststabs.R;

/**
 * Created by igor on 09.03.18.
 */

public class DetailsFragment extends Fragment {

    public static String POSITION_TAG = "position_tag";
    public static String URL_TAG = "url_tag";
    public static String TITLE_TAG = "title_tag";
    private int position;
    private String url, title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION_TAG);
            url = getArguments().getString(URL_TAG);
            title = getArguments().getString(TITLE_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, null);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        Picasso.with(rootView.getContext())
                .load(url)
                .into((ImageView)rootView.findViewById(R.id.iv_picture));
        ((TextView)rootView.findViewById(R.id.tv_text)).setText(String.valueOf(position + 1) + "\n" + title);
    }
}
