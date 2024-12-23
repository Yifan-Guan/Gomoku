package com.gomoku.app;

public class GlobalData {
    static int BoardRows = 15;
    static int BoardColumns = 15;
    static int BoardCellWidth = 50;
    static int PieceWidth = 40;
    static int BoardBoundaryWidth = BoardCellWidth / 2;
    static int BoardWidth = (BoardColumns - 1) * BoardCellWidth + 2 * BoardBoundaryWidth;
    static int BoardHeight = (BoardRows - 1) * BoardCellWidth + 2 * BoardBoundaryWidth;

    // SP is the abbreviation of Statistical Panel.
    static int SPWidth = 400;
    static int SPHeight = BoardHeight;
    static int SPBoundaryWidth = 20;


    static int UIWidth = BoardWidth + SPWidth;
    static int UIHeight = BoardHeight;

    enum PlayerType {NONE, WHITE, BLACK};
    static PlayerType CurrentType = PlayerType.WHITE;
    static PlayerType[][] Pieces = new PlayerType[BoardRows][BoardColumns];

    static void initPieces() {
        for (int i = 0; i < BoardRows; i++)
            for (int j = 0; j < BoardColumns; j++)
                Pieces[i][j] = PlayerType.NONE;
    }
}
