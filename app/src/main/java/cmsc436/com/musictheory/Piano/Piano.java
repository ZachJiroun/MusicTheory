package cmsc436.com.musictheory.Piano;

import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
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

import cmsc436.com.musictheory.R;
import cmsc436.com.musictheory.chords.chords;
import cmsc436.com.musictheory.lessons.LessonsActivity;
import cmsc436.com.musictheory.rhythm.RhythmReference;

public class Piano extends AppCompatActivity {

    Button c2button;
    Button cbutton;
    Button dbutton;
    Button ebutton;
    Button fbutton;
    Button gbutton;
    Button abutton;
    Button bbutton;
    Button csharpbutton;
    Button eflatbutton;
    Button fsharpbutton;
    Button aflatbutton;
    Button bflatbutton;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);
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

        mDrawerToggle = new ActionBarDrawerToggle(Piano.this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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

        c2button = (Button) findViewById(R.id.b2);
        c2button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.b4_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }

        });

        cbutton = (Button) findViewById(R.id.c);
        cbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.c5_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }

        });

        csharpbutton = (Button) findViewById(R.id.csharp);
        csharpbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.c5_sharp_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });

        dbutton = (Button) findViewById(R.id.d);
        dbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.d5_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });

        eflatbutton = (Button) findViewById(R.id.eflat);
        eflatbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.d5_sharp_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });
        ebutton = (Button) findViewById(R.id.e);
        ebutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.e5_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });

        fbutton = (Button) findViewById(R.id.f);
        fbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.f5_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });

        fsharpbutton = (Button) findViewById(R.id.fsharp);
        fsharpbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.f5_sharp_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });

        gbutton = (Button) findViewById(R.id.g);
        gbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.g5_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });

        aflatbutton = (Button) findViewById(R.id.aflat);
        aflatbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.g5_sharp_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });

        abutton = (Button) findViewById(R.id.a);
        abutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.a5_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });

        bflatbutton = (Button) findViewById(R.id.bflat);
        bflatbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.b5_flat_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


            }
        });

        bbutton = (Button) findViewById(R.id.b);
        bbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.b5_quarter);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();


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
                                Intent li = new Intent(Piano.this, LessonsActivity.class);
                                li.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(li);
                                break;
                            case R.id.chords_menu_item:
                                Intent ci = new Intent(Piano.this, chords.class);
                                ci.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ci);
                                break;
                            case R.id.rhythm_menu_item:
                                Intent ri = new Intent(Piano.this, RhythmReference.class);
                                ri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ri);
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
