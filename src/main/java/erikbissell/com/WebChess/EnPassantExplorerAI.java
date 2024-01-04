package erikbissell.com.WebChess;

import java.util.List;

public class EnPassantExplorerAI {
    public EnPassantExplorerAI(){

    }

    public int evaluate(Chessboard board){
        
        int whiteMaterial = materialScore(board.getBoard(),true);
        int blackMaterial = materialScore(board.getBoard(),false);
        if(board.whiteHasMate()) { return 1000000;}
        if(board.blackHasMate()) { return -1000000;}
       // System.out.print("White: " + whiteMaterial);
        //System.out.println(" Black: " + blackMaterial);
        System.out.println("Eval: " + (whiteMaterial - blackMaterial));
        //miniMax(board, 1);
        return -1;
      //  return whiteMaterial - blackMaterial;
    }
    public int materialScore(Piece[][] board, boolean isWhite){
        int materialScore = 0;
        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j].isWhite() == isWhite){
                    materialScore = materialScore + board[i][j].getPieceValue();
                }
            }
        }
        return materialScore;
    }

    public int miniMax(Chessboard board, int depth){
        List<MoveRequest> moves = board.possibleMoves();
        System.out.println("moves.size(): " + moves.size());
        MoveRequest firstMove = moves.get(0);
        MoveRequest bestMove = new MoveRequest(-1, -1, -1, -1);
        int bestScore = 0;
        Chessboard copyChessboard = new Chessboard(board);
        copyChessboard.printBoard();
        firstMove.printMove();
        copyChessboard.movePiece(firstMove.getSourceRow(),firstMove.getSourceCol(),firstMove.getDestRow(),firstMove.getDestCol());
        evaluate(copyChessboard);
        copyChessboard.printBoard();
        return -1;
    }
    
}

/*if(copiedBoard.isWhiteTurn()){
                if(thisMoveScore > bestScore){
                    bestScore = thisMoveScore;
                    bestMove = move;
                }
            }
            else{
                if(thisMoveScore < bestScore){
                    bestScore = thisMoveScore;
                    bestMove = move;
                }
            } */