package sk.stuba.fei.uim.oop;

public class Player {

    private int xPos = 0;
    private int yPos = 0;

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

    /**
     * Allows the player to move right, checking for if the player's next
     * move hits a win cell or a coin cell.
     * @param board The board the player will move on.
     */
    public void moveRight(Board board){
        if((board.get(xPos+1, yPos) != '#') && (board.get(xPos+1, yPos) != '=')){
            board.set(xPos, yPos, 'O');
            if(board.get(xPos+=1, yPos) == '8')	Win();
            else	board.set(xPos, yPos, 'X');
        }
    }

    /**
     * Allows the player to move up, checking for if the player's next
     * move hits a win cell or a coin cell.
     * @param board The board the player will move on.
     */
    public void moveUp(Board board){
        if((board.get(xPos, yPos-1) != '#') && (board.get(xPos, yPos-1) != '=')){
            board.set(xPos, yPos, 'O');
            if(board.get(xPos, yPos-=1) == '8')	Win();
            else	board.set(xPos, yPos, 'X');
        }
    }

    /**
     * Allows the player to move down, checking for if the player's next
     * move hits a win cell or a coin cell.
     * @param board The board the player will move on.
     */
    public void moveDown(Board board){
        if((board.get(xPos, yPos+1) != '#') && (board.get(xPos, yPos+1) != '=')){
            board.set(xPos, yPos, 'O');
            if(board.get(xPos, yPos+=1) == '8') Win();
            else	board.set(xPos, yPos, 'X');
        }
    }

    /**
     * A win method that will display the number of coins obtained.
     */
    public void Win(){
        MazeFrame.wins++;
        MazeFrame.frame.dispose();
        new MazeFrame(MazeFrame.wins);
    }
}
