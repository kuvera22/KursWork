package com.example.kurswork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ListPhoto extends AppCompatActivity {

    Button  btnLogOut;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photo);

        btnLogOut.setOnClickListener(new View.OnClickListener() { //Обработчик кнопки Выйти
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                ToastMessage("Происходит выход" );
                openMainActivity();
            }
        });
    }

    public void ToastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void openMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
