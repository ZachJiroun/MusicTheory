package cmsc436.com.musictheory.games;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cmsc436.com.musictheory.R;
import cmsc436.com.musictheory.lessons.LessonsActivity;
import cmsc436.com.musictheory.rhythm.RhythmReference;
import cmsc436.com.musictheory.scales.Scales;

public class Games extends AppCompatActivity {

    SharedPreferences gameSettings;
    SharedPreferences.Editor prefEditor;
    TextView scalesText;
    TextView chordsText;
    TextView notesText;
    Button scalesGameButton;
    Button resetButton;
    int scalesHigh;
    int chordsHigh;
    int notesHigh;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the navigation drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set up the toolbar.
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        mDrawerToggle = new ActionBarDrawerToggle(Games.this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.popBackStack();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // Set up fragment
        mFragmentManager = getFragmentManager();

        mDrawerToggle.setDrawerIndicatorEnabled(true);

        scalesText = (TextView) findViewById(R.id.scales_game_high_score);
        chordsText = (TextView) findViewById(R.id.chords_game_high_score);
        notesText = (TextView) findViewById(R.id.notes_game_high_score);

        gameSettings = getSharedPreferences("MyGamePreferences", MODE_PRIVATE);
        prefEditor = gameSettings.edit();

        scalesHigh = gameSettings.getInt("scales_score", 0);
        chordsHigh = gameSettings.getInt("chords_score", 0);
        notesHigh = gameSettings.getInt("notes_score", 0);

        setScoreText();

        scalesGameButton = (Button) findViewById(R.id.scales_game_button);

        scalesGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Games.this, ScalesGame.class);
                startActivity(intent);
            }
        });

        resetButton = (Button) findViewById(R.id.reset_scores_button);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefEditor.putInt("scales_score", 0);
                prefEditor.putInt("chords_score", 0);
                prefEditor.putInt("notes_score", 0);
                prefEditor.commit();

                scalesHigh = gameSettings.getInt("scales_score", 0);
                chordsHigh = gameSettings.getInt("chords_score", 0);
                notesHigh = gameSettings.getInt("notes_score", 0);

                setScoreText();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        if(mFragmentManager.getBackStackEntryCount() != 0) {
            mFragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mFragmentManager.getBackStackEntryCount() > 0) {
                    mFragmentManager.popBackStack();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setDrawerIndicatorEnabled(boolean value) {
        if (mDrawerToggle != null) {
            mDrawerToggle.setDrawerIndicatorEnabled(value);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.lessons_menu_item:
                                Intent li = new Intent(Games.this, LessonsActivity.class);
                                li.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(li);
                                break;
                            case R.id.rhythm_menu_item:
                                Intent ri = new Intent(Games.this, RhythmReference.class);
                                ri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ri);
                                break;
                            case R.id.scales_menu_item:
                                Intent si = new Intent(Games.this, Scales.class);
                                si.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(si);
                                break;
                            default:
                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }

                });
    }

    private void setScoreText() {
        scalesText.setText("High Score: " + scalesHigh);
        chordsText.setText("High Score: " + chordsHigh);
        notesText.setText("High Score: " + notesHigh);
    }

}
