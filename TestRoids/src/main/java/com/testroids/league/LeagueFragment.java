package com.testroids.league;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.testroids.R;
import com.testroids.team.Team;
import com.testroids.team.TeamActivity;
import com.testroids.team.TeamFragment;

import java.util.List;

public class LeagueFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Team>> {
    public static final String EXTRA_LEAGUE_NAME = "com.testroids.league.name";
    private static final String TAG = "testroids.LeagueFragment";
    private GridView mGridView;
    private List<Team> mTeams;
    private String mLeagueName; // here to test retained instance

    public static LeagueFragment newInstance(String leagueName) {
        Bundle args = new Bundle();
        args.putString(EXTRA_LEAGUE_NAME, leagueName);

        LeagueFragment fragment = new LeagueFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Loader<List<Team>> onCreateLoader(int i, Bundle bundle) {
        Log.d(TAG, "onCreateLoader");
        return new LeagueLoader(getActivity(), bundle.getString(EXTRA_LEAGUE_NAME));
    }

    @Override
    public void onLoadFinished(Loader<List<Team>> listLoader, List<Team> teams) {
        Log.d(TAG, "LoadFinished - team count: " + (teams != null ? teams.size() : "null"));
        mTeams = teams;
        mGridView.setAdapter(new ArrayAdapter<Team>(getActivity(), android.R.layout.simple_gallery_item, teams));
    }

    @Override
    public void onLoaderReset(Loader<List<Team>> listLoader) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated - savedInstanceState: " + savedInstanceState);
        if (savedInstanceState != null) {
            Log.d(TAG, "onActivityCreated - league name: " + savedInstanceState.getString(EXTRA_LEAGUE_NAME));
            mLeagueName = savedInstanceState.getString(EXTRA_LEAGUE_NAME);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate - savedInstanceState: " + savedInstanceState);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //setRetainInstance(true);

        if (getArguments() != null) {
            mLeagueName = getArguments().getString(EXTRA_LEAGUE_NAME);
            getActivity().setTitle(mLeagueName);
        } else {
            getActivity().setTitle("NULL");
        }

        if(savedInstanceState != null) {
            Log.d(TAG, "onCreate - savedInstanceState: " + savedInstanceState + ", league name: " + savedInstanceState.getString(EXTRA_LEAGUE_NAME));
        }

        // mLeagueName shouldn't be null on back button
        Log.d(TAG, "onCreate - league name: " + mLeagueName);

        getLoaderManager().initLoader(0, getArguments(), this);
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView - savedInstanceState: " + savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_grid_view, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(getActivity()) != null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Team team = mTeams.get(position);
                Intent i = new Intent(getActivity(), TeamActivity.class);
                i.putExtra(TeamFragment.EXTRA_TEAM_NAME, team.getName());

                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(EXTRA_LEAGUE_NAME, mLeagueName);
        Log.d(TAG, "onSaveInstanceState - outState: " + outState + ", league name: " + outState.getString(EXTRA_LEAGUE_NAME));
        super.onSaveInstanceState(outState);
    }
}
