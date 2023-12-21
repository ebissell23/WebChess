package erikbissell.com.WebChess;

public class EnPassantExplorerAI {

    public int evaluate(Piece[][] board){
        int score = 0;
        int whiteMaterial = materialScore(board,true);
        int blackMaterial = materialScore(board,false);
        return whiteMaterial - blackMaterial;
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
}

