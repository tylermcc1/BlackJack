package com.example.tyler.blackjack;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Tyler on 1/1/2015.
 */
public class PlayerTwoChips {

    private int chipCount;
    private Context context;



    public PlayerTwoChips(int initialChips, Context context){
        this.chipCount = initialChips;
        this.context = context;


    }

    public void increaseChips(int amount){
        TextView chipCountText = (TextView) ((Activity)context).findViewById(R.id.chipCountText);
        chipCount+=amount;
        chipCountText.setText(String.valueOf(chipCount));

    }


    public void decreaseChips(int amount){
        TextView chipCountText = (TextView) ((Activity)context).findViewById(R.id.chipCountText);
        chipCount-=amount;
        chipCountText.setText(String.valueOf(chipCount));



    }
}
