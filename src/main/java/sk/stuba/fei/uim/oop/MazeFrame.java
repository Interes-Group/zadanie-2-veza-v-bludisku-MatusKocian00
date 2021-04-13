package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MazeFrame {

    static int wins = 0;


    Board board = new Board();
    Player player = new Player(board);
    static JFrame frame;
    JPanel mazePanel = new JPanel(new BorderLayout());
    JPanel infoPanel = new JPanel(new BorderLayout());
    JLabel winCount = new JLabel("Number of wins: " + getWins());


    public MazeFrame(int wins) {
        frame = new JFrame("Maze");
        mazePanel.add(board, BorderLayout.CENTER);
        mazePanel.setFocusable(true);
        mazePanel.add(winCount, BorderLayout.SOUTH);


        frame.add(mazePanel, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(345, 475);

        JButton south = new JButton("South");
        south.setFocusable(false);
        infoPanel.add(south, BorderLayout.SOUTH);
        south.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                player.moveDown(board);
            }
        });

        JButton north = new JButton("North");
        north.setFocusable(false);
        infoPanel.add(north, BorderLayout.NORTH);
        north.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                player.moveUp(board);
            }
        });

        JButton east = new JButton("East");
        east.setFocusable(false);
        infoPanel.add(east, BorderLayout.EAST);
        east.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                player.moveRight(board);
            }
        });


        JButton west = new JButton("West");
        west.setFocusable(false);
        infoPanel.add(west, BorderLayout.WEST);
        west.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                player.moveLeft(board);
            }
        });

        JButton menu = new JButton("New Game");
        menu.setFocusable(false);
        infoPanel.add(menu, BorderLayout.CENTER);
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                resetWins();
                new MazeFrame(wins);
                frame.dispose();
            }
        });

        mazePanel.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if(e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT) player.moveLeft(board);
                if(e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT) player.moveRight(board);
                if(e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP) player.moveUp(board);
                if(e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN) player.moveDown(board);
            }
        });

        frame.setLocationRelativeTo(null);
    }


    public int getWins() {
        return wins;
    }

    public void addWinCount() {
        wins++;
    }

    public void resetWins() {
        wins = 0;
    }
}
