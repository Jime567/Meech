package com.auxbrain.speech.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.os.AsyncTask;
import android.view.MenuItem;
import androidx.navigation.ui.NavigationUI;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.auxbrain.speech.R;
import com.google.android.material.navigation.NavigationView;

import fragments.mainFragment;
import fragments.processText;
import fragments.secondFragment;

public class MainActivity extends AppCompatActivity implements secondFragment.OnFragmentInteractionListener, mainFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public NavController navController;
    public static String theText = "";
    public boolean areWeOnSeconds = false;
    public boolean areWeOnLoading = false;
    public boolean areWeOnAbout = false;


    public void setText(String newText) {
        theText = newText;
    }

    public static String getText() {
        return theText;
    }

    private static String[] theProcessedText = new String[8];

    public static void setTheProcessedText(String[] newText) {
        theProcessedText = newText;
    }

    public static String[] getTheProcessedText() {
        return theProcessedText;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);

        drawerLayout.closeDrawers();

        int id = item.getItemId();

        switch (id) {

            case R.id.homeNav:
                if (areWeOnLoading == true) {
                    areWeOnLoading = false;
                    navController.navigate(R.id.action_processText_to_mainFragment2);
                }
                else if (areWeOnSeconds == true) {
                    areWeOnSeconds = false;
                    navController.navigate(R.id.action_secondFragment_to_mainFragment2);
                }
                else if (areWeOnAbout == true) {
                    areWeOnAbout = false;
                    navController.navigate(R.id.action_about_to_mainFragment2);
                }

                break;



        }
        return true;

    }
    public void onOpen(View v) {
        drawerLayout = findViewById(R.id.drawerLayout);

        drawerLayout.openDrawer(navigationView);
    }
    public void aboutMenu(View v) {
        navController.navigate(R.id.action_mainFragment2_to_about);
        areWeOnAbout = true;
    }


    public void navMaintoProcess() {
        navController.navigate(R.id.action_mainFragment2_to_processText);
        areWeOnLoading = true;
    }

    public void navProcesstoSecondFragment() {
        navController.navigate(R.id.action_processText_to_secondFragment);
        areWeOnSeconds = true;
    }

    public void navSecondFragmenttoMain() {
        navController.navigate(R.id.action_secondFragment_to_mainFragment2);
        areWeOnSeconds = false;
    }
//Insures the user never hits the back arrow to the loading screen
    //also controls back arrow in all scenarios apparently
   /* @Override
    public void onBackPressed() {
        if (areWeOnSeconds == true) {
        areWeOnSeconds = false;
        navSecondFragmenttoMain();
        }

        else if (areWeOnAbout = true) {
            areWeOnAbout = false;
            navController.navigate(R.id.action_about_to_mainFragment2);

        }

        else if (areWeOnLoading = true) {
            areWeOnLoading = false;
            navController.navigate(R.id.action_processText_to_mainFragment2);
        }
    }
*/


    public void test() {
        Context context = getApplicationContext();
        CharSequence toot = "Please Enter More Text";
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



}





