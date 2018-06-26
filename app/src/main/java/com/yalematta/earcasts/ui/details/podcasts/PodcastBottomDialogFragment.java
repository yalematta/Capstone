package com.yalematta.earcasts.ui.details.podcasts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yalematta.earcasts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/26/18.
 */

public class PodcastBottomDialogFragment extends BottomSheetDialogFragment {

    @BindView(R.id.image) ImageView ivLogo;
    @BindView(R.id.title) TextView tvTitle;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.description) TextView tvDescription;

    public static PodcastBottomDialogFragment newInstance() {
        return new PodcastBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.podcast_bottom_sheet, container, false);

        ButterKnife.bind(this, view);

        return view;

    }
}
