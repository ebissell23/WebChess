package erikbissell.com.WebChess;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook (int initialRank, int initialFile, boolean isWhite){
        super(initialRank, initialFile, isWhite);
        super.setNickName('R');
    }
    public Rook(Rook otherRook){
        setFile(otherRook.getFile());
        setRank(otherRook.getRank());
        setColor(otherRook.isWhite());
        setNickName('R');
    }
    public boolean isValidMove(int newRank, int newFile, Piece[][] board){
       // System.out.println("rook isValidMove");
        int step = 0;
            //vertical move
        if ((getFile() == newFile) && (getRank() != newRank)){
            //System.out.println("Rook vertical");
            if(getRank() > newRank){
                step = -1;
            }
            else{
                step = 1;
            }
            for (int i = getRank() + step; i != newRank; i+=step){
                if( !(board[i][newFile] instanceof EmptySquare)){
                    //System.out.println("Not an empty square at : i: " + i + " newFile: " +newFile );
                    return false;
                }
            }
            return true;
        }
        //horizontal move
        else if( (getRank() == newRank ) && (getFile() != newFile)){
           // System.out.println("Rook horizontal");
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
    public boolean move(int newRank, int newFile, Piece[][] board){
        if(isValidMove(newRank, newFile, board)){
            setRank(newRank);
            setFile(newFile);
            return true;
        }
        return false;
    }
    public boolean isValidCapture(int newRank, int newFile, Piece[][] board){
        if(board[newRank][newFile].isWhite() == isWhite()){
            return false;
        }
        int rankDifference = newRank - getRank();
        int fileDifference = newFile - getFile();
        if ( (Math.abs(rankDifference) != 0) && (Math.abs(fileDifference) != 0)){ //Can't move both ways 
            //System.out.println("can't move both ways");
            return false;
        }
        if( (Math.abs(rankDifference) == 1) | (Math.abs(fileDifference) == 1)){ //only one space away 
           // System.out.println("Capturing one space away");
            return true;
        }
        int step = 0;
        if(fileDifference == 0){
            if ((getFile() == newFile) && (getRank() != newRank)){
            //System.out.println("Rook vertical capture");
            if(getRank() > newRank){
                step = -1;
            }
            else{
                step = 1;
            }
            for (int i = getRank() + step; i != newRank; i+=step){
                if( !(board[i][newFile] instanceof EmptySquare)){
                //    System.out.println("Not an empty square at : i: " + i + " newFile: " +newFile );
                    return false;
                }
            }
            return true;
        }

        }
        else if(rankDifference == 0){
           if ((getRank() == newRank) && (getFile() != newFile)){
                //System.out.println("Rook horizontal capture");
                if(getFile() > newFile){
                    step = -1;
                }
                else{
                    step = 1;
                }
                for (int i = getFile() + step; i != newFile; i+=step){
                    if( !(board[newRank][i] instanceof EmptySquare)){
                    //    System.out.println("Not an empty square at : i: " + i + " newFile: " +newFile );
                        return false;
                    }
                }
                return true;
            }
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
        return "Rook";
    }
    @Override
    public int getPieceValue(){
        int [][] whiteValueAdjustment =
        {
            {0,  0,  0,  0,  0,  0,  0,  0,},
            {5, 10, 10, 10, 10, 10, 10,  5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {0,  0,  0,  5,  5,  0,  0,  0}            
        };

        int [][] blackValueAdjustment =
        {
            {  0,  0,  0,  5,  5,  0,  0,  0},
            { -5,  0,  0,  0,  0,  0,  0, -5},
            { -5,  0,  0,  0,  0,  0,  0, -5},
            { -5,  0,  0,  0,  0,  0,  0, -5},
            { -5,  0,  0,  0,  0,  0,  0, -5},
            { -5,  0,  0,  0,  0,  0,  0, -5},
            { 5, 10, 10, 10, 10, 10, 10,  5},
            { 0,  0,  0,  0,  0,  0,  0,  0}
          
        };
        if(isWhite()){
            return 500 + whiteValueAdjustment[getRank()][getFile()];
        }
        return 500 + blackValueAdjustment[getRank()][getFile()];

    }
    public List<MoveRequest> getPossibleMoves(Piece[][] board){
        List<MoveRequest> moves = new ArrayList<>();
        int[] dRank = {1,-1,0,0};
        int[] dFile = {0,0,1,-1};
        
        for (int i = 0; i < 4; i++){
            int newRank = getRank() + dRank[i];
            int newFile = getFile() + dFile[i];
            boolean canContinue = true;
            while(canContinue){
                if(outOfBounds(newRank, newFile)){
                    canContinue = false;
                }
                else if( board[newRank][newFile] instanceof EmptySquare){
                    if(isValidMove(newRank, newFile, board)){
                        moves.add(new MoveRequest(getRank(), getFile(), newRank, newFile));
                    }
                    else{
                        canContinue = false;
                    }
                }
                else if (isValidCapture(newRank, newFile, board)){
                    moves.add( new MoveRequest(getRank(), getFile(), newRank, newFile));
                    canContinue = false;
                }
                newRank = newRank + dRank[i];
                newFile = newFile + dFile[i];
            }
        }

        return moves;
    }

}