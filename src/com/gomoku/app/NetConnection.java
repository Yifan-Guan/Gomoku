package com.gomoku.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class NetConnection {
    private ServerSocket Server;
    private Socket Partner;
    private BufferedReader InputReader;
    private PrintStream OutStream;

    public void connect(String Host, int Port) {
        try {
            Partner = new Socket(Host, Port);
            InputReader = new BufferedReader(new InputStreamReader(Partner.getInputStream()));
            OutStream = new PrintStream(Partner.getOutputStream(), true);
            ReceiveInput();
        } catch (IOException e) {
        }
    }
    public void beginServer(int Port) {
        new Thread() {
            public void run() {
                try {
                    Server = new ServerSocket(Port);
                    Socket InCome = Server.accept();
                    InputReader = new BufferedReader(new InputStreamReader(InCome.getInputStream()));
                    OutStream = new PrintStream(InCome.getOutputStream(), true);
                    String PlayerText = GlobalData.Player == GlobalData.PlayerType.WHITE ?
                            "BLACK" : "WHITE";
                    OutStream.println(GlobalData.ChatMessageHead + GlobalData.PlayerMessageHead + PlayerText);
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
    public synchronized void AnalysisLine(String Line) {
        if (Line.startsWith(GlobalData.ChessMessageHead)) {
            Line = Line.substring(GlobalData.ChessMessageHead.length());
            String[] Number = Line.split(GlobalData.ChessMessageSeparator);
            GlobalData.PlayerType AnotherPlayer = GlobalData.Player == GlobalData.PlayerType.WHITE ?
                    GlobalData.PlayerType.BLACK : GlobalData.PlayerType.WHITE;
            if(GlobalData.dropPiece(Integer.parseInt(Number[0]), Integer.parseInt(Number[1]), AnotherPlayer)) {
                if (GlobalData.judge(Integer.parseInt(Number[0]), Integer.parseInt(Number[1]))) {
                    GlobalData.Winner = GlobalData.NexDrop;
                }
                GlobalData.NexDrop = GlobalData.NexDrop == GlobalData.PlayerType.WHITE ?
                        GlobalData.PlayerType.BLACK : GlobalData.PlayerType.WHITE;
            }
        }
        else if (Line.startsWith(GlobalData.ChatMessageHead)) {
            Line = Line.substring(GlobalData.ChatMessageHead.length());
            if (Line.startsWith(GlobalData.PlayerMessageHead)) {
                Line = Line.substring(GlobalData.PlayerMessageHead.length());
                if (Line.equals("WHITE")) {
                    GlobalData.Player = GlobalData.PlayerType.WHITE;
                }
                else {
                    GlobalData.Player = GlobalData.PlayerType.BLACK;
                }
            }
            else if (Line.startsWith(GlobalData.RestartMessageHead)) {
                GlobalData.initPieces();
            }
        }
    }
    public void sendMessage(String m) {
        OutStream.println(m);
    }
}
