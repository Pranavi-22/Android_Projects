package com.example.last_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class ContinuousActivity extends AppCompatActivity {
    //intialiating the count of player moves by player 1 and player 2
    protected int count=1;
    protected int count1=1;

    //Two arrays for player 1 and player 2 as I have created the text buttons with unique ids
    final int[] PlayerArray1 = {
            R.id.txt1, R.id.txt2, R.id.txt3, R.id.txt4, R.id.txt5, R.id.txt6, R.id.txt7, R.id.txt8, R.id.txt9, R.id.txt10,
            R.id.txt11, R.id.txt12, R.id.txt13, R.id.txt14, R.id.txt15, R.id.txt16, R.id.txt17, R.id.txt18, R.id.txt19, R.id.txt20,
            R.id.txt21, R.id.txt22, R.id.txt23, R.id.txt24, R.id.txt25, R.id.txt26, R.id.txt27, R.id.txt28, R.id.txt29, R.id.txt30,
            R.id.txt31, R.id.txt32, R.id.txt33, R.id.txt34, R.id.txt35, R.id.txt36, R.id.txt37, R.id.txt38, R.id.txt39, R.id.txt40,
            R.id.txt41, R.id.txt42, R.id.txt43, R.id.txt44, R.id.txt45, R.id.txt46, R.id.txt47, R.id.txt48, R.id.txt49, R.id.txt50,
            R.id.txt51, R.id.txt52, R.id.txt53, R.id.txt54, R.id.txt55, R.id.txt56, R.id.txt57, R.id.txt58, R.id.txt59, R.id.txt60,
            R.id.txt61, R.id.txt62, R.id.txt63, R.id.txt64, R.id.txt65, R.id.txt66, R.id.txt67, R.id.txt68, R.id.txt69, R.id.txt70,
            R.id.txt71, R.id.txt72, R.id.txt73, R.id.txt74, R.id.txt75, R.id.txt76, R.id.txt77, R.id.txt78, R.id.txt79, R.id.txt80,
            R.id.txt81, R.id.txt82, R.id.txt83, R.id.txt84, R.id.txt85, R.id.txt86, R.id.txt87, R.id.txt88, R.id.txt89, R.id.txt90,
            R.id.txt91, R.id.txt92, R.id.txt93, R.id.txt94, R.id.txt95, R.id.txt96, R.id.txt97, R.id.txt98, R.id.txt99, R.id.txt100
    };
    //player 2 values taking from
    final int[] PlayerArray2 = {
            R.id.txt101, R.id.txt102, R.id.txt103, R.id.txt104, R.id.txt105, R.id.txt106, R.id.txt107, R.id.txt108, R.id.txt109, R.id.txt110,
            R.id.txt111, R.id.txt112, R.id.txt113, R.id.txt114, R.id.txt115, R.id.txt116, R.id.txt117, R.id.txt118, R.id.txt119, R.id.txt120,
            R.id.txt121, R.id.txt122, R.id.txt123, R.id.txt124, R.id.txt125, R.id.txt126, R.id.txt127, R.id.txt128, R.id.txt129, R.id.txt130,
            R.id.txt131, R.id.txt132, R.id.txt133, R.id.txt134, R.id.txt135, R.id.txt136, R.id.txt137, R.id.txt138, R.id.txt139, R.id.txt140,
            R.id.txt141, R.id.txt142, R.id.txt143, R.id.txt144, R.id.txt145, R.id.txt146, R.id.txt147, R.id.txt148, R.id.txt149, R.id.txt150,
            R.id.txt151, R.id.txt152, R.id.txt153, R.id.txt154, R.id.txt155, R.id.txt156, R.id.txt157, R.id.txt158, R.id.txt159, R.id.txt160,
            R.id.txt161, R.id.txt162, R.id.txt163, R.id.txt164, R.id.txt165, R.id.txt166, R.id.txt167, R.id.txt168, R.id.txt169, R.id.txt170,
            R.id.txt171, R.id.txt172, R.id.txt173, R.id.txt174, R.id.txt175, R.id.txt176, R.id.txt177, R.id.txt178, R.id.txt179, R.id.txt180,
            R.id.txt181, R.id.txt182, R.id.txt183, R.id.txt184, R.id.txt185, R.id.txt186, R.id.txt187, R.id.txt188, R.id.txt189, R.id.txt190,
            R.id.txt191, R.id.txt192, R.id.txt193, R.id.txt194, R.id.txt195, R.id.txt196, R.id.txt197, R.id.txt198, R.id.txt199, R.id.txt200
    };

    //stop button
    Button stop;
    // two arrays we are using to check whether there to place player1 ,player 2 , gopher
    int[] playerArraysize1 = new int[100];
    int[] playerArraysize2 = new int[100];
    //handlers
    private Handler rHandler = new Handler();
    private Handler mHandler;
    //isgopherfound is false intially it is is found it is changed to true
    boolean isGopherFound = false;
    //textviews in order to intialize the moves and gamestatus
    TextView movesmade1;
    TextView movesmade2;
    TextView gameStatus;
    //these are the outcomes
    final String[] outcomes = {"", "Success", "Near Miss", "Close Guess", "Complete Miss"};
    boolean player1Move = true;
    int GOPHER_POSITION;
    //Case statements using for gopher,player1 and player2
    final int GOPHER = 0;
    final static int PLAYER1 = 1;
    final static int PLAYER2 = 2;
    //similar case statements
    final int SUCCESS = 1;
    static final int NEARMISS = 2;
    static final int CLOSEGUESS = 3;
    static final int COMPLETEMISS = 4;
    //handlers
    Handler t1Handler;
    Handler kHandler;
    Handler LHandler;


    //initializing GOPHER_POSITION_CLOSE,GOPHER_POSITION_NEAR
    ArrayList<Integer> GOPHER_POSITION_CLOSE = new ArrayList<Integer>(Arrays.asList(-999, -999, -999, -999, -999, -999, -999, -999));
    ArrayList<Integer> GOPHER_POSITION_NEAR = new ArrayList<Integer>(Arrays.asList(-999, -999, -999, -999, -999, -999, -999, -999));

    // in order to remove the duplicate values from the list beacuse I am randomly generating the values so I might get duplicate values
    //so craeted a syncronized list to be thread safe
    List even_vales = Collections.synchronizedList(new ArrayList());
    List odd_values = Collections.synchronizedList(new ArrayList());
    public Handler playerOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuous);
        //intializing with -1
        for (int i = 0; i < 100; i++) {
            playerArraysize1[i] = -1;
            playerArraysize2[i] = -1;
        }
        //to keep track of moves made and also keep a scrolling movement for the etxt box
        movesmade1 = findViewById(R.id.movesmade1);
        movesmade2 = findViewById(R.id.movesmade2);
        movesmade1.setMovementMethod(new ScrollingMovementMethod());
        movesmade2.setMovementMethod(new ScrollingMovementMethod());
        movesmade1.setText("");
        movesmade2.setText("");

        //Set random position of GOPHER and place it at the position generated
        GOPHER_POSITION = (int) (Math.random() * 100) % 100;

        // Array pos 0 = topLeft ,Array pos 1 = top, Array pos 2  = topRight ,Array pos 3 = right, Array pos 4 = bottomRight ,Array pos 6 = bottomLeft ,Array pos 7 = left
        final int topLeft = 0, top = 1, topRight = 2, right = 3, bottomRight = 4,bottom = 5, bottomLeft = 6,  left = 7;

        //GopherRow and RowCol
        int gopherRow = (GOPHER_POSITION - (GOPHER_POSITION % 10)) / 10;
        int gopherCol = GOPHER_POSITION % 10;

        // Check if gopher pos is in first row
        // If true then no top neighbors
        if (gopherRow == 0) {
            // if gopher is in column 0 then no left neighbors
            if (gopherCol == 0) {
                GOPHER_POSITION_CLOSE.add(right, GOPHER_POSITION + 2);
                GOPHER_POSITION_CLOSE.add(bottomRight, GOPHER_POSITION + 22);
                GOPHER_POSITION_CLOSE.add(bottom, GOPHER_POSITION + 20);
                GOPHER_POSITION_NEAR.add(right, GOPHER_POSITION + 1);
                GOPHER_POSITION_NEAR.add(bottomRight, GOPHER_POSITION + 11);
                GOPHER_POSITION_NEAR.add(bottom, GOPHER_POSITION + 10);
            }
            // if gopher is in column 9 then no right neighbors
            else if (gopherCol == 9) {
                GOPHER_POSITION_CLOSE.add(left, GOPHER_POSITION - 2);
                GOPHER_POSITION_CLOSE.add(bottomLeft, GOPHER_POSITION + 18);
                GOPHER_POSITION_CLOSE.add(bottom, GOPHER_POSITION + 20);
                GOPHER_POSITION_NEAR.add(left, GOPHER_POSITION - 1);
                GOPHER_POSITION_NEAR.add(bottomLeft, GOPHER_POSITION + 9);
                GOPHER_POSITION_NEAR.add(bottom, GOPHER_POSITION + 10);
            } else {
                GOPHER_POSITION_CLOSE.add(left, GOPHER_POSITION - 2);
                GOPHER_POSITION_CLOSE.add(bottomLeft, GOPHER_POSITION + 18);
                GOPHER_POSITION_CLOSE.add(bottom, GOPHER_POSITION + 20);
                GOPHER_POSITION_CLOSE.add(bottomRight, GOPHER_POSITION + 22);
                GOPHER_POSITION_CLOSE.add(right, GOPHER_POSITION + 2);
                GOPHER_POSITION_NEAR.add(left, GOPHER_POSITION - 1);
                GOPHER_POSITION_NEAR.add(bottomLeft, GOPHER_POSITION + 9);
                GOPHER_POSITION_NEAR.add(bottom, GOPHER_POSITION + 10);
                GOPHER_POSITION_NEAR.add(bottomRight, GOPHER_POSITION + 11);
                GOPHER_POSITION_NEAR.add(right, GOPHER_POSITION + 1);
            }
        }
        // Check if gopher pos is in last row
        // If true then no bottom neighbors
        else if (gopherRow == 9) {
            // if gopher column is 0 then no left neighbors
            if (gopherCol == 0) {
                GOPHER_POSITION_CLOSE.add(top, GOPHER_POSITION - 20);
                GOPHER_POSITION_CLOSE.add(topRight, GOPHER_POSITION - 18);
                GOPHER_POSITION_CLOSE.add(right, GOPHER_POSITION + 2);
                GOPHER_POSITION_NEAR.add(top, GOPHER_POSITION - 10);
                GOPHER_POSITION_NEAR.add(topRight, GOPHER_POSITION - 9);
                GOPHER_POSITION_NEAR.add(right, GOPHER_POSITION + 1);
            }
            // if gopher column is 9 then no right neighbors
            else if (gopherCol == 9) {
                GOPHER_POSITION_CLOSE.add(left, GOPHER_POSITION - 2);
                GOPHER_POSITION_CLOSE.add(topLeft, GOPHER_POSITION - 22);
                GOPHER_POSITION_CLOSE.add(top, GOPHER_POSITION - 20);
                GOPHER_POSITION_NEAR.add(left, GOPHER_POSITION - 1);
                GOPHER_POSITION_NEAR.add(topLeft, GOPHER_POSITION - 11);
                GOPHER_POSITION_NEAR.add(top, GOPHER_POSITION - 10);
            } else {
                GOPHER_POSITION_CLOSE.add(left, GOPHER_POSITION - 2);
                GOPHER_POSITION_CLOSE.add(topLeft, GOPHER_POSITION - 22);
                GOPHER_POSITION_CLOSE.add(top, GOPHER_POSITION - 20);
                GOPHER_POSITION_CLOSE.add(topRight, GOPHER_POSITION - 18);
                GOPHER_POSITION_CLOSE.add(right, GOPHER_POSITION + 2);
                GOPHER_POSITION_NEAR.add(left, GOPHER_POSITION - 1);
                GOPHER_POSITION_NEAR.add(topLeft, GOPHER_POSITION - 11);
                GOPHER_POSITION_NEAR.add(top, GOPHER_POSITION - 10);
                GOPHER_POSITION_NEAR.add(topRight, GOPHER_POSITION - 9);
                GOPHER_POSITION_NEAR.add(right, GOPHER_POSITION + 1);
            }
        }
        // if gopher positions is in column 0 and not in corners then no left neighbors
        else if (gopherCol == 0 && gopherRow != 0 && gopherRow != 9) {
            GOPHER_POSITION_CLOSE.add(top, GOPHER_POSITION - 20);
            GOPHER_POSITION_CLOSE.add(topRight, GOPHER_POSITION - 22);
            GOPHER_POSITION_CLOSE.add(right, GOPHER_POSITION - 2);
            GOPHER_POSITION_CLOSE.add(bottomRight, GOPHER_POSITION + 18);
            GOPHER_POSITION_CLOSE.add(bottom, GOPHER_POSITION + 20);
            GOPHER_POSITION_NEAR.add(top, GOPHER_POSITION - 10);
            GOPHER_POSITION_NEAR.add(topRight, GOPHER_POSITION - 9);
            GOPHER_POSITION_NEAR.add(right, GOPHER_POSITION + 1);
            GOPHER_POSITION_NEAR.add(bottomRight, GOPHER_POSITION + 11);
            GOPHER_POSITION_NEAR.add(bottom, GOPHER_POSITION + 10);
        }
        // if gopher positions is in column 9 and not in corners then no right neighbors
        else if (gopherCol == 9 && gopherRow != 0 && gopherRow != 9) {
            GOPHER_POSITION_CLOSE.add(top, GOPHER_POSITION - 20);
            GOPHER_POSITION_CLOSE.add(topLeft, GOPHER_POSITION - 22);
            GOPHER_POSITION_CLOSE.add(left, GOPHER_POSITION - 2);
            GOPHER_POSITION_CLOSE.add(bottomLeft, GOPHER_POSITION + 18);
            GOPHER_POSITION_CLOSE.add(bottom, GOPHER_POSITION + 20);
            GOPHER_POSITION_NEAR.add(top, GOPHER_POSITION - 10);
            GOPHER_POSITION_NEAR.add(topLeft, GOPHER_POSITION - 11);
            GOPHER_POSITION_NEAR.add(left, GOPHER_POSITION - 1);
            GOPHER_POSITION_NEAR.add(bottomLeft, GOPHER_POSITION + 9);
            GOPHER_POSITION_NEAR.add(bottom, GOPHER_POSITION + 10);
        }
        // Anywhere in the grid with all 8 neighbors
        else {
            GOPHER_POSITION_CLOSE.add(topLeft, GOPHER_POSITION - 22);
            GOPHER_POSITION_CLOSE.add(left, GOPHER_POSITION - 2);
            GOPHER_POSITION_CLOSE.add(bottomLeft, GOPHER_POSITION + 18);
            GOPHER_POSITION_CLOSE.add(bottom, GOPHER_POSITION + 20);
            GOPHER_POSITION_CLOSE.add(bottomRight, GOPHER_POSITION + 22);
            GOPHER_POSITION_CLOSE.add(right, GOPHER_POSITION + 2);
            GOPHER_POSITION_CLOSE.add(topRight, GOPHER_POSITION - 18);
            GOPHER_POSITION_CLOSE.add(top, GOPHER_POSITION - 20);

            GOPHER_POSITION_NEAR.add(topLeft, GOPHER_POSITION - 11);
            GOPHER_POSITION_NEAR.add(left, GOPHER_POSITION - 1);
            GOPHER_POSITION_NEAR.add(bottomLeft, GOPHER_POSITION + 9);
            GOPHER_POSITION_NEAR.add(bottom, GOPHER_POSITION + 10);
            GOPHER_POSITION_NEAR.add(bottomRight, GOPHER_POSITION + 11);
            GOPHER_POSITION_NEAR.add(right, GOPHER_POSITION + 1);
            GOPHER_POSITION_NEAR.add(topRight, GOPHER_POSITION - 9);
            GOPHER_POSITION_NEAR.add(top, GOPHER_POSITION - 10);
        }

       /* Log.i("near", "Turn = " + GOPHER_POSITION);
        Log.i("near", "Turn = " + GOPHER_POSITION_NEAR);
        Log.i("close", "Turn = " + GOPHER_POSITION_CLOSE);*/

        //Gopherposition place_player to place the values
        place_player(GOPHER_POSITION, GOPHER);
        stop = (Button) findViewById(R.id.button);
        gameStatus = findViewById(R.id.gamestatus);



        //handlers ui thread
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                Log.i("Message Handler", "Received " + msg.what + " " + msg.arg1 + " " + msg.arg2);
                int what = msg.what;
                switch (what) {
                    case PLAYER2:
                        place_player(msg.arg1, PLAYER2);
                        movesmade2.setText(outcomes[msg.arg2]);

                        if (msg.arg2 == SUCCESS)
                            gameStatus.setText("Player 2 won!");
                        break;
                }
            }
        };

        t1Handler = new Handler() {
            public void handleMessage(Message msg) {
                int what = msg.what;
                switch (what) {
                    case SUCCESS:
                        movesmade1.setText("success");

                        break;
                    case NEARMISS:
                        movesmade1.setText("nearmiss");

                        break;
                    case CLOSEGUESS:
                        movesmade1.setText("closeguess");

                        break;
                    case COMPLETEMISS:
                        movesmade1.setText("completemiss");

                        break;

                }
            }
        };

        //thread t1 and t2
        Thread t1 = new Thread(new RunnableThread1());
        t1.start();
        Thread t2 = new Thread(new RunnableThread2());
        t2.start();

        // stop button which will go the first page
        stop = findViewById(R.id.button);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count1=1;
                count=1;
                even_vales.removeAll(even_vales);
                odd_values.removeAll(odd_values);
                GOPHER_POSITION_CLOSE.removeAll(GOPHER_POSITION_CLOSE);
                GOPHER_POSITION_NEAR.removeAll(GOPHER_POSITION_NEAR);
                gameStatus.setText(null);
                movesmade1.setText(null);
                movesmade2.setText(null);
                Intent intent = new Intent(ContinuousActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


    //Place the provided item at the position mentioned
    public void place_player(int pos, int item) {

        TextView t1;
        TextView t2;
        switch (item) {
            case GOPHER: {
                // values will be place in that position
                t1 = findViewById(PlayerArray1[pos]);
                //for t1 and t2
                t2 = findViewById(PlayerArray2[pos]);
                // setBackgroundResources will be place in two matrix the gopher
                t1.setBackgroundResource(R.drawable.gopher);
                t2.setBackgroundResource(R.drawable.gopher);
                //playerArraysize is matrix position GOPHER is places
                playerArraysize1[pos] = GOPHER;
                playerArraysize2[pos] = GOPHER;
                break;
            }
            case PLAYER1: {
                //PLAYER1 is placed
                t1 = findViewById(PlayerArray1[pos]);
                t1.setText(Integer.toString(count++));
                playerArraysize1[pos] = PLAYER1;

                break;
            }
            case PLAYER2: {
                //PLAYER2 is placed
                t2 = findViewById(PlayerArray2[pos]);
                t2.setText(Integer.toString(count1++));
                playerArraysize2[pos] = PLAYER2;
                break;
            }

        }

    }

    //Check position given by the player and take corresponding decision
    class RunnableThread1 implements Runnable {
        @Override
        public void run() {
            Looper.prepare();
            while (!isGopherFound) {


                player1Move = false;

                //random value will be generated and the algorithm used for this position is even values
                Random random = new Random();
                //this is used in order to remove duplicate values
                int myPos=-1;
                int flag=0;
                while(flag==0) {
                    myPos = random.nextInt(100 / 2) * 2;
                    if (!even_vales.contains(myPos)) {
                        even_vales.add(myPos);
                        flag=1;
                    }
                }




                int outcome;
                // THIS IS USED AS A AN HANDLE MESSAGE TO CHECK WHETHER IT IS IN THE PARTICULAR POSITION AND SEND MESSAGE TO UI THREAD AND PRINT IT
                if (myPos == GOPHER_POSITION) {
                    outcome = SUCCESS;
                    Message msg = t1Handler.obtainMessage(ContinuousActivity.CLOSEGUESS, PLAYER1);
                    t1Handler.sendMessage(msg);

                } else if (GOPHER_POSITION_CLOSE.contains(myPos)) {
                    outcome = CLOSEGUESS;
                    Message msg = t1Handler.obtainMessage(ContinuousActivity.CLOSEGUESS, PLAYER1);
                    t1Handler.sendMessage(msg);
                } else if (GOPHER_POSITION_NEAR.contains(myPos)) {
                    outcome = NEARMISS;
                    Message msg = t1Handler.obtainMessage(ContinuousActivity.NEARMISS, PLAYER1);
                    t1Handler.sendMessage(msg);

                } else {
                    Message msg = t1Handler.obtainMessage(ContinuousActivity.COMPLETEMISS, PLAYER1);
                    t1Handler.sendMessage(msg);
                    outcome = COMPLETEMISS;
                }


                //Send runnable to UI Thread Handler to place item for PLayer 1
                int finalMyPos = myPos;
                t1Handler.post(new Runnable() {
                    public void run() {
                        place_player(finalMyPos, PLAYER1);

                    }
                });

                if(outcome == SUCCESS){
                    isGopherFound =true;
                    t1Handler.post(new Runnable() {
                        public void run() {
                            gameStatus.setText("Player 1 won!");
                        }
                    });
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            playerOne = new Handler() {
                public void handleMessage(Message msg) {
                    Log.i("handling", "Received " + msg.what);
                    t1Handler.sendMessage(msg);
                }
            };
            Looper.loop();


        }
    }


    class RunnableThread2 implements Runnable {
        @Override
        public void run() {
            while (!isGopherFound) {
                player1Move = true;
                Random random = new Random();

               //random value will be generated and the algorithm used for this position is even values
                int counter=-1;
                int flag=0;
                while(flag==0) {
                    counter = random.nextInt(100 / 2) * 2 + 1;
                    if (!even_vales.contains(counter)) {
                        even_vales.add(counter);
                        flag=1;
                    }
                }
                Log.i("values", String.valueOf(counter));


                // IT WILL CHECK HERE THE POSITION
                int outcome;

                if (counter == GOPHER_POSITION) {
                    outcome = SUCCESS;
                } else if (GOPHER_POSITION_CLOSE.contains(counter)) {
                    outcome = CLOSEGUESS;
                } else if (GOPHER_POSITION_NEAR.contains(counter)) {
                    outcome = NEARMISS;
                } else
                    outcome = COMPLETEMISS;


                Log.i("Player2", "Player 2 :" + outcome);
                final int myPos = counter;

                //Send message to UI Thread handler to place item for PLayer 2
                Message msg = mHandler.obtainMessage(ContinuousActivity.PLAYER2);
                msg.arg1 = myPos;
                msg.arg2 = outcome;
                mHandler.sendMessage(msg);

                if (outcome == SUCCESS) {
                    isGopherFound = true;
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //LOOPER TO SEND THE MESSAGE
            LHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case SUCCESS:
                            t1Handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Message msg = t1Handler.obtainMessage(SUCCESS);


                                }
                            });



                    }
                }


            };
        }
    }
}




