package cmsc436.com.musictheory;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RhythmReference extends AppCompatActivity {

    ImageButton wholeNoteButton;
    ImageButton wholeRestButton;
    ImageButton halfNoteButton;
    ImageButton halfRestButton;
    ImageButton quarterNoteButton;
    ImageButton quarterRestButton;
    ImageButton eighthNoteButton;
    ImageButton eighthRestButton;
    ImageButton sixteenthNoteButton;
    ImageButton sixteenthRestButton;
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm_reference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = new Bundle();
        bundle.putString("button_value", "blank");

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RhythmFragment rhythmFragment = new RhythmFragment();
        rhythmFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        wholeNoteButton = (ImageButton) findViewById(R.id.whole_note_button);
        wholeNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "whole");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    RhythmFragment rhythmFragment = new RhythmFragment();
                    rhythmFragment.setArguments(bundle);
                    fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

            }
        });

        wholeRestButton = (ImageButton) findViewById(R.id.whole_rest_button);
        wholeRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "whole");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        halfNoteButton = (ImageButton) findViewById(R.id.half_note_button);
        halfNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "half");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        halfRestButton = (ImageButton) findViewById(R.id.half_rest_button);
        halfRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "half");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        quarterNoteButton = (ImageButton) findViewById(R.id.quarter_note_button);
        quarterNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "quarter");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        quarterRestButton = (ImageButton) findViewById(R.id.quarter_rest_button);
        quarterRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "quarter");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        eighthNoteButton = (ImageButton) findViewById(R.id.eighth_note_button);
        eighthNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "eighth");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        eighthRestButton = (ImageButton) findViewById(R.id.eighth_rest_button);
        eighthRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "eighth");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        sixteenthNoteButton = (ImageButton) findViewById(R.id.sixteenth_note_button);
        sixteenthNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "sixteenth");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        sixteenthRestButton = (ImageButton) findViewById(R.id.sixteenth_rest_button);
        sixteenthRestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("button_value", "sixteenth");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RhythmFragment rhythmFragment = new RhythmFragment();
                rhythmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


    }

    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
