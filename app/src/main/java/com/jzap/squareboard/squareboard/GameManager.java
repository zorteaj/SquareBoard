package com.jzap.squareboard.squareboard;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by JZ_W541 on 11/16/2016.
 */

public class GameManager {

    MainActivity mMainActivity;
    private Handler mHandler;
    GameBoard mGameBoard;

    GameManager(MainActivity mainActivity)
    {
        mMainActivity = mainActivity;
        setUp();
    }

    private void setUp() {
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                mGameBoard.cellAtCoordinates(new Cell.Coordinates(0, 0)).addPlayer();
                mGameBoard.cellAtCoordinates(new Cell.Coordinates(1, 1)).addPlayer();
            }
        };

        mGameBoard = new GameBoard(mMainActivity, Settings.NUM_BOARD_COLUMNS);
        mMainActivity.getMainLayout().addView(mGameBoard);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!mGameBoard.getReady()){};
                mHandler.obtainMessage().sendToTarget();
            }
        }).start();
    }

}
