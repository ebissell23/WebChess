package erikbissell.com.WebChess;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Piece {

    private int file;
    private int rank;
    private boolean isWhite;
    private boolean hasMoved;
    protected char nickName;
    public Piece(){
        file = -1;
        rank = -1;
        isWhite = false;
        hasMoved = false;
        nickName = '-';
    }
    public Piece(int initialRank, int initialFile, boolean pieceColor){
        rank = initialRank;
        file = initialFile;
        isWhite = pieceColor;
        hasMoved = false;
        nickName = '-';
    }
    public abstract boolean isValidMove(int newRank, int newFile, Piece[][] board);
    public abstract boolean move(int newRank, int newFile, Piece[][] board);
    public abstract boolean isValidCapture(int newRank, int newFile, Piece[][] board);
    public abstract boolean capture(int newRank, int newFile, Piece[][] board);
    public int getRank(){
        return rank;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    public int getFile(){
        return file;
    }
    public void setFile(int file){
        this.file = file;
    }
    public boolean hasMoved(){
        return hasMoved;
    }
    public void setMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }
    public boolean isWhite(){
        return isWhite;
    }
    public void setColor(boolean isWhite){
        this.isWhite = isWhite;
    }
    
    public char getNickName(){
        return nickName;
    }
    public void setNickName(char nickName){
        this.nickName = nickName;
    }
    public int getPieceValue(){
        return 0;
    }
    public List<MoveRequest> getPossibleMoves(Piece[][] board){
        List<MoveRequest> moves = new ArrayList<>();
        return moves;
    }
    public boolean outOfBounds(int newRank, int newFile){
        if(newRank > 7 || newRank < 0 || newFile > 7 || newFile < 0){
            return true;
        }
        return false;
    }


}