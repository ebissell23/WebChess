package erikbissell.com.WebChess;

import com.fasterxml.jackson.annotation.JsonProperty;

public class King extends Piece {
    public King (int initialRank, int initialFile, boolean isWhite){
        super(initialRank, initialFile, isWhite);
        super.setNickName('K');


    }
    public boolean isValidMove(int newRank, int newFile, Piece[][] board){
        int rankDifference = Math.abs(getRank() - newRank);
        int fileDifference = Math.abs(getFile() - newFile);
        if(rankDifference > 1 || fileDifference > 1){
            return false;
        }
        return true;
    }
    public boolean move(int newRank, int newFile, Piece[][] board){
        if(isValidMove(newRank, newFile, board)){
            setRank(newRank);
            setFile(newFile);
            return true;
        }
        return false;
    }
    public boolean isValidCapture(int newRank, int newFile, Piece[][] board){
        return isValidMove(newRank, newFile, board);
    }
   public boolean capture(int newRank, int newFile, Piece[][] board){
        if(isValidCapture(newRank, newFile, board)){
            setRank(newRank);
            setFile(newFile);
            return true;
        }
        return false;
    }
    
    @Override
    public char getNickName(){
        return nickName;
    }
    @Override
    public String toString(){
        return "King";
    }

}
