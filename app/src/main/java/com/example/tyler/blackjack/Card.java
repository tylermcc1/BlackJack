package com.example.tyler.blackjack;

/**
 * Created by Tyler on 11/19/2014.
 */
public class Card {

    private final int value;
    private int iconId;



    public Card(int value, int iconId){
        this.value = value;
        this.iconId = iconId;
    }


    public int getValue(){
        return value;
    }




    public int getIconId(){
        return iconId;
    }




}