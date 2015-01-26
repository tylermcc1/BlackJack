package com.example.tyler.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tyler on 11/19/2014.
 */
public class Deck {

    private List<Card> Deck;
    private int cardCount = 0;

   public Deck(){
        Deck  = new ArrayList<Card>();
        Deck.add(new Card(1,R.drawable.aceofspades));
        Deck.add(new Card(2,R.drawable.twoofspades));
        Deck.add(new Card(3,R.drawable.threeofspades));
        Deck.add(new Card(4,R.drawable.fourofspades));
        Deck.add(new Card(5,R.drawable.fiveofspades));
        Deck.add(new Card(6,R.drawable.sixofspades));
        Deck.add(new Card(7,R.drawable.sevenofspades));
        Deck.add(new Card(8,R.drawable.eightofspades));
        Deck.add(new Card(9,R.drawable.nineofspades));
        Deck.add(new Card(10,R.drawable.tenofspades));
        Deck.add(new Card(10,R.drawable.jackofspades));
        Deck.add(new Card(10,R.drawable.kingofspades));
        Deck.add(new Card(10,R.drawable.queenofspades));
        Deck.add(new Card(1,R.drawable.aceofhearts));
        Deck.add(new Card(2,R.drawable.twoofhearts));
        Deck.add(new Card(3,R.drawable.threeofhearts));
        Deck.add(new Card(4,R.drawable.fourofhearts));
        Deck.add(new Card(5,R.drawable.fiveofhearts));
        Deck.add(new Card(6,R.drawable.sixofhearts));
        Deck.add(new Card(7,R.drawable.sevenofhearts));
        Deck.add(new Card(8,R.drawable.eightofhearts));
        Deck.add(new Card(9,R.drawable.nineofhearts));
        Deck.add(new Card(10,R.drawable.tenofhearts));
        Deck.add(new Card(10,R.drawable.jackofhearts));
        Deck.add(new Card(10,R.drawable.kingofhearts));
        Deck.add(new Card(10,R.drawable.queenofhearts));
        Deck.add(new Card(1,R.drawable.aceofdiamonds));
        Deck.add(new Card(2,R.drawable.twoofdiamonds));
        Deck.add(new Card(3,R.drawable.threeofdiamonds));
        Deck.add(new Card(4,R.drawable.fourofdiamonds));
        Deck.add(new Card(5,R.drawable.fiveofdiamonds));
        Deck.add(new Card(6,R.drawable.sixofdiamonds));
        Deck.add(new Card(7,R.drawable.sevenofdiamonds));
        Deck.add(new Card(8,R.drawable.eightofdiamonds));
        Deck.add(new Card(9,R.drawable.nineofdiamonds));
        Deck.add(new Card(10,R.drawable.tenofdiamonds));
        Deck.add(new Card(10,R.drawable.jackofdiamonds));
        Deck.add(new Card(10,R.drawable.queenofdiamonds));
        Deck.add(new Card(10,R.drawable.kingofdiamonds));
        Deck.add(new Card(1,R.drawable.aceofclubs));
        Deck.add(new Card(2,R.drawable.twoofclubs));
        Deck.add(new Card(3,R.drawable.threeofclubs));
        Deck.add(new Card(4,R.drawable.fourofclubs));
        Deck.add(new Card(5,R.drawable.fiveofclubs));
        Deck.add(new Card(6,R.drawable.sixofclubs));
        Deck.add(new Card(7,R.drawable.sevenofclubs));
        Deck.add(new Card(8,R.drawable.eightofclubs));
        Deck.add(new Card(9,R.drawable.nineofclubs));
        Deck.add(new Card(10,R.drawable.tenofclubs));
        Deck.add(new Card(10,R.drawable.jackofclubs));
        Deck.add(new Card(10,R.drawable.queenofclubs));
        Deck.add(new Card(10,R.drawable.kingofclubs));
    }



    public void shuffle(){
       Collections.shuffle(Deck);


    }


    public Card getTopCard() {
        if (cardCount == 29){
            shuffle();
            cardCount = 0;
            }
        Card topCard = Deck.get(cardCount);
        cardCount++;
        return topCard;
    }
}
