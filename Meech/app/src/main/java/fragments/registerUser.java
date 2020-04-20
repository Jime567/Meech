package fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.ajs.speech.Activities.MainActivity;
import com.ajs.speech.R;
import com.google.firebase.auth.FirebaseAuth;


public class registerUser extends Fragment {

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Navigation stuff
        ((MainActivity)getActivity()).areWeOnLoading = false;
        ((MainActivity)getActivity()).areWeOnSaves = false;
        ((MainActivity)getActivity()).areWeOnAbout = false;
        ((MainActivity)getActivity()).areWeOnLogIn = false;
        ((MainActivity)getActivity()).areWeOnRegister = true;
        ((MainActivity)getActivity()).areWeOnSeconds = false;

    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_register_user, container, false);
        mAuth = FirebaseAuth.getInstance();

        Button button = (Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //closes the keyboard
                final EditText inputText = getView().findViewById(R.id.password);
                final EditText inputText2 = getView().findViewById(R.id.confirmPassword);
                final EditText inputText3 = getView().findViewById(R.id.userName);
                inputText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                inputText2.onEditorAction(EditorInfo.IME_ACTION_DONE);
                inputText3.onEditorAction(EditorInfo.IME_ACTION_DONE);

                //collects user input
                final EditText password = getView().findViewById(R.id.confirmPassword);
                final EditText confPassword = getView().findViewById(R.id.password);

                String passwordText = password.getText().toString();
                String confPasswordText = confPassword.getText().toString();

                final EditText userName = getView().findViewById(R.id.userName);
                String userNameText = userName.getText().toString();

                if (!emailCheck(userNameText)) {
                    ((MainActivity)getActivity()).toastInvalidEmail();
                }

                else if (!confPasswordText.equals(passwordText)) {
                    ((MainActivity)getActivity()).passwordInvalid();

                }
                else if (confPasswordText.length() < 6) {
                    ((MainActivity)getActivity()).passwordTooShort();

                }
                else {
                    ((MainActivity)getActivity()).makeAccount(userNameText, passwordText);

                }


            }
        });
    return view;
    }
    public boolean emailCheck(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }







    public interface OnFragmentInteractionListener {


        void onFragmentInteraction(Uri uri);

    }
}
