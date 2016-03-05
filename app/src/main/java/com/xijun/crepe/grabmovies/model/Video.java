package com.xijun.crepe.grabmovies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Xijun on 5/3/16.
 */
public class Video {

    private int id;

    @SerializedName("result")
    private Result video;

    public int getId() {
        return id;
    }

    public Result getVideo() {
        return video;
    }

    public String getVideoId(){
        return video.getId();
    }



    private class Result{
        private String id;
        private String key;
        private String name;
        private String site;
        private String size;
        private String type;

        public String getId() {
            return id;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getSite() {
            return site;
        }

        public String getSize() {
            return size;
        }

        public String getType() {
            return type;
        }
    }
}
