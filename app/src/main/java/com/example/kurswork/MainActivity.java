package com.example.kurswork;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

   public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText mEmail, mPassword;
    private Button btnLogIn, btnReg;

    public FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                                                     //Объявление кнопок и текстовых полей
        mEmail=(EditText) findViewById(R.id.editTextEmail);
        mPassword=(EditText) findViewById(R.id.editTextPassword);
        btnLogIn=(Button) findViewById(R.id.buttonLogIN);
        btnReg=(Button) findViewById(R.id.buttonRegistration);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null) {
                                                    //если пользователь авторизован
                    ToastMessage("Выполнен вход как "+user.getEmail());
                    openListPhoto();
                } else {                             //если пользователь не авторизован

                }
            }
        };

        btnLogIn.setOnClickListener(new View.OnClickListener() { //Обработчик кнопки Войти
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String pass = mPassword.getText().toString();
                if (!email.equals("") && !pass.equals("")){
                    mAuth.signInWithEmailAndPassword(email, pass);
                } else {
                    ToastMessage("Поля не заполнены");
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() { //Обработчик кнопки Новый пользователь
            @Override
            public void onClick(View view) {
                registration(mEmail.getText().toString(),mPassword.getText().toString());
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void ToastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void registration (String email , String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                (this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    ToastMessage("Регистрация успешна");
                }
                else
                    ToastMessage("Регистрация провалена");
            }
        });
    }

    public void openListPhoto(){
        Intent intent = new Intent(this,ListPhoto.class);
        startActivity(intent);
    }
}