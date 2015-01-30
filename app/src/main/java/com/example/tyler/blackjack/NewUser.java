package com.example.tyler.blackjack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Tyler on 1/29/2015.
 */
public class NewUser extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser);
    }


    //put user info into database
    public void signUpClick(View v){

    }

    public void cancelClick(View v){
        finish();
    }
}
