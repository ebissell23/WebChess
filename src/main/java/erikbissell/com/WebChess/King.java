package erikbissell.com.WebChess;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class King extends Piece {
    public King (int initialRank, int initialFile, boolean isWhite){
        super(initialRank, initialFile, isWhite);
        super.setNickName('K');
    }
    public King(King otherKing){
        
    }
    public boolean isValidMove(int newRank, int newFile, Piece[][] board){
        if(outOfBounds(newRank, newFile)){
            return false;
        }
        if(! (board[newRank][newFile] instanceof EmptySquare)){
            return false;
        }
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
        if(outOfBounds(newRank, newFile)){
            return false;
        }
        if(board[newRank][newFile] instanceof EmptySquare){
            return false;
        }
        if(board[newRank][newFile].isWhite() == isWhite()){
            return false;
        }
        int rankDifference = Math.abs(getRank() - newRank);
        int fileDifference = Math.abs(getFile() - newFile);
        if(rankDifference > 1 || fileDifference > 1){
            return false;
        }
        return true;
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
    @Override 
    public int getPieceValue(){
        int [][] whiteValueAdjustment =
        {
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            {20, 20,  0,  0,  0,  0, 20, 20},
            {20, 30, 10,  0,  0, 10, 30, 20}

        };
        
        int [][] blackValueAdjustment =
        {
            {20, 30, 10,  0,  0, 10, 30, 20},
            {20, 20,  0,  0,  0,  0, 20, 20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30}

        };
        if(isWhite()){
            return 20000 + whiteValueAdjustment[getRank()][getFile()];
        } 
        return 20000 + blackValueAdjustment[getRank()][getFile()];
    }
    @Override 
    public List<MoveRequest> getPossibleMoves(Piece[][] board){
        List<MoveRequest> moves = new ArrayList<>();
        int[] dRank = {-1,-1,0,1,1,1,0,1};
        int[] dFile = {0,-1,-1,-1,0,1,1,1};
        for(int i = 0; i < 8; i ++){
            if((isValidMove(getRank() + dRank[i], getFile() + dFile[i], board)) ||(isValidCapture(getRank() + dRank[i], getFile() + dFile[i], board)) ){
                moves.add(new MoveRequest(getRank(), getFile(), getRank() + dRank[i], getFile() + dFile[i]));
            }
        }
        return moves;
    }
    

}
