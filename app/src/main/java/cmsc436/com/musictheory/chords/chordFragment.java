package cmsc436.com.musictheory.chords;

import android.app.DialogFragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import cmsc436.com.musictheory.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link chordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link chordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class chordFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TextView recipe;
    ImageButton play;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public chordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment chordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static chordFragment newInstance(String param1, String param2) {
        chordFragment fragment = new chordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chord, container, false);

        Bundle bundle = getArguments();
        String button_value = bundle.getString("button_value");

        recipe = (TextView) view.findViewById(R.id.chord_recipe);

        if(button_value.equals("majorTriad")){
            recipe.setText(R.string.major_triad_recipe);
        } else if(button_value.equals("minorTriad")){
            recipe.setText(R.string.minor_triad_recipe);
        } else if(button_value.equals("maj7")){
            recipe.setText(R.string.major_seventh_recipe);
        } else if(button_value.equals("min7")){
            recipe.setText(R.string.minor_seventh_recipe);
        } else if(button_value.equals("dim7")){
            recipe.setText(R.string.diminished_seventh_recipe);
        }

        play = (ImageButton) view.findViewById(R.id.play_button);

        play.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Bundle bundle = getArguments();
                String button_value = bundle.getString("button_value");
                MediaPlayer mp;

                if(button_value.equals("majorTriad")) {
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.majtriad);
                }else if (button_value.equals("minorTriad")){
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.mintriad);
                }else if (button_value.equals("maj7")){
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.maj7);
                }else if (button_value.equals("min7")){
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.min7);
                }else if (button_value.equals("dim7")){
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.dim7);
                }else {
                    mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.dim7);
                }



                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });

                mp.start();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
