package com.gomoku.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Net {
    private ServerSocket Server;
    private Socket InCome;
    private Socket Partner;
    private BufferedReader InputReader;
    private PrintStream OutStream;

    public void connect(String Host, int Port) {
        try {
            Partner = new Socket(Host, Port);
            InputReader = new BufferedReader(new InputStreamReader(InCome.getInputStream()));
            OutStream = new PrintStream(InCome.getOutputStream(), true);
            ReceiveInput();
        } catch (IOException e) {
        }
    }
    public void beginServer(int Port) {
        new Thread() {
            public void run() {
                try {
                    Server = new ServerSocket(Port);
                    InCome = Server.accept();
                    InputReader = new BufferedReader(new InputStreamReader(InCome.getInputStream()));
                    OutStream = new PrintStream(InCome.getOutputStream(), true);
                    ReceiveInput();
                } catch (IOException e) {
                }
            }
        }.start();
    }
    public void ReceiveInput() {
        new Thread() {
            public void run() {
                String NewLine;
                try {
                    while ((NewLine = InputReader.readLine()) != null) {
                        AnalysisLine(NewLine);
                    }
                }
                catch(IOException e) {
                }
            }
        }.start();
    }
    public void AnalysisLine(String Line) {
        if (Line.startsWith(GlobalData.ChessMessageHead)) {
            Line.substring(GlobalData.ChessMessageHead.length());
            String[] Number = Line.split(GlobalData.ChessMessageSeparator);
            GlobalData.dropPiece(Integer.parseInt(Number[0]), Integer.parseInt(Number[1]), GlobalData.Player);
        }
        else if (Line.startsWith(GlobalData.ChatMessageHead)) {
        }
    }
}
