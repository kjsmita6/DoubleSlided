package DoubleSlided;

import javafx.scene.layout.GridPane;

import java.util.Hashtable;

public class Board {
	static Piece[][] pieces = new Piece[3][3];
	static Coordinate emptyTile;
	static GridPane gridPane;
	static Hashtable<Integer, Integer> numbers;
	static int moves;

	public static void generateInitialBoard() {
		gridPane.getChildren().clear();
		numbers = new Hashtable<Integer, Integer>();
		moves = 0;
		pieces[0][0] = new Piece(Color.GRAY, 1, new Coordinate(0, 0));
		pieces[0][1] = new Piece(Color.GRAY, 2, new Coordinate(0, 1));

		emptyTile = new Coordinate(0, 2);
		pieces[0][2] = null;

		pieces[1][0] = new Piece(Color.BLACK, 4, new Coordinate(1, 0));
		pieces[1][1] = new Piece(Color.BLACK, 1, new Coordinate(1, 1));
		pieces[1][2] = new Piece(Color.GRAY, 2, new Coordinate(1, 2));

		pieces[2][0] = new Piece(Color.GRAY, 3, new Coordinate(2, 0));
		pieces[2][1] = new Piece(Color.GRAY, 4, new Coordinate(2, 1));
		pieces[2][2] = new Piece(Color.GRAY, 3, new Coordinate(2, 2));

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(pieces[i][j] != null) {
					drawPiece(pieces[i][j]);
				}
			}
		}
	}

	public static void addNumber(int key) {
		if(numbers.containsKey(key)) {
			int val = numbers.get(key);
//			numbers.remove(key);
//			numbers.put(key, val + 1);
			numbers.replace(key, val + 1);
		}
		else {
			numbers.put(key, 1);
		}
	}

	public static void restartGame() {
		gridPane.getChildren().clear();
		generateInitialBoard();
	}

	public static Coordinate getBoardCoords(double x, double y) {
		int retX = -1;
		int retY = -1;
		if(0 <= x && x <= 154) {
			retX = 0;
		}
		else if(154 < x && x <= 308) {
			retX = 1;
		}
		else if(309 < x && x <= 463) {
			retX = 2;
		}
		else {
			return null;
		}

		if(0 <= y && y <= 138) {
			retY = 0;
		}
		else if(139 < y && y <= 278) {
			retY = 1;
		}
		else if(279 < y && y <= 417) {
			retY = 2;
		}
		else {
			return null;
		}

		Coordinate ret = new Coordinate(retX, retY);
		if(ret.equals(emptyTile)) {
			return null;
		}
		return new Coordinate(retX, retY);
	}

	public static void drawPiece(Piece piece) {
		//Controller.addButton(piece.getButton(), piece.getCoords());
		gridPane.add(piece.button, piece.coords.getX(), piece.coords.getY());
		addNumber(piece.number);
	}

	public static boolean checkWon() {
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

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Piece check = winCond[i][j];
				Piece actual = pieces[i][j];
				if(check != null && actual != null) {
					if (check.number != actual.number || check.color != actual.color) {
						return false;
					}
				}
			}
		}
		if(Board.emptyTile.equals(new Coordinate(1, 1))) {
			return true;
		}
		else {
			return false;
		}
	}
}
