package com.testroids.team;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;

import com.testroids.SimpleDataLoader;
import com.testroids.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamLoader extends SimpleDataLoader<List<Player>> {
    private String mTeamName;
    public TeamLoader(Context context, String teamName) {
        super(context);
        this.mTeamName = teamName;
    }

    @Override
    public List<Player> loadInBackground() {
        List<Player> players = new ArrayList<Player>();
        Player player = new Player();
        player.setName("Tom Jones");
        players.add(player);
        return players;
    }
}