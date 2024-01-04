package erikbissell.com.WebChess;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Knight extends Piece {

    public Knight(){
    }
    public Knight (int initialRank, int initialFile, boolean isWhite){
        super(initialRank, initialFile, isWhite);
        super.setNickName('N');
    }
    public Knight(Knight otherKnight){
        setFile(otherKnight.getFile());
        setRank(otherKnight.getRank());
        setColor(otherKnight.isWhite());
        setNickName('N');
    }
    public boolean isValidMove(int newRank, int newFile, Piece[][] board){
        int dRank[] = {1,1,-1,-1,2,2,-2,-2};
        int dFile[] = {2,-2,2,-2,1,-1,1,-1};
        for(int i = 0; i < 8; i++){
            int potentialRank = getRank() + dRank[i];
            int potentialFile = getFile() + dFile[i];
            if(outOfBounds(potentialRank, potentialFile)){
                continue;
            }
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
        if(outOfBounds(newRank, newFile)){
            return false;
        }
        if(board[newRank][newFile].isWhite() == isWhite()){
            return false;
        }
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

    @Override
    public int getPieceValue(){
        int [][] whiteValueAdjustment = 
        {
            {-50,-40,-30,-30,-30,-30,-40, -50},
            {-40,-20,  0,  0,  0,  0,-20, -40},
            {-30,  0, 10, 15, 15, 10,  0, -30},
            {-30,  5, 15, 20, 20, 15,  5, -30},
            {-30,  0, 15, 20, 20, 15,  0, -30},
            {-30,  5, 10, 15, 15, 10,  5,-30},
            {-40,-20,  0,  5,  5,  0,-20,-40},
            {-50,-40,-30,-30,-30,-30,-40,-50}
        };
        int [][] blackValueAdjustment =
        {
            {-50,-40,-30,-30,-30,-30,-40,-50},
            {-40,-20,  0,  5,  5,  0,-20,-40},
            {-30,  5, 10, 15, 15, 10,  5,-30},
            {-30,  0, 15, 20, 20, 15,  0, -30},
            {-30,  5, 15, 20, 20, 15,  5, -30},
            {-30,  0, 10, 15, 15, 10,  0, -30},
            {-40,-20,  0,  0,  0,  0,-20, -40},
            {-50,-40,-30,-30,-30,-30,-40, -50}
        };
        if (isWhite()){
            return 320 + whiteValueAdjustment[getRank()][getFile()];
        }
        else{
            return 320 + blackValueAdjustment[getRank()][getFile()];
        }
    }

    @Override
    public List<MoveRequest> getPossibleMoves(Piece[][] board){
        List<MoveRequest> moves = new ArrayList<>();
        int dRank[] = {1,1,-1,-1,2,2,-2,-2};
        int dFile[] = {2,-2,2,-2,1,-1,1,-1};
        for(int i = 0; i < 8; i++){
            if( ! (outOfBounds(getRank() + dRank[i], getFile() + dFile[i]))){
                if(board[getRank() + dRank[i]][getFile() + dFile[i]] instanceof EmptySquare){
                    if(isValidMove(getRank() + dRank[i], getFile() + dFile[i],board)){
                        moves.add(new MoveRequest(getRank(), getFile(), getRank() + dRank[i], getFile() + dFile[i]));
                    }
                }
                else if(isValidCapture(getRank() + dRank[i], getFile() + dFile[i], board)){
                        moves.add(new MoveRequest(getRank(), getFile(), getRank() + dRank[i], getFile() + dFile[i]));
                }
            }
        }
        return moves;
    }
    


}