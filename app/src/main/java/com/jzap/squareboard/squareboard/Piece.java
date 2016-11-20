package com.jzap.squareboard.squareboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/**
 * Created by JZ_W541 on 11/10/2016.
 */

public class Piece extends CardView {

    private static final String mTag = "JZAP: Player";

    public enum FlingSector {TOP, MIDDLE, BOTTOM}

    FrameLayout.LayoutParams mLayoutParams;
    private boolean pickedUp = false;
    private GestureDetector mGestureDetector;
    private Cell mCell;
    IntegerRuleProperty mColor;
    private Handler mHandler;
    private Player mPlayer;

    public Piece(Context context, Cell cell, Player player) {
        super(context);
        mCell = cell;
        mPlayer = player;
        setUp();
        FrameLayout mainLayout = (FrameLayout) ((Activity) getContext()).findViewById(R.id.activity_main);  // TODO: This can't be good practice


        // TODO: WTF
        GameBoard mGameBoard = cell.getGameBoard();
        GameBoard.GameRow gameRow = ((GameBoard.GameRow)cell.getParent());
        setY(gameRow.getTop()  + mainLayout.getPaddingTop() + (Settings.CELL_PADDING / 2));
        setX(cell.getLeft() + (Settings.CELL_PADDING / 2));// + mainLayout.getPaddingLeft());// + );

        Log.i(mTag, "adding player");
        mainLayout.addView(this);

        testingRulesIF();
    }

    private void setUp() {
        setUpHandler();
        setUpRules();
        setUpAttributes();
        setUpLayoutParams();
        setUpClickListener();
        setUpGestures();
    }

    private void setUpHandler() {
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                Log.i(mTag, "GOT A MESSAGE");
                // TODO: Obviously we want the color actually passed in the message
                setCardBackgroundColor(((IntegerRuleProperty)inputMessage.obj).getValue());
            }
        };

    }

    private void setUpAttributes() {
        //setCardBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_orange_light));


        setCardBackgroundColor(mColor.getValue());





        setUseCompatPadding(true);
        setClipToOutline(false);
        setElevation(20.0f);
        //setAlpha(Settings.PLAYER_DOWN_ALPHA);
    }

    private void setUpLayoutParams() {
        mLayoutParams = new FrameLayout.LayoutParams(mCell.getWidth() - Settings.CELL_PADDING, mCell.getHeight() - Settings.CELL_PADDING);
        setLayoutParams(mLayoutParams);

        setY(mCell.getGameBoard().getTopPosition() + (Settings.CELL_PADDING / 2));
        setX(Settings.CELL_PADDING / 2);
    }

    private void setUpClickListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pickedUp) {
                    putDown();
                } else {
                    pickUp();
                }
            }
        });
    }

    private void setUpRules() {
        mColor = (IntegerRuleProperty) mCell.getGameBoard().getGameManager().getRulesManager().addRuleProperty(true, getHandler(), RulesManager.RuleType.INTEGER);
    }

    private void pickUp() {
//        animate().zBy(Settings.PLAYER_ELEVATION_DELTA);
//        animate().alpha(1.0f);
//        pickedUp = true;
    }

    private void putDown() {
//        animate().zBy(-1 *Settings.PLAYER_ELEVATION_DELTA);
//        animate().alpha(Settings.PLAYER_DOWN_ALPHA);
//        pickedUp = false;
    }


    private void setUpGestures() {
        mGestureDetector = new GestureDetector(getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onFling(MotionEvent start, MotionEvent event, float velocityX, float velocityY) {
                        float angle = Utils.Gestures.flipAngle(start, event);
                        FlingSector sector = getFlingStartSector(start);
                       // if(pickedUp) {
                            move(angle, sector);
                        //}
                        return true;
                    }
                });

        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    public void setCell(Cell cell) {
        mCell = cell;
    }

    private void move(float angle, FlingSector sector) {
        if(mCell.getGameBoard().getGameManager().getGameState().getTurn() == mPlayer) {
            mCell.getGameBoard().getMoveManager().move(this, mCell, angle, sector);
            mCell.getGameBoard().getGameManager().switchTurn();
        }
    }

    private FlingSector getFlingStartSector(MotionEvent start) {
        FlingSector sector;

        float playerHeightThird = getHeight() / 3;
        float sector1 = playerHeightThird;
        float sector2 = playerHeightThird * 2;

        if(start.getY() < sector1 ) {
            sector = FlingSector.TOP;
        } else if(start.getY() < sector2) {
            sector = FlingSector.MIDDLE;
        } else {
            sector = FlingSector.BOTTOM;
        }

        return sector;
    }

    private void testingRulesIF() {
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(mTag, "Long clicked");

               // mainLayout.addView(c);

                mCell.getGameBoard().getGameManager().getRulesManager().show();

                return false;
            }
        });
    }

    public Handler getHandler() {
        return mHandler;
    }

}
