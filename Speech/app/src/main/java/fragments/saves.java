package fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ajs.speech.Activities.MainActivity;
import com.ajs.speech.Activities.User;
import com.ajs.speech.Activities.meechInit;
import com.ajs.speech.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class saves extends Fragment {
    private EditText editText, etd;
    private Button button;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                //The purpose of this is to make the back arrow go to home and not to the loading screen
                ((MainActivity) getActivity()).navSavestoMain();
                ((MainActivity)getActivity()).areWeOnSaves = false;

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_saves, container, false);
       final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.savesRecycler);

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference meechRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("bio");





        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .child(userId)
                .child("bio")
                .limitToLast(20);


        //meeches @ database.getReference().child("Users").child(userId).child("bio").child(key);

        FirebaseRecyclerOptions<meechInit> options =
                new FirebaseRecyclerOptions.Builder<meechInit>()
                        .setQuery(query, meechInit.class)
                        .build();

         adapter = new FirebaseRecyclerAdapter<meechInit, ViewHolder>(options) {


            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // layout called R.layout.saves_item for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.saves_item, parent, false);


                return new ViewHolder(view);

            }


            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, meechInit model) {
                final ViewHolder holder1 = holder;
                    final String post_key = getRef(position).getKey().toString();
                    holder.setTitle(model.getTitle());
                    String cont = model.getContent();
                    String temp = "";
                    for (int i = 0; i < 100 && i < cont.length(); i++) {
                        temp += cont.charAt(i);
                    }

                    if (cont.length() > 100) {
                    temp += "...";}
                    holder.setContent(temp);
//deletes item from recyclerview
                holder.delBut.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {
//database.getReference().child("Users").child(userId).child("bio").child(key)
                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("bio");
                        db.child(getRef(position).getKey().toString()).removeValue();
                    }
                });

                holder.meechButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("bio").child(getRef(position).getKey()).child("content");
                        db.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String wantedText = dataSnapshot.getValue().toString();
                                ((MainActivity)getActivity()).navSavestoProcess(wantedText);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                });

                }



        };

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);





        ImageButton button = (ImageButton)view.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).createMeech();



            }
        });


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            }




    public interface OnFragmentInteractionListener {


        void onFragmentInteraction(Uri uri);

    }








}


class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView content;
    public String uniqId;
    public ImageButton delBut = itemView.findViewById(R.id.task_delete);
    public ImageView meechButton = itemView.findViewById(R.id.meechGoButton);
    View mView;
    Context mContext;

    public ViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.saves_title);
        content = itemView.findViewById(R.id.saves_content);



    }











    public void setTitle(String string) {
        title.setText(string);
    }


    public void setContent(String string) {
        content.setText(string);
    }

    public void setDelete(ImageButton button) {
        delBut = button;
    }




}
