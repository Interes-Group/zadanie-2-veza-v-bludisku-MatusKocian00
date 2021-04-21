package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.util.ArrayList;

public class Player extends JPanel {

    private int xPos;
    private int yPos;


    private ArrayList<Neighbour> neighbours = new ArrayList<>();
    private final Board board;

    public Player(Board board){
        this.board = board;
        xPos = 1;
        yPos = 1;
    }


    public void moveLeft(Board board){
        if((board.get(xPos-1, yPos) != '#') && (board.get(xPos-1, yPos) != '=')){
            board.set(xPos, yPos, 'v');
            if(board.get(xPos-=1, yPos) == '8')
                Win();
            else 	board.set(xPos, yPos, 'X');
        }
    }

    public void moveRight(Board board){
        if((board.get(xPos+1, yPos) != '#') && (board.get(xPos+1, yPos) != '=')){
            board.set(xPos, yPos, 'v');
            if(board.get(xPos+=1, yPos) == '8')	Win();
            else	board.set(xPos, yPos, 'X');
        }
    }

    public void moveUp(Board board){
        if((board.get(xPos, yPos-1) != '#') && (board.get(xPos, yPos-1) != '=')){
            board.set(xPos, yPos, 'v');
            if(board.get(xPos, yPos-=1) == '8')	Win();
            else	board.set(xPos, yPos, 'X');
        }
    }
    public void moveDown(Board board){
        if((board.get(xPos, yPos+1) != '#') && (board.get(xPos, yPos+1) != '=')){
            board.set(xPos, yPos, 'v');
            if(board.get(xPos, yPos+=1) == '8') Win();
            else	board.set(xPos, yPos, 'X');
        }
    }

    public void Win(){
        MazeFrame.wins++;
        MazeFrame.frame.dispose();
        new MazeFrame(MazeFrame.wins);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void checkRowDown(Player player){
        for (int y = player.getyPos()+1; y < 22; y++){
            if (board.get(player.getxPos(),y) == '#' || (board.get(player.getxPos(), y) == '=')){
                break;
            }
            else{
                neighbours.add(new Neighbour(player.getxPos(),y));
            }
        }
    }
    public void checkRowUp(Player player){
        for (int y = player.getyPos()-1; y > 0; y--){
            if (board.get(player.getxPos(),y) == '#' || (board.get(player.getxPos(), y) == '=')){
                break;
            }
            else{
                neighbours.add(new Neighbour(player.getxPos(),y));
            }
        }
    }
    public void checkRowRight(Player player){
        for (int x = player.getxPos()+1; x < 22; x++){
            if (board.get(x,player.getyPos()) == '#' || (board.get(x,player.getyPos()) == '=')){
                break;
            }
            else{
                neighbours.add(new Neighbour(x, player.getyPos()));
            }
        }
    }
    public void checkRowLeft(Player player){
        for (int x = player.getxPos()-1; x > 0; x--){
            if (board.get(x,player.getyPos()) == '#' || (board.get(x,player.getyPos()) == '=')){
                break;
            }
            else{
                neighbours.add(new Neighbour(x, player.getyPos()));
            }
        }
    }

    public ArrayList<Neighbour> getNeighbours() {
        return neighbours;
    }
    public void clearNeighbours(){
        neighbours.clear();
    }


    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
