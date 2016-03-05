package com.xijun.crepe.grabmovies.model;

/**
 * Created by LiXijun on 2016/3/3.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xijun.crepe.grabmovies.network.MovieDBService;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    @SerializedName("backdrop_path")
    private String backdropPath;
    private int id;
    @SerializedName("poster_path")
    private String posterPath;
    private String title;

    /**
     * @return The backdropPath
     */
    public Object getBackdropPath() {
        return backdropPath;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return The posterPath
     */
    public String getPosterPath() {
        if (!posterPath.contains(MovieDBService.IMAGE_URL_BASE)) {
            // w300 should be enough for poster inside gridView
            posterPath = MovieDBService.IMAGE_URL_BASE + "w300/" + posterPath;
        }
        return posterPath;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

}
