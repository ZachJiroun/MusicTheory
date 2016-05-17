package cmsc436.com.musictheory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Vit on 5/14/2016.
 */
public class SharpFragment extends android.support.v4.app.Fragment {

    public SharpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.sharp_fragment, container, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(300, bundle.getInt("height") + 70);
        view.setLayoutParams(layoutParams);

        return view;
    }

}
