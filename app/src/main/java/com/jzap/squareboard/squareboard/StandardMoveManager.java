package com.jzap.squareboard.squareboard;

import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;

/**
 * Created by JZ_W541 on 11/10/2016.
 */

public class StandardMoveManager extends MoveManager {

    public StandardMoveManager(GameBoard gameBoard) {
        super(gameBoard);
    }

    protected void animateMove(Player player, Cell startCell, Cell destinationCell, Move move) {
        FrameLayout mainLayout = (FrameLayout) mGameBoard.getParent();
        GameBoard.GameRow gameRow = ((GameBoard.GameRow)destinationCell.getParent());

        ViewPropertyAnimator animator = player.animate();

        animator.y(gameRow.getTop() + mGameBoard.getTopPosition() + mainLayout.getPaddingTop() + (Settings.CELL_PADDING / 2));
        animator.x(destinationCell.getLeft() + mainLayout.getPaddingLeft() + (Settings.CELL_PADDING / 2));
        animator.rotationBy(360.0f);
        animator.start();
    }

}
