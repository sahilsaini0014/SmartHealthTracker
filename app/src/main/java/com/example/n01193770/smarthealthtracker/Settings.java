package com.example.n01193770.smarthealthtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;


public class Settings extends AppCompatActivity implements View.OnClickListener{



    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private Button Save;
    private EditText Age;
    private EditText BldGrp;
    private EditText UsrHeight;
    private EditText UsrWeight;
    private EditText Dob;
    private EditText Gndr;
    private CheckBox MBOX;



    public static SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_settings);

        mAuth = mAuth.getInstance();

        //go back to main page
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeAsUpIndicator((R.drawable.ic_arrow_back_black_24dp));
        actionBar.setDisplayHomeAsUpEnabled(true);


        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        Save = (Button) findViewById(R.id.SaveBtn);
        Age = (EditText) findViewById(R.id.textage);
        UsrWeight = (EditText) findViewById(R.id.WgtNmb) ;
        UsrHeight = (EditText) findViewById(R.id.heightx) ;
        BldGrp =(EditText) findViewById(R.id.bldtext);
        Dob = (EditText) findViewById(R.id.dobtext);
        Gndr =(EditText) findViewById(R.id.gender);
        MBOX =(CheckBox) findViewById(R.id.mbox_id);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);








        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSharedPreference();
                savesettings();

                if (MBOX.isChecked()) {
                    setAppLocale("fr");


                }else{
                    setAppLocale("en");
                }

                Intent intent = new Intent(getApplicationContext(),Launcher.class);
                startActivity(intent);
                finish();

            }
        });






        LoadPreference();
    }


    @Override
    public void onBackPressed() {

        // TODO 13: Make sure the Up button will return the values back to MainActivity. Finished.
        saveSharedPreference();
        super.onBackPressed();
    }

    private void savesettings(){
        String age = Age.getText().toString();
         String bldgrp = BldGrp.getText().toString();
         String usrweight = UsrWeight.getText().toString();
         String usrheight = UsrHeight.getText().toString();
         String dob = Dob.getText().toString();
         String gndr = Gndr.getText().toString();

        Bio bio = new Bio(age, bldgrp, usrweight, usrheight, gndr, dob);
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("bio").child("values").setValue(bio);

        Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();


    }

    private void saveSharedPreference(){
        // TODO 4: Save all the settings. finished.
          // save the current user name for next time use.
    // save the current settings from UI, i.e. Username and E-mail.
    SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("mAge",Age.getText().toString());
        editor.putString("mBldgrp",BldGrp.getText().toString());
        editor.putString("mWeight",UsrWeight.getText().toString());
        editor.putString("mHieght",UsrHeight.getText().toString());
        editor.putString("mDob",Dob.getText().toString());
        editor.putString("mGndr",Gndr.getText().toString());
        editor.putBoolean("mbox",MBOX.isChecked());

        editor.commit(); // Save the preference.
}


 public void setAppLocale(String localeCode)
 {
     Resources res = getResources();
     DisplayMetrics dm =res.getDisplayMetrics();
     Configuration conf = res.getConfiguration();

     if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN_MR1){

         conf.setLocale(new Locale(localeCode.toLowerCase()));

     }else {

         conf.locale = new Locale(localeCode.toLowerCase());

     }

     res.updateConfiguration(conf,dm);






 }

    private void LoadPreference(){
        // TODO 5: Load all the settings. finished.

        String mAge = sharedPref.getString("mAge", "");
        String mBldgrp = sharedPref.getString("mBldgrp", "");
        String mWeight = sharedPref.getString("mWeight", "");
        String mHieght = sharedPref.getString("mHieght", "");
        String mDob = sharedPref.getString("mDob", "");
        String mGndr = sharedPref.getString("mGndr", "");
        Boolean mBox = sharedPref.getBoolean("mbox", false);

        // set the preference based on the saved data.
        Age.setText(mAge);
        BldGrp.setText(mBldgrp);
        UsrWeight.setText(mWeight);
        UsrHeight.setText(mHieght);
        Dob.setText(mDob);
        Gndr.setText(mGndr);
        MBOX.setChecked(mBox);
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view == Save){
            savesettings();

        }


    }
}
