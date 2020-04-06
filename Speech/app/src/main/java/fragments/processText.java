package fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.auxbrain.speech.Activities.MainActivity;
import com.auxbrain.speech.R;

import org.w3c.dom.Text;

import static com.auxbrain.speech.Activities.MainActivity.theText;


public class processText extends Fragment {





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String text = ((MainActivity) getActivity()).getText();
        TextView progressText = getView().findViewById(R.id.textView4);
        getContext();
        new background(getContext()).execute();



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_process_text, container, false);



        return view;


    }


    //background task class
   public class background extends AsyncTask<Void, Integer, Void> {
        int n = 0;
        Context context;
//constructor allows us to get the context so we can edit the textView
        public background(Context context) {

            this.context = context;

        }
        TextView progressText = getView().findViewById(R.id.textView4);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            final String text = MainActivity.getText();

            String[] list = new String[8];
            final String one = generateLevel1(text);
            list[0] = one;
            onProgressUpdate(n);
            final String two = generateLevel2(text);
            list[1] = two;
            onProgressUpdate(n);

            final String three = generateLevel3(text);
            list[2] = three;
            onProgressUpdate(n);

            final String four = generateLevel4(text);
            list[3] = four;
            onProgressUpdate(n);

            final String five = generateLevel5(text);
            list[4] = five;
            onProgressUpdate(n);

            final String six = generateLevel6(text);
            list[5] = six;
            onProgressUpdate(n);

            final String seven = generateLevel7(text);
            list[6] = seven;
            onProgressUpdate(n);

            final String eight = generateFinalLevel(text);
            list[7] = eight;
            final String textDos = theText;

            onProgressUpdate(n);

            ((MainActivity)getActivity()).setTheProcessedText(list);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);



            if (n == 0) {
                progressText.setText("1/8");

            }
            else if (n == 1) {
                progressText.setText("2/8");
            }
            else if (n == 2) {
                progressText.setText("3/8");
            }
            else if (n == 3) {
                progressText.setText("4/8");
            }
            else if (n == 4) {
                progressText.setText("5/8");
            }
            else if (n == 5) {
                progressText.setText("6/8");
            }
            else if (n == 6) {
                progressText.setText("7/8");
            }

            else {
                progressText.setText("8/8");
            }

            n+=1;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ((MainActivity)getActivity()).processMethod(theText);


        }

    }



    public static boolean primeCheck(int num) {
        int i = 2;
        boolean prime = true;
        if (num > 3 || num == 1) {
            while (i <= num / 2) {
                if (num % i == 0) {
                    prime = false;
                    break;
                }
                i++;
            }
        }
        else {
            prime = false;
        }
        return prime;
    } //end of primeCheck()

    public static boolean primeCheckWOut1(int num) {
        int i = 2;
        boolean prime = true;
        if (num > 3) {
            while (i <= num / 2) {
                if (num % i == 0) {
                    prime = false;
                    break;
                }
                i++;
            }
        }
        else {
            prime = false;
        }
        return prime;
    } //end of primeCheckWOut1()

    public static String[] multipleThree(String[] inputArray) {
        int amountThree = (inputArray.length) / 3;
        String[] outputArray = new String[amountThree];
        int j = 0;
        int i = 0;
        for (i = 0; i < inputArray.length && j < amountThree; i = i + 3) {

            outputArray[j] = inputArray[i];
            j += 1;

        }
        return outputArray;
    }

    public static String[] multipleTwo(String[] inputArray) {
        int counter = 0;
        for (int l = 0; l < inputArray.length; l++) {
            if (l % 3 == 0) {

            }
            else if (l % 2 == 0) {
                counter += 1;
            }
        }
        int amountTwo = counter;
        String[] outputArray = new String[amountTwo];
        int j = 0;
        int i = 0;
        for (i = 0; i < inputArray.length && j < amountTwo; i = i + 2) {
            if (i % 3 == 0) {

            }
            else {
                outputArray[j] = inputArray[i];
                j += 1;
            }

        }
        return outputArray;
    }//end of multipleTwo

    public static String[] multipleFive(String[] inputArray) {
        int counter = 0;
        for (int l = 0; l < inputArray.length; l++) {
            if (l % 3 == 0 || l % 2 == 0) {

            }
            else if (l % 5 == 0) {
                counter += 1;
            }
        }
        int amountFive = counter;
        String[] outputArray = new String[amountFive];
        int j = 0;
        int i = 0;
        for (i = 0; i < inputArray.length && j < amountFive; i = i + 5) {
            if (i % 3 == 0 || i % 2 == 0) {

            }
            else if (i % 5 == 0) {
                outputArray[j] = inputArray[i];
                j += 1;
            }

        }
        return outputArray;
    }//end of multipleFive

    public static String[] primeNum(String[] inputArray) {
        int primeCounter = 0;
        for (int j = 0; j < inputArray.length; ++j) {
            if (primeCheck(j) == true) {
                primeCounter += 1;
            }
        }

        String[] primeList = new String[primeCounter];


        int k = 0;
        int i = 1;
        for (i = 1; i < inputArray.length && k < primeCounter; i++) {
            if (primeCheck(i) == false) {

            }
            else {
                primeList[k] = inputArray[i];
                k += 1;
            }

        }
        return primeList;
    }//end of primNum

    //to dash conversion methods
    public static String[] multipleThreetoDash1(String[] multThreeInput) {
        String[] newThree = new String[multThreeInput.length];
        for (int i = 0; i < multThreeInput.length; i++) {
            String temp = multThreeInput[i].toString();
            String newTemp = "";
            for (int j = 0; j < temp.length(); j++) {
                if ((j+1)%3 == 0 && temp.charAt(j) != '.' && temp.charAt(j) != '?' && temp.charAt(j) != ' ' && temp.charAt(j) != '\n') {
                    newTemp = newTemp + "_";
                }
                else {
                    newTemp = newTemp + temp.charAt(j);
                }
            }
            newThree[i] = newTemp;
        }
        return newThree;

    } //end of multipleThreetoDash1

    public static String[] multipleTwotoDash1(String[] multTwoInput) {
        String[] newTwo = new String[multTwoInput.length];
        for (int i = 0; i < multTwoInput.length; i++) {
            String temp = multTwoInput[i].toString();
            String newTemp = "";
            for (int j = 0; j < temp.length(); j++) {
                if ((j+1)%3 == 0 && temp.charAt(j) != '.' && temp.charAt(j) != '?' && temp.charAt(j) != ' '  && temp.charAt(j) != '\n') {
                    newTemp = newTemp + "_";
                }
                else {
                    newTemp = newTemp + temp.charAt(j);
                }
            }
            newTwo[i] = newTemp;
        }
        return newTwo;

    } //end of multipleThreetoDash1

    public static String[] multipleThreetoDash2(String[] multThreeInput) {
        String[] oldThree = multipleThreetoDash1(multThreeInput);
        String[] newThree = new String[oldThree.length];
        for (int i = 0; i < multThreeInput.length; i++) {
            String temp = oldThree[i].toString();
            String newTemp = "";
            for (int j = 0; j < temp.length(); j++) {
                if ((j+1)%2 == 0 && temp.charAt(j) != '.' && temp.charAt(j) != '?' && temp.charAt(j) != ' '  && temp.charAt(j) != '\n') {
                    newTemp = newTemp + "_";
                }
                else {
                    newTemp = newTemp + temp.charAt(j);
                }
            }
            newThree[i] = newTemp;
        }
        return newThree;



    } //end of multipleThreetoDash2

    public static String generateFinalLevel(String input) {
        String Final = "";
        String[] inputArray = input.split("\\b");
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].toString().length(); j++) {
                if (j == 0 || inputArray[i].toString().charAt(j) == '.' || inputArray[i].toString().charAt(j) == '?' || inputArray[i].toString().charAt(j) == '!' || inputArray[i].toString().charAt(j) == '-'  || inputArray[i].toString().charAt(j) == '\n' || inputArray[i].toString().charAt(j) == ' '){
                    Final = Final + inputArray[i].toString().charAt(j);
                }
                else {
                    Final = Final + "_";
                }
            }
            Final = Final;
        }

        return Final;
    }


    public static String generateLevel1(String input) {
        String[] inputArray = input.split("\\b");
        String[] mult3 = multipleThree(inputArray);

        String temp = "";
        int j = 0;
        for (int i = 0; i < inputArray.length; i++) {

            if (i == 0) {
                temp = temp + multipleThreetoDash1(mult3)[i].toString();
                j += 1;

            }

            else if ((i)%3 == 0 && j < multipleThreetoDash1(mult3).length) {
                temp = temp + multipleThreetoDash1(mult3)[j].toString();
                j += 1;

            }


            else {
                temp = temp + inputArray[i].toString();


            }

        }
        String string1 = temp;
        return string1;
    }//end of generateLevel1

    public static String generateLevel2(String input) {
        String[] inputArray = input.split("\\b");
        String[] mult3 = multipleThree(inputArray);
        String[] mult2 = multipleTwo(inputArray);

        String temp = "";
        int j = 0;
        int k = 0;
        for (int i = 0; i < inputArray.length; i++) {

            if (i == 0) {
                temp = temp + multipleThreetoDash1(mult3)[i].toString();
                j += 1;

            }

            else if ((i)%3 == 0 && j < multipleThreetoDash1(mult3).length) {
                temp = temp + multipleThreetoDash1(mult3)[j].toString();
                j += 1;

            }

            else if ((i)%2 == 0 && k < multipleTwotoDash1(mult2).length) {
                temp = temp + multipleTwotoDash1(mult2)[k].toString();
                k += 1;
            }

            else {
                temp = temp + inputArray[i].toString();


            }

        }
        return temp;
    }//end of generateLevel2

    public static String generateLevel3(String input) {
        String[] inputArray = input.split("\\b");
        String[] mult3 = multipleThree(inputArray);
        String[] mult2 = multipleTwo(inputArray);

        String temp = "";
        int j = 0;
        int k = 0;
        for (int i = 0; i < inputArray.length; i++) {

            if (i == 0) {
                temp = temp + multipleThreetoDash1(mult3)[i].toString();
                j += 1;

            }

            else if ((i)%3 == 0 && j < multipleThreetoDash2(mult3).length) {
                temp = temp + multipleThreetoDash2(mult3)[j].toString();
                j += 1;

            }

            else if ((i)%2 == 0 && k < multipleTwotoDash1(mult2).length) {
                temp = temp + multipleTwotoDash1(mult2)[k].toString();
                k += 1;
            }

            else {
                temp = temp + inputArray[i].toString();


            }

        }
        return temp;
    }//end of generateLevel3

    public static String generateLevel4(String input) {
        String[] inputArray = input.split("\\b");
        String[] mult3 = multipleThree(inputArray);
        String[] mult2 = multipleTwo(inputArray);
        String[] prime = primeNum(inputArray);

        String temp = "";
        int j = 0;
        int k = 0;
        int l = 0;
        for (int i = 0; i < inputArray.length; i++) {

            if (i == 0) {
                temp = temp + multipleThreetoDash1(mult3)[i].toString();
                j += 1;

            }

            else if ((i)%3 == 0 && j < multipleThreetoDash2(mult3).length) {
                temp = temp + multipleThreetoDash2(mult3)[j].toString();
                j += 1;

            }

            else if ((i)%2 == 0 && k < multipleTwotoDash1(mult2).length) {
                temp = temp + multipleTwotoDash1(mult2)[k].toString();
                k += 1;
            }

            else if (primeCheck(i) == true && l < multipleTwotoDash1(prime).length) {
                temp = temp + multipleTwotoDash1(prime)[l].toString();

                l += 1;
            }

            else {
                temp = temp + inputArray[i].toString();


            }

        }
        return temp;

    }//end of generateLevel4


    public static String generateLevel5(String input) {
        String[] inputArray = input.split("\\b");
        String[] mult3 = multipleThree(inputArray);
        String[] mult2 = multipleTwo(inputArray);
        String[] prime = primeNum(inputArray);
        String[] mult5 = multipleFive(inputArray);

        String temp = "";
        int j = 0;
        int k = 0;
        int l = 0;
        //starts at 1 to ignore first 5 that is also prime
        int m = 1;
        for (int i = 0; i < inputArray.length; i++) {

            if (i == 0) {
                temp = temp + multipleThreetoDash1(mult3)[i].toString();
                j += 1;

            }

            else if ((i)%3 == 0 && j < multipleThreetoDash2(mult3).length) {
                temp = temp + multipleThreetoDash2(mult3)[j].toString();
                j += 1;

            }

            else if ((i)%2 == 0 && k < multipleTwotoDash1(mult2).length) {
                temp = temp + multipleTwotoDash1(mult2)[k].toString();
                k += 1;
            }

            else if (primeCheck(i) == true && l < multipleTwotoDash1(prime).length) {
                temp = temp + multipleTwotoDash1(prime)[l].toString();

                l += 1;
            }
            else if (i % 5 == 0 && m < multipleTwotoDash1(mult5).length ) {
                temp = temp + multipleTwotoDash1(mult5)[m].toString();
                m += 1;
            }

            else {
                temp = temp + inputArray[i].toString();

            }

        }
        return temp;
    }//end of generateLevel5

    public static String generateLevel6(String input) {
        String[] inputArray = input.split("\\b");
        String[] mult3 = multipleThree(inputArray);
        String[] mult2 = multipleTwo(inputArray);
        String[] prime = primeNum(inputArray);
        String[] mult5 = multipleFive(inputArray);

        String temp = "";
        int j = 0;
        int k = 0;
        int l = 0;
        //starts at 1 to ignore first 5 that is also prime
        int m = 1;
        for (int i = 0; i < inputArray.length; i++) {

            if (i == 0) {
                temp = temp + multipleThreetoDash1(mult3)[i].toString();
                j += 1;

            }

            else if ((i)%3 == 0 && j < multipleThreetoDash2(mult3).length) {
                temp = temp + multipleThreetoDash2(mult3)[j].toString();
                j += 1;

            }

            else if ((i)%2 == 0 && k < multipleTwotoDash1(mult2).length) {
                temp = temp + multipleThreetoDash2(mult2)[k].toString();
                k += 1;
            }

            else if (primeCheck(i) == true && l < multipleTwotoDash1(prime).length) {
                temp = temp + multipleTwotoDash1(prime)[l].toString();

                l += 1;
            }
            else if (i % 5 == 0 && m < multipleTwotoDash1(mult5).length ) {
                temp = temp + multipleThreetoDash1(mult5)[m].toString();
                m += 1;
            }

            else {
                temp = temp + inputArray[i].toString();

            }

        }
        return temp;
    }//end of generateLevel6

    public static String generateLevel7(String input) {
        String[] inputArray = input.split("\\b");
        String[] mult3 = multipleThree(inputArray);
        String[] mult2 = multipleTwo(inputArray);
        String[] prime = primeNum(inputArray);
        String[] mult5 = multipleFive(inputArray);

        String temp = "";
        int j = 0;
        int k = 0;
        int l = 0;
        //starts at 1 to ignore first 5 that is also prime
        int m = 1;
        for (int i = 0; i < inputArray.length; i++) {

            if (i == 0) {
                temp = temp + multipleThreetoDash1(mult3)[i].toString();
                j += 1;

            }

            else if ((i)%3 == 0 && j < multipleThreetoDash2(mult3).length) {
                temp = temp + multipleThreetoDash2(mult3)[j].toString();
                j += 1;

            }

            else if ((i)%2 == 0 && k < multipleTwotoDash1(mult2).length) {
                temp = temp + multipleThreetoDash2(mult2)[k].toString();
                k += 1;
            }

            else if (primeCheck(i) == true && l < multipleTwotoDash1(prime).length) {
                temp = temp + multipleThreetoDash2(prime)[l].toString();

                l += 1;
            }
            else if (i % 5 == 0 && m < multipleTwotoDash1(mult5).length ) {
                temp = temp + multipleThreetoDash2(mult5)[m].toString();
                m += 1;
            }

            else {
                temp = temp + inputArray[i].toString();

            }

        }
        return temp;
    }//end of generateLevel7


}







