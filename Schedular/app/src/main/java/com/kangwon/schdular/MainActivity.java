package com.kangwon.schdular;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    private EditText email_login;
    private EditText pwd_login;
    FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button join = (Button) findViewById(R.id.main_join_btn);
        Button login = (Button) findViewById(R.id.main_login_btn);
        email_login = (EditText) findViewById(R.id.main_email);
        pwd_login = (EditText) findViewById(R.id.main_pwd);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_login.getText().toString().trim();
                String pwd = pwd_login.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @SuppressLint("ShowToast")
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, "Login Failure", Toast.LENGTH_SHORT);
                                }
                            }
                        });
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }
}