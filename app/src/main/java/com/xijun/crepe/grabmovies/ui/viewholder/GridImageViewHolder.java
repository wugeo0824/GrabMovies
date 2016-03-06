package com.xijun.crepe.grabmovies.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.xijun.crepe.grabmovies.R;
import com.xijun.crepe.grabmovies.event.OnItemClick;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by LiXijun on 2016/3/6.
 */
public class GridImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView ivPoster;
    public int fragmentId;

    public GridImageViewHolder(View itemView, int fragmentId) {
        super(itemView);
        ivPoster = (ImageView)itemView.findViewById(R.id.ivGridItem);
        itemView.setOnClickListener(this);
        this.fragmentId = fragmentId;
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new OnItemClick(this, getAdapterPosition()));
    }
}
