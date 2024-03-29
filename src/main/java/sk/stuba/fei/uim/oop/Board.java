package sk.stuba.fei.uim.oop;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Board extends JPanel {

    private char[][] board;
    private int size;
    private int unVisited;
    LinkedList<Position> positionList = new LinkedList<Position>();


    public Board(){
       createBoard();
    }
    public void generateBoard(){
        for (int i=0; i < size; i++){
            for (int k=0; k < size; k++){
                board[i][k] = 'u';
            }
        }

        for (int i=0; i < size; i+=2){
            for (int k=0; k < size; k++){
                board[i][k] = '=';
                board[k][i] = '=';
            }
        }
        for (int i=0; i < size; i++){
            board[i][0] = '#';
            board[0][i] = '#';
            board[size-1][i] = '#';
            board[i][size-1] = '#';
        }
        generate(1,1);
    }

    public void paint(Graphics g){
        super.paint(g);
        int n = 15;
        for(int i = 0; i < size; i++){
            for( int k = 0; k < size; k++){
                if((board[i][k] == '#')){
                    g.setColor(Color.black);
                    g.fillRect(i*n, k*n, n-1, n-1);
                } else if(board[i][k] == '='){
                    g.setColor(Color.black);
                    g.fillRect(i*n, k*n, n-1, n-1);
                } else if(board[i][k] == '8'){
                    g.setColor(Color.red);
                    g.fillRect(i*n, k*n, n-1, n-1);
                } else if(board[i][k] == 'X'){
                    g.setColor(Color.blue);
                    g.fillRect(i*n, k*n, n-1, n-1);
                }
                else if (board[i][k]=='n') {
                    g.setColor(Color.green);
                    g.fillRect(i * n, k * n, n - 1, n - 1);
                }
            }
        }
    }

    public void setNeighbours(){
        for (Neighbour element: MazeFrame.player.getNeighbours()){
            if (get(element.getX(),element.getY())!='X' && get(element.getX(),element.getY())!='8'){
                set(element.getX(), element.getY(), 'n');
            }
        }
    }
    public void unsetNeighbours(){
        for (Neighbour element: MazeFrame.player.getNeighbours()){
            if (get(element.getX(),element.getY())!='X' && get(element.getX(),element.getY())!='8'){
                set(element.getX(), element.getY(), 'v');
            }
        }
    }
    public boolean checkInNeighbours(int x, int y){
        for (Neighbour element: MazeFrame.player.getNeighbours()){
            if (x == element.getX() && y == element.getY()){
                return true;
            }
        }
        return false;
    }



    public char get(int x, int y){
        return board[x][y];
    }

    public void set(int x, int y, char value){
        board[x][y] = value;
        repaint();
    }


    public char[] updateDirection(Position cC){
        char north=0,south=0,east=0,west=0;

        if (get(cC.getX(),cC.getY()+1) != '#')
            east = get(cC.getX(), cC.getY()+2);
        if (get(cC.getX(),cC.getY()-1) != '#')
            west = get(cC.getX(), cC.getY()-2);
        if (get(cC.getX()-1,cC.getY()) != '#')
            north = get(cC.getX()-2, cC.getY());
        if (get(cC.getX()+1,cC.getY()) != '#')
            south = get(cC.getX()+2, cC.getY());
        char direction[] = {west,east,south,north};
        return direction;
    }


    Position posList[] = new Position[(2*(getX()/2))];
    Position cC = new Position(5,5);

    public void generate(int posX, int posY){
        cC = new Position(posX,posY);
        set(cC.getX(),cC.getY(), 'v');
        unVisited-=1;

        char north=0,south=0,east=0,west=0;
        char direction[] = {west,east,south,north};

        direction = updateDirection(cC);

        while(unVisited != 0){
            int free = 0;
            if((direction[0] == 'u') || (direction[1] == 'u') || (direction[2] == 'u') || (direction[3] == 'u'))
                free = 1;

            Random generator = new Random();
            int random = generator.nextInt(4);
            set(cC.getX(),cC.getY(), 'v');

            if((random == 0) && (direction[0] == 'u')){ //West
                if (get(cC.getX(),cC.getY()-1) != '#'){
                    set(cC.getX(), cC.getY()-1, 'v');
                    cC = new Position(cC.getX(), cC.getY()-2);
                    positionList.push(cC);

                    direction = updateDirection(cC);
                    unVisited--;

                }
            }
            else if((random == 1) && (direction[1] == 'u')){ //East
                if (get(cC.getX(),cC.getY()+1) != '#'){
                    set(cC.getX(), cC.getY()+1, 'v');
                    cC = new Position(cC.getX(), cC.getY()+2);
                    positionList.push(cC);

                    direction = updateDirection(cC);
                    unVisited--;
                }
            }

            else if((random == 2) && (direction[2] == 'u')){
                if (get(cC.getX()+1,cC.getY()) != '#'){
                    set(cC.getX()+1, cC.getY(), 'v');
                    cC = new Position(cC.getX()+2, cC.getY());
                    positionList.push(cC);

                    direction = updateDirection(cC);
                    unVisited--;

                }
            }
            else if((random == 3) && (direction[3] == 'u')){
                if (get(cC.getX()-1,cC.getY()) != '#'){
                    set(cC.getX()-1, cC.getY(), 'v');
                    cC = new Position(cC.getX()-2, cC.getY());
                    positionList.push(cC);

                    direction = updateDirection(cC);
                    unVisited--;

                }
            } else {
                if(free == 0 && positionList.size() != 0){
                    cC = positionList.get(positionList.size()-1);
                    positionList.remove(positionList.size()-1);
                    direction = updateDirection(cC);


                }
            }
        }
        set(cC.getX(),cC.getY(),'8');
        set(1,1,'X');
    }

    public void createBoard(){
        int x = 11;
        int y = 11;
        unVisited = (x*x);
        x *= 2; y *= 2; x++; y++;

        board = new char [x][y];
        size = x;

        generateBoard();

    }

    public void printBoard(){
        for (int i=0; i < size; i++){
            for (int k=0; k < size; k++){
                System.out.print(board[k][i]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }


}