package com.xijun.crepe.grabmovies.fragments;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.xijun.crepe.grabmovies.DetailActivity;
import com.xijun.crepe.grabmovies.MainActivity;
import com.xijun.crepe.grabmovies.R;
import com.xijun.crepe.grabmovies.event.OnItemClick;
import com.xijun.crepe.grabmovies.event.OnNowPlayingLoadedEvent;
import com.xijun.crepe.grabmovies.model.Movie;
import com.xijun.crepe.grabmovies.network.MovieDBRequstHandler;
import com.xijun.crepe.grabmovies.ui.adapter.GridImageAdapter;
import com.xijun.crepe.grabmovies.ui.customview.GridItemDecoration;
import com.xijun.crepe.grabmovies.utils.Const;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiXijun on 2016/3/4.
 */
public class NowPlayingFragment extends Fragment {

    private static final String TAG = "NowPlayingFragment";
    private RecyclerView gridView;
    private GridImageAdapter adapter;
    private List<Movie> movies = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        initViews(view);
        getMovies();
        return  view;
    }

    private void initViews(View view) {
        gridView = (RecyclerView) view.findViewById(R.id.gv_movies);
        adapter = new GridImageAdapter(getContext(), movies, Const.FRAGMENT_NOW_PLAYING);
        gridView.setAdapter(adapter);
        gridView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        gridView.addItemDecoration(new GridItemDecoration(3,getResources().getDimensionPixelOffset(R.dimen.grid_padding),true));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).hideSnack();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void getMovies() {
        ((MainActivity) getActivity()).showLoadingSnack("Loading...");
        MovieDBRequstHandler.getNowPlaying(1); // for demo purpose, sorry not enough time for pagination loading
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(OnNowPlayingLoadedEvent onNowPlayingLoadedEvent) {

        Log.d(TAG, "onEvent: count is " + onNowPlayingLoadedEvent.getMovieList().size());
        // show placeholder when 1.api call failed 2.api call succeeded but no data
        if (onNowPlayingLoadedEvent.isError() || onNowPlayingLoadedEvent.getMovieList().isEmpty()) {
            showEmptyState(true);
            ((MainActivity) getActivity()).hideSnack();
            ((MainActivity) getActivity()).showActionSnack("Something Went Wrong", "Retry", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getMovies();
                }
            });
        } else {
            showEmptyState(false);
            ((MainActivity) getActivity()).hideSnack();
            movies.clear();
            movies.addAll(onNowPlayingLoadedEvent.getMovieList());
            adapter.notifyDataSetChanged();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(OnItemClick onItemClick) {
        if (onItemClick !=null && onItemClick.getViewHolder().fragmentId == Const.FRAGMENT_NOW_PLAYING){
            DetailActivity.startDetailActivity(onItemClick.getViewHolder().ivPoster, getActivity(), (int) adapter.getItemId(onItemClick.getPosition()));
        }
    }

    private void showEmptyState(boolean b) {
    }



    @Override
    public void onStart() {
        super.onStart();
        //EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
