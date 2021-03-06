package com.example.n01193770.smarthealthtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Distance extends AppCompatActivity {


    DatabaseReference databaseReference;
    private List<String> Distance = new ArrayList();

    private ListView listView;
    FirebaseAuth mAuth;
    SharedPreferences sharedPref;




    private void LoadPreference(){
        String mread = sharedPref.getString("mread", "");
        String mtimestamp = sharedPref.getString("mTimestamp", "");

        Distance.add(convertTimestamp(mtimestamp)+ ":   "  +mread);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, Distance) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.BLACK);

                return view;

            }
        };
        listView.setAdapter(arrayAdapter);

        arrayAdapter.notifyDataSetChanged();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        mAuth = mAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(user.getUid()).child("readings").child("Distance").child("values");
        listView =(ListView) findViewById(R.id.listV);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Distance.clear();

                if(dataSnapshot != null && dataSnapshot.getValue() != null) {


                    for(DataSnapshot a : dataSnapshot.getChildren()){

//                        heart_readings readings = new heart_readings();
//                        readings.setRead(a.getValue(heart_readings.class).getRead());
//                        readings.setTimestamp(a.getValue(heart_readings.class).getTimestamp());

                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("mread",a.getValue(heart_readings.class).getRead());
                        editor.putString("mTimestamp",a.getValue(heart_readings.class).getTimestamp());
                        editor.apply();


                        LoadPreference();



                       // Distance.add(convertTimestamp(readings.getTimestamp())+ ":   "  +readings.getRead()+ "                                   " + convertTimestamp1(readings.getTimestamp()));



                    }
                }

            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    private String convertTimestamp(String timestamp){

        long yourSeconds = Long.valueOf(timestamp);
        Date mDate = new Date(yourSeconds * 1000);
        DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        return df.format(mDate);
    }


    private String convertTimestamp1(String timestamp){

        long yourSeconds = Long.valueOf(timestamp);
        Date mDate = new Date(yourSeconds * 1000);
        DateFormat df = new SimpleDateFormat("hh:mm");
        return df.format(mDate);
    }





}
