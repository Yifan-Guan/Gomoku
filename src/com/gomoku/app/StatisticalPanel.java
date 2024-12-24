package com.gomoku.app;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import javax.swing.*;

public class StatisticalPanel extends JComponent{
    private JFrame ParentFrame;
    private StartUI GrandFrame;
    private int FlashInterval = 500;

    private float InfoFontSize = 30;
    private int InfoFontStyle = Font.BOLD;
    private float ButtonFontSize = 30;
    private int ButtonFontStyle = Font.PLAIN;

    private int ButtonAreaHeight = 150;
    private int InfoAreaHeight = GlobalData.SPHeight - ButtonAreaHeight;

    private Rectangle2D BackRect;
    private Rectangle2D RestartRect;

    public StatisticalPanel(StartUI g, JFrame p) {
        GrandFrame = g;
        ParentFrame = p;
        setSize(GlobalData.SPWidth, GlobalData.SPHeight);
        new Timer(FlashInterval, event -> repaint()).start();
    }
    public void paintComponent(Graphics g) {
        drawInfoAreaFrame(g);
        showText(g);
        drawButtonAreaFrame(g);
        drawButton(g);
        addMouseListener(new ChossButton());
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

        String NextDropInfo = "Next drop is : ";
        if (GlobalData.CurrentType == GlobalData.PlayerType.WHITE)
            NextDropInfo += "white";
        else if (GlobalData.CurrentType == GlobalData.PlayerType.BLACK)
            NextDropInfo += "black";

        double NextDropInfoHeight = TheFont.getStringBounds(NextDropInfo, g2.getFontRenderContext()).getHeight();
        double NextDropInfoWidth = TheFont.getStringBounds(NextDropInfo, g2.getFontRenderContext()).getWidth();
        int NextDropInfoX = (int)((getWidth() - NextDropInfoWidth) / 2);
        int NextDropInfoY = (int)(WinnerInfoY + NextDropInfoHeight * 2);

        g2.drawString(NextDropInfo, NextDropInfoX, NextDropInfoY);
    }
    public Dimension getPreferredSize() {
        return new Dimension(GlobalData.SPWidth, GlobalData.SPHeight);
    }
    public void drawButton(Graphics g) {
        String BackText = "BACK";
        String RestartText = "RESTART";
        Graphics2D g2 = (Graphics2D) g;
        Font TheFont = getFont();
        TheFont = TheFont.deriveFont(ButtonFontStyle, ButtonFontSize);
        g2.setFont(TheFont);
        double BackHeight = TheFont.getStringBounds(BackText, g2.getFontRenderContext()).getHeight();
        double BackWidth = TheFont.getStringBounds(BackText, g2.getFontRenderContext()).getWidth();
        double RestartHeight = TheFont.getStringBounds(RestartText, g2.getFontRenderContext()).getHeight();
        double RestartWidth = TheFont.getStringBounds(RestartText, g2.getFontRenderContext()).getWidth();
        double ButtonWidth = RestartWidth * 1.2;
        double ButtonHeight = RestartHeight;
        double Distance = (getWidth() - 2 * GlobalData.SPBoundaryWidth - 2 * ButtonWidth) / 3;
        double BackRectX = GlobalData.SPBoundaryWidth + Distance;
        double RestartRectX = GlobalData.SPBoundaryWidth + 2 * Distance + ButtonWidth;
        double BackRectY = getHeight() - ButtonAreaHeight / 2 - ButtonHeight / 2;
        double RestartRectY = BackRectY;
        int BackTextX = (int)(BackRectX + ButtonWidth / 2 - BackWidth / 2);
        int RestartTextX = (int)(RestartRectX + ButtonWidth / 2 - RestartWidth / 2);
        int BackTextY = (int)(BackRectY + ButtonHeight * 3 / 4);
        int RestartTextY = (int)(RestartRectY + ButtonHeight * 3 / 4);
        g2.drawString(BackText, BackTextX, BackTextY);
        g2.drawString(RestartText, RestartTextX, RestartTextY);
        BackRect = new Rectangle2D.Double(BackRectX, BackRectY, ButtonWidth, ButtonHeight);
        RestartRect = new Rectangle2D.Double(RestartRectX, RestartRectY, ButtonWidth, ButtonHeight);
        g2.setStroke(new BasicStroke(5));
        g2.draw(BackRect);
        g2.draw(RestartRect);
    }

    private class ChossButton extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            int EX = event.getX();
            int EY = event.getY();
            if (EX >= BackRect.getX() && EX <= BackRect.getX() + BackRect.getWidth()
                    && EY >= BackRect.getY() && EY <= BackRect.getY() + BackRect.getHeight()) {
                GrandFrame.setVisible(true);
                ParentFrame.setVisible(false);

            }
            if (EX >= RestartRect.getX() && EX <= RestartRect.getX() + RestartRect.getWidth()
                    && EY >= RestartRect.getY() && EY <= RestartRect.getY() + RestartRect.getHeight()) {
                GlobalData.initPieces();
                ParentFrame.repaint();
            }
        }
    }
}
