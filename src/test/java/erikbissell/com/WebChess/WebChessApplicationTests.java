package erikbissell.com.WebChess;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class WebChessApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	void getChessBoard() throws Exception{

		mockMvc.perform(MockMvcRequestBuilders.get("/api/chess/board"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void contextLoads() {
	}
	//Tests for Pawn
	@Test
	void pawn_MoveThreeSpaces_NotValid(){
		Chessboard chessboard = new Chessboard();
		assertFalse(chessboard.movePiece(6,0,3,0));
	}
	@Test
	void pawn_MoveTwoSpaces_Valid(){
		Chessboard chessboard = new Chessboard();
		assertTrue(chessboard.movePiece(6,0,4,0));

	}
	

}
