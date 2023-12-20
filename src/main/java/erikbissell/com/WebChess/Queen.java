package erikbissell.com.WebChess;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Queen extends Piece {
    public Queen (int initialRank, int initialFile, boolean isWhite){
        super(initialRank, initialFile, isWhite);
        super.setNickName('Q');
    }
    public boolean isValidMove(int newRank, int newFile, Piece[][] board){
        if((bishopIsValidMove(newRank, newFile, board))||(rookIsValidMove(newRank,newFile,board))){
            return true;
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
        if( (bishopIsValidCapture(newRank, newFile, board)) || (rookIsValidCapture(newRank, newFile, board))){
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

    public boolean rookIsValidCapture(int newRank,int newFile, Piece [][]board){

        int rankDifference = newRank - getRank();
        int fileDifference = newFile - getFile();
        if ( (Math.abs(rankDifference) != 0) && (Math.abs(fileDifference) != 0)){ //Can't move both ways 
            System.out.println("can't move both ways");
            return false;
        }
        if( (Math.abs(rankDifference) == 1) | (Math.abs(fileDifference) == 1)){ //only one space away 
            System.out.println("Capturing one space away");
            return true;
        }
        int step = 0;
        if(fileDifference == 0){
            if ((getFile() == newFile) && (getRank() != newRank)){
                System.out.println("Rook vertical");
                if(getRank() > newRank){
                    step = -1;
                }
                else{
                    step = 1;
                }
                for (int i = getRank() + step; i != newRank; i+=step){
                    if( !(board[i][newFile] instanceof EmptySquare)){
                        System.out.println("Not an empty square at : i: " + i + " newFile: " +newFile );
                        return false;
                    }
                }
                return true;
            }

        }
        else if(rankDifference == 0){
           if ((getFile() == newFile) && (getRank() != newRank)){
                System.out.println("Rook vertical");
                if(getRank() > newRank){
                    step = -1;
                }
                else{
                    step = 1;
                }
                for (int i = getRank() + step; i != newRank; i+=step){
                    if( !(board[i][newFile] instanceof EmptySquare)){
                        System.out.println("Not an empty square at : i: " + i + " newFile: " +newFile );
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    public boolean bishopIsValidMove(int newRank, int newFile, Piece[][] board){
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
    public boolean bishopIsValidCapture(int newRank, int newFile, Piece[][] board){
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
    public boolean rookIsValidMove(int newRank, int newFile, Piece[][] board){
        System.out.println("rook isValidMove");
        int step = 0;
            //vertical move
        if ((getFile() == newFile) && (getRank() != newRank)){
            System.out.println("Rook vertical");
            if(getRank() > newRank){
                step = -1;
            }
            else{
                step = 1;
            }
            for (int i = getRank() + step; i != newRank; i+=step){
                if( !(board[i][newFile] instanceof EmptySquare)){
                    System.out.println("Not an empty square at : i: " + i + " newFile: " +newFile );
                    return false;
                }
            }
            return true;
        }
        //horizontal move
        else if( (getRank() == newRank )&& (getFile() != newFile)){
            System.out.println("Rook horizontal");
            if(getFile() > newFile){
                step = -1;
            }
            else{
                step = 1;
            }
            for (int i = getFile()+step; i!= newFile; i+=step){
                if(!(board[newRank][i] instanceof EmptySquare)){
                    return false;
                }
            }
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
        return "Queen";
    }
    
}