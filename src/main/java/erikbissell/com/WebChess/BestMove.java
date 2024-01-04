package erikbissell.com.WebChess;

public class BestMove {
    private int score;
    private MoveRequest move;
    
    public BestMove(int score, MoveRequest move){
        this.score = score;
        this.move = move;
    }
    public int getScore(){
        return score;
    }
    public MoveRequest getMove(){
        return move;
    }
    
}
