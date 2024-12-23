package com.gomoku.app;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class StatisticalPanel extends JComponent{
    private StartUI ParentFrame;
    private int FlashInterval = 500;

    private float InfoFontSize = 30;
    private int InfoFontStyle = Font.BOLD;
    private float ButtonFontSize = 30;
    private int ButtonFontStyle = Font.PLAIN;

    private int ButtonAreaHeight = 150;
    private int InfoAreaHeight = GlobalData.SPHeight - ButtonAreaHeight;

    private Rectangle2D BackRect;
    private Rectangle2D CloseRect;

    public StatisticalPanel(StartUI p) {
        ParentFrame = p;
        setSize(GlobalData.SPWidth, GlobalData.SPHeight);
        new Timer(FlashInterval, event -> repaint()).start();
    }
    public void paintComponent(Graphics g) {
        drawInfoAreaFrame(g);
        showText(g);
        drawButtonAreaFrame(g);
        drawButton(g);
    }
    public void drawInfoAreaFrame(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(GlobalData.SPBoundaryWidth, GlobalData.SPBoundaryWidth,
                getWidth() - 2 * GlobalData.SPBoundaryWidth,
                InfoAreaHeight - 2 * GlobalData.SPBoundaryWidth);
    }
    public void drawButtonAreaFrame(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(GlobalData.SPBoundaryWidth, getHeight() - ButtonAreaHeight,
                getWidth() - 2 * GlobalData.SPBoundaryWidth,
                ButtonAreaHeight - GlobalData.SPBoundaryWidth);
    }
    public void showText(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Font TheFont = getFont();
        TheFont = TheFont.deriveFont(InfoFontStyle, InfoFontSize);

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
    public void drawButton(Graphics g) {
        String BackText = "BACK";
        String CloseText = "CLOSE";
        Graphics2D g2 = (Graphics2D) g;
        Font TheFont = getFont();
        TheFont = TheFont.deriveFont(ButtonFontStyle, ButtonFontSize);
        g2.setFont(TheFont);
        double BackHeight = TheFont.getStringBounds(BackText, g2.getFontRenderContext()).getHeight();
        double BackWidth = TheFont.getStringBounds(BackText, g2.getFontRenderContext()).getWidth();
        double CloseHeight = TheFont.getStringBounds(CloseText, g2.getFontRenderContext()).getHeight();
        double CloseWidth = TheFont.getStringBounds(CloseText, g2.getFontRenderContext()).getWidth();
        double ButtonWidth = CloseWidth * 2;
        double ButtonHeight = CloseHeight;
        double Distance = (getWidth() - 2 * GlobalData.SPBoundaryWidth - 2 * ButtonWidth) / 3;
        double BackRectX = GlobalData.SPBoundaryWidth + Distance;
        double CloseRectX = GlobalData.SPBoundaryWidth + 2 * Distance + ButtonWidth;
        double BackRectY = getHeight() - ButtonAreaHeight / 2 - ButtonHeight / 2;
        double CloseRectY = BackRectY;
        int BackTextX = (int)(BackRectX + ButtonWidth / 2 - BackWidth / 2);
        int CloseTextX = (int)(CloseRectX + ButtonWidth / 2 - CloseWidth / 2);
        int BackTextY = (int)(BackRectY + ButtonHeight * 3 / 4);
        int CloseTextY = (int)(CloseRectY + ButtonHeight * 3 / 4);
        g2.drawString(BackText, BackTextX, BackTextY);
        g2.drawString(CloseText, CloseTextX, CloseTextY);
        BackRect = new Rectangle2D.Double(BackRectX, BackRectY, ButtonWidth, ButtonHeight);
        CloseRect = new Rectangle2D.Double(CloseRectX, CloseRectY, ButtonWidth, ButtonHeight);
        g2.setStroke(new BasicStroke(5));
        g2.draw(BackRect);
        g2.draw(CloseRect);
    }
}
