package com.jzap.squareboard.squareboard;

/**
 * Created by JZ_W541 on 11/10/2016.
 */

public class Settings {
    public static final MoveManager.MoveStyle MOVE_STYLE = MoveManager.MoveStyle.STANDARD;
    public static final int NUM_BOARD_COLUMNS = 4;
    public static final int CELL_PADDING = 40; // TODO: This should probably be relative to the actual cell size (which depends on screen size).
    public static final int PLAYER_ELEVATION_DELTA = 10;
    public static final int CELL_ELEVATION_DELTA = 5;
    public static final float PLAYER_DOWN_ALPHA = 0.8f;
}
