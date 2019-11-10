package DoubleSlided;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
		primaryStage.setTitle("CS 3733 Individual Project by Kyle Smith (kjsmith@wpi.edu)");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

		GridPane pane = (GridPane)primaryStage.getScene().lookup("#gridPane");
		AnchorPane anchor = (AnchorPane)primaryStage.getScene().lookup("#gridSide");
		Label moveCounter = (Label)primaryStage.getScene().lookup("#moveCounter");
		Button resetButton = (Button)primaryStage.getScene().lookup("#resetButton");
		SplitPane split = (SplitPane)primaryStage.getScene().lookup("#splitPane");
		SplitPane.Divider div = split.getDividers().get(0);

		div.positionProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				div.setPosition(div.getPosition());
			}
		});

		Board.gridPane = pane;
		Board.generateInitialBoard();

		resetButton.setOnMousePressed(e -> {
			Board.restartGame();
			moveCounter.setText("Moves: 0");
		});

		anchor.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
			Coordinate coords = Board.getBoardCoords(e.getX(), e.getY());

				if(coords == null) {
					return;
				}
			Coordinate oldCoords = new Coordinate(coords.getX(), coords.getY());
			Piece oldPiece = Board.pieces[oldCoords.getX()][oldCoords.getY()];
			Piece newPiece = new Piece(oldPiece.swapColor(), Piece.TILE_NUM_TOTAL - oldPiece.number, Board.emptyTile);
			if (oldPiece.flip()) {
				Board.moves++;
				moveCounter.setText(String.format("Moves: %d", Board.moves));

				Board.pieces[oldCoords.getX()][oldCoords.getY()] = null; // remove old piece
				Board.pieces[newPiece.coords.getX()][newPiece.coords.getY()] = newPiece; // add new piece
				Board.gridPane.getChildren().clear();
				Board.numbers.clear();
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						if(Board.pieces[i][j] != null) {
							Board.drawPiece(Board.pieces[i][j]);
							if(Board.numbers.get(Board.pieces[i][j].number) >= 4) {
								Alert lose = new Alert(Alert.AlertType.CONFIRMATION);
								lose.setContentText("You lost the game. Restart?");
								Optional<ButtonType> result = lose.showAndWait();
								if(result.isPresent() && result.get() == ButtonType.OK) {
									Board.restartGame();
									moveCounter.setText(String.format("Moves: %d", Board.moves));
									return; // TODO: If this doesn't work, create bool for lost and check it before entering bottom code
								}
								else {
									primaryStage.close();
									return;
								}
							}
						}
					}
				}

				Board.emptyTile = oldCoords; // set emptyTile to old location
				if(Board.checkWon()) {
					Alert won = new Alert(Alert.AlertType.INFORMATION);
					won.setContentText("You won the game!!!!");
					Optional<ButtonType> result = won.showAndWait();
					if(result.isPresent() && result.get() == ButtonType.OK) {
						primaryStage.close();
						return;
					}
				}
			}
		});
    }

    public static void main(String[] args) {
        launch(args);
    }
}
