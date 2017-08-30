package ohlsen.kindermathe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link nameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link nameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nameFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public nameFragment() {
        // Required empty public constructor
    }


    public static nameFragment newInstance() {
        nameFragment fragment = new nameFragment();
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
        return inflater.inflate(R.layout.fragment_name, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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
    public void onResume() {
        super.onResume();
        Button weiterBtn = (Button) getActivity().findViewById(R.id.weiterBtn);
        if(weiterBtn != null) {
            weiterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickBtn(view);
                }
            });
        }
        ImageButton settingsBtn = (ImageButton) getActivity().findViewById(R.id.settingsBtn);
        if(settingsBtn != null) {
            settingsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickBtn(view);
                }
            });
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

    private void onClickBtn(View v) {
        if(v != null) {
            switch(v.getId()) {
                case R.id.weiterBtn:
                    weiterBtnClick();
                    break;
                case R.id.settingsBtn:
                    settingsBtnClicked();
                    break;
            }
        }
    }

    private void settingsBtnClicked() {
        this.getFragmentManager().beginTransaction()
                .replace(R.id.container, new SettingsFragment())
                .addToBackStack(null)
                .commit();
    }

    private void weiterBtnClick() {
        //get players name and save it to ApplicationManager
        EditText textField = (EditText) getActivity().findViewById(R.id.txtName);
        if(textField != null && textField.getText() != null) {
            ApplicationManager.getInstance().setPlayerName(textField.getText().toString());
        }
        this.getFragmentManager().beginTransaction()
                .replace(R.id.container, rechner.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
