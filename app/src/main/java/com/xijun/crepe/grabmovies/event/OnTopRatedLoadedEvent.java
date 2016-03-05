package com.xijun.crepe.grabmovies.event;

import com.xijun.crepe.grabmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiXijun on 2016/3/4.
 */
public class OnTopRatedLoadedEvent extends BaseEvent {
    private List<Movie> movieList = new ArrayList<>();

    public OnTopRatedLoadedEvent(boolean isError, List<Movie> movies) {
        setError(isError);
        this.movieList = movies;
    }

    public List<Movie> getMovieList(){
        return movieList;
    }
}
