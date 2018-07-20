package com.yalematta.podable.ui.podcasts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Episode;
import com.yalematta.podable.ui.podcasts.episodes.PodcastEpisodesPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 7/9/18.
 */

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private Context context;
    private List<Episode> list = new ArrayList<>();
    private PodcastEpisodesPresenterImpl mPodcastEpisodesPresenter;

    public EpisodeAdapter(Context context, List<Episode> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public EpisodeAdapter.EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item,parent,false);
        EpisodeAdapter.EpisodeViewHolder episodeViewHolder = new EpisodeAdapter.EpisodeViewHolder(view);
        return episodeViewHolder;
    }

    @Override
    public void onBindViewHolder(EpisodeAdapter.EpisodeViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView tvTitle;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}