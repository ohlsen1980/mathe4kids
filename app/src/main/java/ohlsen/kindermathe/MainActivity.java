package ohlsen.kindermathe;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements rechner.OnFragmentInteractionListener, nameFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            this.getFragmentManager().beginTransaction()
                    .replace(R.id.container, nameFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
