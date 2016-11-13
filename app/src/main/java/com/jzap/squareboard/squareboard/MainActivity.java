package com.jzap.squareboard.squareboard;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static final String mTag = "JZAP: MainActivity";

    GameBoard mGameBoard;
    FrameLayout mMainLayout;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                Log.i(mTag, "Got a message");
                mGameBoard.cellAtCoordinates(new Cell.Coordinates(0, 0)).addPlayer();
            }
        };

        mMainLayout = (FrameLayout) findViewById(R.id.activity_main);
        mMainLayout.setClipToOutline(false);
        init();
    }

    private void init() {
        Log.i(mTag, "Init");
        mGameBoard = new GameBoard(this, Settings.NUM_BOARD_COLUMNS);
        mMainLayout.addView(mGameBoard);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!mGameBoard.getReady()){};
                mHandler.obtainMessage().sendToTarget();
            }
        }).start();

//        CardView c = new CardView(this);
//        FrameLayout.LayoutParams l = new FrameLayout.LayoutParams(50, 50);
//        c.setLayoutParams(l);
//        c.setX(200);
//        c.setY(200);
//        c.setCardBackgroundColor(Color.RED);
//        mMainLayout.addView(c);
    }

}
