package DoubleSlided;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

	@BeforeAll
	static void setUp() {
		Main.main(null);
	}

	@Test
	void generateInitialBoard() {
		Board.generateInitialBoard();

		assertTrue(Board.emptyTile.equals(new Coordinate(0, 2)));
		assertEquals(8, Board.gridPane.getChildren().size());
	}

	@Test
	void addNumber() {
		Board.numbers = new Hashtable<Integer, Integer>();
		Board.numbers.put(1, 3);

		Board.addNumber(1);
		Board.addNumber(2);

		assertEquals(4, Board.numbers.get(1));
		assertEquals(1, Board.numbers.get(2));
	}

	@Test
	void getBoardCoords() {
		int x1 = 100;
		int y1 = 100;

		assertTrue(Board.getBoardCoords(x1, y1).equals(new Coordinate(0, 0)));

		int x2 = 500;
		int y2 = 500;

		assertTrue(Board.getBoardCoords(x2, y2) == null);
	}

	@Test
	void checkWon() {
		Piece[][] winCond = new Piece[3][3];
		winCond[0][0] = new Piece(Color.GRAY, 1, null);
		winCond[1][0] = new Piece(Color.GRAY, 2, null);
		winCond[2][0] = new Piece(Color.GRAY, 3, null);
		winCond[2][1] = new Piece(Color.GRAY, 4, null);
		winCond[2][2] = new Piece(Color.BLACK, 1, null);
		winCond[1][2] = new Piece(Color.BLACK, 2, null);
		winCond[0][2] = new Piece(Color.BLACK, 3, null);
		winCond[0][1] = new Piece(Color.BLACK, 4, null);
		winCond[1][1] = null;

		assertFalse(Board.checkWon());

		for (int i = 0; i < Board.pieces.length; i++) {
			for (int j = 0; j < Board.pieces.length; j++) {
				Board.pieces[i][j] = winCond[i][j];
			}
		}
		Board.emptyTile = new Coordinate(1, 1);

		assertTrue(Board.checkWon());

		Board.emptyTile = new Coordinate(0, 0);

		assertFalse(Board.checkWon());
	}

	@Test
	void restartGame() {
		Board.restartGame();

		generateInitialBoard();
	}
}
