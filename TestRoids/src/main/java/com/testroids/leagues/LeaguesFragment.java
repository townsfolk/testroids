package com.testroids.leagues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.testroids.R;
import com.testroids.league.LeagueActivity;
import com.testroids.league.LeagueFragment;

import java.util.List;

public class LeaguesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<League>> {

    private static final String TAG = "testroids.LeaguesFragment";

    private GridView mGridView;
    private List<League> mLeagues;

    public static LeaguesFragment newInstance() {
        return new LeaguesFragment();
    }

    @Override
    public Loader<List<League>> onCreateLoader(int i, Bundle bundle) {
        Log.d(TAG, "onCreateLoader");
        return new LeaguesLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<League>> listLoader, List<League> leagues) {
        Log.d(TAG, "LoadFinished - league count: " + (leagues != null ? leagues.size() : "null"));
        mLeagues = leagues;
        mGridView.setAdapter(new ArrayAdapter<League>(getActivity(), android.R.layout.simple_gallery_item, leagues));
    }

    @Override
    public void onLoaderReset(Loader<List<League>> listLoader) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_grid_view, container, false);
        mGridView = (GridView)view.findViewById(R.id.grid_view);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                League league = mLeagues.get(position);

                Intent i = new Intent(getActivity(), LeagueActivity.class);
                i.putExtra(LeagueFragment.EXTRA_LEAGUE_NAME, league.getName());

                startActivity(i);
            }
        });

        return view;
    }
}
