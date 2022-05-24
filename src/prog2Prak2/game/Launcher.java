package prog2Prak2.game;

import prog2Prak2.entities.MasterSquirrel;
import prog2Prak2.entities.MasterSquirrelBot;
import java.util.Timer;
import java.util.TimerTask;
import HandOperated.HandOperatedFactory;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {

	FxUI userInterface2;

	public static void main(String[] args) {
		launch(args);

	}

	public void startGame() {
		Timer gameLoopTimer = new Timer("GameLoopTimer");
		int timer = 500;
		// UI userInterface = new ConsoleUI();
		
		HandOperatedFactory squirrelFactory = new HandOperatedFactory();
		MasterSquirrel masterSquirrel = new MasterSquirrelBot(1,1, squirrelFactory);
		MasterSquirrel [] squirrelList = {masterSquirrel};
		
		//HandOperatedMasterSquirrel handOpMs = new HandOperatedMasterSquirrel(1, 1);
		Board board = new Board(squirrelList);
		State state = new State(board);
		Game game = new GameImpl(state, userInterface2, timer, squirrelFactory);

		try {
			gameLoopTimer.schedule(new TimerTask() {

				@Override
				public void run() {
					game.run();
				}

			}, timer);
		} catch (Exception e) {
 
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		userInterface2 = new FxUI();

		startGame();

		primaryStage.onCloseRequestProperty().set(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent e) {
				System.exit(0);

			}

		});
		Scene scene = new Scene(userInterface2.getBorderPane(), 1000, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Squirrel Game");
		primaryStage.show();

	}

}