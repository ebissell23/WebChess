package erikbissell.com.WebChess;

public class PieceTest {

    public static void main(String[] args){

        Piece piece = new Knight (0,0,true);

        char nickname = piece.getNickName();
        System.out.println("Nickname: " + nickname);
    }
    
}
