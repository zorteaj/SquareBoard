package com.jzap.squareboard.squareboard;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


/**
 * Created by JZ_W541 on 11/10/2016.
 */

public class GameBoard extends TableLayout {

    private static final String mTag = "JZAP: GameBoard";

    private MoveManager mMoveManager;
    private int mNumColumns;
    private int mNumRows;
    private FrameLayout.LayoutParams mLayoutParams;
    private boolean mReady = false;
    private int mCellSize;


    public class GameRow extends TableRow {
        TableLayout.LayoutParams mLayoutParams;
        private int mIndex;

        public GameRow(Context context, int index) {
            super(context);
            mIndex = index;
            setUpRow();
        }

        private void setUpRow() {
            setUpRowAttributes();
            setUpRowLayoutParams();
            setUpRowCells();
        }

        private void setUpRowAttributes() {
            setClipToPadding(false);
            setClipChildren(false);
            setClipToOutline(false);
        }

        private void setUpRowLayoutParams() {
            mLayoutParams = new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        private void setUpRowCells() {
            for(int i = 0; i < mNumColumns; i++) {
                Cell cell = new Cell(getContext(), mCellSize, GameBoard.this, new Cell.Coordinates(mIndex, i));
                addView(cell);
            }
        }

    }


    public GameBoard(Context context, int numColumns) {
        super(context);
        mNumColumns = numColumns;
        preLayoutSetUp();
    }

    private void preLayoutSetUp() {
        setUpLayoutParams();
        setUpLayoutListener();
        setUpAttributes();
    }

    public void postLayoutSetUp() {
        setUpMeasurements();
        drawBoard();
        resizeForContent();
        setUpMoveManager();
        mReady = true;
    }

    private void setUpLayoutListener() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                postLayoutSetUp();
            }
        });
    }

    private void setUpLayoutParams() {
        mLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mLayoutParams.gravity = Gravity.CENTER;
        this.setLayoutParams(mLayoutParams);
    }

    private void setUpAttributes() {
        setClipToPadding(false);
        setClipChildren(false);
        setClipToOutline(false);
    }

    private void resizeForContent() {
        mLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        //mLayoutParams.addRule(FrameLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mLayoutParams.gravity = Gravity.CENTER;
        setLayoutParams(mLayoutParams);
    }

    private void setUpMeasurements() {
        mCellSize = this.getWidth() / mNumColumns;
        mNumRows = this.getHeight() / mCellSize;
    }

    private void setUpMoveManager() {
        if(Settings.MOVE_STYLE == MoveManager.MoveStyle.STANDARD) {
            mMoveManager = new StandardMoveManager(this);
        } else if(Settings.MOVE_STYLE == MoveManager.MoveStyle.BOOMERANG) {
            mMoveManager = new BoomerangMoveManager(this);
        }
    }

    private void drawBoard() {
        for(int i = 0; i < mNumRows; i++) {
            GameRow gameRow = new GameRow(getContext(), i);
            addView(gameRow);
        }
    }

    public boolean getReady() {
        return mReady;
    }

    public int getCellSize() {
        return mCellSize;
    }

    public MoveManager getMoveManager() {
        return mMoveManager;
    }

    public Cell cellAtCoordinates(Cell.Coordinates coords) {
        if((coords.getX() < 0) || (coords.getX() > getChildCount() -1)) {
            return null;
        }
        GameRow row  = (GameRow) getChildAt(coords.getX());

        if((coords.getY() < 0) || (coords.getY() > row.getChildCount() -1)) {
            return null;
        }
        return (Cell) row.getChildAt(coords.getY());
    }

    // Use this to find top of gameboard, relative to parent frame layout
    // It's not entirely clear to me why I have to do this
    public float getTopPosition() {
        Log.i(mTag, "Frame padding = " + ((Activity) getContext()).findViewById(R.id.activity_main).getPaddingTop());
        Log.i(mTag, "Top Position = " + (getTop() - ((Activity) getContext()).findViewById(R.id.activity_main).getPaddingTop()));
       return getTop() - ((Activity) getContext()).findViewById(R.id.activity_main).getPaddingTop();
    }
}
