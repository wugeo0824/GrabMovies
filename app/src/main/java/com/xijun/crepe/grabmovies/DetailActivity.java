package com.xijun.crepe.grabmovies;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xijun.crepe.grabmovies.event.OnMovieInfoLoadedEvent;
import com.xijun.crepe.grabmovies.event.OnTopRatedLoadedEvent;
import com.xijun.crepe.grabmovies.model.MovieInfo;
import com.xijun.crepe.grabmovies.network.MovieDBRequstHandler;
import com.xijun.crepe.grabmovies.ui.customview.MovieField;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LiXijun on 2016/3/3.
 */
public class DetailActivity extends AppCompatActivity {

    private static final String MOVIE_ID = "movie_id";
    private Toolbar toolbar;
    private int movieId;
    private Snackbar snack;
    private AppBarLayout appBar;
    private CoordinatorLayout coordinatorLayout;
    private LinearLayout llFieldsContainer;
    private MovieInfo movieInfo;
    private ImageView ivBackdrop, ivPoster;
    private TextView tvTitle, tvTagline, tvOverview, tvMovieUrl;
    private CollapsingToolbarLayout collapsingToolbar;
    private float scrollPercentage = 0;

    public static void startDetailActivity(View sharedImageView, FragmentActivity startingActivity, int id) {
        Intent intent = new Intent(startingActivity, DetailActivity.class);
        intent.putExtra(MOVIE_ID,id);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedImageView.setTransitionName(startingActivity.getString(R.string.transition_image));

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(startingActivity, sharedImageView, sharedImageView.getTransitionName());
            //startingActivity.getWindow().setExitTransition(null);
            startingActivity.startActivity(intent, options.toBundle());
        } else {
            startingActivity.startActivity(intent, ActivityOptions.makeCustomAnimation(startingActivity, R.anim.slide_in_right,
                    R.anim.slide_out_left).toBundle());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        // We don't want the status bar and nav bar to animate as that causes flickering:
        // http://stackoverflow.com/questions/26600263/how-do-i-prevent-the-status-bar-and-navigation-bar-from-animating-during-an-acti
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            Transition fade = new Fade();
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setEnterTransition(fade);
            getWindow().setReturnTransition(fade);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        movieId = getIntent().getIntExtra(MOVIE_ID,-1);
        initViews();
        getMovieInfo();
    }

    private void initViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBar = (AppBarLayout) findViewById(R.id.appbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);
        ivBackdrop = (ImageView) findViewById(R.id.ivBackdrop);
        ivPoster = (ImageView) findViewById(R.id.ivDetailPoster);
        tvTitle = (TextView) findViewById(R.id.tvDetailTitle);
        tvTagline = (TextView) findViewById(R.id.tvTagLine);
        tvMovieUrl = (TextView) findViewById(R.id.tvMovieUrl);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appBar.addOnOffsetChangedListener(appBarOffsetChangedListener);
        llFieldsContainer = (LinearLayout) findViewById(R.id.llFieldsContainer);

    }

    private AppBarLayout.OnOffsetChangedListener appBarOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            int maxOffset = appBar.getTotalScrollRange();
            scrollPercentage = verticalOffset / (float) maxOffset;
            scrollPercentage = Math.abs(scrollPercentage);

            if (scrollPercentage == 1){
                collapsingToolbar.setTitle(movieInfo.getTitle());
            }else{
                collapsingToolbar.setTitle(" ");
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void getMovieInfo() {
        snack = Snackbar.make(coordinatorLayout,"Loading...",Snackbar.LENGTH_INDEFINITE);
        snack.show();
        MovieDBRequstHandler.getMovieInfo(movieId);
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(OnMovieInfoLoadedEvent onMovieInfoLoadedEvent) {

        // show placeholder when 1.api call failed 2.api call succeeded but no data
        if (onMovieInfoLoadedEvent.isError() || onMovieInfoLoadedEvent.getMovieInfo() == null) {
            snack.dismiss();
            snack = Snackbar.make(coordinatorLayout, "Something went wrong.", Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getMovieInfo();
                }
            });
        } else {
            snack.dismiss();
            movieInfo = onMovieInfoLoadedEvent.getMovieInfo();
            bindViews();
        }
    }

    private void bindViews() {
        Picasso.with(this).load(movieInfo.getBackdropPath()).placeholder(R.drawable.image_placeholder).fit().centerCrop().into(ivBackdrop);
        Picasso.with(this).load(movieInfo.getPosterPath()).placeholder(R.drawable.grid_placeholder).fit().centerCrop().into(ivPoster);
        String[] date = movieInfo.getReleaseDate().split("-");
        String year = date[0];
        tvTitle.setText(movieInfo.getTitle() + " (" + year + ") ");
        tvTagline.setText(movieInfo.getTagline());
        tvOverview.setText(movieInfo.getOverview());
        if (!TextUtils.isEmpty(movieInfo.getHomepage())){
            tvMovieUrl.setText(movieInfo.getHomepage());
            tvMovieUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movieInfo.getHomepage()));
                    startActivity(browserIntent);
                }
            });
            tvMovieUrl.setVisibility(View.VISIBLE);
        }else{
            tvMovieUrl.setVisibility(View.GONE);
        }

        collapsingToolbar.setTitle(movieInfo.getTitle());
        if (movieInfo.getAdult() != null){
            String right;
            if(movieInfo.getAdult())
                right = "yes";
            else
                right = "no";
            llFieldsContainer.addView(new MovieField(this,"Adult Only",right));
        }
        if (!TextUtils.isEmpty(movieInfo.getStatus())){
            llFieldsContainer.addView(new MovieField(this,"Status",movieInfo.getStatus()));
        }
        if (!TextUtils.isEmpty(movieInfo.getReleaseDate())){
            llFieldsContainer.addView(new MovieField(this,"Release Date", movieInfo.getReleaseDate()));
        }
        if (movieInfo.getBudget()!=null){
            llFieldsContainer.addView(new MovieField(this,"Budget","$" + movieInfo.getBudget()));
        }
        if (movieInfo.getRevenue()!=null){
            llFieldsContainer.addView(new MovieField(this,"Revenue", "$" + movieInfo.getRevenue()));
        }
        if (movieInfo.getRuntime()!=null){
            llFieldsContainer.addView(new MovieField(this,"Movie Length", movieInfo.getRuntime() + " min"));
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else
            supportFinishAfterTransition();
            //super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
