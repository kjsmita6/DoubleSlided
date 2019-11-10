package DoubleSlided;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;

public class Piece {
	static final int TILE_NUM_TOTAL = 5;

	Color color;
	int number;
	Button button;
	Coordinate coords;

	public Piece(Color color, int number, Coordinate coords) {
		this.color = color;
		this.number = number;
		this.button = createButton();
		this.coords = coords;
	}

	public boolean flip() {
		if(isAdjacentTo(new Piece(null, 0, Board.emptyTile))) {
			this.color = swapColor();
			this.number = TILE_NUM_TOTAL - this.number;
			this.coords = Board.emptyTile;
			this.button = createButton();
			return true;
		}
		return false;
	}

	public Color swapColor() {
		return color == Color.BLACK ? Color.GRAY : Color.BLACK;
	}

	public Button createButton() {
		Button button = new Button(Integer.toString(this.number));
		if(color == Color.BLACK) {
			button.setFont(new Font(48));
			button.setTextFill(javafx.scene.paint.Color.WHITE);
		}
		else {
			button.setFont(new Font(48));
		}
		button.setBackground(new Background(new BackgroundFill(convertToSceneColor(), CornerRadii.EMPTY, Insets.EMPTY)));
		button.setMinHeight(138.667);
		button.setMinWidth(154.667);
		return button;
	}

	public javafx.scene.paint.Color convertToSceneColor() {
		return this.color == Color.BLACK ? javafx.scene.paint.Color.BLACK : javafx.scene.paint.Color.GRAY;
	}

	public boolean isAdjacentTo(Piece piece) {
		return !this.coords.equals(piece.coords) && (Math.sqrt(Math.pow(this.coords.getY() - piece.coords.getY(), 2) + Math.pow(this.coords.getX() - piece.coords.getX(), 2))) == 1;
	}
}
