package cmsc436.com.musictheory;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Vit on 5/16/2016.
 */
public class ChordsGame extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button pl;
    HashMap<Integer, String> major_map = new HashMap();
    HashMap<Integer, String> minor_map = new HashMap();
    HashMap<String, String>maj_chord_map = new HashMap();
    HashMap<String, String>min_chord_map = new HashMap();
    HashMap<String, Integer> heightMap = new HashMap();
    String note_name_1 = "";
    String note_name_2 = "";
    String note_name_3 = "";

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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

                int scale_type = generateInterval(0, 1);
//                int scale_type = 1;
                if (scale_type == 0) {

                    rand = generateInterval(0, 14);
                    // rand = 1;
                    String note_name_key = major_map.get(rand);
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
                            ChordSharp sharp = new ChordSharp();
                            sharp.setArguments(s);
                            signTransaction_1.add(R.id.fragment_container, sharp, "SIGN_1");
                        } else {
                            ChordFlat flat = new ChordFlat();
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
                            ChordSharp sharp = new ChordSharp();
                            sharp.setArguments(s);
                            signTransaction_2.add(R.id.fragment_container, sharp, "SIGN_2");
                        } else {
                            ChordFlat flat = new ChordFlat();
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
                            ChordSharp sharp = new ChordSharp();
                            sharp.setArguments(s);
                            signTransaction_3.add(R.id.fragment_container, sharp, "SIGN_3");
                        } else {
                            ChordFlat flat = new ChordFlat();
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
                        ChordLedger ledge = new ChordLedger();
                        FragmentTransaction ledgeFrag = fragmentManager.beginTransaction();
                        int ledger_height = getResources().getInteger(R.integer.bot_chord_ledger);
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
                            ChordSharp sharp = new ChordSharp();
                            sharp.setArguments(s);
                            signTransaction_1.add(R.id.fragment_container, sharp, "SIGN_1");
                        } else {
                            ChordFlat flat = new ChordFlat();
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
                            ChordSharp sharp = new ChordSharp();
                            sharp.setArguments(s);
                            signTransaction_2.add(R.id.fragment_container, sharp, "SIGN_2");
                        } else {
                            ChordFlat flat = new ChordFlat();
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
                            ChordSharp sharp = new ChordSharp();
                            sharp.setArguments(s);
                            signTransaction_3.add(R.id.fragment_container, sharp, "SIGN_3");
                        } else {
                            ChordFlat flat = new ChordFlat();
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
                        ChordLedger ledge = new ChordLedger();
                        FragmentTransaction ledgeFrag = fragmentManager.beginTransaction();
                        int ledger_height = getResources().getInteger(R.integer.bot_chord_ledger);
                        first_ledge.putInt("height", ledger_height);
                        ledge.setArguments(first_ledge);
                        ledgeFrag.add(R.id.fragment_container, ledge, "LEDGE");
                        ledgeFrag.commit();
                    }
                    note_name_1 = mp3FileName(chord[0]);
                    note_name_1 = mp3FileName(chord[1]);
                    note_name_1 = mp3FileName(chord[2]);
                }
//                Context context = getApplicationContext();
//                Resources res = context.getResources();
//                int soundId_1 = res.getIdentifier(note_name_1, "raw", context.getPackageName());
//                int soundId_2 = res.getIdentifier(note_name_2, "raw", context.getPackageName());
//                int soundId_3 = res.getIdentifier(note_name_3, "raw", context.getPackageName());
//                Log.d("NOTE", note_name_1);
//
//                mp1.create(ChordsGame.this, soundId_1).start();
//                mp1.create(ChordsGame.this, soundId_2).start();
//                mp1.create(ChordsGame.this, soundId_3).start();

//                if(mp1.isPlaying()){
//                    mp1.stop();
//                }
//                if(mp2.isPlaying()){
//                    mp2.stop();
//                }
//                if(mp3.isPlaying()){
//                    mp3.stop();
//                }
//                try{
//                    mp1.reset();
//                    mp2.reset();
//                    mp3.reset();
//                try{
//                    mp1.reset();
//                    AssetFileDescriptor af1 = getAssets().openFd(note_name_1);
//                    mp1.setDataSource(af1.getFileDescriptor(), af1.getStartOffset(), af1.getLength());
//                    mp1.prepare();
//                    mp1.start();
//                    AssetFileDescriptor af2 = getAssets().openFd(note_name_2);
//                    mp2.setDataSource(af2.getFileDescriptor(), af2.getStartOffset(), af2.getLength());
//                    mp2.prepare();
//                    mp2.start();
//                    AssetFileDescriptor af3 = getAssets().openFd(note_name_3);
//                    mp3.setDataSource(af3.getFileDescriptor(), af3.getStartOffset(), af3.getLength());
//                    mp3.prepare();
//                    mp3.start();
//
//                }catch (IllegalStateException | IOException e) {
//                    e.printStackTrace();
//                }

//                    AssetFileDescriptor af2 = getAssets().openFd(note_name_2);
//                    AssetFileDescriptor af3 = getAssets().openFd(note_name_3);
//                    mp1.setDataSource(af1.getFileDescriptor(), af1.getStartOffset(), af1.getLength());
//                    mp1.prepare();
//                    mp2.setDataSource(af2.getFileDescriptor(), af2.getStartOffset(), af2.getLength());
//                    mp2.prepare();
//                    mp3.setDataSource(af3.getFileDescriptor(), af3.getStartOffset(), af3.getLength());
//                    mp3.prepare();
//
//                    mp1.start();
//                    mp2.start();
//                    mp3.start();
//
//                } catch (IllegalStateException | IOException e) {
//                    e.printStackTrace();
//                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

