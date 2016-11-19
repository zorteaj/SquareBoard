package com.jzap.squareboard.squareboard;

import android.animation.Animator;
import android.util.Log;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;

/**
 * Created by JZ_W541 on 11/10/2016.
 */

public class StandardMoveManager extends MoveManager {

    public StandardMoveManager(GameBoard gameBoard) {
        super(gameBoard);
    }

    protected void animateMove(Piece player, final Cell startCell, final Cell destinationCell, Move move, Piece.FlingSector sector) {
        FrameLayout mainLayout = (FrameLayout) mGameBoard.getParent();
        GameBoard.GameRow gameRow = ((GameBoard.GameRow)destinationCell.getParent());

        startCell.pickUp();
        destinationCell.putDown();


        ViewPropertyAnimator animator = player.animate();
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(mTag, "Animation over");
                //destinationCell.putDown();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });



        animator.y(gameRow.getTop() + mGameBoard.getTopPosition() + mainLayout.getPaddingTop() + (Settings.CELL_PADDING / 2));
        animator.x(destinationCell.getLeft() + mainLayout.getPaddingLeft() + (Settings.CELL_PADDING / 2));
       // animator.rotationBy(getRotation(sector));
        animator.rotationBy(360.0f);
        animator.start();
    }

    private int getRotation(Piece.FlingSector sector) {
        int rotation = 0;
        switch (sector){
            case TOP : rotation = 360;
                break;
            case MIDDLE : rotation = 0;
                break;
            case BOTTOM : rotation = -360;
                break;
        }
        return rotation;
    }

}
