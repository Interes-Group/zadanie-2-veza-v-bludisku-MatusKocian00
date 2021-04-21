package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class MazeFrame {

    static int wins = 0;

    Mouse mouse = new Mouse();
    static Board board;
    static Player player;
    static JFrame frame;
    static ArrayList<Neighbour> neighbours = new ArrayList<>();
    JPanel mazePanel = new JPanel(new BorderLayout());
    JPanel infoPanel = new JPanel(new BorderLayout());
    JLabel winCount = new JLabel("Number of wins: " + getWins());


    public MazeFrame(int wins) {
        board = new Board();
        player = new Player(board);
        frame = new JFrame("Maze");
        mazePanel.add(board, BorderLayout.CENTER);
        mazePanel.setFocusable(true);
        mazePanel.add(winCount, BorderLayout.SOUTH);
        mazePanel.addMouseListener(mouse);
        mazePanel.addMouseMotionListener(mouse);


        frame.add(mazePanel, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(345, 475);

        JButton down = new JButton("Down");
        down.setFocusable(false);
        infoPanel.add(down, BorderLayout.SOUTH);
        down.addActionListener(ae -> {
            board.unsetNeighbours();
            player.moveDown(board);

        });

        JButton up= new JButton("Up");
        up.setFocusable(false);
        infoPanel.add(up, BorderLayout.NORTH);
        up.addActionListener(ae -> {
            board.unsetNeighbours();
            player.moveUp(board);

        });

        JButton right = new JButton("Right");
        right.setFocusable(false);
        infoPanel.add(right, BorderLayout.EAST);
        right.addActionListener(ae -> {
            board.unsetNeighbours();
            player.moveRight(board);

        });


        JButton left = new JButton("Left");
        left.setFocusable(false);
        infoPanel.add(left, BorderLayout.WEST);
        left.addActionListener(ae -> {
            board.unsetNeighbours();
            player.moveLeft(board);

        });

        JButton menu = new JButton("New Game");
        menu.setFocusable(false);
        infoPanel.add(menu, BorderLayout.CENTER);
        menu.addActionListener(ae -> {

            frame.dispose();
            resetWins();
            new MazeFrame(wins);

        });

        mazePanel.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if(e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT) {
                    player.moveLeft(board);
                    board.unsetNeighbours();
                }
                if(e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT) {
                    player.moveRight(board);
                    board.unsetNeighbours();
                }
                if(e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP) {
                    player.moveUp(board);
                    board.unsetNeighbours();
                }
                if(e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN) {
                    player.moveDown(board);
                    board.unsetNeighbours();
                }
            }
        });

        frame.setLocationRelativeTo(null);
    }


    public int getWins() {
        return wins;
    }

    public void resetWins() {
        wins = 0;
    }

}
