package com.springer.patryk.udacity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {
    private static final String LOG_TAG=DetailsActivityFragment.class.getSimpleName();
    private String intentMessage;
    private static final String FORECAST_SHARE_HASHTAG="#SunshineApp";
    public DetailsActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.detailfragment,menu);
        MenuItem menuItem=menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider=(ShareActionProvider)MenuItemCompat.getActionProvider(menuItem);
        if(shareActionProvider!=null)
            shareActionProvider.setShareIntent(createShareForecastIntent());
        else
            Log.d(LOG_TAG,"Share Action Provider is null?");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_details, container, false);
        Intent intent=getActivity().getIntent();
        if(intent!=null&& intent.hasExtra(Intent.EXTRA_TEXT)){
            intentMessage=intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView)rootView.findViewById(R.id.detailsText)).setText(intentMessage);
        }
        return rootView;
    }
    private Intent createShareForecastIntent(){
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,intentMessage+FORECAST_SHARE_HASHTAG);
        return shareIntent;
    }
}
