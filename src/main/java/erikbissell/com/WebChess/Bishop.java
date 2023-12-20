package erikbissell.com.WebChess;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bishop extends Piece{

    public Bishop(){

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
        for (int i = 1; i < rankDifference; i ++){
            int intermediateRank = getRank() + (i * rankDirection );
            int intermediateFile = getFile() + (i * fileDirection);
            if ( !(board[intermediateRank][intermediateFile] instanceof EmptySquare)){
                System.out.println("No Empty Square at Rank: " + intermediateRank + " File: " + intermediateFile);
                return false;
            }
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
        for (int i = 1; i < rankDifference; i ++){
            int intermediateRank = getRank() + (i * rankDirection );
            int intermediateFile = getFile() + (i * fileDirection);
            if ( !(board[intermediateRank][intermediateFile] instanceof EmptySquare)){
                System.out.println("No Empty Square at Rank: " + intermediateRank + " File: " + intermediateFile);
                return false;
            }
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
        return "Bishop";
    }
   

}