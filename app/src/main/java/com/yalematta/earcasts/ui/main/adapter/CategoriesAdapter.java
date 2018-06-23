package com.yalematta.earcasts.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CatViewHolder> {

    private Context context;
    private HashMap<Integer, String> map = new HashMap<>();

    public CategoriesAdapter(Context context, HashMap<Integer, String> map){
        this.context = context;
        this.map = map;
    }

    @Override
    public CategoriesAdapter.CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent,false);
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.CatViewHolder holder, final int position) {
        holder.tvCategoryName.setText(map.get(position));
        holder.tvCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, map.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view) TextView tvCategoryName;

        public CatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}