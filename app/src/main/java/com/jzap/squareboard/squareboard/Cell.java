package com.jzap.squareboard.squareboard;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;

import android.widget.TableRow;

/**
 * Created by JZ_W541 on 11/10/2016.
 */

public class Cell extends CardView {

    private static final String mTag = "JZAP: Cell";

    private GameBoard mGameBoard;
    private Coordinates mCoordinates;
    private int mSize;
    private TableRow.LayoutParams mLayoutParams;
    private LinearLayoutNoClip mLinearLayout;
    private boolean pickedUp = false;

    public static class Coordinates {
        int mX;
        int mY;
        public Coordinates(int x, int y) {
            mX = x;
            mY = y;
        }
        public int getX() {
            return mX;
        }
        public int getY() {
            return mY;
        }
    }

    public Cell(Context context, int size, GameBoard gameBoard, Coordinates coordinates) {
        super(context);
        mSize = size;
        mGameBoard = gameBoard;
        mCoordinates = coordinates;
        setUp();
    }

    private void setUp() {
        setUpLayoutParams();
        setUpAttributes();
        setUpLinearLayout();
    }

    private void setUpLayoutParams() {
        mLayoutParams = new TableRow.LayoutParams(mSize, mSize);
        setLayoutParams(mLayoutParams);
    }

    private void setUpAttributes() {
        setUseCompatPadding(true);
        setClipToPadding(false);
        setClipChildren(false);
        setClipToOutline(false);
        setContentPadding(Settings.CELL_PADDING,Settings.CELL_PADDING, Settings.CELL_PADDING, Settings.CELL_PADDING);
    }

    private void setUpLinearLayout() {
        mLinearLayout = new LinearLayoutNoClip(getContext());
        addView(mLinearLayout);
    }

    public void addPiece(Piece player) {
        player.setCell(this);
        //addView(player);
    }

    public Piece addPiece(Player player) {
        Piece piece = new Piece(getContext(), this, player);
        putDown();
        return piece;
    }

    public void removePlayer(Piece player) {
        removeView(player);
    }

    public GameBoard getGameBoard() {
        return mGameBoard;
    }

    public Coordinates getCoordinates() {
        return mCoordinates;
    }

    public int getSize() {
        return mSize;
    }

    public float getHorizontalCompactPaddingDistance() {
        Resources res = getContext().getResources();
        float elevation = getMaxCardElevation();
        float radius = getRadius();
        return (float) (elevation * 1.5 + (1.0 - Math.cos(45)) * radius);
    }

    public void pickUp() {
        animate().zBy(Settings.CELL_ELEVATION_DELTA);
        animate().alpha(1.0f);
        pickedUp = true;
    }

    public void putDown() {
        animate().zBy(-1 *Settings.CELL_ELEVATION_DELTA);
        pickedUp = false;
    }

}
