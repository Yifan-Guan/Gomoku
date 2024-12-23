package com.gomoku.app;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JComponent {
    public Board() {
        setSize(GlobalData.BoardWidth, GlobalData.BoardHeight);
        GlobalData.initPieces();
        addMouseListener(new MouseDropPiece());
    }
    public void paintComponent(Graphics g) {
        drawGrid(g);
        drawPieces(g);
    }
    private void drawPieces(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int StartX = GlobalData.BoardBoundaryWidth;
        int StartY = GlobalData.BoardBoundaryWidth;
        for (int i = 0; i < GlobalData.BoardRows; i++) {
            for (int j = 0; j < GlobalData.BoardColumns; j++) {
                if (GlobalData.Pieces[i][j] != GlobalData.PlayerType.NONE) {
                    double PX = StartX + j * GlobalData.BoardCellWidth - (double) (GlobalData.PieceWidth / 2);
                    double PY = StartY + i * GlobalData.BoardCellWidth - (double) (GlobalData.PieceWidth / 2);
                    Ellipse2D.Double piece = new Ellipse2D.Double(PX, PY, GlobalData.PieceWidth, GlobalData.PieceWidth);
                    if (GlobalData.Pieces[i][j] == GlobalData.PlayerType.BLACK)
                        g2.fill(piece);
                    else if (GlobalData.Pieces[i][j] == GlobalData.PlayerType.WHITE)
                        g2.draw(piece);
                }
            }
        }
    }
    private void drawGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int StartX = GlobalData.BoardBoundaryWidth;
        int StartY = GlobalData.BoardBoundaryWidth;
        int EndX = StartX + (GlobalData.BoardColumns - 1) * GlobalData.BoardCellWidth;
        int EndY = StartY + (GlobalData.BoardRows - 1) * GlobalData.BoardCellWidth;
        for (int i = 0; i < GlobalData.BoardRows; i++) {
            Line2D.Double RowLine = new Line2D.Double(StartX, StartY + i * GlobalData.BoardCellWidth,
                    EndX, StartY + i * GlobalData.BoardCellWidth);
            g2.draw(RowLine);
        }
        for (int i = 0; i < GlobalData.BoardColumns; i++) {
            Line2D.Double ColumnLine = new Line2D.Double(StartX + i * GlobalData.BoardCellWidth, StartY,
                    StartX + i * GlobalData.BoardCellWidth, EndY);
            g2.draw(ColumnLine);
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(GlobalData.BoardWidth, GlobalData.BoardHeight);
    }

    private class MouseDropPiece extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            double MX = event.getX() - GlobalData.BoardBoundaryWidth;
            double MY = event.getY() - GlobalData.BoardBoundaryWidth;
            double MRow = MY / GlobalData.BoardCellWidth;
            double MColumn = MX / GlobalData.BoardCellWidth;
            int SourceRow = (int)MRow;
            int SourceColumn = (int)MColumn;
            int TestRow = (int)(MRow + 0.5);
            int TestColumn = (int)(MColumn + 0.5);
            if (SourceRow != TestRow)
                SourceRow = TestRow;
            if (SourceColumn != TestColumn)
                SourceColumn = TestColumn;
            if (GlobalData.Pieces[SourceRow][SourceColumn] == GlobalData.PlayerType.NONE) {
                GlobalData.Pieces[SourceRow][SourceColumn] = GlobalData.CurrentType;
                GlobalData.CurrentType = GlobalData.CurrentType == GlobalData.PlayerType.WHITE ?
                        GlobalData.PlayerType.BLACK : GlobalData.PlayerType.WHITE;
                repaint();
            }
        }
    }
}

