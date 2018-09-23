package com.yalematta.podable.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yalematta.podable.data.models.podcast.Podcast;

import java.util.List;

/**
 * Created by yalematta on 9/23/18.
 */

public class SearchAdapter extends ArrayAdapter {

    private Context mContext;
    private List<Podcast> mDataList;
    private int searchResultItemLayout;

    public SearchAdapter(Context context, int resource, List<Podcast> dataList) {
        super(context, resource, dataList);
        mContext = context;
        mDataList = dataList;
        searchResultItemLayout = resource;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Podcast getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(searchResultItemLayout, parent, false);
        }

        TextView resultItem = (TextView) view.findViewById(android.R.id.text1);
        resultItem.setText(getItem(position).getTitle());
        return view;
    }
}
