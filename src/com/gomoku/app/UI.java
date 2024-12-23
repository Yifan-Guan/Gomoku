package com.gomoku.app;

import java.awt.*;
import javax.swing.*;

public class UI extends JFrame{
    public UI() {
        Container c = getContentPane();
        setSize(GlobalData.UIWidth, GlobalData.UIHeight);
        c.setLayout(new FlowLayout());
        c.add(new Board());
        c.add(new StatisticalPanel());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
