package com.zimad.liststabs.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zimad.liststabs.R;

/**
 * Created by igor on 09.03.18.
 */

public class ListItemHolder extends RecyclerView.ViewHolder {
    public LinearLayout llRoot;
    public ImageView ivPicture;
    public TextView tvText;

    public ListItemHolder(View itemView, final OnItemClick onItemClick) {
        super(itemView);
        llRoot = itemView.findViewById(R.id.ll_root);
        ivPicture = itemView.findViewById(R.id.iv_picture);
        tvText = itemView.findViewById(R.id.tv_text);
        llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClick((int)llRoot.getTag());
            }
        });
    }
}
