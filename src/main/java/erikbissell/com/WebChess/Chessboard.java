package erikbissell.com.WebChess;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

public class Chessboard {

    private Piece[][] board;
    ArrayList<MoveRequest> moveList = new ArrayList<MoveRequest>();
    public Chessboard(){
        board = new Piece[8][8];
        initializeEmptyBoard();
        initializeStartingBoard();
    }
    public void initializeEmptyBoard(){
        for (int rank = 0; rank < 8; rank++){
            for (int file = 0; file < 8; file++){
                board[rank][file] = new EmptySquare(rank,file);
            }
        }
    }
    public void initializeStartingBoard(){
        //Black Major and Minor Pieces
        board[0][0] = new Rook(0, 0, false);
        board[0][1] = new Knight(0, 1, false);
        board[0][2] = new Bishop(0, 2, false);
        board[0][3] = new Queen(0, 3, false);
        board[0][4] = new King(0, 4, false);
        board[0][5] = new Bishop(0, 5, false);
        board[0][6] = new Knight(0, 6, false);
        board[0][7] = new Rook(0, 7, false);
        //Black Pawns
        for (int j = 0; j < 8; j++) {
            board[1][j] = new Pawn(1, j, false);
        }
        //White Major and Minor Pieces
        board[7][0] = new Rook(7, 0, true);
        board[7][1] = new Knight(7, 1, true);
        board[7][2] = new Bishop(7, 2, true);
        board[7][3] = new Queen(7, 3, true);
        board[7][4] = new King(7, 4, true);
        board[7][5] = new Bishop(7, 5, true);
        board[7][6] = new Knight(7, 6, true);
        board[7][7] = new Rook(7, 7, true);
        //White Pawns
        for (int j = 0; j < 8; j++) {
            board[6][j] = new Pawn(6, j, true);
        }   
    }
    public Piece[][] getBoard(){
        return board;
    }
    public boolean movePiece(int sourceRow, int sourceCol, int destRow, int destCol){
        
        //TODO: check for en passant
        if(checkEnPassant(sourceRow, sourceCol, destRow, destCol)){

        }

        if( (sourceCol == destCol) && (sourceRow == destRow)){
            return false;
        }
        //Destination is Empty, try to move. Piece handles if allowed
        if(board[destRow][destCol] instanceof EmptySquare){
            System.out.println("Calling Move");
            if(board[sourceRow][sourceCol].move(destRow,destCol,board)){
                board[destRow][destCol] = board[sourceRow][sourceCol];
                board[sourceRow][sourceCol] = new EmptySquare(sourceRow,sourceCol);
                moveList.add(new MoveRequest(sourceRow, sourceCol, destRow, destCol));
                return true;
            }
            else{
                System.out.println("can't make that move");
                return false;
            }
        }
        else{ //destination isn't empty, try to capture. Piece handles if allowed
            //can't capture own piece
            
            if(board[sourceRow][sourceCol].isWhite() == board[destRow][destCol].isWhite()){
                return false;
            }
            System.out.println("Calling Capture");
            if(board[sourceRow][sourceCol].capture(destRow,destCol,board)){
                if(board[destRow][destCol] instanceof King){
                    System.out.println("GameOver!");
                    gameOver();
                }
                board[destRow][destCol] = board[sourceRow][sourceCol];
                board[sourceRow][sourceCol] = new EmptySquare(sourceRow,sourceCol);
                return true;
            }
            else{
                System.out.println("can't make that capture");
                return false;
            }
        }
    }
    public void gameOver(){

    }
    public boolean checkEnPassant(int sourceRow, int sourceCol, int destRow, int destCol){
        return false;
    }
    public void printBoard(){
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 8; j++){
                System.out.print(board[i][j].getNickName());
            }
            System.out.println();
        }

    }
}