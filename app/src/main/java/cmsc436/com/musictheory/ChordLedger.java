package cmsc436.com.musictheory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by twinniss on 5/16/2016.
 */
public class ChordLedger extends android.support.v4.app.Fragment {

    public ChordLedger(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.chord_ledger, container, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(385, bundle.getInt("height"));
        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(432, getResources().getInteger(R.integer.bottom_ledger));
        view.setLayoutParams(layoutParams);

        return view;
    }

}
