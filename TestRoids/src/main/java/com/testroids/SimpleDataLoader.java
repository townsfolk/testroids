package com.testroids;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public abstract class SimpleDataLoader<D> extends AsyncTaskLoader<D> {

    public SimpleDataLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public void deliverResult(D data) {
        if(isStarted()) super.deliverResult(data);
    }
}
