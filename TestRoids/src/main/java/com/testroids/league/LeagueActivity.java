package com.testroids.league;


import android.support.v4.app.Fragment;

import com.testroids.SingleFragmentActivity;

public class LeagueActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        String leagueName = getIntent().getStringExtra(LeagueFragment.EXTRA_LEAGUE_NAME);

        return LeagueFragment.newInstance(leagueName);
    }
}
