package com.xijun.crepe.grabmovies.event;

/**
 * Created by LiXijun on 2016/3/3.
 */
public abstract class BaseEvent {

    protected boolean isError;
    protected int errorCode;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
