package cmsc436.com.musictheory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by twinniss on 5/16/2016.
 */
public class WholeNoteFragment  extends android.support.v4.app.Fragment {

    public WholeNoteFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.whole_note_fragment, container, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(450, bundle.getInt("height"));
        view.setLayoutParams(layoutParams);

        return view;
    }


}
