package cmsc436.com.musictheory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Vit on 5/15/2016.
 */
public class LedgerFragment extends android.support.v4.app.Fragment {

    public LedgerFragment (){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.ledger_fragment, container, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(432, bundle.getInt("height"));
        view.setLayoutParams(layoutParams);

        return view;
    }

}