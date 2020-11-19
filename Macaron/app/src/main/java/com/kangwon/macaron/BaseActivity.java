package com.kangwon.macaron;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    public void setProgressBar(int resId) { mProgressBar = findViewById(resId); }

    public void showProgressBar(){
        if(mProgressBar != null){
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if(mProgressBar!=null){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public String getUid(){return FirebaseAuth.getInstance().getCurrentUser().getUid();}
}