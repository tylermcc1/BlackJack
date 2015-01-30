package com.example.tyler.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Tyler on 1/28/2015.
 */
public class SignIn extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
    }

    //Make sure username and password in database
    public void signInClick(View v){

    }

    //Sends user to New User page to create profile
    public void newUserClick(View v){
        Intent intent = new Intent(this,NewUser.class);
        startActivity(intent);


    }
}
