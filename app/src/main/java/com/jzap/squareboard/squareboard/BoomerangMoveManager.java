package com.jzap.squareboard.squareboard;

import android.animation.Animator;
import android.util.Log;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;


/**
 * Created by JZ_W541 on 11/10/2016.
 */

public class BoomerangMoveManager extends MoveManager {

    public BoomerangMoveManager(GameBoard gameBoard) {
        super(gameBoard);
    }

    protected void animateMove(Piece player, Cell startCell, final Cell destinationCell, Move move, Piece.FlingSector sector) {


        final FrameLayout mainLayout = (FrameLayout) mGameBoard.getParent();
        final GameBoard.GameRow gameRow = ((GameBoard.GameRow)destinationCell.getParent());

        startCell.pickUp();
        destinationCell.putDown();




        Cell intermediateCell = destinationCell(destinationCell, move);
        final ViewPropertyAnimator animator = player.animate();

        animator.y(gameRow.getTop() + mGameBoard.getTopPosition() + mainLayout.getPaddingTop() + (Settings.CELL_PADDING / 2));
        animator.x(intermediateCell.getLeft() + mainLayout.getPaddingLeft() + (Settings.CELL_PADDING / 2));
        // animator.rotationBy(getRotation(sector));
        animator.rotationBy(360.0f);
        animator.setDuration(250);
        animator.start();





//        ViewPropertyAnimator animator = player.animate();
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(mTag, "Animation over");
                //destinationCell.putDown();
                animator.y(gameRow.getTop() + mGameBoard.getTopPosition() + mainLayout.getPaddingTop() + (Settings.CELL_PADDING / 2));
                animator.x(destinationCell.getLeft() + mainLayout.getPaddingLeft() + (Settings.CELL_PADDING / 2));
        // animator.rotationBy(getRotation(sector));
                animator.rotationBy(360.0f);
                animator.setDuration(500);
                animator.start();
                animator.setListener(null);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });




    }

}
