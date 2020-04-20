package com.ajs.speech.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ajs.speech.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import fragments.mainFragment;
import fragments.secondFragment;
import com.ajs.speech.Activities.meechInit;

public class MainActivity extends AppCompatActivity implements secondFragment.OnFragmentInteractionListener, mainFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public NavController navController;
    public static String theText = "";
    public static String tempTitle;
    public static String tempText;
    public static String tempUniqId;

    //boolean values to allow for correct navigation
    public boolean areWeOnSeconds = false;
    public boolean areWeOnLoading = false;
    public boolean areWeOnAbout = false;
    public boolean areWeOnLogIn = false;
    public boolean areWeOnRegister = false;
    public boolean areWeOnSaves = false;


    //Firebase Stuff
    private FirebaseAuth mAuth;



    public void setText(String newText) {
        theText = newText;
    }

    public static String getText() {
        return theText;
    }

    public void setTempText(String newTempText) { tempText = newTempText;
    }

    public static String getTempText() {
        return tempText;
    }

    public void setTempTitle(String newText) {
        tempTitle = newText;
    }

    public static String getTempTitle() {
        return tempTitle;
    }

    private static String[] theProcessedText = new String[8];

    public static void setTheProcessedText(String[] newText) {
        theProcessedText = newText;
    }

    public static String[] getTheProcessedText() {
        return theProcessedText;
    }

    public static void setTempUniqId(String newText) {
        tempUniqId = newText;
    }

    public static String getTempUniqId() {
        return tempUniqId;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firebase stuff
        mAuth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        setupNavigation();




    }


    @Override
    public void onFragmentInteraction(Uri uri) {
    }


    private  void setupNavigation() {


        drawerLayout = findViewById(R.id.drawerLayout);

        navigationView = findViewById(R.id.navigationView);

        navController = Navigation.findNavController(this, R.id.demo_nav_host_fragment);

        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
        if (mAuth.getCurrentUser() == null) {
            Menu menu = navigationView.getMenu();

            MenuItem navSaves = menu.findItem(R.id.savesNav);
            navSaves.setVisible(false);
            MenuItem navLogIn = menu.findItem(R.id.loginNav);
            navLogIn.setVisible(true);
            MenuItem signOut = menu.findItem(R.id.signOut);
            signOut.setVisible(false);
        }
        else {
            Menu menu = navigationView.getMenu();
            MenuItem navSaves = menu.findItem(R.id.savesNav);
            navSaves.setVisible(true);
            MenuItem signOut = menu.findItem(R.id.signOut);
            signOut.setVisible(true);
            MenuItem navLogIn = menu.findItem(R.id.loginNav);
            navLogIn.setVisible(false);


        }
    }

    public void toastInvalidEmail() {
        Toast.makeText((getApplicationContext()),"Email Not Valid", Toast.LENGTH_LONG).show();

    }

    public void passwordInvalid() {
        Toast.makeText(getApplicationContext(), "Passwords do not Match", Toast.LENGTH_LONG).show();

    }

    public void passwordTooShort() {
        Toast.makeText(getApplicationContext(), "Password too short: minimum of 6 Characters ", Toast.LENGTH_LONG).show();

    }

    //take inputted infor to log in user
public void logIn(final String usernameText,final String passwordText){
    mAuth = FirebaseAuth.getInstance();

    mAuth.signInWithEmailAndPassword(usernameText, passwordText)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                        navController.navigate(R.id.action_loginFragment_to_mainFragment2);
                        areWeOnLogIn = false;
                        Menu menu = navigationView.getMenu();
                        MenuItem navLog = menu.findItem(R.id.loginNav);
                        navLog.setVisible(false);
                        MenuItem navSaves = menu.findItem(R.id.savesNav);
                        navSaves.setVisible(true);
                        MenuItem signOut = menu.findItem(R.id.signOut);
                        signOut.setVisible(true);

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Username or Password Not Recognized", Toast.LENGTH_LONG).show();
                    }
                }
            });



}
//takes inputted info to make a new user
        public void makeAccount(final String userNameText, final String passwordText) {

        mAuth.createUserWithEmailAndPassword(userNameText, passwordText).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        FirebaseDatabase database =  FirebaseDatabase.getInstance();
                        String userId = user.getUid();
                        DatabaseReference mRef =  database.getReference().child("Users").child(userId);
                        User dude = new User(userNameText, "blank", "blank");
                        mRef.setValue(dude);

                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                        navController.navigate(R.id.action_registerUser_to_loginFragment);
                        areWeOnLogIn = true;
                        areWeOnRegister = false;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });




        }

//Controls for the navigation side drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);

        drawerLayout.closeDrawers();

        int id = item.getItemId();

        switch (id) {
            case R.id.signOut:

                Menu menu = navigationView.getMenu();

                MenuItem navSaves = menu.findItem(R.id.savesNav);
                navSaves.setVisible(false);
                MenuItem navLogIn = menu.findItem(R.id.loginNav);
                navLogIn.setVisible(true);
                MenuItem signOut = menu.findItem(R.id.signOut);
                signOut.setVisible(false);

                if (areWeOnLoading == true) {
                    areWeOnLoading = false;
                    navController.navigate(R.id.action_processText_to_mainFragment2);
                } else if (areWeOnSeconds == true) {
                    areWeOnSeconds = false;
                    navController.navigate(R.id.action_secondFragment_to_mainFragment2);
                } else if (areWeOnAbout == true) {
                    areWeOnAbout = false;
                    navController.navigate(R.id.action_about_to_mainFragment2);
                } else if (areWeOnRegister == true) {
                    areWeOnRegister = false;
                    navController.navigate(R.id.action_registerUser_to_mainFragment2);

                } else if (areWeOnSaves == true) {
                    areWeOnSaves = false;
                    navController.navigate(R.id.action_saves_to_mainFragment2);
                } else if (areWeOnLogIn == true) {
                    areWeOnLogIn = false;
                    navController.navigate(R.id.action_loginFragment_to_mainFragment2);
                }

                mAuth.signOut();
                Toast.makeText((getApplicationContext()),"Signed Out Successfully", Toast.LENGTH_LONG).show();

                break;

            case R.id.homeNav:

                if (areWeOnLoading == true) {
                    areWeOnLoading = false;
                    navController.navigate(R.id.action_processText_to_mainFragment2);
                } else if (areWeOnSeconds == true) {
                    areWeOnSeconds = false;
                    navController.navigate(R.id.action_secondFragment_to_mainFragment2);
                } else if (areWeOnAbout == true) {
                    areWeOnAbout = false;
                    navController.navigate(R.id.action_about_to_mainFragment2);
                } else if (areWeOnRegister == true) {
                    areWeOnRegister = false;
                    navController.navigate(R.id.action_registerUser_to_mainFragment2);

                } else if (areWeOnSaves == true) {
                    areWeOnSaves = false;
                    navController.navigate(R.id.action_saves_to_mainFragment2);
                } else if (areWeOnLogIn == true) {
                    areWeOnLogIn = false;
                    navController.navigate(R.id.action_loginFragment_to_mainFragment2);
                }


                break;
            case R.id.registerNav:
               if (areWeOnRegister == true) {
                   break;
               }
                else if (areWeOnLoading == true) {
                    areWeOnLoading = false;
                    areWeOnRegister = true;
                    navController.navigate(R.id.action_processText_to_registerUser);
                } else if (areWeOnSeconds == true) {
                    areWeOnSeconds = false;
                    areWeOnRegister = true;
                    navController.navigate(R.id.action_secondFragment_to_registerUser);
                } else if (areWeOnAbout == true) {
                    areWeOnAbout = false;
                    areWeOnRegister = true;
                    navController.navigate(R.id.action_about_to_registerUser);
                } else if (areWeOnLogIn == true) {
                    areWeOnLogIn = false;
                    areWeOnRegister = true;
                    navController.navigate(R.id.action_loginFragment_to_registerUser2);
                } else if (areWeOnSaves == true) {
                    areWeOnSaves = false;
                    navController.navigate(R.id.action_saves_to_registerUser);
                } else {
                    areWeOnRegister = true;
                    navController.navigate(R.id.action_mainFragment2_to_registerUser);

                }
                areWeOnRegister = true;
                break;

            case R.id.loginNav:
                if (areWeOnLogIn == true) {
                    break;
                }
               else if (areWeOnLoading == true) {
                    areWeOnLoading = false;

                    navController.navigate(R.id.action_processText_to_loginFragment);
                } else if (areWeOnSeconds == true) {
                    areWeOnSeconds = false;

                    navController.navigate(R.id.action_secondFragment_to_loginFragment);
                } else if (areWeOnAbout == true) {
                    areWeOnAbout = false;

                    navController.navigate(R.id.action_about_to_loginFragment);
                } else if (areWeOnRegister == true) {
                    areWeOnRegister = false;

                    navController.navigate(R.id.action_registerUser_to_loginFragment);
                } else if (areWeOnSaves == true) {
                    areWeOnSaves = false;
                    navController.navigate(R.id.action_saves_to_loginFragment);
                } else {
                    areWeOnLogIn = true;
                    navController.navigate(R.id.action_mainFragment2_to_loginFragment);
                }
                areWeOnLogIn = true;
                break;


            case R.id.savesNav:
                if (areWeOnSaves == true) {
                    break;
                }
                else if (areWeOnLoading == true) {
                    areWeOnLoading = false;

                    navController.navigate(R.id.action_processText_to_saves);
                } else if (areWeOnSeconds == true) {
                    areWeOnSeconds = false;

                    navController.navigate(R.id.action_secondFragment_to_saves);
                } else if (areWeOnAbout == true) {
                    areWeOnAbout = false;

                    navController.navigate(R.id.action_about_to_saves);
                } else if (areWeOnRegister == true) {
                    areWeOnRegister = false;

                    navController.navigate(R.id.action_registerUser_to_saves);
                } else {
                    areWeOnSaves = true;
                    navController.navigate(R.id.action_mainFragment2_to_saves);
                }
                areWeOnSaves = true;
                break;
        }



        return true;

    }
    public void onOpen(View v) {
        drawerLayout = findViewById(R.id.drawerLayout);

        drawerLayout.openDrawer(navigationView);
    }
    public void aboutMenu(View v) {
        if (areWeOnSeconds == true) {
            areWeOnSeconds = false;
            areWeOnAbout = true;
            navController.navigate(R.id.action_secondFragment_to_about);
        }
        else if (areWeOnLoading == true) {
            areWeOnLoading = false;
            areWeOnAbout = true;
            navController.navigate(R.id.action_processText_to_about);
        }
        else if (areWeOnRegister == true) {
            navController.navigate(R.id.action_registerUser_to_about);
            areWeOnRegister = false;
            areWeOnAbout = true;

        }

        else if (areWeOnLogIn == true) {
            areWeOnLogIn = false;
            navController.navigate(R.id.action_loginFragment_to_about);
        }

        else if (areWeOnSaves == true) {
            navController.navigate(R.id.action_saves_to_about);
        }

        else if (areWeOnAbout != true) {
        navController.navigate(R.id.action_mainFragment2_to_about);
        areWeOnAbout = true;}




        areWeOnAbout = true;
    }


    public void navMaintoProcess() {
        navController.navigate(R.id.action_mainFragment2_to_processText);

        areWeOnLoading = true;
    }

    public void navProcesstoSecondFragment() {
        navController.navigate(R.id.action_processText_to_secondFragment);
        areWeOnLoading = false;
        areWeOnSeconds = true;
    }

    public void navSecondFragmenttoMain() {
        navController.navigate(R.id.action_secondFragment_to_mainFragment2);
        areWeOnSeconds = false;
    }

    public void navProcesstoMain() {
        areWeOnLoading = false;
        navController.navigate(R.id.action_processText_to_mainFragment2);
    }

    public void navSavestoMain() {
        areWeOnSaves = false;
        navController.navigate(R.id.action_saves_to_mainFragment2);
    }
    public void navSavestoProcess(String text) {
        areWeOnSaves = false;
        areWeOnLoading = true;
        setText(text + " ");
        navController.navigate(R.id.action_saves_to_processText);

    }
    public void navAbouttoMain() {
        navController.navigate(R.id.action_about_to_mainFragment2);
        areWeOnAbout = false;
    }

    public void test1() {
        Context context = getApplicationContext();
        CharSequence toot = "Please Enter More Text (min 3 Characters)";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, toot, duration);
        toast.show();
    }


    public void test() {
        Context context = getApplicationContext();
        CharSequence toot = "Please Enter More Text (min 10 Characters)";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, toot, duration);
        toast.show();
    }

    public void processMethod(String text) {

//TODO get all the dumb seekbar stuff in later
if (areWeOnLoading == true) {
    navProcesstoSecondFragment();
    areWeOnLoading = false;
}
        areWeOnSeconds = true;




    }

public void createMeech() {
    final EditText taskEditText = new EditText(this);

    AlertDialog dialog = new AlertDialog.Builder(this)
            .setTitle("Add a new Meech")
            .setMessage("Give your Meech a title")
            .setView(taskEditText)
            .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String title = String.valueOf(taskEditText.getText());
                    if (title.length() < 3) {
                       test1();
                    }
                    else {
                        setTempTitle(title);
                        String paste = pasteInMeech();
                    }


                }
            })
            .setNegativeButton("Cancel", null)
            .create();
    dialog.show();






}

public String pasteInMeech() {
    final String[] paste = {""};
    final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout_dialog, null);
    final EditText taskEditText = (EditText)customLayout.findViewById(R.id.customEditText);

    AlertDialog tim = new AlertDialog.Builder(this)
            .setTitle("Add a new Meech")
            .setMessage("Paste in the text")
            .setView(customLayout)
            .setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface tim, int which) {
                    String task = String.valueOf(taskEditText.getText());
                    int t = 0;
                    String temp = task.replaceAll("\\s", "");

                    if (task.length() < 10 || temp.length() < 10) {
                        test();
                    }
                    else {


                        paste[0] = task;
                        setTempText(task);


                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        String key = database.getReference("bio").push().getKey();
                        setTempUniqId(key);

                        meechInit meech = new meechInit();
                        meech.setTitle(getTempTitle());
                        meech.setContent(getTempText());
                        meech.setUniqId(tempUniqId);

                        FirebaseUser user = mAuth.getInstance().getCurrentUser();
                        String userId = user.getUid();

                        DatabaseReference mRef = database.getReference().child("Users").child(userId).child("bio").child(key);
                        mRef.setValue(meech);
                    }
                }
            })
            .setNegativeButton("Cancel", null)
            .create();
    tim.show();



return paste[0].toString();
}


}








