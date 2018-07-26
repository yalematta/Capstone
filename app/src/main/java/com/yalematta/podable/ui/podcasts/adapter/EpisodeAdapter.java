package com.yalematta.podable.ui.podcasts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Episode;
import com.yalematta.podable.data.models.podcast.Podcast;
import com.yalematta.podable.ui.podcasts.episodes.PodcastEpisodesContract;
import com.yalematta.podable.util.progress_pie.ProgressBarAnimation;
import com.yalematta.podable.util.progress_pie.ProgressPie;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 7/9/18.
 */

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private Context context;
    private Podcast podcast;
    private Interpolator interpolator;
    private List<Episode> list = new ArrayList<>();
    private PodcastEpisodesContract.onEpisodeClickListener episodeClickListener;

    public EpisodeAdapter(Context context, Podcast podcast, PodcastEpisodesContract.onEpisodeClickListener episodeClickListener){
        this.context = context;
        this.podcast = podcast;
        this.list = podcast.getEpisodes();
        this.episodeClickListener = episodeClickListener;
    }

    @Override
    public EpisodeAdapter.EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item,parent,false);
        EpisodeAdapter.EpisodeViewHolder episodeViewHolder = new EpisodeAdapter.EpisodeViewHolder(view);
        return episodeViewHolder;
    }

    @Override
    public void onBindViewHolder(final EpisodeAdapter.EpisodeViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getTitle());

        // This is to parse your current date string
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        String pub = list.get(position).getPubdate();
        Date pubDate = null; // Handle the ParseException here
        try {
            pubDate = sdf.parse(pub);
            Calendar c = Calendar.getInstance();
            c.setTime(pubDate);
            String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            String month = new SimpleDateFormat("MMM").format(c.getTime());
            holder.tvDay.setText(day);
            holder.tvMonth.setText(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        interpolator = new LinearInterpolator();

        holder.ibDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                episodeClickListener.onEpisodeClick(list.get(position), podcast.getSlug());
                holder.progressPie.setVisibility(View.VISIBLE);
                holder.ibDownload.setVisibility(View.GONE);

                String percentageStr = "100";
                Integer percent = percentageStr.isEmpty() ? 0 : Integer.valueOf(percentageStr);
                ProgressBarAnimation animation = new ProgressBarAnimation(holder.progressPie,
                        0, percent > 100 ? 100 : percent);
                animation.setDuration(2000);
                animation.setInterpolator(interpolator);
                holder.progressPie.startAnimation(animation);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.day) TextView tvDay;
        @BindView(R.id.title) TextView tvTitle;
        @BindView(R.id.month) TextView tvMonth;
        @BindView(R.id.download) ImageView ibDownload;
        @BindView(R.id.progress_pie) ProgressPie progressPie;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}