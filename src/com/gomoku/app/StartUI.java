package com.gomoku.app;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class StartUI extends JFrame {
    public StartUI() {
        Container c = getContentPane();
        StartPanel SP = new StartPanel(this);
        c.add(SP);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class StartPanel extends JComponent {
    private StartUI ParentFrame;
    private int TitleSize = 90;
    private int TitleStyle = Font.BOLD;
    private int OptionSize = 50;
    private int OptionStyle = Font.PLAIN;
    private Rectangle2D.Double PVPRect;

    public StartPanel(StartUI p) {
        ParentFrame = p;
        addMouseListener(new ChoseOption());
    }

    public void paintComponent(Graphics g) {
        drawTitle(g);
        drawButton(g);
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
    public void drawButton(Graphics g) {
        String PVPText = "P V P";
        Graphics2D g2 = (Graphics2D) g;
        Font TheFont = getFont();
        TheFont = TheFont.deriveFont(OptionStyle, OptionSize);
        g2.setFont(TheFont);
        double PVPHeight = TheFont.getStringBounds(PVPText, g2.getFontRenderContext()).getHeight();
        double PVPWidth = TheFont.getStringBounds(PVPText, g2.getFontRenderContext()).getWidth();
        double PVPLeading = TheFont.getLineMetrics(PVPText, g2.getFontRenderContext()).getLeading();
        int PVPX = (int)((getWidth() - PVPWidth) / 2);
        int PVPY = (int)(getHeight() - PVPHeight * 2);
        g2.drawString(PVPText, PVPX, PVPY);
        PVPRect = new Rectangle2D.Double(PVPX - PVPWidth / 2, PVPY - PVPHeight * 3 / 4,
                PVPWidth * 2, PVPHeight);
        g2.setStroke(new BasicStroke(5));
        g2.draw(PVPRect);
    }

    public Dimension getPreferredSize() {
        return new Dimension(GlobalData.StartUIWidth, GlobalData.StartUIHeight);
    }

    private class ChoseOption extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            int EX = event.getX();
            int EY = event.getY();
            if (EX >= PVPRect.getX() && EX <= PVPRect.getX() + PVPRect.getWidth()
            && EY >= PVPRect.getY() && EY <= PVPRect.getY() + PVPRect.getHeight()) {
                PVPUI ThePVPUI = new PVPUI(ParentFrame);
                ParentFrame.setVisible(false);
                ThePVPUI.setVisible(true);
            }
        }
    }
}