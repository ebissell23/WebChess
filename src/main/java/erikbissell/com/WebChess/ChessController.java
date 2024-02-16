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
        return chessboardData;
    }
    @PostMapping("/move")
    public ResponseEntity<String> movePiece(@RequestBody MoveRequest moveRequest) {
      int sourceRow = moveRequest.getSourceRow();
      int sourceCol = moveRequest.getSourceCol();
      int destRow = moveRequest.getDestRow();
      int destCol = moveRequest.getDestCol();
      //TODO: add while loop so user can keep trying moves if their original attempt is invalid.
      //currently they need to refresh the page to do so
        try {
          //makes user move
          if(chessboard.movePiece(sourceRow, sourceCol, destRow, destCol)){
            chessboard.checkLastMove(); //checks last user move for mate
            //TODO: allow user to set engine depth before the game begins
            BestMove bestMove = engine.miniMax(chessboard,3);
            //performs engine move
             if(bestMove != null && bestMove.getMove() != null){
              if(chessboard.movePiece(bestMove.getMove().getSourceRow(), bestMove.getMove().getSourceCol(), bestMove.getMove().getDestRow(), bestMove.getMove().getDestCol())){
                chessboard.checkLastMove(); // checks last engine move for mate
              } 
              else{
                return ResponseEntity.badRequest().body("Invalid Computer Move");
              } 
            }
            else{
              //TODO: what do we do when engine move is null?
              System.out.println("bestmove is null");
            }  
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
