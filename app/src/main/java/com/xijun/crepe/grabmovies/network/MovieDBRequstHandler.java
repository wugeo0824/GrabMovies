package com.xijun.crepe.grabmovies.network;

import android.util.Log;

import com.xijun.crepe.grabmovies.event.OnMovieInfoLoadedEvent;
import com.xijun.crepe.grabmovies.event.OnNowPlayingLoadedEvent;
import com.xijun.crepe.grabmovies.event.OnTopRatedLoadedEvent;
import com.xijun.crepe.grabmovies.model.MovieInfo;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LiXijun on 2016/3/3.
 */
public class MovieDBRequstHandler {

    private static final String TAG = "MovieDBRequstHandler";

    public static void getNowPlaying(int page){

        MovieDBService.getApiManager().getNowPlaying(page).enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.isSuccess())
                    EventBus.getDefault().post(new OnNowPlayingLoadedEvent(false, response.body().getResults()));
                else
                    EventBus.getDefault().post(new OnNowPlayingLoadedEvent(true, null)); // all error cases were responded by a empty state image
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                EventBus.getDefault().post(new OnNowPlayingLoadedEvent(true, null)); // all error cases were responded by a empty state image
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    public static void getTopRated(int page){
        MovieDBService.getApiManager().getTopRated(page).enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.isSuccess())
                    EventBus.getDefault().post(new OnTopRatedLoadedEvent(false, response.body().getResults()));
                else
                    EventBus.getDefault().post(new OnTopRatedLoadedEvent(true, null)); // all error cases were responded by a empty state image
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                EventBus.getDefault().post(new OnTopRatedLoadedEvent(true, null)); // all error cases were responded by a empty state image
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public static void getMovieInfo(int id){
        MovieDBService.getApiManager().getMovieInfo(id).enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {
                if (response.isSuccess())
                    EventBus.getDefault().post(new OnMovieInfoLoadedEvent(false, response.body()));
                else
                    EventBus.getDefault().post(new OnMovieInfoLoadedEvent(true, null)); // all error cases were responded by a empty state image
            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {
                EventBus.getDefault().post(new OnMovieInfoLoadedEvent(true, null)); // all error cases were responded by a empty state image
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
