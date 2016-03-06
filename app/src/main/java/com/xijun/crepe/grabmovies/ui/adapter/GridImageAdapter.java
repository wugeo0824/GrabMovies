package com.xijun.crepe.grabmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xijun.crepe.grabmovies.R;
import com.xijun.crepe.grabmovies.model.Movie;
import com.xijun.crepe.grabmovies.ui.viewholder.GridImageViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiXijun on 2016/3/3.
 */
public class GridImageAdapter extends RecyclerView.Adapter<GridImageViewHolder> {
    private Context context;
    private List<Movie> movieList = new ArrayList<>();
    private int fragmentId =-1;

    public GridImageAdapter(Context context, List<Movie> movieList, int fragmentId) {
        this.context = context;
        this.movieList = movieList;
        this.fragmentId = fragmentId;
    }

    @Override
    public GridImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_grid_movies, parent, false);
        GridImageViewHolder viewHolder = new GridImageViewHolder(view, fragmentId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GridImageViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Picasso.with(context).load(movie.getPosterPath()).placeholder(R.drawable.grid_placeholder).fit().centerCrop().into(holder.ivPoster);
    }

    @Override
    public long getItemId(int position) {
        return movieList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
