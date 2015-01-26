package com.example.tyler.blackjack;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Tyler on 12/23/2014.
 */
public class PlayerOneChips {

    private int chipCount;
    private Context context;



    public PlayerOneChips(int initialChips, Context context){
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
