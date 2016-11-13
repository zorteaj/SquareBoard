package com.jzap.squareboard.squareboard;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by JZ_W541 on 11/10/2016.
 */

public class Player extends CardView {

    private static final String mTag = "JZAP: Player";

    FrameLayout.LayoutParams mLayoutParams;
    private boolean pickedUp = false;
    private GestureDetector mGestureDetector;
    private Cell mCell;

    public Player(Context context, Cell cell) {
        super(context);
        mCell = cell;
        setUp();
        FrameLayout mainLayout = (FrameLayout) ((Activity) getContext()).findViewById(R.id.activity_main);  // TODO: This can't be good practice
        Log.i(mTag, "adding player");
        mainLayout.addView(this);
    }

    private void setUp() {
        setUpAttributes();
        setUpLayoutParams();
        setUpClickListener();
        setUpGestures();
    }

    private void setUpAttributes() {
        setCardBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_orange_light));
        setUseCompatPadding(true);
        setClipToOutline(false);
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

    private void pickUp() {
        animate().zBy(Settings.PLAYER_ELEVATION_DELTA);
        pickedUp = true;
    }

    private void putDown() {
        animate().zBy(-1 *Settings.PLAYER_ELEVATION_DELTA);
        pickedUp = false;
    }


    private void setUpGestures() {
        mGestureDetector = new GestureDetector(getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onFling(MotionEvent start, MotionEvent event, float velocityX, float velocityY) {
                        Log.i(mTag, "Flinged");
                        float angle = Utils.Gestures.flipAngle(start, event);
                        Log.i(mTag, "Angle = " + angle);
                        //if(pickedUp) {
                            move(angle);
                        //}
                        return false;
                    }
                });

        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(mTag, "Touched");
                mGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    public void setCell(Cell cell) {
        mCell = cell;
    }

    private void move(float angle) {
        mCell.getGameBoard().getMoveManager().move(this, mCell, angle);
    }

}
