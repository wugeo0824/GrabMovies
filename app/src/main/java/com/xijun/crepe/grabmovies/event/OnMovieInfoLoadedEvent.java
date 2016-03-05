package com.xijun.crepe.grabmovies.event;

import com.xijun.crepe.grabmovies.model.MovieInfo;

/**
 * Created by LiXijun on 2016/3/5.
 */
public class OnMovieInfoLoadedEvent extends BaseEvent {
    private MovieInfo movieInfo;

    public OnMovieInfoLoadedEvent(boolean isError, MovieInfo movieInfo) {
        setError(isError);
        this.movieInfo = movieInfo;
    }

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }
}
