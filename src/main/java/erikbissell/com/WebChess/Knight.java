package erikbissell.com.WebChess;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Knight extends Piece {

    public Knight(){
    }
    public Knight (int initialRank, int initialFile, boolean isWhite){
        super(initialRank, initialFile, isWhite);
        super.setNickName('N');
    }
    public boolean isValidMove(int newRank, int newFile, Piece[][] board){
        int dRank[] = {1,1,-1,-1,2,2,-2,-2};
        int dFile[] = {2,-2,2,-2,1,-1,1,-1};
        for(int i = 0; i < 8; i++){
            int potentialRank = getRank() + dRank[i];
            int potentialFile = getFile() + dFile[i];
            if( (potentialRank == newRank) && (potentialFile == newFile)){
                return true;
            }
        }
        return false;
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
        return (isValidMove(newRank, newFile, board));
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
        return "Knight";
    }
    


}