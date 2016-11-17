package com.jzap.squareboard.squareboard;

import android.app.Activity;
import android.content.Context;
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

public class Player extends CardView {

    private static final String mTag = "JZAP: Player";

    public enum FlingSector {TOP, MIDDLE, BOTTOM}

    FrameLayout.LayoutParams mLayoutParams;
    private boolean pickedUp = false;
    private GestureDetector mGestureDetector;
    private Cell mCell;

    public Player(Context context, Cell cell) {
        super(context);
        mCell = cell;
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
        setUpAttributes();
        setUpLayoutParams();
        setUpClickListener();
        setUpGestures();
    }

    private void setUpAttributes() {
        setCardBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_orange_light));
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
        mCell.getGameBoard().getMoveManager().move(this, mCell, angle, sector);
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
                FrameLayout mainLayout = (FrameLayout) ((Activity) getContext()).findViewById(R.id.activity_main);

                CardView c = new CardView(getContext());
                FrameLayout.LayoutParams l = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                c.setLayoutParams(l);
                c.setElevation(20.0f);
                mainLayout.addView(c);


                LinearLayout linear = new LinearLayout(getContext());
                ViewGroup.LayoutParams lpp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                linear.setLayoutParams(lpp);
                c.addView(linear);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                RadioButton b = new RadioButton(getContext());
                b.setLayoutParams(lp);
                linear.addView(b);


               // mainLayout.addView(c);




                return false;
            }
        });
    }

}
