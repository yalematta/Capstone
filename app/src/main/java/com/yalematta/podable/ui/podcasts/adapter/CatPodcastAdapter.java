package com.yalematta.podable.ui.podcasts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Podcast;
import com.yalematta.podable.ui.podcasts.category.CategoryPodcastsContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 7/15/18.
 */

public class CatPodcastAdapter extends RecyclerView.Adapter<CatPodcastAdapter.CatPodViewHolder> {

    private Context context;
    private List<Podcast> list = new ArrayList<>();
    private CategoryPodcastsContract.onPodcastClickListener podcastClickListener;

    public CatPodcastAdapter(Context context, List<Podcast> list, CategoryPodcastsContract.onPodcastClickListener podcastClickListener){
        this.list = list;
        this.context = context;
        this.podcastClickListener = podcastClickListener;
    }

    @Override
    public CatPodcastAdapter.CatPodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_podcast_item,parent,false);
        CatPodViewHolder catPodViewHolder = new CatPodViewHolder(view);
        return catPodViewHolder;
    }

    @Override
    public void onBindViewHolder(CatPodViewHolder holder, final int position) {
        Glide.with(context)
                .load(list.get(position).getSmallImageURL())
                .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder).fitCenter())
                .into(holder.ivPodImage);
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvEpisodes.setText(list.get(position).getEpisodeCount() + " episodes");
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                podcastClickListener.onPodcastClick(list.get(position).getId());
            }
        });
        holder.btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CatPodViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view) ImageView ivPodImage;
        @BindView(R.id.subscribe) Button btnSubscribe;
        @BindView(R.id.episodes) TextView tvEpisodes;
        @BindView(R.id.title) TextView tvTitle;
        @BindView(R.id.root) LinearLayout root;

        public CatPodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}