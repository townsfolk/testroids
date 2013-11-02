package com.testroids.leagues;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.testroids.SimpleDataLoader;

import java.util.ArrayList;
import java.util.List;

public class LeaguesLoader extends SimpleDataLoader<List<League>> {

    public LeaguesLoader(Context context) {
        super(context);
    }

    @Override
    public List<League> loadInBackground() {
        List<League> leagues = new ArrayList<League>();
        League league = new League();
        league.setName("Baseball");
        leagues.add(league);
        return leagues;
    }
}
