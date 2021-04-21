package sk.stuba.fei.uim.oop;

public class Neighbour {
    private int x;
    private int y;

    Neighbour(int x, int y){
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){

        return "x: " + getX() + " y: "+ getY();
    }
}
