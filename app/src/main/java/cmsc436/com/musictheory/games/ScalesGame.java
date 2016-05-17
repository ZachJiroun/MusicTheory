package cmsc436.com.musictheory.games;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.TreeMap;

import cmsc436.com.musictheory.Piano.Piano;
import cmsc436.com.musictheory.R;
import cmsc436.com.musictheory.chords.chords;
import cmsc436.com.musictheory.lessons.LessonsActivity;
import cmsc436.com.musictheory.rhythm.RhythmReference;
import cmsc436.com.musictheory.scales.Scales;

public class ScalesGame extends AppCompatActivity {

    TreeMap<Integer, String> scaleMap;
    Spinner scaleGameSpinner;
    ImageView scaleImage;
    Button submitButton;
    String currentScale;
    TextView currentScoreText;
    TextView highScoreText;
    int currentScore;
    SharedPreferences gameSettings;
    SharedPreferences.Editor prefEditor;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scales_game);
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

        mDrawerToggle = new ActionBarDrawerToggle(ScalesGame.this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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

        gameSettings = getSharedPreferences("MyGamePreferences", MODE_PRIVATE);
        prefEditor = gameSettings.edit();

        currentScore = 0;

        currentScoreText = (TextView) findViewById(R.id.scales_current_score);
        highScoreText = (TextView) findViewById(R.id.scales_high_score);

        currentScoreText.setText("Current Score: " + 0);

        int highScore = gameSettings.getInt("scales_score", 0);

        highScoreText.setText("High Score: " + highScore);

        scaleImage = (ImageView) findViewById(R.id.scale_game_image);

        scaleGameSpinner = (Spinner) findViewById(R.id.scale_game_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.scales_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        scaleGameSpinner.setAdapter(adapter);

        scaleGameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        scaleMap = new TreeMap<Integer, String>();

        scaleMap.put(1, "Bb Major");
        scaleMap.put(2, "Bb Minor");
        scaleMap.put(3, "C Major");
        scaleMap.put(4, "C Minor");
        scaleMap.put(5, "D Major");
        scaleMap.put(6, "D Minor");
        scaleMap.put(7, "F Major");
        scaleMap.put(8, "F Minor");
        scaleMap.put(9, "G Major");
        scaleMap.put(10, "G Minor");
        scaleMap.put(11, "A Major");
        scaleMap.put(12, "A Minor");

        generateNewImage();

        submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedItem = (String) scaleGameSpinner.getSelectedItem();
                int currentHigh = gameSettings.getInt("scales_score", 0);

                if (currentScale.equals(selectedItem)) {
                    Toast.makeText(getBaseContext(), "You're Right!", Toast.LENGTH_SHORT).show();
                    currentScore++;
                    currentScoreText.setText("Current Score: " + currentScore);

                    if (currentScore > currentHigh) {
                        highScoreText.setText("High Score: " + currentScore);

                        prefEditor.putInt("scales_score", currentScore);
                        prefEditor.commit();
                    }

                }else {
                    currentScore = 0;
                    currentScoreText.setText("Current Score: " + 0);
                    Toast.makeText(getBaseContext(), "You're Wrong!", Toast.LENGTH_SHORT).show();
                }

                generateNewImage();
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
                                Intent li = new Intent(ScalesGame.this, LessonsActivity.class);
                                li.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(li);
                                break;
                            case R.id.rhythm_menu_item:
                                Intent ri = new Intent(ScalesGame.this, RhythmReference.class);
                                ri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ri);
                                break;
                            case R.id.scales_menu_item:
                                Intent si = new Intent(ScalesGame.this, Scales.class);
                                si.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(si);
                                break;
                            case R.id.games_menu_item:
                                Intent gi = new Intent(ScalesGame.this, Games.class);
                                gi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(gi);
                                break;
                            case R.id.chords_menu_item:
                                Intent ci = new Intent(ScalesGame.this, chords.class);
                                ci.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ci);
                                break;
                            case R.id.piano_menu_item:
                                Intent pi = new Intent(ScalesGame.this, Piano.class);
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

    public int generateRandom(int min, int max) {
        Random seed = new Random();
        return seed.nextInt(max - min) + min;
    }

    public void generateNewImage() {

        Integer randomNum = generateRandom(1, 13);
        Integer randomNumBass = generateRandom(1,3);

        if (scaleMap.get(randomNum).equals("Bb Major")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.bb_major);
            }else{
                scaleImage.setImageResource(R.drawable.bb_major_bass);
            }

            currentScale = "Bb Major";
        }else if (scaleMap.get(randomNum).equals("Bb Minor")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.bb_minor);
            }else{
                scaleImage.setImageResource(R.drawable.bb_minor_bass);
            }

            currentScale = "Bb Minor";
        }else if (scaleMap.get(randomNum).equals("C Major")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.c_major);
            }else{
                scaleImage.setImageResource(R.drawable.c_major_bass);
            }

            currentScale = "C Major";
        }else if (scaleMap.get(randomNum).equals("C Minor")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.c_minor);
            }else{
                scaleImage.setImageResource(R.drawable.c_minor_bass);
            }

            currentScale = "C Minor";
        }else if (scaleMap.get(randomNum).equals("D Major")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.d_major);
            }else{
                scaleImage.setImageResource(R.drawable.d_major_bass);
            }

            currentScale = "D Major";
        }else if (scaleMap.get(randomNum).equals("D Minor")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.d_minor);
            }else{
                scaleImage.setImageResource(R.drawable.d_minor_bass);
            }

            currentScale = "D Minor";
        }else if (scaleMap.get(randomNum).equals("F Major")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.f_major);
            }else{
                scaleImage.setImageResource(R.drawable.f_major_bass);
            }

            currentScale = "F Major";
        }else if (scaleMap.get(randomNum).equals("F Minor")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.f_minor);
            }else{
                scaleImage.setImageResource(R.drawable.f_minor_bass);
            }

            currentScale = "F Minor";
        }else if (scaleMap.get(randomNum).equals("G Major")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.g_major);
            }else{
                scaleImage.setImageResource(R.drawable.g_major_bass);
            }

            currentScale = "G Major";
        }else if (scaleMap.get(randomNum).equals("G Minor")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.g_minor);
            }else{
                scaleImage.setImageResource(R.drawable.g_minor_bass);
            }

            currentScale = "G Minor";
        }else if (scaleMap.get(randomNum).equals("A Major")) {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.a_major);
            }else{
                scaleImage.setImageResource(R.drawable.a_major_bass);
            }

            currentScale = "A Major";
        }else {
            if (randomNumBass.equals(1)) {
                scaleImage.setImageResource(R.drawable.a_minor);
            }else{
                scaleImage.setImageResource(R.drawable.a_minor_bass);
            }

            currentScale = "A Minor";
        }
    }

}
