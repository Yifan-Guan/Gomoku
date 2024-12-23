package com.gomoku.app;

import java.awt.*;
import javax.swing.*;

public class UI extends JFrame{
    public UI() {
        Container c = getContentPane();
        setSize(GlobalData.UIWidth, GlobalData.UIHeight);
        c.setLayout(new FlowLayout());
        Board ChessBoard = new Board();
        StatisticalPanel ChessSP = new StatisticalPanel();
        c.add(ChessBoard);
        c.add(ChessSP);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
