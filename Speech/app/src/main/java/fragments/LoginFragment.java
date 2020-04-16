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


public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Navigation stuff
        ((MainActivity)getActivity()).areWeOnLoading = false;
        ((MainActivity)getActivity()).areWeOnSaves = false;
        ((MainActivity)getActivity()).areWeOnAbout = false;
        ((MainActivity)getActivity()).areWeOnLogIn = true;
        ((MainActivity)getActivity()).areWeOnRegister = false;
        ((MainActivity)getActivity()).areWeOnSeconds = false;


    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();

        Button button = (Button)view.findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //closes the keyboard
                final EditText inputText = getView().findViewById(R.id.enterPassword);
                final EditText inputText2 = getView().findViewById(R.id.enterUser);

                inputText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                inputText2.onEditorAction(EditorInfo.IME_ACTION_DONE);


                //collects user input
                final EditText password = getView().findViewById(R.id.enterPassword);

                String passwordText = password.getText().toString();

                final EditText userName = getView().findViewById(R.id.enterUser);
                String userNameText = userName.getText().toString();

                if (!emailCheck(userNameText)) {
                    ((MainActivity)getActivity()).toastInvalidEmail();
                }


                else if (passwordText.length() < 6) {
                    ((MainActivity)getActivity()).passwordTooShort();

                }
                else {
                   ((MainActivity)getActivity()).logIn(userNameText, passwordText);

                }


            }
        });
        return view;
    }
    public boolean emailCheck(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void SignUp(View v) {

    }





    public interface OnFragmentInteractionListener {


        void onFragmentInteraction(Uri uri);

    }
}
