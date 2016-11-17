package com.jzap.squareboard.squareboard;

/**
 * Created by JZ_W541 on 11/10/2016.
 */

public abstract class MoveManager {

    protected static final String mTag = "JZAP: MoveManager";

    public enum MoveStyle {STANDARD, BOOMERANG}
    protected enum Move {UP, DOWN, LEFT, RIGHT, UP_RIGHT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT}

    public static GameBoard mGameBoard;


    public MoveManager(GameBoard gameBoard) {
        mGameBoard = gameBoard;
    }

    public void move(Player player, Cell startCell, float angle, Player.FlingSector sector) {
        Move move = determineMove(angle);
        Cell destinationCell = destinationCell(startCell, move);

        if(destinationCell != null) {
            animateMove(player, startCell, destinationCell, move, sector);
            player.setCell(destinationCell);
        }
    }

    protected abstract void animateMove(Player player, Cell startCell, Cell destinationCell, Move move, Player.FlingSector sector);

    protected Move determineMove(float angle) {

        if(Utils.Gestures.angleInRange(angle, 337.5f, 22.5f)) {
            return Move.RIGHT;
        } else if (Utils.Gestures.angleInRange(angle, 22.5f, 67.5f)) {
            return Move.UP_RIGHT;
        } else if (Utils.Gestures.angleInRange(angle, 67.5f, 122.5f)) {
            return Move.UP;
        } else if (Utils.Gestures.angleInRange(angle, 122.5f, 157.5f)) {
            return Move.UP_LEFT;
        } else if (Utils.Gestures.angleInRange(angle, 157.5f, 202.5f)) {
            return Move.LEFT;
        } else if (Utils.Gestures.angleInRange(angle, 202.5f, 247.5f)) {
            return Move.DOWN_LEFT;
        } else if (Utils.Gestures.angleInRange(angle, 247.5f, 292.5f)) {
            return Move.DOWN;
        } else { // if (Utils.Gestures.angleInRange(angle,  292.5f, 337.5f)) {
            return Move.DOWN_RIGHT;
        }

    }

    protected Cell destinationCell(Cell startCell, Move move) {
        Cell.Coordinates startCellCoords = startCell.getCoordinates();
        Cell.Coordinates destinationCoords = startCellCoords;

        switch(move) {
            case UP : destinationCoords = offsetCoordinates(startCellCoords, -1, 0);
                break;
            case DOWN : destinationCoords = offsetCoordinates(startCellCoords, 1, 0);
                break;
            case LEFT : destinationCoords = offsetCoordinates(startCellCoords, 0, -1);
                break;
            case RIGHT : destinationCoords = offsetCoordinates(startCellCoords, 0, 1);
                break;
            case UP_RIGHT : destinationCoords = offsetCoordinates(startCellCoords, -1, 1);
                break;
            case UP_LEFT : destinationCoords = offsetCoordinates(startCellCoords, -1, -1);
                break;
            case DOWN_RIGHT : destinationCoords = offsetCoordinates(startCellCoords, 1, 1);
                break;
            case DOWN_LEFT : destinationCoords = offsetCoordinates(startCellCoords, 1, -1);
                break;
        }
        return mGameBoard.cellAtCoordinates(destinationCoords);
    }

    protected Cell.Coordinates offsetCoordinates(Cell.Coordinates coords, int offsetX, int offsetY) {
        return new Cell.Coordinates(coords.getX() + offsetX, coords.getY() + offsetY);
    }

}

