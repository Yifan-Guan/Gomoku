package com.gomoku.app;

import java.awt.*;
import javax.swing.*;

public class ConnectUI extends JFrame{
    private StartUI ParentFrame;
    private int FontSize = 30;
    private int LableStyle = Font.BOLD;
    private int TextStyle = Font.PLAIN;

    private String Host = "localhost";
    private int Port = 8189;
    private int ConnectType = 0;
    private GlobalData.PlayerType Player = GlobalData.PlayerType.WHITE;

    public ConnectUI(StartUI p) {
        ParentFrame = p;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(GlobalData.ConnectUIWidth, GlobalData.ConnectUIHeight);

        Container Panel = getContentPane();
        GridBagLayout Layout = new GridBagLayout();
        Panel.setLayout(Layout);

        JLabel HostLabel = new JLabel("Host: ");
        JLabel PortLabel = new JLabel("Port: ");
        JTextField HostField = new JTextField(Host, 10);
        JTextField PortField = new JTextField(String.valueOf(Port), 10);
        HostField.setEnabled(false);

        JLabel TypeLabel = new JLabel("Type:");
        ButtonGroup TypeButtons = new ButtonGroup();
        JRadioButton ServerButton = new JRadioButton("Server");
        JRadioButton PartnerButton = new JRadioButton("Partner");
        TypeButtons.add(ServerButton);
        TypeButtons.add(PartnerButton);
        ServerButton.setSelected(true);

        JLabel PlayerLabel = new JLabel("Player:");
        ButtonGroup PlayerButtons = new ButtonGroup();
        JRadioButton WhiteButton = new JRadioButton("White");
        JRadioButton BlackButton = new JRadioButton("Black");
        PlayerButtons.add(WhiteButton);
        PlayerButtons.add(BlackButton);
        WhiteButton.setSelected(true);

        JButton ConnectButton = new JButton("Connect");
        JButton CancelButton = new JButton("Cancel");

        HostField.addActionListener(e -> {
            Host = HostField.getText();
        });
        PortField.addActionListener(e -> {
            Port = Integer.parseInt(PortField.getText());
        });

        ServerButton.addActionListener(e -> {
            ConnectType = 0;
            HostField.setEnabled(false);
            WhiteButton.setEnabled(true);
            BlackButton.setEnabled(true);
        });
        PartnerButton.addActionListener(e -> {
            ConnectType = 1;
            HostField.setEnabled(true);
            WhiteButton.setEnabled(false);
            BlackButton.setEnabled(false);
        });

        WhiteButton.addActionListener(e -> {
            Player = GlobalData.PlayerType.WHITE;
        });
        BlackButton.addActionListener(e -> {
            Player = GlobalData.PlayerType.BLACK;
        });

        ConnectButton.addActionListener(e -> {
            setVisible(false);
            ParentFrame.startNet();
        });
        CancelButton.addActionListener(e -> {
            ParentFrame.setVisible(true);
            setVisible(false);
        });

        Font LabelFont = new Font("", LableStyle, FontSize);
        Font TextFont = new Font("", TextStyle, FontSize);

        HostLabel.setFont(LabelFont);
        HostField.setFont(TextFont);
        PortLabel.setFont(LabelFont);
        PortField.setFont(TextFont);

        TypeLabel.setFont(LabelFont);
        ServerButton.setFont(TextFont);
        PartnerButton.setFont(TextFont);

        PlayerLabel.setFont(LabelFont);
        WhiteButton.setFont(TextFont);
        BlackButton.setFont(TextFont);

        ConnectButton.setFont(LabelFont);
        CancelButton.setFont(LabelFont);

        Panel.add(HostLabel, new GBC(0, 0, 4, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));
        Panel.add(HostField, new GBC(4, 0, 6, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));

        Panel.add(PortLabel, new GBC(0, 2, 4, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));
        Panel.add(PortField, new GBC(4, 2, 6, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));

        Panel.add(TypeLabel, new GBC(0, 4, 4, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));
        Panel.add(ServerButton, new GBC(4, 4, 3, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));
        Panel.add(PartnerButton, new GBC(7, 4, 3, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));

        Panel.add(PlayerLabel, new GBC(0, 6, 4, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));
        Panel.add(WhiteButton, new GBC(4, 6, 3, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));
        Panel.add(BlackButton, new GBC(7, 6, 3, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));

        Panel.add(ConnectButton, new GBC(0, 9, 5, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));
        Panel.add(CancelButton, new GBC(5, 9, 5, 2).setAnchor(GridBagConstraints.CENTER).setWeight(100, 100));
    }

    public String getHost() {return Host;}
    public int getPort() {return Port;}
    public int getConnectType() {return ConnectType;}
    public GlobalData.PlayerType getPlayer() {return Player;}
}

class GBC extends GridBagConstraints {
    public GBC(int x, int y) {
        gridx = x;
        gridy = y;
    }
    public GBC(int x, int y, int w, int h) {
        gridx = x;
        gridy = y;
        gridwidth = w;
        gridheight = h;
    }
    public GBC setAnchor(int a) {
        anchor = a;
        return this;
    }
    public GBC setFill(int f) {
        fill = f;
        return this;
    }
    public GBC setWeight(int x, int y) {
        weightx = x;
        weighty = y;
        return this;
    }
}
