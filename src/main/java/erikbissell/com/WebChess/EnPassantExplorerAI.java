package erikbissell.com.WebChess;

import java.util.List;

public class EnPassantExplorerAI {
    int MAX_DEPTH = 2;
    public EnPassantExplorerAI(){

    }

    public int evaluate(Chessboard board){
        
        int whiteMaterial = materialScore(board.getBoard(),true);
        int blackMaterial = materialScore(board.getBoard(),false);
        if(board.whiteHasMate()) { return 1000000;}
        if(board.blackHasMate()) { return -1000000;}
       // System.out.print("White: " + whiteMaterial);
        //System.out.println(" Black: " + blackMaterial);
        //System.out.println("Eval: " + (whiteMaterial - blackMaterial));
        //miniMax(board, 1);
        return whiteMaterial - blackMaterial;
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

    public BestMove miniMax(Chessboard board, int depth){
        if(depth == 0){
            return new BestMove(evaluate(board), null);
        }
        List<MoveRequest> moves = board.possibleMoves();
        BestMove bestMove = null;
    
        for (int i = 0; i < moves.size(); i++){
            Chessboard copyChessboard = new Chessboard(board);
            MoveRequest move = moves.get(i);
            copyChessboard.movePiece(move.getSourceRow(),move.getSourceCol(), move.getDestRow(), move.getDestCol());
            int moveScore = miniMax(copyChessboard, depth - 1).getScore();
            if(copyChessboard.isWhiteTurn()){
                if(bestMove == null || moveScore < bestMove.getScore()){
                    bestMove = new BestMove(moveScore, move);
                }
            }
            else{
                if(bestMove == null || moveScore > bestMove.getScore()){
                    bestMove = new BestMove(moveScore, move);
                }
            }
        }
        return bestMove;
    }
    public BestMove miniMaxPruned(Chessboard board, int depth, int alpha, int beta){
        if(depth == 0){
            return new BestMove(evaluate(board), null);
        }
        List<MoveRequest> moves = board.possibleMoves();
        BestMove bestMove = null;
    
        for (int i = 0; i < moves.size(); i++){
            Chessboard copyChessboard = new Chessboard(board);
            MoveRequest move = moves.get(i);
            copyChessboard.movePiece(move.getSourceRow(),move.getSourceCol(), move.getDestRow(), move.getDestCol());
            int moveScore = miniMax(copyChessboard, depth - 1).getScore();
            if(copyChessboard.isWhiteTurn()){
                if(bestMove == null || moveScore < bestMove.getScore()){
                    bestMove = new BestMove(moveScore, move);
                }
            }
            else{
                if(bestMove == null || moveScore > bestMove.getScore()){
                    bestMove = new BestMove(moveScore, move);
                }
            }
        }
        return bestMove;


    }
    
}