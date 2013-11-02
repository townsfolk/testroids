package com.testroids.leagues;

import android.support.v4.app.Fragment;

import com.testroids.SingleFragmentActivity;

public class LeaguesActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return LeaguesFragment.newInstance();
    }
}
