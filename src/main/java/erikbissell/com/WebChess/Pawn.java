package erikbissell.com.WebChess;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pawn extends Piece {

    public Pawn (int initialRank, int initialFile, boolean isWhite){
        super(initialRank, initialFile, isWhite);
        super.setNickName('P');


    }
    public boolean isValidMove(int newRank, int newFile, Piece[][] board){
        //must be capturing to change files
        if(newFile != getFile()){
            return false;
        }
        if(isWhite()){ //white pawns can only move up
            if(newRank >= getRank()){
                return false;
            } //first move can move 2 spaces
            else if((getRank() == 6) && (newRank==4)){
                return true;
            } //otherwise can only move 1 space
            else if(newRank == getRank() -1){
                return true;
            }
            return false;
        }
        else{ //black pawns can only move down
            if(newRank <= getRank()){
                return false;
            }
            else if( (getRank() == 1) && (newRank == 3)){
                return true;
            }
            else if(newRank == getRank()+1){
                return true;
            }
            return false;
        }      
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
        //pawns can only capture one space ahead, one file over
        if(newFile == getFile() || (Math.abs(newRank - getRank()) != 1)){
            return false;
        }
        if (isWhite()){
            if (newRank >= getRank()){ //white only moves up
                return false;
            }
        }
        else{
            if(newRank <= getRank()){ //black only moves down
                return false;
            }
        }
        if( (getFile() + 1 == newFile) || (getFile() - 1 == newFile)){
            return true;
        }
        return false;
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
        return "Pawn";
    }
    @Override
    public int getPieceValue(){
        int [][] whiteValueAdjustment =
        {
            {0,  0,  0,  0,  0,  0,  0,  0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5,  5, 10, 25, 25, 10,  5,  5},
            {0,  0,  0, 20, 20,  0,  0,  0},
            {5, -5,-10,  0,  0,-10, -5,  5},
            {5, 10, 10,-20,-20, 10, 10,  5},
            {0,  0,  0,  0,  0,  0,  0,  0}
        };
        int [][] blackValueAdjustment =
        {
            {0,  0,  0,  0,  0,  0,  0,  0},
            {5, 10, 10,-20,-20, 10, 10,  5},
            {5, -5,-10,  0,  0,-10, -5,  5},
            {0,  0,  0, 20, 20,  0,  0,  0},
            {5,  5, 10, 25, 25, 10,  5,  5},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {50, 50, 50, 50, 50, 50, 50, 50}
        };
        if(isWhite()){
            return 100 + whiteValueAdjustment[getRank()][getFile()];
        }
        else{
            return 100 + blackValueAdjustment[getRank()][getFile()];
        }
        
    }

}


 