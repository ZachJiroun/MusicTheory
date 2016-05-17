package cmsc436.com.musictheory.games;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import cmsc436.com.musictheory.Piano.PianoActivity;
import cmsc436.com.musictheory.R;
import cmsc436.com.musictheory.chords.ChordsActivity;
import cmsc436.com.musictheory.lessons.LessonsActivity;
import cmsc436.com.musictheory.rhythm.RhythmReference;
import cmsc436.com.musictheory.scales.ScalesActivity;

/**
 * Created by Vit on 5/14/2016.
 */
public class SharpFragment extends android.support.v4.app.Fragment {

    public SharpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.sharp_fragment, container, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(300, bundle.getInt("height") + 70);
        view.setLayoutParams(layoutParams);

        return view;
    }

    /**
     * Created by twinniss on 5/17/2016.
     */
    public static class ChordFlatFragment extends android.support.v4.app.Fragment {

        public ChordFlatFragment(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle bundle = this.getArguments();
            View view = inflater.inflate(R.layout.chord_flat, container, false);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(250, bundle.getInt("height"));
            view.setLayoutParams(layoutParams);

            return view;
        }

    }

    /**
     * Created by twinniss on 5/16/2016.
     */
    public static class ChordLedgerFragment extends android.support.v4.app.Fragment {

        public ChordLedgerFragment(){

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle bundle = this.getArguments();
            View view = inflater.inflate(R.layout.chord_ledger, container, false);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(385, bundle.getInt("height"));
            //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(432, getResources().getInteger(R.integer.bottom_ledger));
            view.setLayoutParams(layoutParams);

            return view;
        }

    }

    /**
     * Created by Vit on 5/16/2016.
     */
    public static class ChordsGameActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener {

        Button pl, submit;
        EditText sEdit;
        String check_answer;
        HashMap<Integer, String> major_map = new HashMap();
        HashMap<Integer, String> minor_map = new HashMap();
        HashMap<String, String>maj_chord_map = new HashMap();
        HashMap<String, String>min_chord_map = new HashMap();
        HashMap<String, Integer> heightMap = new HashMap();
        String note_name_1 = "";
        String note_name_2 = "";
        String note_name_3 = "";
        SharedPreferences gameSettings;
        SharedPreferences.Editor prefEditor;
        int currentScore;
        TextView currentScoreText;
        TextView highScoreText;

        private DrawerLayout mDrawerLayout;
        private Toolbar mToolbar;
        private ActionBarDrawerToggle mDrawerToggle;
        android.app.FragmentManager mFragmentManager;


        public void generateHeight(){
            Resources res = getResources();
            String[]notes = res.getStringArray(R.array.note_array);
            int height = 575;
    //        int last_entry = 0;
            for(int count = 0; count < notes.length - 1; count++){
                heightMap.put(notes[count], height);
                String letter = notes[count];
                if(notes[count + 1].charAt(0) != notes[count].charAt(0)){
                    height -=24;
                }
    //            last_entry = count;
            }
    //        Log.d("index ~~~~~~~~~~~~~~~~", Integer.toString(last_entry));
    //        heightMap.put(notes[last_entry + 1], height);
        }
        public void generateMap(String mode[], HashMap m, HashMap c, int r_id){
            String [] chord = getResources().getStringArray(r_id);
            for(int count = 0; count < mode.length; count++){
                m.put(count, mode[count]);
                c.put(mode[count], chord[count]);
            }
        }



        public int generateInterval(int min, int max){
            Random seed = new Random();
            return seed.nextInt(max - min) + min;
        }

        private float dpToPixel(float dp) {
            DisplayMetrics metrics = this.getResources().getDisplayMetrics();
            return dp * (metrics.densityDpi/160f);
        }

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

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.chord_game_layout);
            final MediaPlayer mp1 = new MediaPlayer();
            final MediaPlayer mp2 = new MediaPlayer();
            final MediaPlayer mp3 = new MediaPlayer();


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

            mDrawerToggle = new ActionBarDrawerToggle(ChordsGameActivity.this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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

            int highScore = gameSettings.getInt("chords_score", 0);

            highScoreText.setText("High Score: " + highScore);

            String maj[] = getResources().getStringArray(R.array.maj_scale);
            generateMap(maj, major_map, maj_chord_map, R.array.major_scale);
            String min[] = getResources().getStringArray(R.array.min_scale);
            generateMap(min, minor_map, min_chord_map, R.array.minor_scale);
            generateHeight();


            pl = (Button) findViewById(R.id.play);
            pl.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    int rand;

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction note_1 = fragmentManager.beginTransaction();
                    FragmentTransaction note_2 = fragmentManager.beginTransaction();
                    FragmentTransaction note_3 = fragmentManager.beginTransaction();


                    FragmentTransaction frag_1 = fragmentManager.beginTransaction();
                    FragmentTransaction frag_2 = fragmentManager.beginTransaction();
                    FragmentTransaction frag_3 = fragmentManager.beginTransaction();

                    FragmentTransaction d_s_1 = fragmentManager.beginTransaction();
                    FragmentTransaction d_s_2 = fragmentManager.beginTransaction();
                    FragmentTransaction d_s_3 = fragmentManager.beginTransaction();
                    FragmentTransaction ledge_delete = fragmentManager.beginTransaction();

                    //clearing any fragments before drawing new chord

                    Fragment f_1 = fragmentManager.findFragmentByTag("SIGN_1");
                    if (f_1 != null) {
                        d_s_1.remove(f_1).commit();
                    }
                    Fragment f_2 = fragmentManager.findFragmentByTag("SIGN_2");
                    if (f_2 != null) {
                        d_s_2.remove(f_2).commit();
                    }
                    Fragment f_3 = fragmentManager.findFragmentByTag("SIGN_3");
                    if (f_3 != null) {
                        d_s_3.remove(f_3).commit();
                    }

                    Fragment pl = fragmentManager.findFragmentByTag("LEDGE");
                    if (pl != null) {
                        ledge_delete.remove(pl).commit();
                    }

                    Fragment prev = fragmentManager.findFragmentByTag("NOTE_1");
                    if (prev != null) {
                        frag_1.remove(prev).commit();
                    }
                    Fragment prev2 = fragmentManager.findFragmentByTag("NOTE_2");
                    if (prev != null) {
                        frag_2.remove(prev2).commit();
                    }
                    Fragment prev3 = fragmentManager.findFragmentByTag("NOTE_3");
                    if (prev != null) {
                        frag_3.remove(prev3).commit();
                    }

                    int scale_type = generateInterval(0, 2);
    //                int scale_type = 1;
                    if (scale_type == 0) {

                        rand = generateInterval(0, 14);

                        // rand = 1;
                        String note_name_key = major_map.get(rand);
                        check_answer = note_name_key.replaceAll("[0-9]","");
                        Log.d("random number", Integer.toString(rand));
                        String chord[] = maj_chord_map.get(note_name_key).split(" ");

                        int height_1 = (int) dpToPixel(heightMap.get(chord[0]));
                        int height_2 = (int) dpToPixel(heightMap.get(chord[1]));
                        int height_3 = (int) dpToPixel(heightMap.get(chord[2]));


                        if (chord[0].length() != 2) {
                            Log.d("first note", chord[0]);
                            char sign = chord[0].charAt(2);
                            FragmentTransaction signTransaction_1 = fragmentManager.beginTransaction();
                            Bundle s = new Bundle();
                            s.putInt("height", height_1);
                            if (sign == '#') {
                                ChordSharpFragment sharp = new ChordSharpFragment();
                                sharp.setArguments(s);
                                signTransaction_1.add(R.id.fragment_container, sharp, "SIGN_1");
                            } else {
                                ChordFlatFragment flat = new ChordFlatFragment();
                                flat.setArguments(s);
                                signTransaction_1.add(R.id.fragment_container, flat, "SIGN_1");
                            }
                            signTransaction_1.commit();
                        }
                        if (chord[1].length() != 2) {
                            char sign = chord[1].charAt(2);
                            FragmentTransaction signTransaction_2 = fragmentManager.beginTransaction();
                            Bundle s = new Bundle();
                            s.putInt("height", height_2);
                            if (sign == '#') {
                                ChordSharpFragment sharp = new ChordSharpFragment();
                                sharp.setArguments(s);
                                signTransaction_2.add(R.id.fragment_container, sharp, "SIGN_2");
                            } else {
                                ChordFlatFragment flat = new ChordFlatFragment();
                                flat.setArguments(s);
                                signTransaction_2.add(R.id.fragment_container, flat, "SIGN_2");
                            }
                            signTransaction_2.commit();
                        }
                        if (chord[2].length() != 2) {
                            char sign = chord[2].charAt(2);
                            FragmentTransaction signTransaction_3 = fragmentManager.beginTransaction();
                            Bundle s = new Bundle();
                            s.putInt("height", height_3);
                            if (sign == '#') {
                                ChordSharpFragment sharp = new ChordSharpFragment();
                                sharp.setArguments(s);
                                signTransaction_3.add(R.id.fragment_container, sharp, "SIGN_3");
                            } else {
                                ChordFlatFragment flat = new ChordFlatFragment();
                                flat.setArguments(s);
                                signTransaction_3.add(R.id.fragment_container, flat, "SIGN_3");
                            }
                            signTransaction_3.commit();
                        }

                        Bundle n1 = new Bundle();
                        Bundle n2 = new Bundle();
                        Bundle n3 = new Bundle();


                        n1.putInt("height", height_1);
                        n2.putInt("height", height_2);
                        n3.putInt("height", height_3);


                        WholeNoteFragment w1 = new WholeNoteFragment();
                        WholeNoteFragment w2 = new WholeNoteFragment();
                        WholeNoteFragment w3 = new WholeNoteFragment();

                        w1.setArguments(n1);
                        w2.setArguments(n2);
                        w3.setArguments(n3);

                        note_1.add(R.id.fragment_container, w1, "NOTE_1");
                        note_2.add(R.id.fragment_container, w2, "NOTE_2");
                        note_3.add(R.id.fragment_container, w3, "NOTE_3");

                        note_1.commit();
                        note_2.commit();
                        note_3.commit();

                        if (rand < 4) {
                            Bundle first_ledge = new Bundle();
                            ChordLedgerFragment ledge = new ChordLedgerFragment();
                            FragmentTransaction ledgeFrag = fragmentManager.beginTransaction();
                            int ledger_height = getResources().getInteger(R.integer.bot_chord_ledger) - 550;
                            first_ledge.putInt("height", ledger_height);
                            ledge.setArguments(first_ledge);
                            ledgeFrag.add(R.id.fragment_container, ledge, "LEDGE");
                            ledgeFrag.commit();
                        }
                        note_name_1 = mp3FileName(chord[0]);
                        note_name_1 = mp3FileName(chord[1]);
                        note_name_1 = mp3FileName(chord[2]);

                    } else {
                        rand = generateInterval(0, 13);
    //                    rand = 4;
                        Log.d("random number", Integer.toString(rand));

                        String note_name_key = minor_map.get(rand);
                        check_answer = note_name_key.replaceAll("[0-9]","");
                        check_answer= check_answer.concat("m");
                        String chord[] = min_chord_map.get(note_name_key).split(" ");
                        Log.d("chord name1", chord[0]);
                        Log.d("chord name1", chord[1]);
                        Log.d("chord name1", chord[2]);
                        Log.d("height_1", Integer.toString(heightMap.get(chord[0])));
                        Log.d("height_1", Integer.toString(heightMap.get(chord[1])));
                        Log.d("height_1", Integer.toString(heightMap.get(chord[2])));


                        int height_1 = (int) dpToPixel(heightMap.get(chord[0]));
                        int height_2 = (int) dpToPixel(heightMap.get(chord[1]));
                        int height_3 = (int) dpToPixel(heightMap.get(chord[2]));


                        if (chord[0].length() != 2) {
                            Log.d("first note", chord[0]);
                            char sign = chord[0].charAt(2);
                            FragmentTransaction signTransaction_1 = fragmentManager.beginTransaction();
                            Bundle s = new Bundle();
                            s.putInt("height", height_1);
                            if (sign == '#') {
                                ChordSharpFragment sharp = new ChordSharpFragment();
                                sharp.setArguments(s);
                                signTransaction_1.add(R.id.fragment_container, sharp, "SIGN_1");
                            } else {
                                ChordFlatFragment flat = new ChordFlatFragment();
                                flat.setArguments(s);
                                signTransaction_1.add(R.id.fragment_container, flat, "SIGN_1");
                            }
                            signTransaction_1.commit();
                        }
                        if (chord[1].length() != 2) {
                            char sign = chord[1].charAt(2);
                            FragmentTransaction signTransaction_2 = fragmentManager.beginTransaction();
                            Bundle s = new Bundle();
                            s.putInt("height", height_2);
                            if (sign == '#') {
                                ChordSharpFragment sharp = new ChordSharpFragment();
                                sharp.setArguments(s);
                                signTransaction_2.add(R.id.fragment_container, sharp, "SIGN_2");
                            } else {
                                ChordFlatFragment flat = new ChordFlatFragment();
                                flat.setArguments(s);
                                signTransaction_2.add(R.id.fragment_container, flat, "SIGN_2");
                            }
                            signTransaction_2.commit();
                        }
                        if (chord[2].length() != 2) {
                            char sign = chord[2].charAt(2);
                            FragmentTransaction signTransaction_3 = fragmentManager.beginTransaction();
                            Bundle s = new Bundle();
                            s.putInt("height", height_3);
                            if (sign == '#') {
                                ChordSharpFragment sharp = new ChordSharpFragment();
                                sharp.setArguments(s);
                                signTransaction_3.add(R.id.fragment_container, sharp, "SIGN_3");
                            } else {
                                ChordFlatFragment flat = new ChordFlatFragment();
                                flat.setArguments(s);
                                signTransaction_3.add(R.id.fragment_container, flat, "SIGN_3");
                            }
                            signTransaction_3.commit();
                        }

                        Bundle n1 = new Bundle();
                        Bundle n2 = new Bundle();
                        Bundle n3 = new Bundle();


                        n1.putInt("height", height_1);
                        n2.putInt("height", height_2);
                        n3.putInt("height", height_3);


                        WholeNoteFragment w1 = new WholeNoteFragment();
                        WholeNoteFragment w2 = new WholeNoteFragment();
                        WholeNoteFragment w3 = new WholeNoteFragment();

                        w1.setArguments(n1);
                        w2.setArguments(n2);
                        w3.setArguments(n3);

                        note_1.add(R.id.fragment_container, w1, "NOTE_1");
                        note_2.add(R.id.fragment_container, w2, "NOTE_2");
                        note_3.add(R.id.fragment_container, w3, "NOTE_3");

                        note_1.commit();
                        note_2.commit();
                        note_3.commit();

                        if (rand < 4) {
                            Bundle first_ledge = new Bundle();
                            ChordLedgerFragment ledge = new ChordLedgerFragment();
                            FragmentTransaction ledgeFrag = fragmentManager.beginTransaction();
                            int ledger_height = getResources().getInteger(R.integer.bot_chord_ledger) - 550;
                            first_ledge.putInt("height", ledger_height);
                            ledge.setArguments(first_ledge);
                            ledgeFrag.add(R.id.fragment_container, ledge, "LEDGE");
                            ledgeFrag.commit();
                        }
                        note_name_1 = mp3FileName(chord[0]);
                        note_name_1 = mp3FileName(chord[1]);
                        note_name_1 = mp3FileName(chord[2]);
                    }

                    submit = (Button)findViewById(R.id.sub);
                    submit.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            sEdit = (EditText) findViewById(R.id.editText);
                            int currentHigh = gameSettings.getInt("chords_score", 0);

                            Log.d(check_answer, check_answer);
                            Log.d(sEdit.getText().toString(), sEdit.getText().toString());
                            if (check_answer.equals(sEdit.getText().toString())) {
                                playCorrect();
                                Toast.makeText(getBaseContext(), "You're Right!", Toast.LENGTH_SHORT).show();
                                currentScore++;
                                currentScoreText.setText("Current Score: " + currentScore);

                                if (currentScore > currentHigh) {
                                    highScoreText.setText("High Score: " + currentScore);

                                    prefEditor.putInt("chords_score", currentScore);
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
                                    Intent li = new Intent(ChordsGameActivity.this, LessonsActivity.class);
                                    li.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(li);
                                    break;
                                case R.id.rhythm_menu_item:
                                    Intent ri = new Intent(ChordsGameActivity.this, RhythmReference.class);
                                    ri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(ri);
                                    break;
                                case R.id.scales_menu_item:
                                    Intent si = new Intent(ChordsGameActivity.this, ScalesActivity.class);
                                    si.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(si);
                                    break;
                                case R.id.games_menu_item:
                                    Intent gi = new Intent(ChordsGameActivity.this, GamesActivity.class);
                                    gi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(gi);
                                case R.id.chords_menu_item:
                                    Intent ci = new Intent(ChordsGameActivity.this, ChordsActivity.class);
                                    ci.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(ci);
                                    break;
                                case R.id.piano_menu_item:
                                    Intent pi = new Intent(ChordsGameActivity.this, PianoActivity.class);
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
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


    }

    /**
     * Created by twinniss on 5/17/2016.
     */
    public static class ChordSharpFragment extends Fragment {

        public ChordSharpFragment(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle bundle = this.getArguments();
            View view = inflater.inflate(R.layout.chord_sharp, container, false);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(250, bundle.getInt("height"));
            view.setLayoutParams(layoutParams);

            return view;
        }
    }

    /**
     * Created by twinniss on 5/16/2016.
     */
    public static class WholeNoteFragment  extends Fragment {

        public WholeNoteFragment(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle bundle = this.getArguments();
            View view = inflater.inflate(R.layout.whole_note_fragment, container, false);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(550, bundle.getInt("height"));
            view.setLayoutParams(layoutParams);

            return view;
        }


    }
}
