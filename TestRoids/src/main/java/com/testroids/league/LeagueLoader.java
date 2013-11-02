package com.testroids.league;

import android.content.Context;
import android.support.v4.content.Loader;

import com.testroids.SimpleDataLoader;
import com.testroids.team.Team;

import java.util.ArrayList;
import java.util.List;

public class LeagueLoader extends SimpleDataLoader<List<Team>> {
    private String mLeagueName;

    public LeagueLoader(Context context, String leagueName) {
        super(context);
        this.mLeagueName = leagueName;
    }

    @Override
    public List<Team> loadInBackground() {
        List<Team> teams = new ArrayList<Team>();
        Team team = new Team();
        team.setName("Team One");
        teams.add(team);
        return teams;
    }
}
