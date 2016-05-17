package cmsc436.com.musictheory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by twinniss on 5/17/2016.
 */
public class ChordSharp extends android.support.v4.app.Fragment {

    public ChordSharp(){

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
