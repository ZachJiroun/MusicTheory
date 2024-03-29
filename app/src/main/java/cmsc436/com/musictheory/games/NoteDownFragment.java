package cmsc436.com.musictheory.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cmsc436.com.musictheory.R;

/**
 * Created by Vit on 5/14/2016.
 */
public class NoteDownFragment extends android.support.v4.app.Fragment {

    public NoteDownFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.note_down_fragment, container, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(480, bundle.getInt("height")+155);
        view.setLayoutParams(layoutParams);

        return view;
    }

}
