package erikbissell.com.WebChess;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

public class Chessboard {

    private Piece[][] board;
    private boolean isWhiteTurn = true;
    private EnPassantExplorerAI engine = new EnPassantExplorerAI();
    private ArrayList<MoveRequest> moveList = new ArrayList<MoveRequest>();
    
    //standard starting setup
    public Chessboard(){
        board = new Piece[8][8];
        initializeEmptyBoard();
        initializeStartingBoard();
    }
    //used for chess engine so it doesnt change gameboard when it is evaluating positions
    public Chessboard(Chessboard otherBoard){
        Piece[][] copyOfBoard = otherBoard.getBoard();
        isWhiteTurn = otherBoard.isWhiteTurn();
        board = new Piece[8][8];
        //iterate through board and make copy of pieces so gameboard doesn't change when engine evaluates moves
        for (int i = 0; i < 8; i++){
            for ( int j = 0; j < 8; j++){
                    board[i][j] = getCopyOfPiece(copyOfBoard[i][j]);
            }
        }
    }

    public void checkLastMove(){
        //checks if last move was capturing the king
        //Can't end the game once it is captured because engine would end the game when it finds a king capture
        boolean kingCaptured = moveList.get(moveList.size() -1).checkCapturedKing();
        if(kingCaptured){
            gameOver();
        }
    }

    public Piece getCopyOfPiece(Piece piece){
        if(piece instanceof Pawn){
            return new Pawn((Pawn)piece);
        }
        else if(piece instanceof Rook){
            return new Rook((Rook)piece);
        }
        else if(piece instanceof Bishop){
            return new Bishop((Bishop)piece);
        }
        else if(piece instanceof Knight){
            return new Knight((Knight)piece);
        }
        else if(piece instanceof King){
            return new King((King)piece);
        }
        else if(piece instanceof Queen){
            return new Queen((Queen)piece);
        }
        else{ //its an emptySquare
            return new EmptySquare((EmptySquare)piece);
        }
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
        
        //En passant is a forced move. Check if it is available 
        if(checkEnPassant(sourceRow, sourceCol, destRow, destCol)){
            //TODO: enforce en passant
        }
        //can't stay in the same place
        if( (sourceCol == destCol) && (sourceRow == destRow)){
            return false;
        }
        //Destination is Empty, try to move. Piece handles if allowed 
        if(board[destRow][destCol] instanceof EmptySquare){
            if(board[sourceRow][sourceCol].move(destRow,destCol,board)){
                //updates board, adds move to moveList, changes turn. 
                board[destRow][destCol] = board[sourceRow][sourceCol];
                board[sourceRow][sourceCol] = new EmptySquare(sourceRow,sourceCol);
                moveList.add(new MoveRequest(sourceRow, sourceCol, destRow, destCol));
                changeTurn();
                return true;
            }
            else{
                return false;
            }
        }
        else{ //destination isn't empty, try to capture. Piece handles if allowed
            //can't capture own piece
            if(board[sourceRow][sourceCol].isWhite() == board[destRow][destCol].isWhite()){
                return false;
            }
            if(board[sourceRow][sourceCol].capture(destRow,destCol,board)){
                //special handler for a king capture which can end a game
                if(board[destRow][destCol] instanceof King){
                    board[destRow][destCol] = board[sourceRow][sourceCol];
                    board[sourceRow][sourceCol] = new EmptySquare(sourceRow,sourceCol);
                    moveList.add(new MoveRequest(sourceRow, sourceCol, destRow, destCol));
                    moveList.get(moveList.size() - 1).setCaptureKing(true);
                    changeTurn();
                    return true;
                }
                board[destRow][destCol] = board[sourceRow][sourceCol];
                board[sourceRow][sourceCol] = new EmptySquare(sourceRow,sourceCol);
                moveList.add(new MoveRequest(sourceRow, sourceCol, destRow, destCol));
                changeTurn();
                return true;
            }
            else{
                return false;
            }
        }
    }
    //gives us a fresh game board
    public void gameOver(){
        initializeEmptyBoard();
        initializeStartingBoard();
    }

    public void changeTurn(){
        isWhiteTurn = !isWhiteTurn;
    }

    public boolean isWhiteTurn(){
        return isWhiteTurn;
    }

    public boolean checkEnPassant(int sourceRow, int sourceCol, int destRow, int destCol){
        //TODO: implement 
        //check move list to see if last move was a 2 postition pawn move  
        return false;
    }

    public List<MoveRequest> possibleMoves(){
        List<MoveRequest> moves = new ArrayList<>();
        //iterate through each piece. Add its possible moves to the list of possible moves
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if ( (board[i][j].isWhite() == isWhiteTurn()) && (! (board[i][j] instanceof EmptySquare) ) ){
                    List<MoveRequest> pieceMoves = board[i][j].getPossibleMoves(board);
                    for (int k = 0; k < pieceMoves.size(); k++){
                        moves.add(pieceMoves.get(k));
                    }
                }
            }
        }
        return moves;
    }
    //engine use only
    //TODO: add member variable to board -boolean whiteHasMate so we don't need to iterate through the board each time
    public boolean whiteHasMate(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if( (board[i][j] instanceof King) && (board[i][j].isWhite() == false) ){
                    return false;
                }
            }
        }
        return true;
    }
    //engine use only
    //TODO: add member variable to board -boolean whiteHasMate so we don't need to iterate through the board each time
    public boolean blackHasMate(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if( (board[i][j] instanceof King) && (board[i][j].isWhite() ) ){
                    return false;
                }
            }
        }
        System.out.println("black has mate");
        return true;
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