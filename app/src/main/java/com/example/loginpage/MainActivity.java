package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button singup,login;
    EditText in_email,in_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singup= findViewById(R.id.btsignup);
        login= findViewById(R.id.btlogin);

        in_email= findViewById(R.id.email);
        in_pass= findViewById(R.id.Password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String user_email = in_email.getText().toString();
             String user_pass = in_pass.getText().toString();
             if(!user_email.isEmpty())
             {
                 in_email.setError(null);

                 if(!user_pass.isEmpty())
                 {
                     in_pass.setError(null);
                     String email1 = in_email.getText().toString();
                     String pass1 = in_pass.getText().toString();
                     FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                     DatabaseReference databaseReference = firebaseDatabase.getReference("datauser ");
                     Query check_username= databaseReference.orderByChild("phoneno").equalTo(email1);
                     check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                             if(snapshot.exists())
                             {
                                 in_email.setError(null);
                                 String pass_check=snapshot.child(email1).child("password").getValue(String.class);
                                 if(pass1.equals(pass_check))
                                 {
                                     in_pass.setError(null);
                                     Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                                     Intent intent= new Intent(getApplicationContext(),result_scr.class);
                                     startActivity(intent);
                                     finish();
                                 }
                                 else
                                 {
                                     in_pass.setError("Wrong Password");
                                     Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_LONG).show();

                                 }
                             }
                             else
                             {
                                 in_email.setError("User Does Not Exists");
                             }
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {

                         }
                     });
                 }
                 else
                 {

                     in_pass.setError("Please Enter The Password");
                 }

             }
             else
             {
                 in_email.setError("Please Enter The Email");

             }

            }
        });


        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}