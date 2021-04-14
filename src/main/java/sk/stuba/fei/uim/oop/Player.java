package sk.stuba.fei.uim.oop;

public class Player {

    private int xPos;
    private int yPos;

    private final Board board;

    public Player(Board board){
        this.board = board;
        xPos = 1;
        yPos = 1;
    }

    public void moveLeft(Board board){
        if((board.get(xPos-1, yPos) != '#') && (board.get(xPos-1, yPos) != '=')){
            board.set(xPos, yPos, 'O');
            if(board.get(xPos-=1, yPos) == '8')
                Win();
            else 	board.set(xPos, yPos, 'X');
        }
    }

    public void moveRight(Board board){
        if((board.get(xPos+1, yPos) != '#') && (board.get(xPos+1, yPos) != '=')){
            board.set(xPos, yPos, 'O');
            if(board.get(xPos+=1, yPos) == '8')	Win();
            else	board.set(xPos, yPos, 'X');
        }
    }

    public void moveUp(Board board){
        if((board.get(xPos, yPos-1) != '#') && (board.get(xPos, yPos-1) != '=')){
            board.set(xPos, yPos, 'O');
            if(board.get(xPos, yPos-=1) == '8')	Win();
            else	board.set(xPos, yPos, 'X');
        }
    }
    public void moveDown(Board board){
        if((board.get(xPos, yPos+1) != '#') && (board.get(xPos, yPos+1) != '=')){
            board.set(xPos, yPos, 'O');
            if(board.get(xPos, yPos+=1) == '8') Win();
            else	board.set(xPos, yPos, 'X');
        }
    }

    public void Win(){
        MazeFrame.wins++;
        MazeFrame.frame.dispose();
        new MazeFrame(MazeFrame.wins);
    }
}
