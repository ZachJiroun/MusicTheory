package cmsc436.com.musictheory;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    HashMap<String, Integer> heightMap = new HashMap<>();
    Button st, playback;
    String filename = "";


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
        return map;
    }

    public int generateInterval(){
        int min = 0;
        int max = 49;
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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final MediaPlayer mp = new MediaPlayer();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        st = (Button) findViewById(R.id.st);
        st.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int rand = generateInterval();

                HashMap<Integer, String> map = generateMap();
                FragmentManager fragmentManager = getSupportFragmentManager();
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
                int height = (int) dpToPixel(heightMap.get(key));
                args.putInt("height", height);


                if(mp.isPlaying()){
                    mp.stop();
                }
                try{
                    //note stem is up
                    if((25 <= rand && rand <= 34) || rand <= 13){
                        NoteUpFragment note = new NoteUpFragment();
                        Log.d("UP", Integer.toString(rand));
                        note.setArguments(args);
                        fragmentTransaction.add(R.id.fragment_container, note, "NOTE");
                        if(rand <= 4 || rand == 25 || rand == 26){
                            FragmentTransaction ledgeFrag = fragmentManager.beginTransaction();
                            LedgerFragment ledge = new LedgerFragment();
                            Log.d("Height", Integer.toString(heightMap.get(key)));
                            ledge.setArguments(args);
                            ledgeFrag.add(R.id.fragment_container, ledge, "LEDGE");
                            Log.d("Ledger", "Print Ledger Here~~~~~~~~~~~~~~~~~");
                            ledgeFrag.commit();

                            //adding second ledger line
                            if(rand == 0 || rand == 1){
                                FragmentTransaction sl = fragmentManager.beginTransaction();
                                LedgerFragment second_ledge = new LedgerFragment();
                                args.putInt("height", height - 30);
                                second_ledge.setArguments(args);
                                sl.add(R.id.fragment_container, second_ledge, "LEDGE_TWO");
                                sl.commit();
                            }
                        }
                        //note stem is down
                    } else {
                        if (rand >= 45){
                            FragmentTransaction ledgeFrag = fragmentManager.beginTransaction();
                            LedgerFragment ledge = new LedgerFragment();
                            Log.d("Height", Integer.toString(heightMap.get(key)));
                            ledge.setArguments(args);
                            ledgeFrag.add(R.id.fragment_container, ledge, "LEDGE");
                            Log.d("Ledger", "Print Ledger Here~~~~~~~~~~~~~~~~~");
                            ledgeFrag.commit();
                            if(rand == 48 || rand == 49){
                                FragmentTransaction sl = fragmentManager.beginTransaction();
                                LedgerFragment second_ledge = new LedgerFragment();
                                args.putInt("height", height - 30);
                                second_ledge.setArguments(args);
                                sl.add(R.id.fragment_container, second_ledge, "LEDGE_TWO");
                                sl.commit();
                            }
                        }
                        NoteDownFragment note = new NoteDownFragment();
                        Log.d("DOWN", Integer.toString(rand));
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
                    Log.d(key, key);
                    mp.reset();
                    filename = mp3FileName(key);
                    Log.d(filename, filename);
                    AssetFileDescriptor afd = getAssets().openFd(filename);
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }

                }


        });
//        st = (Button) findViewById(R.id.st);
//        st.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
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
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
