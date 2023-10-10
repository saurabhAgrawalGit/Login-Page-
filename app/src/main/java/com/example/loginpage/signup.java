package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
   FirebaseDatabase firebaseDatabase;
   DatabaseReference reference;
   EditText name_in,phone_in,email_in,pass_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        name_in  = findViewById(R.id.et_Name);
        phone_in = findViewById(R.id.et_Phone);
        email_in = findViewById(R.id.et_email);
        pass_in  = findViewById(R.id.et_pass);

    }

    public void resisterClick(View view) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("datauser ");
        String name  = name_in.getText().toString();
        String phone = phone_in.getText().toString();
        String email= email_in.getText().toString();
        String pass  = pass_in.getText().toString();
        storingdata ob=new storingdata(name,email,phone,pass);
        reference.child(phone).setValue(ob);
        Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),result_scr.class);
        startActivity(intent);
        finish();

    }
}