package com.xijun.crepe.grabmovies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xijun.crepe.grabmovies.R;
import com.xijun.crepe.grabmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiXijun on 2016/3/3.
 */
public class GridImageAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> movieList = new ArrayList<>();

    public GridImageAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Movie getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movieList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_grid_movies, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivGridItem);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        Picasso.with(context).load(movieList.get(position).getPosterPath()).placeholder(R.drawable.grid_placeholder).fit().centerInside().into(viewHolder.ivPoster);

        return convertView;

    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */
    private static class ViewHolder {
        ImageView ivPoster;
    }
}
