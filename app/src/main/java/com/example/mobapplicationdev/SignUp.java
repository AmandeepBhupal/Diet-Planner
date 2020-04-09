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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    EditText etEmail,etPass,etFirstName,etLastName;
    Button BtLogin,BtSignUp;
    FirebaseAuth myFireAuth;
    String myEmail,myPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        myFireAuth = FirebaseAuth.getInstance();

        if(myFireAuth.getCurrentUser()!=null){
            Intent intent = new Intent(SignUp.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        etEmail = findViewById(R.id.UserName);
        etPass = findViewById(R.id.editPassword);
        BtLogin = findViewById(R.id.LoginBtnSignUp);
        BtSignUp = findViewById(R.id.SignUpBtn);
        etFirstName = findViewById(R.id.editFirstName);
        etLastName = findViewById(R.id.editLastName);


        BtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myEmail = etEmail.getText().toString();
                myPassword = etPass.getText().toString();
                CreateNewUser();
            }
        });

        BtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this,loginpage.class);
                startActivity(i);
            }
        });
    }

    private void CreateNewUser(){
        myFireAuth.createUserWithEmailAndPassword(myEmail,myPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Bundle b = new Bundle();
                    b.putString("firstname",etFirstName.getText().toString());
                    b.putString("lastname",etLastName.getText().toString());
                    Intent intent = new Intent(SignUp.this,SignUpInfo.class);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
