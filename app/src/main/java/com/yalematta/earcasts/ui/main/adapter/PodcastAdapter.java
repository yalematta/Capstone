package com.yalematta.earcasts.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.ui.details.podcasts.PodcastBottomDialogFragment;
import com.yalematta.earcasts.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/17/18.
 */

public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.PodViewHolder> {

    private Context context;
    private List<Podcast> list = new ArrayList<>();

    public PodcastAdapter(Context context, List<Podcast> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public PodcastAdapter.PodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_item,parent,false);
        PodViewHolder podViewHolder = new PodViewHolder(view);
        return podViewHolder;
    }

    @Override
    public void onBindViewHolder(PodcastAdapter.PodViewHolder holder, final int position) {
        Glide.with(context)
                .load(list.get(position).getSmallImageURL())
                .into(holder.ivPodImage);

        holder.ivPodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, list.get(position).getTitle().toString(), Toast.LENGTH_LONG).show();
                PodcastBottomDialogFragment bottomPodcast = PodcastBottomDialogFragment.newInstance(list.get(position).getId());
                bottomPodcast.show(((MainActivity)context).getSupportFragmentManager(), PodcastBottomDialogFragment.class.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PodViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view) ImageView ivPodImage;

        public PodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}