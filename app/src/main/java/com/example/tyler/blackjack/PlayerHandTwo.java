package com.example.tyler.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler on 12/2/2014.
 */
public class PlayerHandTwo {

    private int aceCount=0;
    private List<Card> aceValue1 = new ArrayList();
    private List<Card> aceValue11 = new ArrayList();


    protected void addCard(Card card){
        if(card.getValue() == 1) {
            aceCount++;
            if (aceCount == 1) {
                //create new card for ace with value 11
                Card ace = new Card(11,card.getIconId());
                aceValue1.add(card);
                aceValue11.add(ace);
            } else {
                aceValue1.add(card);
                aceValue11.add(card);
            }
        }
        else{
            aceValue1.add(card);
            aceValue11.add(card);

        }}

    protected int getBetterHand(){
        int ace1Hand=0;
        int ace11Hand=0;
        for(Card c : aceValue1){
            ace1Hand+=c.getValue();
        }
        for(Card c : aceValue11){
            ace11Hand+=c.getValue();
        }

        if (ace11Hand <= 21){
            return ace11Hand;
        }
        else{
            return ace1Hand;
        }

    }

    protected int getCardTotal(){
        return getBetterHand();

    }

    protected int numberofCards(){
        int count = 0;
        for ( Card c : aceValue1){
            count++;
        }
        return count;
    }

    protected Card getCard(int index){
        return aceValue1.get(index);
    }




    protected void newHand(){

        aceValue1.clear();
        aceValue11.clear();
        aceCount = 0;

    }

}
