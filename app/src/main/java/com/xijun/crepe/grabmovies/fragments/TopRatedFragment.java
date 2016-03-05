package com.xijun.crepe.grabmovies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.xijun.crepe.grabmovies.MainActivity;
import com.xijun.crepe.grabmovies.R;
import com.xijun.crepe.grabmovies.event.OnTopRatedLoadedEvent;
import com.xijun.crepe.grabmovies.model.Movie;
import com.xijun.crepe.grabmovies.network.MovieDBRequstHandler;
import com.xijun.crepe.grabmovies.ui.adapter.GridImageAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiXijun on 2016/3/4.
 */
public class TopRatedFragment extends Fragment {

    private static final String TAG = "NowPlayingFragment";
    private GridView gridView;
    private GridImageAdapter adapter;
    private List<Movie> movies = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        initViews(view);
        getMovies();
        return view;
    }

    private void initViews(View view) {
        gridView = (GridView) view.findViewById(R.id.gv_movies);
        adapter = new GridImageAdapter(getContext(), movies);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).hideSnack();

    }

    private void getMovies() {
        ((MainActivity) getActivity()).showLoadingSnack("Loading...");
        MovieDBRequstHandler.getTopRated(1);
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(OnTopRatedLoadedEvent onTopRatedLoadedEvent) {

        Log.d(TAG, "onEvent: count is " + onTopRatedLoadedEvent.getMovieList().size());
        // show placeholder when 1.api call failed 2.api call succeeded but no data
        if (onTopRatedLoadedEvent.isError() || onTopRatedLoadedEvent.getMovieList().isEmpty()) {
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
            movies.addAll(onTopRatedLoadedEvent.getMovieList());
            adapter.notifyDataSetChanged();
        }
    }

    private void showEmptyState(boolean b) {
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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
