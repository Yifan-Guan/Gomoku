package com.gomoku.app;

import java.awt.*;
import javax.swing.*;

public class StartUI extends JFrame {
    public StartUI() {
        Container c = getContentPane();
        StartPanel SP = new StartPanel();
        c.add(SP);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class StartPanel extends JComponent {
    private int TitleSize = 90;
    private int TitleStyle = Font.BOLD;
    public Dimension getPreferredSize() {
        return new Dimension(GlobalData.StartUIWidth, GlobalData.StartUIHeight);
    }
    public void paintComponent(Graphics g) {
        drawTitle(g);
    }
    public void drawTitle(Graphics g) {
        String TitleText = "GOMOKU";
        Graphics2D g2 = (Graphics2D) g;
        Font TheFont = getFont();
        TheFont = TheFont.deriveFont(TitleStyle, TitleSize);
        g2.setFont(TheFont);
        double TitleHeight = TheFont.getStringBounds(TitleText, g2.getFontRenderContext()).getHeight();
        double TitleWidth = TheFont.getStringBounds(TitleText, g2.getFontRenderContext()).getWidth();
        int TitleX = (int)((getWidth() - TitleWidth) / 2);
        int TitleY = (int)(TitleHeight * 2);
        g2.drawString(TitleText, TitleX, TitleY);
    }
}