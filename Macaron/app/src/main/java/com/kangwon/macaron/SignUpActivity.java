package com.kangwon.macaron;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.kangwon.macaron.databinding.ActivitySignUpBinding;
import com.kangwon.macaron.models.User;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SignUpActivity";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        // Views progress bar
        setProgressBar(R.id.singupprogressBar);

//        final ArrayList<String> list = new ArrayList<>();
//        list.add("Owner");
//        list.add("Employee");

//        Spinner spinner = binding.signupCheckOwner;
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
//
//        spinner.setAdapter(arrayAdapter);
//        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), list.get(position), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        // Click listeners
        binding.signupBtn.setOnClickListener(this);
    }

    private void signUp() {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        showProgressBar();
        String email = binding.signupEmail.getText().toString();
        String password = binding.signupPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                    hideProgressBar();

                    if (task.isSuccessful()) {
                        onAuthSuccess(task.getResult().getUser());
                    } else {
                        Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());

        // go to MainActivity
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(binding.signupEmail.getText().toString())) {
            binding.signupEmail.setError("Required");
            result = false;
        } else {
            binding.signupEmail.setError(null);
        }

        if (TextUtils.isEmpty(binding.signupPassword.getText().toString())) {
            binding.signupPassword.setError("Required");
            result = false;
        } else if (!TextUtils.equals(binding.signupPassword.getText().toString(), binding.signupCheckPassword.getText().toString())) {
            binding.signupCheckPassword.setError("Check Password Again");
            result = false;
        } else {
            binding.signupPassword.setError(null);
        }

        if (TextUtils.isEmpty(binding.signupPhoneNum.getText().toString())) {
            binding.signupPhoneNum.setError("Required");
            result = false;
        } else {
            binding.signupPhoneNum.setError(null);
        }

        return result;
    }

    // [START basic_write]
    private void writeNewUser(String uid, String name, String email) {
        String phone = binding.signupPhoneNum.getText().toString().trim();
        boolean isowner = true;
        User user = new User(name, email, phone, isowner);

        mDatabase.child("users").child(uid).setValue(user);
    }
    // [END basic_write]

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.signupBtn)
            signUp();
    }
}