package erikbissell.com.WebChess;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class WebChessApplicationTests {

	@Test
	void contextLoads() {
	}
	//Tests for Pawn
	@Test
	void pawnMoveThreeSpacesReturnsFalse(){
		Chessboard chessboard = new Chessboard();
		assertFalse(chessboard.movePiece(6,0,3,0));
	}
	@Test
	void pawnMoveTwoSpacesReturnsTrue(){
		Chessboard chessboard = new Chessboard();
		assertTrue(chessboard.movePiece(6,0,4,0));

	}

}
