package com.yalematta.earcasts.ui.main.featured;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yalematta.earcasts.R;
import com.yalematta.earcasts.data.models.podcast.Podcast;
import com.yalematta.earcasts.ui.main.adapter.PodcastAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yalematta on 6/16/18.
 */

public class FeaturedTabFragment extends Fragment implements FeaturedContract.View {

    private static final String TAG = FeaturedTabFragment.class.getSimpleName();

    private FeaturedContract.Presenter mPresenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private GridLayoutManager gridLayoutManager;
    private PodcastAdapter podcastAdapter;

    public FeaturedTabFragment() {

    }

    public static FeaturedTabFragment newInstance() {
        return new FeaturedTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.featured_frag, container, false);
        ButterKnife.bind(this, root);
        int numberOfColumns = 3;
        mPresenter.getFeaturedData(30 , "en");
        gridLayoutManager = new GridLayoutManager(this.getContext(), numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);

        // TODO: Click handling for RecyclerView
        //        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(
        //                new ItemClickSupport.OnItemClickListener() {
        //                    @Override
        //                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        //                        ShowToast("clicked " + position);
        //                    }
        //                }
        //        );

        // TODO: In another fragment this would help us in the load more functionality
        //        recyclerView.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
        //            @Override
        //            protected void loadMoreItems() {
        //                isLoading = true;
        //                currentPage++;
        //                if (screenType.equalsIgnoreCase(SHOWS_DETAILS)) {
        ////                        getRelatedEpisodes();
        //                    getRelatedEpisodesLoadMore();
        //                } else {
        //                    getRelatedBulletins();
        //                }
        //            }
        //
        //            @Override
        //            public int getTotalPageCount() {
        //                return 0;
        //            }
        //
        //            @Override
        //            public boolean isLastPage() {
        //                return isLastPage;
        //            }
        //
        //            @Override
        //            public boolean isLoading() {
        //                return isLoading;
        //            }
        //        });

        return root;
    }

    @Override
    public void setPresenter(FeaturedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void ShowToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataSuccess(String message, List<Podcast> list) {
        podcastAdapter = new PodcastAdapter(getContext(), list);
        recyclerView.setAdapter(podcastAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }

}

