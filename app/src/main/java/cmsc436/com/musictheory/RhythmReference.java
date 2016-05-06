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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm_reference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        wholeNoteButton = (ImageButton) findViewById(R.id.whole_note_button);
        wholeNoteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    RhythmFragment rhythmFragment = new RhythmFragment();
                    fragmentTransaction.add(R.id.rhythm_fragment_container, rhythmFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

            }
        });
    }

}
