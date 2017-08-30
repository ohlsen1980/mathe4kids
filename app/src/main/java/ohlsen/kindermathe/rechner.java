package ohlsen.kindermathe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link rechner.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link rechner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rechner extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView aufgabeTxt = null;
    private EditText ergebnisTxt = null;
    private Aufgabe aktuelleAufgabe = null;

    public rechner() {
        // Required empty public constructor
    }


    public static rechner newInstance() {
        rechner fragment = new rechner();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rechner, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView txtView = (TextView) getActivity().findViewById(R.id.anspracheTxt);
        if(txtView != null) {
            txtView.setText(getResources().getString(R.string.hello_blank_fragment) + " " + ApplicationManager.getInstance().getPlayerName());
        }
        this.aufgabeTxt = (TextView) getActivity().findViewById(R.id.aufgabeTxt);
        this.ergebnisTxt = (EditText) getActivity().findViewById(R.id.ergebnisTxt);
        this.ergebnisTxt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                onKeyPressed(view, i, keyEvent);
                return false;
            }
        });
        generateNewAufgabe();
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
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void onKeyPressed(View view, int i, KeyEvent keyEvent) {
        if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                && (i == KeyEvent.KEYCODE_ENTER)) {
            // Perform action on Enter key press
            checkErgebnis();
        }
    }

    private void generateNewAufgabe() {
        this.aktuelleAufgabe = AufgabenFactory.getInstance(getActivity()).getAufgabe();
        this.aufgabeTxt.setText(this.aktuelleAufgabe.toString());
    }

    private void checkErgebnis() {
        if(this.ergebnisTxt.getText() != null){
            int ergebnis = Integer.parseInt(this.ergebnisTxt.getText().toString());
            if(ergebnis == this.aktuelleAufgabe.getErgebnis()) {
                Toast.makeText(getContext(), "Prima " +ApplicationManager.getInstance().getPlayerName() + ", weiter so!", Toast.LENGTH_LONG).show();
                generateNewAufgabe();
                this.ergebnisTxt.setText("");
            }
            else {
                Toast.makeText(getContext(), "Leider falsch, versuche es noch einmal!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
