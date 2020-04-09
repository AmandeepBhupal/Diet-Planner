package com.example.mobapplicationdev;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {
    EditText loginUserText,loginPasswordText;
    Button btnLogin;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        loginUserText = findViewById(R.id.UserNameLogin);
        loginPasswordText = findViewById(R.id.editPasswordLogin);
        btnLogin = findViewById(R.id.LoginBtn);
        firebaseAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(loginUserText.getText().toString(),loginPasswordText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(loginpage.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(loginpage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        
                    }
                });

            }
        });


    }
}
