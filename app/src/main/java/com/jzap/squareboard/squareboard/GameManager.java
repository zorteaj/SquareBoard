package com.jzap.squareboard.squareboard;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by JZ_W541 on 11/16/2016.
 */

public class GameManager {

    private static final String mTag = "JZAP: GameManager";

    private MainActivity mMainActivity;
    private Handler mHandler;
    private GameBoard mGameBoard;
    private RulesManager mRulesManager;
    private Player mPlayers[] = new Player[2];
    private GameState mGameState;


    class GameState {
        private Player mTurn;

        public GameState() {}

        public void setTurn(Player player) {
            mTurn = player;
        }

        public Player getTurn() {
            return mTurn;
        }
    }

    GameManager(MainActivity mainActivity)
    {
        mMainActivity = mainActivity;
        mGameState = new GameState();
        setUp();
    }

    private void setUp() {
        // Don't work with the GameBoard until it's ready
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                mPlayers[0] = new Player(GameManager.this, "Top");
                mPlayers[0].addPiece(new Cell.Coordinates(0, 0));

                mPlayers[1] = new Player(GameManager.this, "Bottom");
                mPlayers[1].addPiece(new Cell.Coordinates(5, 3));

                mGameState.setTurn(mPlayers[0]);
            }
        };

        mGameBoard = new GameBoard(this, mMainActivity, Settings.NUM_BOARD_COLUMNS);
        mMainActivity.getMainLayout().addView(mGameBoard);
        mRulesManager = new RulesManager(mMainActivity);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!mGameBoard.getReady()){}
                mHandler.obtainMessage().sendToTarget();
            }
        }).start();
    }

    public RulesManager getRulesManager() {
        return mRulesManager;
    }

    public GameBoard getGameBoard() {
        return mGameBoard;
    }

    public GameState getGameState() {
        return mGameState;
    }

    public void switchTurn() {
        if(mGameState.getTurn() == mPlayers[0]) {
            mGameState.setTurn(mPlayers[1]);
        } else {
            mGameState.setTurn(mPlayers[0]);
        }
    }
}
