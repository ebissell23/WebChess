package erikbissell.com.WebChess;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bishop extends Piece{
    //adjustments based on where the piece is located
    int [][] whiteValueAdjustment =
        {   
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10,  0,  5, 10, 10,  5,  0,-10},
            {-10,  5,  5, 10, 10,  5,  5,-10},
            {-10,  0, 10, 10, 10, 10,  0,-10},
            {-10, 10, 10, 10, 10, 10, 10,-10},
            {-10,  5,  0,  0,  0,  0,  5,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}
        };

        int [][] blackValueAdjustment =
        {
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,  5,  0,  0,  0,  0,  5,-10},
            {-10, 10, 10, 10, 10, 10, 10,-10},
            {-10,  0, 10, 10, 10, 10,  0,-10},
            {-10,  5,  5, 10, 10,  5,  5,-10},
            {-10,  0,  5, 10, 10,  5,  0,-10},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}
        };

    public Bishop(){
    }
    public Bishop(Bishop otherBishop){
        setFile(otherBishop.getFile());
        setRank(otherBishop.getRank());
        setColor(otherBishop.isWhite());
        setNickName('B');
    }

    public Bishop (int initialRank, int initialFile, boolean isWhite){
        super(initialRank, initialFile, isWhite);
        super.setNickName('B');
    }

    public boolean isValidMove(int newRank, int newFile, Piece[][] board){
        int rankDifference = Math.abs(newRank - getRank());
        int fileDifference = Math.abs(newFile - getFile());
        
        //Bishop can only move perfectly diagnal. 
        if(rankDifference != fileDifference){
            return false;
        }
        int rankDirection = getRank() > newRank ? -1 : 1;
        int fileDirection = getFile() > newFile ? -1 : 1;
        //looks for any pieces between current square and destination square
        for (int i = 1; i < rankDifference; i ++){
            int intermediateRank = getRank() + (i * rankDirection );
            int intermediateFile = getFile() + (i * fileDirection);
            if ( !(board[intermediateRank][intermediateFile] instanceof EmptySquare)){
                System.out.println("No Empty Square at Rank: " + intermediateRank + " File: " + intermediateFile);
                return false;
            }
        }
        //nothing found in between - we can move there
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
        //can't capture own piece
        if(board[newRank][newFile].isWhite() == isWhite()){
            return false;
        }
        int rankDifference = Math.abs(newRank - getRank());
        int fileDifference = Math.abs(newFile - getFile());
        if(rankDifference != fileDifference){
            return false;
        }
        int rankDirection = getRank() > newRank ? -1 : 1;
        int fileDirection = getFile() > newFile ? -1 : 1;
        //can always capture one space away
        if (rankDifference == 1 && fileDifference == 1){
            return true;
        }
        //look for any piece in between start and ending square
        for (int i = 1; i < rankDifference; i ++){
            int intermediateRank = getRank() + (i * rankDirection );
            int intermediateFile = getFile() + (i * fileDirection);
            if ( !(board[intermediateRank][intermediateFile] instanceof EmptySquare)){
                System.out.println("No Empty Square at Rank: " + intermediateRank + " File: " + intermediateFile);
                return false;
            }
        }
        //nothing in between - valid move
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
        return "Bishop";
    }

    @Override
    public int getPieceValue(){
        
        if(isWhite()){
            return 320 + whiteValueAdjustment[getRank()][getFile()];
        }
        return 320 + blackValueAdjustment[getRank()][getFile()];
    }
   
    @Override 
    public List<MoveRequest> getPossibleMoves(Piece[][] board){
        List<MoveRequest> moves = new ArrayList<>();
        //matrix of the ways the bishop can move
        int[] dRank = {1,1,-1,-1};
        int[] dFile = {1,-1,1,-1};
        for(int i = 0; i < 4; i++){

            int newRank = getRank() + dRank[i];
            int newFile = getFile() + dFile[i];
            boolean canContinue = true;
            //move in that direction and add each legal move iteratively 
            while (canContinue){
                if(outOfBounds(newRank,newFile)){
                    canContinue = false;
                    continue;
                }
                if(board[newRank][newFile] instanceof EmptySquare){
                    if(isValidMove(newRank, newFile, board)){
                        moves.add(new MoveRequest(getRank(), getFile(), newRank, newFile));
                    }
                    else{
                        canContinue = false;
                    }
                }
                else{
                    if(isValidCapture(newRank, newFile, board)){
                        moves.add(new MoveRequest(getRank(), getFile(), newRank, newFile));
                    }
                    canContinue = false;
                }
                newRank = newRank + dRank[i];
                newFile = newFile + dFile[i];
            }
        }
        return moves;
    }
    

}