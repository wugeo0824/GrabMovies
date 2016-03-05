package com.xijun.crepe.grabmovies.model;

/**
 * Created by LiXijun on 2016/3/5.
 */

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xijun.crepe.grabmovies.network.MovieDBService;

/**
 *
 */
public class MovieInfo {

    @SerializedName("adult")
    @Expose
    public Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("belongs_to_collection")
    @Expose
    public Object belongsToCollection;
    @SerializedName("budget")
    @Expose
    public Integer budget;
    @SerializedName("genres")
    @Expose
    public List<Genre> genres = new ArrayList<Genre>();
    @SerializedName("homepage")
    @Expose
    public String homepage;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("imdb_id")
    @Expose
    public String imdbId;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("production_companies")
    @Expose
    public List<ProductionCompany> productionCompanies = new ArrayList<ProductionCompany>();
    @SerializedName("production_countries")
    @Expose
    public List<ProductionCountry> productionCountries = new ArrayList<ProductionCountry>();
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @SerializedName("revenue")
    @Expose
    public Integer revenue;
    @SerializedName("runtime")
    @Expose
    public Integer runtime;
    @SerializedName("spoken_languages")
    @Expose
    public List<SpokenLanguage> spokenLanguages = new ArrayList<SpokenLanguage>();
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("tagline")
    @Expose
    public String tagline;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("video")
    @Expose
    public Boolean video;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;

    public Boolean getAdult() {
        return adult;
    }

    public String getBackdropPath() {
        if (!backdropPath.contains(MovieDBService.IMAGE_URL_BASE)) {
            // w1000 is not that big
            backdropPath = MovieDBService.IMAGE_URL_BASE + "w1000/" + backdropPath;
        }
        return backdropPath;
    }

    public Object getBelongsToCollection() {
        return belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public Integer getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        if (!posterPath.contains(MovieDBService.IMAGE_URL_BASE)) {
            posterPath = MovieDBService.IMAGE_URL_BASE + "w300/" + posterPath;
        }
        return posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getVideo() {
        return video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public class ProductionCompany {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("id")
        @Expose
        public Integer id;

    }

    public class ProductionCountry {

        @SerializedName("iso_3166_1")
        @Expose
        public String iso31661;
        @SerializedName("name")
        @Expose
        public String name;

    }

    public class SpokenLanguage {

        @SerializedName("iso_639_1")
        @Expose
        public String iso6391;
        @SerializedName("name")
        @Expose
        public String name;

    }

    public class Genre {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;

    }

}
