package erikbissell.com.WebChess;
import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/chess")


public class ChessController {
   
    private Chessboard chessboard;
    private EnPassantExplorerAI engine = new EnPassantExplorerAI();
    public ChessController(){
        this.chessboard = new Chessboard();
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/board")
    public Piece[][] getChessboard(){
        Piece[][] chessboardData = chessboard.getBoard();
        //System.out.println("Chessboard Data: " + Arrays.deepToString(chessboardData));
        return chessboardData;
    }
    @PostMapping("/move")
    public ResponseEntity<String> movePiece(@RequestBody MoveRequest moveRequest) {
    //  System.out.println("made it to movePiece java");
      int sourceRow = moveRequest.getSourceRow();
      int sourceCol = moveRequest.getSourceCol();
      int destRow = moveRequest.getDestRow();
      int destCol = moveRequest.getDestCol();
        try {
          if(chessboard.movePiece(sourceRow, sourceCol, destRow, destCol)){
            chessboard.checkLastMove();
            //BestMove bestMove = engine.miniMax(chessboard,3);
            /* if(bestMove != null && bestMove.getMove() != null){
              if(chessboard.movePiece(bestMove.getMove().getSourceRow(), bestMove.getMove().getSourceCol(), bestMove.getMove().getDestRow(), bestMove.getMove().getDestCol())){
                chessboard.checkLastMove();
              } 
              else{
                return ResponseEntity.badRequest().body("Invalid Computer Move");
              } 
              //bestMove.getMove().printMove();
            }
            else{
              System.out.println("bestmove is null");
            }  */
           
            return ResponseEntity.ok("Success");
          }
          else{
            return ResponseEntity.badRequest().body("Invalid Move");
          }
        }
        catch(Exception e){
          return ResponseEntity.status(500).body("Internal server error");
        }

    }
}
