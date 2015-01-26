package com.example.tyler.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;





public class StartMenu extends Activity {

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_menu);
        start = (Button) findViewById(R.id.playButton);
    }

    public void startClick(View v){
        Intent intent = new Intent(this, BlackJack.class);
        startActivity(intent);
    }
}