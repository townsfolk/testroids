package com.testroids.team;

import android.support.v4.app.Fragment;

import com.testroids.SingleFragmentActivity;

public class TeamActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        String teamName = getIntent().getStringExtra(TeamFragment.EXTRA_TEAM_NAME);
        return TeamFragment.newInstance(teamName);
    }
}
