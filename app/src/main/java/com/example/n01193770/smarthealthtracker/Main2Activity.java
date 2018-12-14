package com.example.n01193770.smarthealthtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private TextView Age;
    private TextView Gendr;
    private TextView Weight;
    private TextView Height;
    private TextView Dob;
    private TextView BldGroup;
    SharedPreferences sharedPref;
    private static final String TAG = "MAinAct";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


         sharedPref = this.getPreferences(Context.MODE_PRIVATE);




        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);


        mAuth = mAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("user").child(user.getUid()).child("bio");

        Age = findViewById(R.id.usrage);
        Gendr = findViewById(R.id.usrgender);
        Weight = findViewById(R.id.usrweight);
        Height = findViewById(R.id.usrheight);
        Dob = findViewById(R.id.usrdob);
        BldGroup = findViewById(R.id.usrBld);

        reterieveData();
        loadpref();

        //


    }

//    private boolean connection(){
//        Context context = null;
//        ConnectivityManager cm =
//                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null &&
//                activeNetwork.isConnectedOrConnecting();
//
//        return isConnected;
//    }


    private void reterieveData() {



        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Bio bio= dataSnapshot.getValue(Bio.class);
                //if (bio.getAge() ==null) return;
                Age.setText(bio.getAge());
                BldGroup.setText(bio.getBldgrp());
                Weight.setText(bio.getUsrweight());
                Height.setText(bio.getUsrheight());
                Dob.setText(bio.getDob());
                Gendr.setText(bio.getGndr());

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("mAge",Age.getText().toString());
                editor.putString("mBldgrp",BldGroup.getText().toString());
                editor.putString("mWeight",Weight.getText().toString());
                editor.putString("mHieght",Height.getText().toString());
                editor.putString("mDob",Dob.getText().toString());
                editor.putString("mGndr",Gendr.getText().toString());


                editor.commit(); // Save the preference.



            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Bio bio = dataSnapshot.getValue(Bio.class);

                Age.setText(bio.getAge());
                Gendr.setText(bio.getGndr());
                Weight.setText(bio.getUsrweight());
                Height.setText(bio.getUsrheight());
                Dob.setText(bio.getDob());
                BldGroup.setText(bio.getBldgrp());

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("mAge",Age.getText().toString());
                editor.putString("mBldgrp",BldGroup.getText().toString());
                editor.putString("mWeight",Weight.getText().toString());
                editor.putString("mHieght",Height.getText().toString());
                editor.putString("mDob",Dob.getText().toString());
                editor.putString("mGndr",Gendr.getText().toString());


                editor.commit(); // Save the preference.

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



     //   private void Loadpref(){}
        // TODO: Get the whole data array on a reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Bio> arraylist = new ArrayList<Bio>();

                // TODO: Now data is reteieved, needs to process data.
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    // iterate all the items in the dataSnapshot
                    for (DataSnapshot a : dataSnapshot.getChildren()) {
                        Bio bio = new Bio();
                        bio.setAge(a.getValue(Bio.class).getAge());
                        bio.setGndr(a.getValue(Bio.class).getGndr());
                        bio.setUsrweight(a.getValue(Bio.class).getUsrweight());
                        bio.setUsrheight(a.getValue(Bio.class).getUsrheight());
                        bio.setDob(a.getValue(Bio.class).getDob());
                        bio.setBldgrp(a.getValue(Bio.class).getBldgrp());

                        arraylist.add(bio);  // now all the data is in arraylist.

                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting data failed, log a message
               // Log.d("MapleLeaf", "Data Loading Cancelled/Failed.", databaseError.toException());
            }
        });

    }


    private void loadpref(){


        String mAge = sharedPref.getString("mAge", "");
        String mBldgrp = sharedPref.getString("mBldgrp", "");
        String mWeight = sharedPref.getString("mWeight", "");
        String mHieght = sharedPref.getString("mHieght", "");
        String mDob = sharedPref.getString("mDob", "");
        String mGndr = sharedPref.getString("mGndr", "");


        // set the preference based on the saved data.
     // Log.d(TAG, "The age is :  " + mAge );
        Age.setText(mAge);
        BldGroup.setText(mBldgrp);
        Weight.setText(mWeight);
        Height.setText(mHieght);
        Dob.setText(mDob);
        Gendr.setText(mGndr);



    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.logout, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){

            int id = item.getItemId();

            if (id == R.id.logout) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));


            }

            if (item.getItemId() == android.R.id.home) {
                finish();
                return true;

            }
            return super.onOptionsItemSelected(item);
        }


    }


