package com.yalematta.earcasts.ui.details.podcasts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.podcast.Podcast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/26/18.
 */

public class PodcastBottomDialogFragment extends BottomSheetDialogFragment implements PodcastBottomDialogContract.View {

    private int mPodcastId;
    private static final String PODCAST_ID = "PODCAST_ID";
    private PodcastBottomDialogContract.Presenter mPresenter;

    @BindView(R.id.image)
    ImageView ivLogo;
    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.description)
    TextView tvDescription;

    public static PodcastBottomDialogFragment newInstance(int podcastId) {
        PodcastBottomDialogFragment fragment = new PodcastBottomDialogFragment();
        Bundle args = new Bundle();
        args.putInt(PODCAST_ID, podcastId);
        fragment.setArguments(args);
        return fragment;
    }

    private int getPodcastId() {
        if (getArguments() != null) {
            return getArguments().getInt(PODCAST_ID);
        } else {
            return 0;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.podcast_bottom_sheet, container, false);
        ButterKnife.bind(this, view);
        if (getPodcastId() != 0) {
            mPodcastId = getPodcastId();
            mPresenter.getPodcastData(mPodcastId);
        }
        return view;
    }

    @Override
    public void setPresenter(PodcastBottomDialogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void ShowToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataSuccess(String message, Podcast podcast) {
        tvTitle.setText(podcast.getTitle());
        tvDescription.setText(podcast.getDescription());

        Glide.with(getActivity())
                .load(podcast.getSmallImageURL())
                .into(ivLogo);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }
}
