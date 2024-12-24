package com.gomoku.app;

import java.awt.*;
import javax.swing.*;

public class PVPUI extends JFrame{
    private StartUI ParentFrame;
    StatisticalPanel ChessSP;
    Board ChessBoard;

    public PVPUI(StartUI p) {
        ParentFrame = p;
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        ChessBoard = new Board(this, ParentFrame);
        ChessSP = new StatisticalPanel(ParentFrame, this);
        c.add(ChessBoard);
        c.add(ChessSP);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
