package com.example.kurswork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ListPhoto extends AppCompatActivity {

    Button  btnLogOut;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photo);

    }
}
