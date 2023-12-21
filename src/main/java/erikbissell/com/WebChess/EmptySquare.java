package erikbissell.com.WebChess;

public class EmptySquare extends Piece {

    public EmptySquare(int rank, int file){
        setRank(rank);
        setFile(file);
        setNickName('-');
    }

    @Override
    public boolean isValidMove(int newRank, int newFile, Piece[][] board) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean move(int newRank, int newFile, Piece[][] board) {
        // TODO Auto-generated method stub
        return false;
    }
        
    @Override
    public boolean isValidCapture(int newRank, int newFile, Piece[][] board) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean capture(int newRank, int newFile, Piece[][] board) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public char getNickName(){
        return nickName;
    }
    
}
