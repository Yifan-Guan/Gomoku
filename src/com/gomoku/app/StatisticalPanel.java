package com.gomoku.app;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class StatisticalPanel extends JComponent{
    public StatisticalPanel() {
        setSize(GlobalData.SPWidth, GlobalData.SPHeight);

    }
    public void paintComponent(Graphics g) {
        g.drawRect(GlobalData.SPBoundaryWidth, GlobalData.SPBoundaryWidth,
                GlobalData.SPWidth - 2 * GlobalData.SPBoundaryWidth,
                GlobalData.SPHeight - 2 * GlobalData.SPBoundaryWidth);
    }
    public Dimension getPreferredSize() {
        return new Dimension(GlobalData.SPWidth, GlobalData.SPHeight);
    }
}
