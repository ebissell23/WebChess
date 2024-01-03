package erikbissell.com.WebChess;

import java.util.List;

public class EnPassantExplorerAI {
    public EnPassantExplorerAI(){

    }

    public int evaluate(Chessboard board){
        
        int whiteMaterial = materialScore(board.getBoard(),true);
        int blackMaterial = materialScore(board.getBoard(),false);
        System.out.print("White: " + whiteMaterial);
        System.out.println(" Black: " + blackMaterial);
        System.out.println("Eval: " + (whiteMaterial - blackMaterial));
        miniMax(board, 1);
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
      /*   for(int i = 0; i < moves.size(); i++){
            //moves.get(i).printMove();
        } */

        return -1;
    }
}

