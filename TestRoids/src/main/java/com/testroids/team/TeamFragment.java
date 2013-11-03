package com.testroids.team;

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
import com.testroids.player.Player;

import java.util.List;

public class TeamFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Player>> {
    public static final String EXTRA_TEAM_NAME = "com.testroids.team.name";
    private static final String TAG = "testroids.TeamFragment";

    private GridView mGridView;
    private List<Player> mPlayers;
    private String mTeamName; // here to test retained instance.

    public static TeamFragment newInstance(String teamName) {
        Bundle args = new Bundle();
        args.putString(EXTRA_TEAM_NAME, teamName);

        TeamFragment fragment = new TeamFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Loader<List<Player>> onCreateLoader(int i, Bundle bundle) {
        Log.d(TAG, "onCreateLoader");
        String teamName = bundle.getString(EXTRA_TEAM_NAME);

        return new TeamLoader(getActivity(), teamName);
    }

    @Override
    public void onLoadFinished(Loader<List<Player>> listLoader, List<Player> players) {
        Log.d(TAG, "LoadFinished - player count: " + (players != null ? players.size() : "null"));
        this.mPlayers = players;
        mGridView.setAdapter(new ArrayAdapter<Player>(getActivity(), android.R.layout.simple_gallery_item, players));
    }

    @Override
    public void onLoaderReset(Loader<List<Player>> listLoader) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //setRetainInstance(true);

        if (getArguments() != null) {
            mTeamName = getArguments().getString(EXTRA_TEAM_NAME);
            getActivity().setTitle(mTeamName);
        } else {
            getActivity().setTitle("NULL");
        }
        getLoaderManager().initLoader(0, getArguments(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
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
                Player player = mPlayers.get(position);
                /*
                Intent i = new Intent(getActivity(), TeamActivity.class);
                i.putExtra(TeamFragment.EXTRA_TEAM_NAME, player.getName());

                startActivity(i);
                */
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
}
