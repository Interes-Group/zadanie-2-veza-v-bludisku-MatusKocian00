package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse extends JPanel implements MouseListener, MouseMotionListener{

    int counter = 0;

    Mouse(){
    }


    @Override
    public void mouseClicked(MouseEvent e) {
            movePlayer(e);
        }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e){

    }

    public boolean checkPlayer(MouseEvent e){
        return MazeFrame.player.getxPos() == e.getX() / 15 && MazeFrame.player.getyPos() == e.getY() / 15;
    }

    public void movePlayer(MouseEvent e){
        counter++;
        if (checkPlayer(e) && counter == 1) {
            MazeFrame.player.checkRowLeft(MazeFrame.player);
            MazeFrame.player.checkRowRight(MazeFrame.player);
            MazeFrame.player.checkRowUp(MazeFrame.player);
            MazeFrame.player.checkRowDown(MazeFrame.player);
            MazeFrame.board.setNeighbours();
        }
        else{
            if (counter == 2) {
                moveByMouse(e);

                MazeFrame.board.unsetNeighbours();
                MazeFrame.player.clearNeighbours();
                counter = 0;
            }
        }
    }

    public void moveByMouse(MouseEvent e){
            if (MazeFrame.board.checkInNeighbours(e.getX()/15,e.getY()/15)){
                MazeFrame.board.set(MazeFrame.player.getxPos(),MazeFrame.player.getyPos(),'v');
                MazeFrame.player.setyPos(e.getY()/15);
                MazeFrame.player.setxPos(e.getX()/15);
                if (MazeFrame.board.get(e.getX()/15, e.getY()/15) == '8') {
                    MazeFrame.player.Win();
                }
                else
                    MazeFrame.board.set(MazeFrame.player.getxPos() ,MazeFrame.player.getyPos(), 'X');
        }
    }
}
