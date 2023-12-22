package erikbissell.com.WebChess;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pawn extends Piece {

    public Pawn (int initialRank, int initialFile, boolean isWhite){
        super(initialRank, initialFile, isWhite);
        super.setNickName('P');


    }
    public boolean isValidMove(int newRank, int newFile, Piece[][] board){
        if( ! (board[newRank][newFile] instanceof EmptySquare)){
            return false;
        }
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
        if(newRank > 7 || newRank < 0 || newFile > 7 || newFile < 0){
            return false;
        }
        if(board[newRank][newFile].isWhite() == isWhite()){
            return false;
        }
        if(board[newRank][newFile] instanceof EmptySquare){
            return false;
        }
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
    public List<MoveRequest> getPossibleMoves(Piece[][] board){
        List<MoveRequest> possibleMoves = new ArrayList<>();
       // System.out.println("Pawn.possibleMoves() at Rank: " + getRank() + " File: " + getFile());
        //Possible Moves for Pawn are one space ahead, two spaces ahead, and capture diagonal 
        if(isWhite() && getRank() == 6){ // White moves from starting space
            if(isValidMove(4,getFile(),board) ){
                possibleMoves.add(new MoveRequest(getRank(), getFile(), 4, getFile()));
            }
            if(isValidMove(5,getFile(),board) ){
                possibleMoves.add(new MoveRequest(getRank(), getFile(), 5, getFile()));
            }
        }
        else if(!isWhite() && getRank() == 1){ //blackMoves from starting spaces
            if(isValidMove(3,getFile(),board) ){
                possibleMoves.add(new MoveRequest(getRank(), getFile(), 3, getFile()));
            }
            if(isValidMove(2,getFile(),board) ){
                possibleMoves.add(new MoveRequest(getRank(), getFile(), 2, getFile()));
            }
        }
        else{ //Moves not from starting place
            if(isWhite()){ // White
                if(isValidMove(getRank() - 1, getFile(), board)){
                    possibleMoves.add(new MoveRequest(getRank(), getFile(), getRank() - 1, getFile()));
                }
            }
            else{ // Black
                if(isValidMove(getRank() + 1, getFile(), board)){
                    possibleMoves.add(new MoveRequest(getRank(), getFile(), getRank() + 1, getFile()));
                }
            }
        }
        //Possible Captures
        if(isWhite()){ 
            if(isValidCapture(getRank() - 1, getFile() - 1, board)){ //diag Left Capture
                possibleMoves.add(new MoveRequest(getRank(), getFile(), getRank() - 1, getFile() - 1));
            }
            if(isValidCapture(getRank() - 1, getFile() + 1, board)){// Diag Right Capture
                possibleMoves.add(new MoveRequest(getRank(), getFile(), getRank() - 1, getFile() + 1));
            } 
        }
        else{
            if(isValidCapture(getRank() + 1, getFile() - 1, board)){ //diag Left Capture
                possibleMoves.add(new MoveRequest(getRank(), getFile(), getRank() + 1, getFile() - 1));
            }
            if(isValidCapture(getRank() + 1, getFile() + 1, board)){// Diag Right Capture
                possibleMoves.add(new MoveRequest(getRank(), getFile(), getRank() + 1, getFile() + 1));
            } 

        }
        //System.out.println("Pawn.possibleMOves.size()" + possibleMoves.size());
        return possibleMoves;
    }


}

 