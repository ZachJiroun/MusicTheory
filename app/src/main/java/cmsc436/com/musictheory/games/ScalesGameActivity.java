package cmsc436.com.musictheory.games;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

import cmsc436.com.musictheory.Piano.PianoActivity;
import cmsc436.com.musictheory.R;
import cmsc436.com.musictheory.chords.ChordsActivity;
import cmsc436.com.musictheory.lessons.LessonsActivity;
import cmsc436.com.musictheory.rhythm.RhythmReference;
import cmsc436.com.musictheory.scales.ScalesActivity;

public class ScalesGameActivity extends AppCompatActivity {

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

        mDrawerToggle = new ActionBarDrawerToggle(ScalesGameActivity.this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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
                                Intent li = new Intent(ScalesGameActivity.this, LessonsActivity.class);
                                li.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(li);
                                break;
                            case R.id.rhythm_menu_item:
                                Intent ri = new Intent(ScalesGameActivity.this, RhythmReference.class);
                                ri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ri);
                                break;
                            case R.id.scales_menu_item:
                                Intent si = new Intent(ScalesGameActivity.this, ScalesActivity.class);
                                si.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(si);
                                break;
                            case R.id.games_menu_item:
                                Intent gi = new Intent(ScalesGameActivity.this, GamesActivity.class);
                                gi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(gi);
                                break;
                            case R.id.chords_menu_item:
                                Intent ci = new Intent(ScalesGameActivity.this, ChordsActivity.class);
                                ci.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ci);
                                break;
                            case R.id.piano_menu_item:
                                Intent pi = new Intent(ScalesGameActivity.this, PianoActivity.class);
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

    public static class NotesActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener {

        HashMap<String, Integer> heightMap = new HashMap<>();
        Button st, playback, submit;
        String filename = "";
        String check_answer = "";
        EditText sEdit;
        SharedPreferences gameSettings;
        SharedPreferences.Editor prefEditor;
        int currentScore;
        TextView currentScoreText;
        TextView highScoreText;


        private DrawerLayout mDrawerLayout;
        private Toolbar mToolbar;
        private ActionBarDrawerToggle mDrawerToggle;
        FragmentManager mFragmentManager;

        public String mp3FileName (String string) {
            String answer = string.substring(0, 2);
            if(string.length() != 2) {
                char sign = string.charAt(2);
                if(sign == '#'){
                    answer = answer.concat(" sharp");
                } else {
                    answer = answer.concat(" flat");
                }
            }
            return answer.concat(" quarter.mid");
        }

        public HashMap<Integer, String> generateMap(){
            char letter = 'C';
            int register = 3;
            String sign = "";
            HashMap<Integer, String> map = new HashMap<>();
    /*        map.put(1,"C3");
            map.put(2,"C#3");
            map.put(3,"D3");
            map.put(4,"D#3");
            map.put(5,"E3");
            map.put(6,"F3");
            map.put(7,"F3#");
            map.put(8,"G3");
            map.put(9,"G3#");
            map.put(10,"A3");
            map.put(11,"B3b");
            map.put(12,"B3");
            map.put(13,"C4");
            map.put(14,"C#4");

            ~~~~~flip head ~~~~~

            map.put(15,"D4");
            map.put(16,"D#4");
            map.put(17,"E4");
            map.put(18,"F4");
            map.put(19,"F4#");
            map.put(20,"G4");
            map.put(21,"G4#");
            map.put(22,"A4");
            map.put(23,"B4b");
            map.put(24,"B4");

            ~~~~move to treble and start down~~~~

            map.put(25,"C5");
            map.put(26,"C#5");
            map.put(27,"D5");
            map.put(28,"D#5");
            map.put(29,"E5");
            map.put(30,"F5");
            map.put(31,"F#5");
            map.put(32,"G5");
            map.put(33,"G#5");
            map.put(34,"A5");

            ~~~~flip head~~~~

            map.put(35,"Bb5");
            map.put(36,"B5");
            map.put(37,"C6");
            map.put(38,"C#6");
            map.put(39,"D6");
            map.put(40,"D#6");
            map.put(41,"E6");
            map.put(42,"F6");
            map.put(43,"F#6");
            map.put(44,"G6");
            map.put(45,"G#6");
            map.put(46,"A6");
            map.put(47,"Bb6");
            map.put(48,"B6");
            map.put(49,"C7");
            map.put(50,"C#7");*/

            //C3 initial height in layout
            int height = 478;

            for(int count = 0; count < 50; count++){
                String name = String.valueOf(letter) + register + sign;
                map.put(count, name);
                heightMap.put(name, height);
                if(letter == 'B'){
                    if(sign.equals("")){
                        register++;
                        letter++;
                        height = height - 13;
                    }else {
                        sign = "";
                    }
                } else if (letter == 'G' && sign.equals("#")){
                    letter = 'A';
                    sign = "";
                    height = height - 13;
                } else if (letter == 'E') {
                    letter = 'F';
                    height = height - 13;
                } else if (letter == 'A'){
                    letter = 'B';
                    sign = "b";
                    height = height - 13;
                } else {
                    if (sign == ""){
                        sign = "#";
                    } else {
                        sign = "";
                        letter++;
                        height = height - 13;
                    }
                }
            }


            letter = 'C';
            sign = "";
            register = 5;
            height = 213;

            //loop over second half again to adjust height correctly for treble staff
            for(int count = 24; count < 50; count++){
                String name = String.valueOf(letter) + register + sign;
                heightMap.put(name, height);
                if(letter == 'B'){
                    if(sign.equals("")){
                        register++;
                        letter++;
                        height = height - 13;
                    }else {
                        sign = "";
                    }
                } else if (letter == 'G' && sign.equals("#")){
                    letter = 'A';
                    sign = "";
                    height = height - 13;
                } else if (letter == 'E') {
                    letter = 'F';
                    height = height - 13;
                } else if (letter == 'A'){
                    letter = 'B';
                    sign = "b";
                    height = height - 13;
                } else {
                    if (sign == ""){
                        sign = "#";
                    } else {
                        sign = "";
                        letter++;
                        height = height - 13;
                    }
                }
            }
            return map;
        }

        public int generateInterval(){
            int min = 0;
            int max = 50;
            Random seed = new Random();
            return seed.nextInt(max - min) + min;
        }

        private float dpToPixel(float dp) {
            DisplayMetrics metrics = this.getResources().getDisplayMetrics();
            return dp * (metrics.densityDpi/160f);
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.notes_activity);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            final MediaPlayer mp = new MediaPlayer();


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

            mDrawerToggle = new ActionBarDrawerToggle(NotesActivity.this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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

            int highScore = gameSettings.getInt("notes_score", 0);

            highScoreText.setText("High Score: " + highScore);


            st = (Button) findViewById(R.id.st);

            st.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    int rand = generateInterval();

                    HashMap<Integer, String> map = generateMap();
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FragmentTransaction del_frag = fragmentManager.beginTransaction();
                    FragmentTransaction deleteTrans = fragmentManager.beginTransaction();
                    FragmentTransaction ledge_delete = fragmentManager.beginTransaction();
                    FragmentTransaction del_s_led = fragmentManager.beginTransaction();

                    Fragment f = fragmentManager.findFragmentByTag("SIGN");
                    if(f != null){
                        deleteTrans.remove(f).commit();
                    }

                    Fragment pl = fragmentManager.findFragmentByTag("LEDGE");
                    if(pl != null){
                        ledge_delete.remove(pl).commit();
                    }

                    Fragment prev = fragmentManager.findFragmentByTag("NOTE");
                    if(prev != null){
                        del_frag.remove(prev).commit();
                    }

                    Fragment sec = fragmentManager.findFragmentByTag("LEDGE_TWO");
                    if(sec != null){
                        del_s_led.remove(sec).commit();
                    }


                    Bundle args = new Bundle();
                    String key = map.get(rand);
                    check_answer = key.replaceAll("[0-9]","");
                    int height = (int) dpToPixel(heightMap.get(key));
                    args.putInt("height", height);


                    if(mp.isPlaying()){
                        mp.stop();
                    }
                    try{
                        //note stem is up
                        if((24 <= rand && rand <= 33) || rand <= 13){
                            NoteUpFragment note = new NoteUpFragment();
                            note.setArguments(args);
                            fragmentTransaction.add(R.id.fragment_container, note, "NOTE");

                            if(rand <= 4 || rand == 24 || rand == 25){
                                Bundle first_ledge = new Bundle();
                                LedgerFragment ledge = new LedgerFragment();
                                FragmentTransaction ledgeFrag = fragmentManager.beginTransaction();
                                int ledger_height = getResources().getInteger(R.integer.bottom_ledger);
                                if(rand == 24 || rand == 25){
                                    ledger_height = ledger_height- 630;
                                }
                                first_ledge.putInt("height", ledger_height);
                                ledge.setArguments(first_ledge);
                                ledgeFrag.add(R.id.fragment_container, ledge, "LEDGE");
                                ledgeFrag.commit();

                                //adding second ledger line
                                if(rand == 0 || rand == 1){
                                    Bundle ledge_args = new Bundle();
                                    FragmentTransaction sl = fragmentManager.beginTransaction();
                                    LedgerFragment second_ledge = new LedgerFragment();
                                    ledge_args.putInt("height", ledger_height + 65);
                                    second_ledge.setArguments(ledge_args);
                                    sl.add(R.id.fragment_container, second_ledge, "LEDGE_TWO");
                                    sl.commit();
                                }
                            }
                            //note stem is down
                        } else {
                            if (rand >= 45){
                                FragmentTransaction ledgeFrag = fragmentManager.beginTransaction();
                                LedgerFragment ledge = new LedgerFragment();
                                int ledger_height = getResources().getInteger(R.integer.top_ledger);
                                Bundle h_bundle = new Bundle();
                                h_bundle.putInt("height", ledger_height);
                                ledge.setArguments(h_bundle);
                                ledgeFrag.add(R.id.fragment_container, ledge, "LEDGE");
                                ledgeFrag.commit();
                                if(rand == 48 || rand == 49){
                                    FragmentTransaction sl = fragmentManager.beginTransaction();
                                    LedgerFragment second_ledge = new LedgerFragment();
                                    Bundle bun = new Bundle();
                                    bun.putInt("height", ledger_height - 63);
                                    second_ledge.setArguments(bun);
                                    sl.add(R.id.fragment_container, second_ledge, "LEDGE_TWO");
                                    sl.commit();
                                }
                            }
                            NoteDownFragment note = new NoteDownFragment();
                            note.setArguments(args);
                            fragmentTransaction.add(R.id.fragment_container, note, "NOTE");
                        }
                        //Comment out this line if you want to negate back button functionality (previous state not saved)
                        //fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        //After drawing note we then check for sign to add corresponding sign if needed
                        //index of sign in note's name
                        if(key.length() != 2){
                            char sign = key.charAt(2);
                            FragmentTransaction signTransaction = fragmentManager.beginTransaction();

                            if(sign == '#'){
                                SharpFragment sharp = new SharpFragment();
                                sharp.setArguments(args);
                                signTransaction.add(R.id.fragment_container, sharp, "SIGN");
                            } else {
                                FlatFragment flat = new FlatFragment();
                                flat.setArguments(args);
                                signTransaction.add(R.id.fragment_container, flat, "SIGN");
                            }
                            signTransaction.commit();
                        }
                        mp.reset();
                        filename = mp3FileName(key);
                        AssetFileDescriptor afd = getAssets().openFd(filename);
                        mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        mp.prepare();
                        mp.start();
                    } catch (IllegalStateException | IOException e) {
                        e.printStackTrace();
                    }
                    }
            });

            playback = (Button) findViewById(R.id.pb);
            playback.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(!filename.equals("")){
                        final MediaPlayer mp = new MediaPlayer();
                        if(mp.isPlaying()){
                            mp.stop();
                        }
                        try{
                            mp.reset();
                            AssetFileDescriptor afd = getAssets().openFd(filename);
                            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                            mp.prepare();
                            mp.start();
                        } catch (IllegalStateException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            submit = (Button)findViewById(R.id.sub);
            submit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    sEdit = (EditText) findViewById(R.id.editText);
                    int currentHigh = gameSettings.getInt("notes_score", 0);

                    Log.d(check_answer, check_answer);
                    Log.d(sEdit.getText().toString(), sEdit.getText().toString());
                    if (check_answer.equals(sEdit.getText().toString())) {
                        playCorrect();

                        Toast.makeText(getBaseContext(), "You're Right!", Toast.LENGTH_SHORT).show();
                        currentScore++;
                        currentScoreText.setText("Current Score: " + currentScore);

                        if (currentScore > currentHigh) {
                            highScoreText.setText("High Score: " + currentScore);

                            prefEditor.putInt("notes_score", currentScore);
                            prefEditor.commit();
                        }
                    }else{
                        currentScore = 0;
                        currentScoreText.setText("Current Score: " + 0);
                        Toast.makeText(getBaseContext(), "You're Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        public void playCorrect(){
            final MediaPlayer mp = new MediaPlayer();
            if(mp.isPlaying()){
                mp.stop();
            }
            try{
                mp.reset();
                AssetFileDescriptor afd = getAssets().openFd(getString(R.string.correct));
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }

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
                                    Intent li = new Intent(NotesActivity.this, LessonsActivity.class);
                                    li.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(li);
                                    break;
                                case R.id.rhythm_menu_item:
                                    Intent ri = new Intent(NotesActivity.this, RhythmReference.class);
                                    ri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(ri);
                                    break;
                                case R.id.scales_menu_item:
                                    Intent si = new Intent(NotesActivity.this, ScalesActivity.class);
                                    si.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(si);
                                    break;
                                case R.id.games_menu_item:
                                    break;
                                case R.id.chords_menu_item:
                                    Intent ci = new Intent(NotesActivity.this, ChordsActivity.class);
                                    ci.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(ci);
                                    break;
                                case R.id.piano_menu_item:
                                    Intent pi = new Intent(NotesActivity.this, PianoActivity.class);
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

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }
}
