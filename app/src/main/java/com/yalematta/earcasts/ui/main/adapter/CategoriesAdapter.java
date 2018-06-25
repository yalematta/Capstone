package com.yalematta.earcasts.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.category.Category;
import com.yalematta.earcasts.data.models.podcast.Podcast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/17/18.
 */

public class CategoriesAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> categoriesList;

    public CategoriesAdapter(Context context, int resource, List<String> categoriesList) {
        super(context, resource, categoriesList);
        this.context = context;
        this.categoriesList = categoriesList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}