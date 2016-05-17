package cmsc436.com.musictheory.rhythm;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import cmsc436.com.musictheory.Piano.PianoActivity;
import cmsc436.com.musictheory.R;
import cmsc436.com.musictheory.games.GamesActivity;
import cmsc436.com.musictheory.chords.ChordsActivity;
import cmsc436.com.musictheory.lessons.LessonsActivity;
import cmsc436.com.musictheory.scales.ScalesActivity;

public class RhythmReference extends AppCompatActivity {

    ImageButton wholeNoteButton;
    ImageButton wholeRestButton;
    ImageButton halfNoteButton;
    ImageButton halfRestButton;
    ImageButton quarterNoteButton;
    ImageButton quarterRestButton;
    ImageButton eighthNoteButton;
    ImageButton eighthRestButton;
    ImageButton sixteenthNoteButton;
    ImageButton sixteenthRestButton;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm_reference);

        Bundle bundle = new Bundle();
        bundle.putString("button_value", "blank");

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

        mDrawerToggle = new ActionBarDrawerToggle(RhythmReference.this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        RhythmFragment rhythmFragment = new RhythmFragment();
        rhythmFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
        fragmentTransaction.commit();

        wholeNoteButton = (ImageButton) findViewById(R.id.whole_note_button);
        wholeNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "whole");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    RhythmFragment rhythmFragment = new RhythmFragment();
                    rhythmFragment.setArguments(bundle);
                    fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

            }
        });

        wholeRestButton = (ImageButton) findViewById(R.id.whole_rest_button);
        wholeRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "whole");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        halfNoteButton = (ImageButton) findViewById(R.id.half_note_button);
        halfNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "half");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        halfRestButton = (ImageButton) findViewById(R.id.half_rest_button);
        halfRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "half");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        quarterNoteButton = (ImageButton) findViewById(R.id.quarter_note_button);
        quarterNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "quarter");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        quarterRestButton = (ImageButton) findViewById(R.id.quarter_rest_button);
        quarterRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "quarter");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        eighthNoteButton = (ImageButton) findViewById(R.id.eighth_note_button);
        eighthNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "eighth");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        eighthRestButton = (ImageButton) findViewById(R.id.eighth_rest_button);
        eighthRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "eighth");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        sixteenthNoteButton = (ImageButton) findViewById(R.id.sixteenth_note_button);
        sixteenthNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "sixteenth");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        sixteenthRestButton = (ImageButton) findViewById(R.id.sixteenth_rest_button);
        sixteenthRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "sixteenth");

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

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
            Intent intent = NavUtils.getParentActivityIntent(this);
            startActivity(intent);
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
                                Intent li = new Intent(RhythmReference.this, LessonsActivity.class);
                                li.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(li);
                                break;
                            case R.id.rhythm_menu_item:
                                break;
                            case R.id.scales_menu_item:
                                Intent si = new Intent(RhythmReference.this, ScalesActivity.class);
                                si.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(si);
                                break;
                            case R.id.games_menu_item:
                                Intent gi = new Intent(RhythmReference.this, GamesActivity.class);
                                gi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(gi);
                                break;
                            case R.id.chords_menu_item:
                                Intent ci = new Intent(RhythmReference.this, ChordsActivity.class);
                                ci.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ci);
                                break;
                            case R.id.piano_menu_item:
                                Intent pi = new Intent(RhythmReference.this, PianoActivity.class);
                                pi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(pi);
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

}
