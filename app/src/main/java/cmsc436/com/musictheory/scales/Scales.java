package cmsc436.com.musictheory.scales;

import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import cmsc436.com.musictheory.R;
import cmsc436.com.musictheory.games.Games;
import cmsc436.com.musictheory.lessons.LessonsActivity;
import cmsc436.com.musictheory.rhythm.RhythmReference;

public class Scales extends AppCompatActivity {

    Spinner spinner;
    ImageButton playButton;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scales);
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

        mDrawerToggle = new ActionBarDrawerToggle(Scales.this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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

        spinner = (Spinner) findViewById(R.id.scales_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.scales_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = (String) parent.getItemAtPosition(position);

                ImageView scaleImage = (ImageView) findViewById(R.id.scale_image);
                ImageView bassScaleImage = (ImageView) findViewById(R.id.scale_image_bass);
                TextView scalesNoteText = (TextView) findViewById(R.id.scales_note_text);

                if (selectedItem.equals("Bb Major")) {
                    scaleImage.setImageResource(R.drawable.bb_major);
                    bassScaleImage.setImageResource(R.drawable.bb_major_bass);
                    scalesNoteText.setText("            Bb       C      D      Eb        F        G       A      Bb");
                }else if (selectedItem.equals("Bb Minor")) {
                    bassScaleImage.setImageResource(R.drawable.bb_minor_bass);
                    scaleImage.setImageResource(R.drawable.bb_minor);
                    scalesNoteText.setText("            Bb      C       Db     Eb        F      Gb      Ab     Bb");
                }else if (selectedItem.equals("C Major")) {
                    bassScaleImage.setImageResource(R.drawable.c_major_bass);
                    scaleImage.setImageResource(R.drawable.c_major);
                    scalesNoteText.setText("            C       D       E       F          G        A       B      C");
                }else if (selectedItem.equals("C Minor")) {
                    bassScaleImage.setImageResource(R.drawable.c_minor_bass);
                    scaleImage.setImageResource(R.drawable.c_minor);
                    scalesNoteText.setText("            C       D      Eb      F          G       Ab     Bb     C");
                }else if (selectedItem.equals("D Major")) {
                    bassScaleImage.setImageResource(R.drawable.d_major_bass);
                    scaleImage.setImageResource(R.drawable.d_major);
                    scalesNoteText.setText("            D       E      F#      G          A       B       C#     D");
                }else if (selectedItem.equals("D Minor")) {
                    bassScaleImage.setImageResource(R.drawable.d_minor_bass);
                    scaleImage.setImageResource(R.drawable.d_minor);
                    scalesNoteText.setText("            D       E       F       G          A      Bb      C       D");
                }else if (selectedItem.equals("F Major")) {
                    bassScaleImage.setImageResource(R.drawable.f_major_bass);
                    scaleImage.setImageResource(R.drawable.f_major);
                    scalesNoteText.setText("            F        G      A      Bb        C        D       E        F");
                }else if (selectedItem.equals("F Minor")) {
                    bassScaleImage.setImageResource(R.drawable.f_minor_bass);
                    scaleImage.setImageResource(R.drawable.f_minor);
                    scalesNoteText.setText("            F       G      Ab     Bb         C        Db      Eb     F");
                }else if (selectedItem.equals("G Major")) {
                    bassScaleImage.setImageResource(R.drawable.g_major_bass);
                    scaleImage.setImageResource(R.drawable.g_major);
                    scalesNoteText.setText("            G       A      B       C          D        E       F#     G");
                }else if (selectedItem.equals("G Minor")) {
                    bassScaleImage.setImageResource(R.drawable.g_minor_bass);
                    scaleImage.setImageResource(R.drawable.g_minor);
                    scalesNoteText.setText("            G       A     Bb      C          D        Eb      F      G");
                }else if (selectedItem.equals("A Major")) {
                    bassScaleImage.setImageResource(R.drawable.a_major_bass);
                    scaleImage.setImageResource(R.drawable.a_major);
                    scalesNoteText.setText("            A       B      C#     D          E        F#      G#     A");
                }else if (selectedItem.equals("A Minor")) {
                    bassScaleImage.setImageResource(R.drawable.a_minor_bass);
                    scaleImage.setImageResource(R.drawable.a_minor);
                    scalesNoteText.setText("           A        B      C       D           E       F       G      A");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        playButton = (ImageButton) findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentItem = (String) spinner.getSelectedItem();
                MediaPlayer mp;

                if (currentItem.equals("Bb Major")) {
                    mp = MediaPlayer.create(getBaseContext(), R.raw.bb_major);
                }else if (currentItem.equals("Bb Minor")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.bb_minor);
                }else if (currentItem.equals("C Major")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.c_major);
                }else if (currentItem.equals("C Minor")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.c_minor);
                }else if (currentItem.equals("D Major")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.d_major);
                }else if (currentItem.equals("D Minor")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.d_minor);
                }else if (currentItem.equals("F Major")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.f_major);
                }else if (currentItem.equals("F Minor")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.f_minor);
                }else if (currentItem.equals("G Major")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.g_major);
                }else if (currentItem.equals("G Minor")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.g_minor);
                }else if (currentItem.equals("A Major")){
                    mp = MediaPlayer.create(getBaseContext(), R.raw.a_major);
                }else {
                    mp = MediaPlayer.create(getBaseContext(), R.raw.a_minor);
                }

                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
                                Intent li = new Intent(Scales.this, LessonsActivity.class);
                                li.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(li);
                                break;
                            case R.id.rhythm_menu_item:
                                Intent ri = new Intent(Scales.this, RhythmReference.class);
                                ri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ri);
                                break;
                            case R.id.scales_menu_item:
                                break;
                            case R.id.games_menu_item:
                                Intent gi = new Intent(Scales.this, Games.class);
                                gi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(gi);
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
