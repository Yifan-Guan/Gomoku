package com.gomoku.app;

import java.awt.*;
import javax.swing.*;

public class StatisticalPanel extends JComponent{
    private float FontSize = 30;
    private int FlashInterval = 500;
    private int FontStyle = Font.BOLD;

    public StatisticalPanel() {
        setSize(GlobalData.SPWidth, GlobalData.SPHeight);
        new Timer(FlashInterval, event -> repaint()).start();
    }
    public void paintComponent(Graphics g) {
        drawFrame(g);
        showText(g);
    }
    public void drawFrame(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(GlobalData.SPBoundaryWidth, GlobalData.SPBoundaryWidth,
                getWidth() - 2 * GlobalData.SPBoundaryWidth,
                getHeight() - 2 * GlobalData.SPBoundaryWidth);
    }
    public void showText(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Font TheFont = getFont();
        TheFont = TheFont.deriveFont(FontStyle, FontSize);

        g2.setFont(TheFont);
        String WinnerInfo = "Winner is :";
        if (GlobalData.Winer == GlobalData.PlayerType.WHITE)
            WinnerInfo += "whiter player";
        else if (GlobalData.Winer == GlobalData.PlayerType.BLACK)
            WinnerInfo += "blacker player";

        double WinnerInfoHeight = TheFont.getStringBounds(WinnerInfo, g2.getFontRenderContext()).getHeight();
        double WinnerInfoWidth = TheFont.getStringBounds(WinnerInfo, g2.getFontRenderContext()).getWidth();
        int WinnerInfoX = (int)((getWidth() - WinnerInfoWidth) / 2);
        int WinnerInfoY = (int)(GlobalData.SPBoundaryWidth + WinnerInfoHeight * 2);

        g2.drawString(WinnerInfo, WinnerInfoX, WinnerInfoY);
    }
    public Dimension getPreferredSize() {
        return new Dimension(GlobalData.SPWidth, GlobalData.SPHeight);
    }
}
