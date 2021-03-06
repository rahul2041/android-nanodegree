package com.hassanabid.popularmoviesapp2;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MovieListActivity extends AppCompatActivity
        implements MovieListFragment.OnMovieSelectedListener {

    private static final String LOG_TAG = MovieListActivity.class.getSimpleName();
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }

    }


    @Override
    public void onMovieSelected(int position,int id, String title, String poster_path, String overview, String release_date, String votes) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(MovieDetailFragment.ID_KEY, id);
            arguments.putString(MovieDetailFragment.TITLE_KEY, title);
            arguments.putString(MovieDetailFragment.POSTER_KEY, poster_path);
            arguments.putString(MovieDetailFragment.OVERVIEW_KEY, overview);
            arguments.putString(MovieDetailFragment.RELEASE_DATE_KEY, release_date);
            arguments.putString(MovieDetailFragment.VOTES_KEY, votes);


            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode,

            Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ID_KEY,id);
            intent.putExtra(MovieDetailFragment.TITLE_KEY, title);
            intent.putExtra(MovieDetailFragment.POSTER_KEY, poster_path);
            intent.putExtra(MovieDetailFragment.OVERVIEW_KEY, overview);
            intent.putExtra(MovieDetailFragment.RELEASE_DATE_KEY, release_date);
            intent.putExtra(MovieDetailFragment.VOTES_KEY, votes);
            startActivity(intent);
        }
    }

    @Override
    public void onFetchFirstMovie(int position, int id, String title, String poster_path, String overview, String release_date, String votes) {
        if(mTwoPane) {
            Log.d(LOG_TAG,"fetch first movie for tablet");
            Bundle arguments = new Bundle();
            arguments.putInt(MovieDetailFragment.ID_KEY, id);
            arguments.putString(MovieDetailFragment.TITLE_KEY, title);
            arguments.putString(MovieDetailFragment.POSTER_KEY, poster_path);
            arguments.putString(MovieDetailFragment.OVERVIEW_KEY, overview);
            arguments.putString(MovieDetailFragment.RELEASE_DATE_KEY, release_date);
            arguments.putString(MovieDetailFragment.VOTES_KEY, votes);


            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();

        }
    }
}
