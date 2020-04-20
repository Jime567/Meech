package fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ajs.speech.Activities.MainActivity;
import com.ajs.speech.R;

import static com.ajs.speech.Activities.MainActivity.getTheProcessedText;
import static com.ajs.speech.Activities.MainActivity.theText;


public class secondFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Navigation stuff
        ((MainActivity)getActivity()).areWeOnLoading = false;
        ((MainActivity)getActivity()).areWeOnSaves = false;
        ((MainActivity)getActivity()).areWeOnAbout = false;
        ((MainActivity)getActivity()).areWeOnLogIn = false;
        ((MainActivity)getActivity()).areWeOnRegister = false;
        ((MainActivity)getActivity()).areWeOnSeconds = true;


        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                //The purpose of this is to make the back arrow go to home and not to the loading screen
                ((MainActivity) getActivity()).navSecondFragmenttoMain();
                ((MainActivity)getActivity()).areWeOnSeconds = false;

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //These are the controls for the seekbar that makes the text change for the user
        super.onActivityCreated(savedInstanceState);
        SeekBar seekBar = getView().findViewById(R.id.seekBar);
        final String textDos = theText;
        final TextView textView = getView().findViewById(R.id.textView2);
        textView.setText(theText);
        seekBar.setProgress(100);
        final String[] theProcessedText = getTheProcessedText();



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (seekBar.getProgress() > 90) {
                    textView.setText(textDos);
                } else if (seekBar.getProgress() <= 90 && seekBar.getProgress() > 80) {
                    textView.setText(theProcessedText[0].toString());
                } else if (seekBar.getProgress() <= 80 && seekBar.getProgress() > 70) {
                    textView.setText(theProcessedText[1].toString());
                } else if (seekBar.getProgress() <= 70 && seekBar.getProgress() > 60) {
                    textView.setText(theProcessedText[2].toString());
                } else if (seekBar.getProgress() <= 60 && seekBar.getProgress() > 50) {
                    textView.setText(theProcessedText[3].toString());
                } else if (seekBar.getProgress() <= 50 && seekBar.getProgress() > 40) {
                    textView.setText(theProcessedText[4].toString());
                } else if (seekBar.getProgress() <= 40 && seekBar.getProgress() > 30) {
                    textView.setText(theProcessedText[5].toString());
                } else if (seekBar.getProgress() <= 30 && seekBar.getProgress() > 20) {
                    textView.setText(theProcessedText[6].toString());
                } else {
                    textView.setText(theProcessedText[7].toString());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);


    }







    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

        void onFragmentInteraction(Uri uri);

    }
}
