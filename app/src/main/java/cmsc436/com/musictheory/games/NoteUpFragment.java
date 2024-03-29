package cmsc436.com.musictheory.games;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.RelativeLayout;

import cmsc436.com.musictheory.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteUpFragment extends android.support.v4.app.Fragment {

    public NoteUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.note_up_fragment, container, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(700, bundle.getInt("height"));
        view.setLayoutParams(layoutParams);

        return view;
    }

}
