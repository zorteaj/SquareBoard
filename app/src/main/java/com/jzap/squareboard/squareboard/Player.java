package com.jzap.squareboard.squareboard;

import java.util.ArrayList;

/**
 * Created by JZ_W541 on 11/19/2016.
 */

public class Player {
    private String mName;
    private ArrayList<Piece> mPieces = new ArrayList<>();
    private GameManager mGameManager;

    public Player(GameManager gameManager, String name) {
        mGameManager = gameManager;
        mName = name;
    }

    public void addPiece(Cell.Coordinates coordinates)
    {
        Piece piece = mGameManager.getGameBoard().cellAtCoordinates(coordinates).addPiece(this);
        mPieces.add(piece);
    }

    public String getName() {
        return mName;
    }
}
