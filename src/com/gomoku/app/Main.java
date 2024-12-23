package com.gomoku.app;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            UI MainUi = new UI();
        });
    }
}
