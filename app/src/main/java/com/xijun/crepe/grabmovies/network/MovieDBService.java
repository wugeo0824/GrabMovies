package com.xijun.crepe.grabmovies.network;

import com.xijun.crepe.grabmovies.model.MovieInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by LiXijun on 2016/3/3.
 */
public class MovieDBService {

    private static final String API_URL = "http://api.themoviedb.org/3/";
    private static final String API_KEY = "25689eed5383086b4413a135f1a4e8b3";
    public static final String IMAGE_URL_BASE = "http://image.tmdb.org/t/p/";

    private static MovieDbApi apiManager;

    public static MovieDbApi getApiManager() {
        if(apiManager == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build();
                            request = request.newBuilder().url(url).build();
                            return chain.proceed(request);
                        }
                    })
                    .addInterceptor(interceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiManager = retrofit.create(MovieDbApi.class);
        }

        return apiManager;
    }

    interface MovieDbApi {

        /**
         * Get the list of now_playing movies
         * sample url: http://api.themoviedb.org/3/movie/now_playing?api_key=4df263f48a4fe2621749627f5d001bf0&page=1
         */
        @GET("movie/now_playing")
        Call<MovieListResponse> getNowPlaying(@Query("page") int page);

        /**
         * Get the list of now_playing movies
         * sample url: http://api.themoviedb.org/3/movie/top_rated?api_key=4df263f48a4fe2621749627f5d001bf0&page=1
         */
        @GET("movie/top_rated")
        Call<MovieListResponse> getTopRated(@Query("page") int page);

        /**
         * Get the detail information of the movie
         * sample url: http://api.themoviedb.org/3/movie/550?api_key=25689eed5383086b4413a135f1a4e8b3
         */
        @GET("movie/{id}")
        Call<MovieInfo> getMovieInfo(@Path("id") int id);

    }
}
