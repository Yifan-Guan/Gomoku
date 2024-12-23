package com.gomoku.app;

import java.awt.*;
import javax.swing.*;

public class PVPUI extends JFrame{
    private StartUI ParentFrame;

    public PVPUI(StartUI p) {
        ParentFrame = p;
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        Board ChessBoard = new Board();
        StatisticalPanel ChessSP = new StatisticalPanel(ParentFrame);
        c.add(ChessBoard);
        c.add(ChessSP);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
