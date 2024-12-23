package com.gomoku.app;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StartUI MainUi = new StartUI();
            MainUi.setVisible(true);
        });
    }
}
