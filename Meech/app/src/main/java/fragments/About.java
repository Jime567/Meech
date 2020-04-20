package fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajs.speech.Activities.MainActivity;
import com.ajs.speech.R;


public class About extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Navigation stuff
        ((MainActivity)getActivity()).areWeOnLoading = false;
        ((MainActivity)getActivity()).areWeOnSaves = false;
        ((MainActivity)getActivity()).areWeOnAbout = true;
        ((MainActivity)getActivity()).areWeOnLogIn = false;
        ((MainActivity)getActivity()).areWeOnRegister = false;
        ((MainActivity)getActivity()).areWeOnSeconds = false;

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                //The purpose of this is to make the back arrow go to home and not to the loading screen
                ((MainActivity) getActivity()).navAbouttoMain();
                ((MainActivity)getActivity()).areWeOnAbout = false;

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }










    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);


    }







    public interface OnFragmentInteractionListener {


        void onFragmentInteraction(Uri uri);

    }
}
