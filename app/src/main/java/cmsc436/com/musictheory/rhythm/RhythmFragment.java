package cmsc436.com.musictheory.rhythm;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import cmsc436.com.musictheory.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RhythmFragment extends Fragment {

    ImageButton play_stop_button;
    ImageView measure_display;
    private View myFragmentView;

    public RhythmFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.rhythm_fragment, container, false);

        Bundle bundle = getArguments();
        String button_value = bundle.getString("button_value");

        measure_display = (ImageView) myFragmentView.findViewById(R.id.measure_image);

        if (button_value.equals("whole")) {
            measure_display.setImageResource(R.drawable.whole_note_measure);
        }else if (button_value.equals("half")){
            measure_display.setImageResource(R.drawable.half_note_measure);
        }else if (button_value.equals("quarter")){
            measure_display.setImageResource(R.drawable.quarter_note_measure);
        }else if (button_value.equals("eighth")){
            measure_display.setImageResource(R.drawable.eighth_note_measure);
        }else if (button_value.equals("sixteenth")){
            measure_display.setImageResource(R.drawable.sixteenth_note_measure);
        }

        play_stop_button = (ImageButton) myFragmentView.findViewById(R.id.play_stop_button);

        play_stop_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Bundle bundle = getArguments();
                String button_value = bundle.getString("button_value");
                MediaPlayer mp;



                if(button_value.equals("whole")) {
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.whole_note);
                }else if (button_value.equals("half")){
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.half_note);
                }else if (button_value.equals("quarter")){
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.quarter_note);
                }else if (button_value.equals("eighth")){
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.eighth_note);
                }else if (button_value.equals("sixteenth")){
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.sixteenth_note);
                }else {
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.blank_120);
                }
                mp.start();

                while(mp.isPlaying())
                {
                    //Do nothing
                }

                mp.release();
            }
        });



        return myFragmentView;
    }

}
