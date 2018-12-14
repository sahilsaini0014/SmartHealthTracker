package com.example.n01193770.smarthealthtracker;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity implements View.OnClickListener {

private Button Signup;
private EditText Email;
private EditText Password;
private EditText Name;
private EditText Lastname;
private EditText Phone;

private ProgressDialog progressDialog;
private FirebaseAuth mAuth;
private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = mAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainScreen.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        FirebaseUser user = mAuth.getCurrentUser();
        progressDialog =new ProgressDialog(this);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);


        Signup = (Button) findViewById(R.id.signupBtn);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        Name = (EditText) findViewById(R.id.firstName);
        Lastname =(EditText) findViewById(R.id.lastName);
        Phone = (EditText) findViewById(R.id.phone);

        Signup.setOnClickListener(this);

    }

    private void registeruser(){
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;

        }

        if(TextUtils.isEmpty(password)){

            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;

        }

        //VALIDATION

        progressDialog.setMessage("Wait while Signing Up");
        progressDialog.show();


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            saveUserInformation();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainScreen.class));
                        }else{
                            Toast.makeText(Signup.this, "Could not register.... please try again", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                    }
                });


    }

    private void saveUserInformation(){
       String name = Name.getText().toString();
       String lastname = Lastname.getText().toString();
       String email = Email.getText().toString();
       String phone = Phone.getText().toString();

       UserInformation userInformation = new UserInformation(name, lastname ,email, phone);
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("user_Info").push().setValue(userInformation);

        Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();

    }

    @Override
    public  void onClick(View view){
        if(view == Signup){
            registeruser();


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return  true;

        }
        return super.onOptionsItemSelected(item);
    }

}
