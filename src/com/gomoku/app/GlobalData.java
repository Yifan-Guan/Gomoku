package com.gomoku.app;

public class GlobalData {
    static int Criterion = 5;

    static int StartUIWidth = 600;
    static int StartUIHeight = 700;

    static int BoardRows = 15;
    static int BoardColumns = 15;
    static int BoardCellWidth = 50;
    static int PieceWidth = 40;
    static int BoardBoundaryWidth = BoardCellWidth / 2;
    static int BoardWidth = (BoardColumns - 1) * BoardCellWidth + 2 * BoardBoundaryWidth;
    static int BoardHeight = (BoardRows - 1) * BoardCellWidth + 2 * BoardBoundaryWidth;

    // SP is the abbreviation of Statistical Panel.
    static int SPWidth = 550;
    static int SPHeight = BoardHeight;
    static int SPBoundaryWidth = 20;

    enum PlayerType {NONE, WHITE, BLACK};
    static PlayerType Player = PlayerType.WHITE;
    static PlayerType NexDrop = PlayerType.WHITE;
    static PlayerType Winner = PlayerType.NONE;
    static PlayerType[][] Pieces = new PlayerType[BoardRows][BoardColumns];

    static int WhiteDrops = 0;
    static int BlackDrops = 0;

    static int ConnectUIWidth = 600;
    static int ConnectUIHeight = 300;

    static boolean NetMode = false;
    static String ChessMessageHead = "Chess: ";
    static String ChessMessageSeparator = ",";
    static String ChatMessageHead = "Chat: ";
    static String PlayerMessageHead = "Player: ";
    static String RestartMessageHead = "Restart";

    static void initPieces() {
        NexDrop = PlayerType.WHITE;
        Winner = PlayerType.NONE;
        WhiteDrops = 0;
        BlackDrops = 0;
        for (int i = 0; i < BoardRows; i++)
            for (int j = 0; j < BoardColumns; j++)
                Pieces[i][j] = PlayerType.NONE;
    }

    static boolean dropPiece(int r, int c, PlayerType t) {
        if (Pieces[r][c] == PlayerType.NONE && t == NexDrop) {
            Pieces[r][c] = t;
            if (t == PlayerType.WHITE) {
                WhiteDrops++;
            } else if (t == PlayerType.BLACK) {
                BlackDrops++;
            }
            return true;
        } else
            return false;
    }
    static boolean judge(int r, int c) {
        if (r < 0 || r >= BoardRows || c < 0 || c >= BoardColumns)
            return false;
        boolean result = false;
        int[] Count = new int[4];
        boolean[] BreakOut = new boolean[8];
        for (int i = 1; i < Criterion; i++) {
            if (r - i > 0 && !BreakOut[0]) {
                if (Pieces[r - i][c] == Pieces[r][c])
                    Count[0]++;
                else
                    BreakOut[0] = true;
            }
            if (r + i < BoardRows && !BreakOut[1]) {
                if (Pieces[r + 1][c] == Pieces[r][c])
                    Count[0]++;
                else
                    BreakOut[1] = true;
            }
            if (r - i > 0 && c + i < BoardColumns && !BreakOut[2]) {
                if (Pieces[r - i][c + i] == Pieces[r][c])
                    Count[1]++;
                else
                    BreakOut[2] = true;
            }
            if (r + i < BoardRows && c - i > 0 && !BreakOut[3]) {
                if (Pieces[r + i][c - i] == Pieces[r][c])
                    Count[1]++;
                else
                    BreakOut[3] = true;
            }
            if (c - i > 0 && !BreakOut[4]) {
                if (Pieces[r][c - i] == Pieces[r][c])
                    Count[2]++;
                else
                    BreakOut[4] = true;
            }
            if (c + i < BoardColumns && !BreakOut[5]) {
                if (Pieces[r][c + i] == Pieces[r][c])
                    Count[2]++;
                else
                    BreakOut[5] = true;
            }
            if (r - i > 0 && c - i > 0 && !BreakOut[6]) {
                if (Pieces[r - i][c - i] == Pieces[r][c])
                    Count[3]++;
                else
                    BreakOut[6] = true;
            }
            if (r + i < BoardRows && c + i < BoardColumns && !BreakOut[7]) {
                if (Pieces[r + i][c + i] == Pieces[r][c])
                    Count[3]++;
                else
                    BreakOut[7] = true;
            }
        }
        for (int i : Count) {
            if (i >= Criterion - 1) {
                result = true;
                break;
            }
        }
        return result;
    }
}
