package DoubleSlided;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

	static Piece test;

	@BeforeEach
	void setup() {
		test = new Piece(Color.BLACK, 1, new Coordinate(0, 0));
		Board.emptyTile = new Coordinate(0, 1);
	}

	@Test
	void flip() {
		test.flip();

		assertEquals(Color.GRAY, test.color);
		assertEquals(4, test.number);
		assertTrue(test.coords.equals(Board.emptyTile));
		assertFalse(test.coords.equals(test));


		Board.emptyTile = new Coordinate(2,2);
		assertFalse(test.flip());
	}

	@Test
	void swapColor() {
		test.color = Color.BLACK;
		assertEquals(Color.GRAY, test.swapColor());

		test.color = Color.GRAY;
		assertEquals(Color.BLACK, test.swapColor());
	}

	@Test
	void convertToSceneColor() {
		test.color = Color.BLACK;
		assertEquals(test.convertToSceneColor(), javafx.scene.paint.Color.BLACK);

		test.color = Color.GRAY;
		assertEquals(test.convertToSceneColor(), javafx.scene.paint.Color.GRAY);
	}

	@Test
	void isAdjacentTo() {
		Piece sameCoords = new Piece(Color.BLACK, 1, new Coordinate(0, 0));
		Piece adjacent = new Piece(Color.BLACK, 1, new Coordinate(1, 0));
		Piece diagonal = new Piece(Color.BLACK, 1, new Coordinate(1, 1));

		assertFalse(test.isAdjacentTo(sameCoords));
		assertTrue(test.isAdjacentTo(adjacent));
		assertFalse(test.isAdjacentTo(diagonal));
	}
}
