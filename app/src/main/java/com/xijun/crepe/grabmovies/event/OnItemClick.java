package com.xijun.crepe.grabmovies.event;

import com.xijun.crepe.grabmovies.ui.viewholder.GridImageViewHolder;

/**
 * Created by LiXijun on 2016/3/5.
 */
public class OnItemClick {

    private int position;
    private GridImageViewHolder viewHolder;

    public OnItemClick(GridImageViewHolder viewHolder, int position) {
        this.position = position;
        this.viewHolder = viewHolder;
    }

    public int getPosition() {
        return position;
    }

    public GridImageViewHolder getViewHolder() {
        return viewHolder;
    }
}
