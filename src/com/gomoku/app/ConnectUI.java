package com.gomoku.app;

import java.awt.*;
import javax.swing.*;

public class ConnectUI extends JFrame{
    private JFrame ParentFrame;
    public ConnectUI(JFrame p) {
        ParentFrame = p;
        setSize(GlobalData.ConnectUIWidth, GlobalData.ConnectUIHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
