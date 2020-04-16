package fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.ajs.speech.Activities.MainActivity;
import com.ajs.speech.R;


public class mainFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Navigation stuff
        ((MainActivity)getActivity()).areWeOnLoading = false;
        ((MainActivity)getActivity()).areWeOnSaves = false;
        ((MainActivity)getActivity()).areWeOnAbout = false;
        ((MainActivity)getActivity()).areWeOnLogIn = false;
        ((MainActivity)getActivity()).areWeOnRegister = false;
        ((MainActivity)getActivity()).areWeOnSeconds = false;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button)view.findViewById(R.id.submitText);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText inputText = getView().findViewById(R.id.textInputBox);

                String text = inputText.getText().toString();
                //closes the keyboard
                inputText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                if (text.length() < 13) {
                    ((MainActivity)getActivity()).test();

                }

                else {


                    ((MainActivity) getActivity()).setText(text);
                    ((MainActivity) getActivity()).navMaintoProcess();

                }

            }
        });


    return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}