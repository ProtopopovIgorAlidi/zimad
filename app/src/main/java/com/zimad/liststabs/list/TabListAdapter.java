package com.zimad.liststabs.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.zimad.liststabs.R;
import com.zimad.liststabs.retrofit.ItemData;

import java.util.List;

/**
 * Created by igor on 09.03.18.
 */

public class TabListAdapter extends RecyclerView.Adapter<ListItemHolder> {

    private List<ItemData> listData;
    private OnListItemClick onListItemClick;

    public TabListAdapter(OnListItemClick onListItemClick) {
        super();
        this.onListItemClick = onListItemClick;
    }

    public void updateList(List<ItemData> newListData) {
        if (newListData == null) {
            return;
        }
        listData = newListData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ListItemHolder listItemHolder = new ListItemHolder(vItem, new OnItemClick() {
            @Override
            public void onClick(int id) {
                if (onListItemClick != null) {
                    onListItemClick.onClick(id, listData.get(id).getUrl(), listData.get(id).getTitle());
                }
            }
        });

        return listItemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Picasso.with(holder.ivPicture.getContext())
                .load(listData.get(position).getUrl())
                .into(holder.ivPicture);
        holder.tvText.setText(String.valueOf(position + 1) + "\n" + listData.get(position).getTitle());
        holder.llRoot.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
