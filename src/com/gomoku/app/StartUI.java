package com.gomoku.app;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class StartUI extends JFrame {
    private StartPanel SP;
    public StartUI() {
        Container c = getContentPane();
        SP = new StartPanel(this);
        c.add(SP);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void startNet() {
        SP.startNet();
    }
}

class StartPanel extends JComponent {
    private StartUI ParentFrame;
    private int TitleSize = 90;
    private int TitleStyle = Font.BOLD;
    private int OptionSize = 50;
    private int OptionStyle = Font.PLAIN;
    private Rectangle2D.Double PVPRect;
    private Rectangle2D.Double NetRect;

    private NetConnection Net;
    private PVPUI ThePVPUI;
    private ConnectUI TheConnectUI;

    public StartPanel(StartUI p) {
        ParentFrame = p;
        ThePVPUI = new PVPUI(ParentFrame);
        TheConnectUI = new ConnectUI(ParentFrame);
        Net = new NetConnection();
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
        Graphics2D g2 = (Graphics2D) g;
        Font TheFont = getFont();
        TheFont = TheFont.deriveFont(OptionStyle, OptionSize);
        g2.setFont(TheFont);

        String PVPText = "P V P";
        double PVPHeight = TheFont.getStringBounds(PVPText, g2.getFontRenderContext()).getHeight();
        double PVPWidth = TheFont.getStringBounds(PVPText, g2.getFontRenderContext()).getWidth();
        int PVPX = (int)((getWidth() - PVPWidth) / 2);
        int PVPY = (int)(getHeight() - PVPHeight * 2);
        g2.drawString(PVPText, PVPX, PVPY);
        PVPRect = new Rectangle2D.Double(PVPX - PVPWidth / 2, PVPY - PVPHeight * 3 / 4,
                PVPWidth * 2, PVPHeight);
        g2.setStroke(new BasicStroke(5));
        g2.draw(PVPRect);

        String NetText = "N E T";
        double NetHeight = TheFont.getStringBounds(NetText, g2.getFontRenderContext()).getHeight();
        double NetWidth = TheFont.getStringBounds(NetText, g2.getFontRenderContext()).getWidth();
        int NetX = (int)((getWidth() - NetWidth) / 2);
        int NetY = (int)(PVPY - NetHeight * 2);
        g2.drawString(NetText, NetX, NetY);
        NetRect = new Rectangle2D.Double(NetX - NetWidth / 2, NetY - NetHeight * 3 / 4,
                NetWidth * 2, NetHeight);
        g2.setStroke(new BasicStroke(5));
        g2.draw(NetRect);
    }

    public void startNet() {
        if (TheConnectUI.getConnectType() == 0) {
            System.out.println("server");
            Net.beginServer(TheConnectUI.getPort());
        }
        else if (TheConnectUI.getConnectType() == 1) {
            System.out.println("partner");
            Net.connect(TheConnectUI.getHost(), TheConnectUI.getPort());
        }
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
                ParentFrame.setVisible(false);
                ThePVPUI.setVisible(true);
            }
            if (EX >= NetRect.getX() && EX <= NetRect.getX() + NetRect.getWidth()
                    && EY >= NetRect.getY() && EY <= NetRect.getY() + NetRect.getHeight()) {
                ParentFrame.setVisible(false);
                TheConnectUI.setVisible(true);
            }
        }
    }
}