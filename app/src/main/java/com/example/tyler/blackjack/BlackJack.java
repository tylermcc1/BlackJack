

/*Tyler McCarthy*/
package com.example.tyler.blackjack;


        import android.app.Activity;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.GestureDetector;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.animation.TranslateAnimation;
        import android.widget.Button;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;



public class BlackJack extends Activity implements DialogInterface.OnClickListener{

    private ImageView  p1,p2, p3, p4, p5, p6, p7, p8, p9,
            p10, p11,s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11;
    private ImageView[] playerViewList;
    private ImageView[] playerTwoViewList;
    private ImageView[] dealerViewList;
    private Card topCard;
    private PlayerHand playerHand;
    private PlayerHandTwo playerHandTwo;
    private PlayerOneChips playerChips;
    private PlayerTwoChips playerTwoChips;
    private Deck deck;
    private DealerHand dealerHand;
    private ImageView currentView;
    private ImageView currentViewTwo;
    private int pViewIndex = 2;
    private int dViewIndex = 2;
    private int sViewIndex = 2;
    private int duration = Toast.LENGTH_SHORT;
    private FrameLayout myLayout;
    private GestureDetector gestureDetector;
    private View.OnTouchListener listener;
    static Context context;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private Button increaseBet,decreaseBet, placeBet, split, doubleDown;
    private int currentHand = 1;
    private int handCount = 1;
    private int secondCount = 0;
    static boolean dealerTurn = false;
    public int playerBet = 10;
    private TextView playerBetText;
    private Handler repeatUpdateHandler = new Handler();
    private boolean mAutoIncrement = false;
    private boolean mAutoDecrement = false;
    static int REP_DELAY = 50;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        context = getApplicationContext();
        p1 = (ImageView) findViewById(R.id.pCard1);
        p2 = (ImageView) findViewById(R.id.pCard2);
        p3 = (ImageView) findViewById(R.id.pCard3);
        p4 = (ImageView) findViewById(R.id.pCard4);
        p5 = (ImageView) findViewById(R.id.pCard5);
        p6 = (ImageView) findViewById(R.id.pCard6);
        p7 = (ImageView) findViewById(R.id.pCard7);
        p8 = (ImageView) findViewById(R.id.pCard8);
        p9 = (ImageView) findViewById(R.id.pCard9);
        p10 = (ImageView) findViewById(R.id.pCard10);
        p11 = (ImageView) findViewById(R.id.pCard11);
        s1 = (ImageView) findViewById(R.id.sCard1);
        s2 = (ImageView) findViewById(R.id.sCard2);
        s3 = (ImageView) findViewById(R.id.sCard3);
        s4 = (ImageView) findViewById(R.id.sCard4);
        s5 = (ImageView) findViewById(R.id.sCard5);
        s6 = (ImageView) findViewById(R.id.sCard6);
        s7 = (ImageView) findViewById(R.id.sCard7);
        s8 = (ImageView) findViewById(R.id.sCard8);
        s9 = (ImageView) findViewById(R.id.sCard9);
        s10 = (ImageView) findViewById(R.id.sCard10);
        s11 = (ImageView) findViewById(R.id.sCard11);
        d1 = (ImageView) findViewById(R.id.dCard1);
        d2 = (ImageView) findViewById(R.id.dCard2);
        d3 = (ImageView) findViewById(R.id.dCard3);
        d4 = (ImageView) findViewById(R.id.dCard4);
        d5 = (ImageView) findViewById(R.id.dCard5);
        d6 = (ImageView) findViewById(R.id.dCard6);
        d7 = (ImageView) findViewById(R.id.dCard7);
        d8 = (ImageView) findViewById(R.id.dCard8);
        d9 = (ImageView) findViewById(R.id.dCard9);
        d10 = (ImageView) findViewById(R.id.dCard10);
        d11 = (ImageView) findViewById(R.id.dCard11);
        increaseBet = (Button) findViewById(R.id.increaseBet);
        decreaseBet = (Button) findViewById(R.id.decreaseBet);
        placeBet = (Button) findViewById(R.id.placeBetButton);
        playerBetText = (TextView) findViewById(R.id.playerBetText);
        split = (Button) findViewById(R.id.splitButton);
        doubleDown = (Button) findViewById(R.id.ddButton);


        playerViewList = new ImageView[] {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11};
        playerTwoViewList = new ImageView[] {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11};
        dealerViewList = new ImageView[] {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11};
        myLayout = (FrameLayout) findViewById(R.id.layout);
        playerHand = new PlayerHand();
        playerHandTwo = new PlayerHandTwo();
        playerChips = new PlayerOneChips(1000, BlackJack.this);
        dealerHand = new DealerHand();
        deck = new Deck();
        deck.shuffle();
        split.setVisibility(View.INVISIBLE);
        doubleDown.setVisibility(View.INVISIBLE);


        //Set the gesture detector for the entire screen
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        listener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        myLayout.setOnTouchListener(listener);

        //Set longClickListener for increasing player bet
        increaseBet.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        mAutoIncrement = true;
                        repeatUpdateHandler.post(new RptUpdater());
                        return false;
                    }
                }
        );

        increaseBet.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                        && mAutoIncrement) {
                    mAutoIncrement = false;
                }
                return false;
            }
        });


        //Set longClickListener for decreasing player bet.
        decreaseBet.setOnLongClickListener(
                new View.OnLongClickListener(){
                    public boolean onLongClick(View arg0) {
                        mAutoDecrement = true;
                        repeatUpdateHandler.post( new RptUpdater() );
                        return false;
                    }
                }
        );

        decreaseBet.setOnTouchListener( new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL)
                        && mAutoDecrement ){
                    mAutoDecrement = false;
                }
                return false;
            }
        });




    }








    public void menuClick(View v){
        finish();

    }



    public void placeBetClick(View v){
        playerBet = Integer.parseInt(playerBetText.getText().toString());
        playerChips.decreaseChips(playerBet);
        dealCards();
        dealerTurn = false;
        increaseBet.setVisibility(View.INVISIBLE);
        decreaseBet.setVisibility(View.INVISIBLE);
        placeBet.setVisibility(View.INVISIBLE);
        split.setVisibility(View.VISIBLE);
        doubleDown.setVisibility(View.VISIBLE);

    }



    private void dealCards() {
        Card firstCard = deck.getTopCard();
        Card secondCard = deck.getTopCard();
        Card thirdCard = deck.getTopCard();
        Card fourthCard = deck.getTopCard();
        Card dealerCard = new Card(fourthCard.getValue(),R.drawable.backofcard);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        dealerHand.addCard(thirdCard);
        dealerHand.addCard(fourthCard);
        setImage(p1, firstCard, 500);
        setImage(p2, secondCard, 1000);
        setImage(d1, thirdCard, 1500);
        setImage(d2, dealerCard, 2000);

    }

    public void increaseBetClick(View v) {
        if (playerBet == 1000) {

        } else {
            playerBet += 5;
            playerBetText.setText(String.valueOf(playerBet));
        }
    }




    public void decreaseBetClick (View v){
        if(playerBet == 10){


        }
        else{
            playerBet-=5;
            playerBetText.setText(String.valueOf(playerBet));
        }
    }






    private void firstHandHit() {
        if(!dealerTurn) {
            topCard = deck.getTopCard();
            currentView = playerViewList[pViewIndex];
            playerHand.addCard(topCard);
            setImage(currentView, topCard, 0);
            pViewIndex++;
            if (playerHand.getCardTotal() > 21) {
                firstHandBust(1000);
            }
        }
    }





    private void setImage(ImageView iv, Card c, int milliSeconds) {
        final ImageView image = iv;
        final Card card = c;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (image == d2) {
                    image.setImageResource(R.drawable.backofcard);
                }
                image.setImageResource(card.getIconId());
            }
        }, milliSeconds);


    }





    private void firstHandBust(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                CharSequence bustText = "You Bust...Dealer Wins!\n\nPlayer Total : " + playerHand.getCardTotal();
                Toast toast = Toast.makeText(context, bustText, duration);
                toast.show();
                //for a single hand
                if (handCount == 1) {
                    newRound(2500);
                }
                //for a split, if right hand still exists find winner
                else {
                    handCount = 1;
                    currentHand = 2;
                    for (int i = 0; i < sViewIndex; i++) {
                        playerTwoViewList[i].setColorFilter(Color.argb(0, 0, 0, 0));
                    }
                    clearFirstHand(2000);
                    dealerTurn(4000);
                }
            }
        }, milliseconds);
    }


    private void secondHandHit() {
        if(!dealerTurn) {
            topCard = deck.getTopCard();
            currentViewTwo = playerTwoViewList[sViewIndex];
            playerHandTwo.addCard(topCard);
            setImage(currentViewTwo, topCard, 0);
            sViewIndex++;
            if (playerHandTwo.getCardTotal() > 21) {
                secondHandBust(1000);
            }
        }
    }

    private void secondHandBust(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                CharSequence bustText = "You Bust...Dealer Wins!\n\nPlayer Total : " + playerHandTwo.getCardTotal();
                Toast toast = Toast.makeText(context, bustText, duration);
                toast.show();
                handCount = 1;
                currentHand = 1;
                clearSecondHand(2000);
                p2.setColorFilter(Color.argb(0, 0, 0, 0));
                p1.setColorFilter(Color.argb(0, 0, 0, 0));


            }
        }, milliseconds);


    }


    private void dealerTurn(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dealerTurn = true;
                setImage(d2, dealerHand.getCard(1), 500);
                while (dealerHand.getCardTotal() <= 16) {
                    secondCount += 1500;
                    dealerHit();

                }
                if(dealerHand.getCardTotal() > 21){
                    dealerBust(secondCount+1000);
                }
                //if single hand or right hand was a bust
                else if (handCount == 1 && currentHand == 1) {
                findWinnerLeftHand(secondCount + 2000);
                newRound(secondCount + 4000);
                }
                 //if left hand and right hand not a bust
                else if (handCount == 2 && currentHand == 1) {
                    findWinnerRightHand(secondCount + 2000);
                    findWinnerLeftHand(secondCount + 4000);
                    newRound(secondCount + 6000);
                    }
                    //Left hand was a bust
                    else if (handCount == 1 && currentHand == 2) {
                        findWinnerRightHand(secondCount + 2000);
                        newRound(secondCount + 4000);
                    }





            }
        }, milliseconds);
    }


    private void dealerHit() {
        topCard = deck.getTopCard();
        currentView = dealerViewList[dViewIndex];
        dealerHand.addCard(topCard);
        setImage(currentView, topCard, secondCount);
        dViewIndex++;
    }


    private void dealerBust(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (currentHand == 1 && handCount == 1) {
                    CharSequence dBustText = "Dealer Bust...You Win\n\nDealer Total : " + dealerHand.getCardTotal();
                    Toast toast = Toast.makeText(context, dBustText, duration);
                    toast.show();
                    newRound(secondCount);
                } else if (currentHand == 2 && handCount == 1) {
                    CharSequence dBustText = "Dealer Bust...You Win\n\nDealer Total : " + dealerHand.getCardTotal();
                    Toast toast = Toast.makeText(context, dBustText, duration);
                    toast.show();
                    newRound(secondCount+3000);

                } else if (currentHand == 1 && handCount == 2) {
                    CharSequence dBustText = "Dealer Bust...You Win Both Hands\n\nDealer Total : " + dealerHand.getCardTotal();
                    Toast toast = Toast.makeText(context, dBustText, duration);
                    toast.show();
                    newRound(secondCount+3000);

                }
            }
        }, milliseconds);
    }


    private void findWinnerLeftHand(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                if (handCount == 2) {
                    for (int i = 0; i < sViewIndex; i++) {
                        playerTwoViewList[i].setColorFilter(Color.argb(120, 0, 0, 0));
                    }
                    for (int i = 0; i < pViewIndex; i++) {
                        playerViewList[i].setColorFilter(Color.argb(0, 0, 0, 0));
                    }
                }
                if (dealerHand.getCardTotal() > playerHand.getCardTotal()) {
                    CharSequence text1 = "Dealer Wins!\n\nPlayer Total : " + playerHand.getCardTotal() + "\n\nDealer Total : " + dealerHand.getCardTotal();
                    Toast toast = Toast.makeText(context, text1, duration);
                    toast.show();
                } else if (dealerHand.getCardTotal() < playerHand.getCardTotal()) {
                    CharSequence text2 = "Well Done, You Win!\n\nPlayer Total : " + playerHand.getCardTotal() + "\n\nDealer Total : " + dealerHand.getCardTotal();
                    Toast toast = Toast.makeText(context, text2, duration);
                    toast.show();

                } else {
                    CharSequence text3 = "Tie!\n\nPlayer Total : " + playerHand.getCardTotal() + "\n\nDealer Total : " + dealerHand.getCardTotal();
                    Toast toast = Toast.makeText(context, text3, duration);
                    toast.show();
                }

            }
        }, milliseconds);
    }


    private void findWinnerRightHand(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {


                if (dealerHand.getCardTotal() > playerHandTwo.getCardTotal()) {
                    CharSequence text1 = "Dealer Wins!\n\nPlayer Total : " + playerHandTwo.getCardTotal() + "\n\nDealer Total : " + dealerHand.getCardTotal();
                    Toast toast = Toast.makeText(context, text1, duration);
                    toast.show();
                } else if (dealerHand.getCardTotal() < playerHandTwo.getCardTotal()) {
                    CharSequence text2 = "Well Done, You Win!\n\nPlayer Total : " + playerHandTwo.getCardTotal() + "\n\nDealer Total : " + dealerHand.getCardTotal();
                    Toast toast = Toast.makeText(context, text2, duration);
                    toast.show();
                } else {
                    CharSequence text3 = "Tie!\n\nPlayer Total : " + playerHandTwo.getCardTotal() + "\n\nDealer Total : " + dealerHand.getCardTotal();
                    Toast toast = Toast.makeText(context, text3, duration);
                    toast.show();
                }

            }
        }, milliseconds);
    }


    private void newRound(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (handCount ==2) {
                    resetCardColor();
                    clearSecondHand(0);
                    clearFirstHand(0);
                    clearDealerCards(0);
                    startNewRound();
                }
                else if (handCount==1 && currentHand == 1){
                    clearFirstHand(0);
                    clearDealerCards(0);
                    startNewRound();
                    }
                else if (handCount == 1 && currentHand == 2){
                    clearSecondHand(0);
                    clearDealerCards(0);
                    startNewRound();
                    }
              }
        }, milliseconds);


    }


    private void clearFirstHand(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                pViewIndex = 2;
                //for if a double down occurred, put the imageView straight
                p3.setRotation(0);
                for (ImageView iv : playerViewList) {
                    iv.setImageDrawable(null);
                }

            }
        }, milliseconds);
    }


    private void clearSecondHand(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                sViewIndex = 2;
                s3.setRotation(0);
                for (ImageView iv : playerTwoViewList) {
                    iv.setImageDrawable(null);
                }
            }
        }, milliseconds);
    }


    private void clearDealerCards(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dViewIndex = 2;
                for (ImageView iv : dealerViewList) {
                    iv.setImageDrawable(null);
                }
            }
        }, milliseconds);
    }



    private void startNewRound(){
        if (currentHand == 2 || handCount == 2){
            playerHandTwo.newHand();
            handCount = 1;
            currentHand = 1;
        }
        playerHand.newHand();
        dealerHand.newHand();
        secondCount = 0;
        deck.shuffle();
        playerBet = 10;
        playerBetText.setText(String.valueOf(playerBet));
        increaseBet.setVisibility(View.VISIBLE);
        decreaseBet.setVisibility(View.VISIBLE);
        placeBet.setVisibility(View.VISIBLE);
        split.setVisibility(View.INVISIBLE);
        doubleDown.setVisibility(View.INVISIBLE);


    }


    private void resetCardColor() {
        for (int i = 0; i < pViewIndex; i++) {
            playerViewList[i].setColorFilter(Color.argb(0, 0, 0, 0));
        }
        for (int i = 0; i < sViewIndex; i++) {
            playerTwoViewList[i].setColorFilter(Color.argb(0, 0, 0, 0));
        }

    }


    public void splitClick(View v){
        if(!dealerTurn ) {
            //NEED TO CREATE NEW IMAGEVIEW PROGRAMATICALLY FOR P2...
            TranslateAnimation animation = new TranslateAnimation(0.0f, 188.0f,
                    0.0f, 50.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
            animation.setDuration(2000);  // animation duration
            animation.setRepeatCount(0);  // animation repeat count
            animation.setRepeatMode(0);   // repeat animation (left to right, right to left )
            //animation.setFillAfter(true);


            p2.startAnimation(animation);
            split();

        }
    }


    private void split() {
        Card firstCard = playerHand.getCard(0);
        Card secondCard = playerHand.getCard(1);
        if (playerHand.numberOfCards() == 2 && firstCard.getValue() == secondCard.getValue()) {
            createSecondHand(secondCard);

        } else {
            CharSequence text = "Cannot split at this time.";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }

    private void createSecondHand(Card secondCard){
        //Create second hand object for chips
        playerTwoChips = new PlayerTwoChips(playerBet,BlackJack.this);
        currentHand = 2;
        handCount = 2;

        //Remove card from left hand
        playerHand.removeCard(1);
        //Add it to right hand
        playerHandTwo.addCard(secondCard);
        Card topCard = deck.getTopCard();
        playerHandTwo.addCard(topCard);
        setImage(s2, topCard, 2000);
        topCard = deck.getTopCard();
        playerHand.addCard(topCard);
        setImage(p2, topCard, 2500);
        p2.setColorFilter(Color.argb(120, 0, 0, 0));
        p1.setColorFilter(Color.argb(120, 0, 0, 0));
    }

    public void doubleDownClick(View v) {
        if(!dealerTurn) {
            if (currentHand == 1 && playerHand.numberOfCards() == 2) {
                doubleDown();
            } else if (currentHand == 2 && playerHandTwo.numberofCards() == 2) {
                doubleDown();
            } else {
                CharSequence text = "Cannot double down at this time.";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }

    }


    private void doubleDown(){
        topCard = deck.getTopCard();
        if (currentHand == 1) {
            currentView = playerViewList[pViewIndex];
            playerHand.addCard(topCard);
            setImageHorizontal(currentView, topCard, 0);
            if (playerHand.getCardTotal() > 21) {
                firstHandBust(1000);
            }   else{
                    dealerTurn(500);
            }
        }
        else{
            currentView = playerTwoViewList[sViewIndex];
            playerHandTwo.addCard(topCard);
            setImageHorizontal(currentView, topCard, 0);
            if(playerHandTwo.getCardTotal() > 21) {
              secondHandBust(1000);
            } else{
                currentHand = 1;
                p2.setColorFilter(Color.argb(0, 0, 0, 0));
                p1.setColorFilter(Color.argb(0, 0, 0, 0));
                for (int i=0; i< sViewIndex; i++){
                    playerTwoViewList[i].setColorFilter(Color.argb(150, 0, 0, 0));
                }}


        }
    }


    private void setImageHorizontal(ImageView iv, Card c, int milliSeconds) {
        final ImageView image = iv;
        final Card card = c;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                image.setImageResource(card.getIconId());
                image.setRotation(90);

            }
        }, milliSeconds);


    }







    public void onClick(DialogInterface d, int i) {

    }




    //Gesture Detector Class for onFling and doubleTap methods

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        //when user slides from left to right representing stand
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                        return false;
                    // right to left swipe
                    if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY ) {
                        Toast.makeText(BlackJack.this, "Swipe Left to Right for Stand", Toast.LENGTH_SHORT).show();

                    }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY ) {

                            if(handCount == 2 && currentHand == 2){
                                currentHand = 1;
                                p2.setColorFilter(Color.argb(0, 0, 0, 0));
                                p1.setColorFilter(Color.argb(0, 0, 0, 0));
                                for (int i=0; i< sViewIndex; i++){
                                    playerTwoViewList[i].setColorFilter(Color.argb(150, 0, 0, 0));
                                }}

                            else if (handCount == 2 && currentHand==1){
                                for(int i=0; i < pViewIndex; i++){
                                    playerViewList[i].setColorFilter(Color.argb(150, 0, 0, 0));
                                }
                                for (int i=0; i< sViewIndex; i++){
                                    playerTwoViewList[i].setColorFilter(Color.argb(0, 0, 0, 0));
                                }
                                dealerTurn(0);

                            }

                            else {
                                dealerTurn(500);
                        }

                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
                return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if(currentHand == 1) {
                firstHandHit();
            }
            else{
                secondHandHit();
            }


            return super.onSingleTapConfirmed(e);}


        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

    }





    //Create separate thread for long click to increase or decrease player bet
    class RptUpdater implements Runnable {
        public void run() {
            if( mAutoIncrement ){
                increaseBet.performClick();
                repeatUpdateHandler.postDelayed(new RptUpdater(), REP_DELAY);
            } else if( mAutoDecrement ){
                decreaseBet.performClick();
                repeatUpdateHandler.postDelayed(new RptUpdater(), REP_DELAY);
            }
        }
    }







     @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("test");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


   }






