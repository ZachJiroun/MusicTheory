package cmsc436.com.musictheory;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.view.ViewStub;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class noteFragment extends android.support.v4.app.Fragment {

    HashMap<String, Integer> heightMap = new HashMap<>();

    //Converts device pixels to regular pixels to draw
    private float dpToPixel(float dp) {
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi/160f);
        return px;
    }

    public static noteFragment newInstance(int index){
        noteFragment n = new noteFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        n.setArguments(args);
        return n;
    }

    public int generateInterval(){
        int min = 0;
        int max = 49;
        Random seed = new Random();
        return seed.nextInt(max - min) + min;
    }



    public HashMap<Integer, String> generateMap(){
        char letter = 'C';
        int register = 3;
        String sign = "";
        HashMap<Integer, String> map = new HashMap<Integer, String>();
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
            Log.d(name, name);
            Log.d(name, Integer.toString(height));
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


    public noteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //reset view by removing fragment
        //getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragment_container)).commit();
        //ViewStub stub = findById(R.layout.fragment_note, container, false);

        HashMap<Integer, String> map = generateMap();
        String key = map.get(generateInterval());
        int height = (int) dpToPixel(heightMap.get(key));
        Log.d("random", String.valueOf(height));
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) dpToPixel(144), height);
        view.setLayoutParams(layoutParams);

        return view;
    }

}
