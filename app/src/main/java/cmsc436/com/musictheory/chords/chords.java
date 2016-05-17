package cmsc436.com.musictheory.chords;

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
import android.widget.Button;

import cmsc436.com.musictheory.Piano.Piano;
import cmsc436.com.musictheory.R;
import cmsc436.com.musictheory.games.Games;
import cmsc436.com.musictheory.lessons.LessonsActivity;
import cmsc436.com.musictheory.rhythm.RhythmReference;
import cmsc436.com.musictheory.scales.Scales;

public class chords extends AppCompatActivity {

    Button major;
    Button minor;
    Button major7;
    Button minor7;
    Button diminished7;

    FragmentManager fm;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chords);

        Bundle bundle = new Bundle();
        bundle.putString("button_value", "blank");

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

        mDrawerToggle = new ActionBarDrawerToggle(chords.this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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
                fm.popBackStack();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        fm = getFragmentManager();

        mDrawerToggle.setDrawerIndicatorEnabled(true);

        major = (Button) findViewById(R.id.majorbutton);
        major.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("button_value", "majorTriad");

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                chordFragment cFragment = new chordFragment();
                cFragment.setArguments(bundle);
                ft.add(cFragment, "majorTriad");
                ft.addToBackStack(null);
                ft.commit();
                //cFragment.show(ft, "majorTriad");
            }
        }
        );

        minor = (Button) findViewById(R.id.minorbutton);
        minor.setOnClickListener(new View.OnClickListener(){

                                     @Override
                                     public void onClick(View v) {
                                         Bundle bundle = new Bundle();
                                         bundle.putString("button_value", "minorTriad");

                                         FragmentTransaction ft = getFragmentManager().beginTransaction();
                                         chordFragment cFragment = new chordFragment();
                                         cFragment.setArguments(bundle);
                                         ft.add(cFragment, "minorTriad");
                                         ft.addToBackStack(null);
                                         ft.commit();
                                     }
                                 }
        );

        major7 = (Button) findViewById(R.id.maj7);
        major7.setOnClickListener(new View.OnClickListener(){

                                     @Override
                                     public void onClick(View v) {
                                         Bundle bundle = new Bundle();
                                         bundle.putString("button_value", "maj7");

                                         FragmentTransaction ft = getFragmentManager().beginTransaction();
                                         chordFragment cFragment = new chordFragment();
                                         cFragment.setArguments(bundle);
                                         ft.add(cFragment, "maj7");
                                         ft.addToBackStack(null);
                                         ft.commit();
                                     }
                                 }
        );

        minor7 = (Button) findViewById(R.id.min7);
        minor7.setOnClickListener(new View.OnClickListener(){

                                      @Override
                                      public void onClick(View v) {
                                          Bundle bundle = new Bundle();
                                          bundle.putString("button_value", "min7");

                                          FragmentTransaction ft = getFragmentManager().beginTransaction();
                                          chordFragment cFragment = new chordFragment();
                                          cFragment.setArguments(bundle);
                                          ft.add(cFragment, "min7");
                                          ft.addToBackStack(null);
                                          ft.commit();
                                      }
                                  }
        );

        diminished7 = (Button) findViewById(R.id.dim7);
        diminished7.setOnClickListener(new View.OnClickListener(){

                                      @Override
                                      public void onClick(View v) {
                                          Bundle bundle = new Bundle();
                                          bundle.putString("button_value", "dim7");

                                          FragmentTransaction ft = getFragmentManager().beginTransaction();
                                          chordFragment cFragment = new chordFragment();
                                          cFragment.setArguments(bundle);
                                          ft.add(cFragment, "dim7");
                                          ft.addToBackStack(null);
                                          ft.commit();
                                      }
                                  }
        );
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        if(fm.getBackStackEntryCount() != 0) {
            fm.popBackStack();
        } else {
            Intent intent = NavUtils.getParentActivityIntent(this);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
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
                                Intent li = new Intent(chords.this, LessonsActivity.class);
                                li.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(li);
                                break;
                            case R.id.rhythm_menu_item:
                                Intent ri = new Intent(chords.this, RhythmReference.class);
                                ri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ri);
                                break;
                            case R.id.scales_menu_item:
                                Intent si = new Intent(chords.this, Scales.class);
                                si.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(si);
                                break;
                            case R.id.games_menu_item:
                                Intent gi = new Intent(chords.this, Games.class);
                                gi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(gi);
                                break;
                            case R.id.chords_menu_item:
                                break;
                            case R.id.piano_menu_item:
                                Intent pi = new Intent(chords.this, Piano.class);
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
