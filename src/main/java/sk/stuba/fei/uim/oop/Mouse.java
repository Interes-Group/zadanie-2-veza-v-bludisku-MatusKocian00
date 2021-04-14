package sk.stuba.fei.uim.oop;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    Mouse(){
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println((e.getX()/15)+" "+ (e.getY())/15);

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
        System.out.println(e.getX()/15+ " " +e.getY()/15);
    }


}
