package prog2Prak2.game;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prog2Prak2.SquirrelCommandReader.Command;
import prog2Prak2.entities.XY;

public class FxUI implements UI {

	private GraphicsContext graphicsContext;
	private Canvas gameFieldCanvas;
	private BorderPane borderPane;
	private GUIEventHandler guiEventHandler;

	Label statusBarLabel;
	MenuBar menuBar;
	VBox leftVBox;
	VBox rightVBox;
	HBox statusBar;
	Pane gameField;
	BoardView view;
	
	Button pauseButton;
	Button resumeButton;
	MenuItem pauseMenuItem;
	MenuItem resumeMenuItem;

	public FxUI() {
		menuBar = createMenuBar();
		leftVBox = createLeftVBox();
		rightVBox = createRightVBox();
		statusBar = createStatusBar("Game currently playing...");
		gameField = createGameField();

		borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setLeft(leftVBox);
		borderPane.setRight(rightVBox);
		borderPane.setCenter(gameField);
		borderPane.setBottom(statusBar);

		guiEventHandler = new GUIEventHandler();

		borderPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				guiEventHandler.getSquirrelMoveCommand(e);
			}
		});

	}

	public Pane getBorderPane() {
		return this.borderPane;
	}

	private MenuBar createMenuBar() {
		pauseMenuItem = new MenuItem("Pause");
		pauseMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));

		resumeMenuItem = new MenuItem("Resume");
		resumeMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
		resumeMenuItem.setDisable(true);

		MenuItem allMenuItem = new MenuItem("All");
		allMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));

		MenuItem helpMenuItem = new MenuItem("Help");
		helpMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));

		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));

		Menu fileMenu = new Menu("File");
		fileMenu.getItems().addAll(pauseMenuItem, resumeMenuItem, allMenuItem, new SeparatorMenuItem(), helpMenuItem, exitMenuItem);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);

		pauseMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				pauseMenuItem.setDisable(true);
				pauseButton.setDisable(true);
				resumeMenuItem.setDisable(false);
				resumeButton.setDisable(false);
				
				guiEventHandler.actionEventHandler(e);

			}

		});

		resumeMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				resumeMenuItem.setDisable(true);
				resumeButton.setDisable(true);
				pauseMenuItem.setDisable(false);
				pauseButton.setDisable(false);
				guiEventHandler.actionEventHandler(e);

			}

		});

		allMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				guiEventHandler.actionEventHandler(e);

			}

		});

		helpMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				guiEventHandler.actionEventHandler(e);

			}

		});

		exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				guiEventHandler.actionEventHandler(e);

			}

		});

		return menuBar;
	}

	private VBox createLeftVBox() {
		pauseButton = new Button("Pause");

		resumeButton = new Button("Resume");
		resumeButton.setDisable(true);
		//resumeButton.disableProperty().bind(menuBar.getMenus().get(0).getItems().get(1).disableProperty());
		pauseButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				pauseButton.setDisable(true);
				pauseMenuItem.setDisable(true);
				resumeButton.setDisable(false);
				resumeMenuItem.setDisable(false);
				guiEventHandler.actionEventHandler(e);

			}

		});

		resumeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				pauseButton.setDisable(false);
				pauseMenuItem.setDisable(false);
				resumeButton.setDisable(true);
				resumeMenuItem.setDisable(true);
				guiEventHandler.actionEventHandler(e);

			}

		});

		Label controlLabel = new Label("Controls");
		controlLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));

		VBox controlVBox = new VBox();
		controlVBox.setPadding(new Insets(20, 0, 20, 0));
		controlVBox.getChildren().addAll(controlLabel, pauseButton, resumeButton);

		Label legendLabel = new Label("Legend");
		legendLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
		Label emptyLabel = new Label("Empty");
		Label bbLabel = new Label("BadBeast");
		Label bpLabel = new Label("BadPlant");
		Label gbLabel = new Label("GoodBeast");
		Label gpLabel = new Label("GoodPlant");
		Label masqLabel = new Label("MasterSquirrel");
		Label misqLabel = new Label("MiniSquirrel");
		Label wLabel = new Label("Wall");
		Label handOpMsLabel = new Label("HandOperatedMasterSquirrel");

		VBox entityBox = new VBox();
		entityBox.getChildren().addAll(legendLabel, emptyLabel, bbLabel, bpLabel, gbLabel, gpLabel, masqLabel,
				misqLabel, wLabel, handOpMsLabel);

		Label emptySymbol = new Label("⬛");
		emptySymbol.setTextFill(Color.WHITE);
		Label bbSymbol = new Label("⬛");
		bbSymbol.setTextFill(Color.RED);
		Label bpSymbol = new Label("⬤");
		bpSymbol.setTextFill(Color.RED);
		Label gbSymbol = new Label("⬛");
		gbSymbol.setTextFill(Color.GREEN);
		Label gpSymbol = new Label("⬤");
		gpSymbol.setTextFill(Color.GREEN);
		Label masqSymbol = new Label("⬛");
		masqSymbol.setTextFill(Color.BLACK);
		Label misqSymbol = new Label("⬛");
		misqSymbol.setTextFill(Color.LIGHTBLUE);
		Label wSymbol = new Label("⬛");
		wSymbol.setTextFill(Color.ORANGE);
		Label handOpMsSymbol = new Label("⬛");
		handOpMsSymbol.setTextFill(Color.BLUE);

		VBox symbolBox = new VBox();
		symbolBox.getChildren().addAll(emptySymbol, bbSymbol, bpSymbol, gbSymbol, gpSymbol, masqSymbol, misqSymbol,
				wSymbol, handOpMsSymbol);

		HBox symbolEntityBox = new HBox();
		symbolEntityBox.getChildren().addAll(symbolBox, entityBox);

		VBox completeLegendBox = new VBox();
		completeLegendBox.getChildren().addAll(legendLabel, symbolEntityBox);

		TextField textField = new TextField("100");
		Label spawnMiniLabel = new Label("Spawn MiniSquirrel");
		Button spinbotButton = new Button("Become Immortal");
		spinbotButton.setOnAction(new EventHandler<ActionEvent> () {

			@Override
			public void handle(ActionEvent arg0) {
				guiEventHandler.godMode();
				
			}
			
		});
		
		
		Button spawnMiniButton = new Button("Spawn!");
	
		spawnMiniButton.setOnAction(new EventHandler<ActionEvent> () {

			@Override
			public void handle(ActionEvent e) {
				guiEventHandler.miniSpawner(textField.getText());
				
				
			}
			
		});
		
		VBox miniSquirrelVBox = new VBox();
		miniSquirrelVBox.setPadding(new Insets(20, 0, 20, 0));
		miniSquirrelVBox.setSpacing(5);
		miniSquirrelVBox.getChildren().addAll(spawnMiniLabel, textField, spawnMiniButton, spinbotButton);
		
		VBox controlLegendBox = new VBox();
		controlLegendBox.getChildren().addAll(controlVBox, completeLegendBox);

		VBox leftVBox = new VBox();
		leftVBox.getChildren().addAll(controlLegendBox, miniSquirrelVBox);
		
		return leftVBox;

	}

	private Pane createGameField() { 
		gameFieldCanvas = new Canvas();
		Pane gameFieldCanvasPane = new Pane();
		gameFieldCanvasPane.setPrefSize(1500, 1500);
		gameFieldCanvasPane.getChildren().add(gameFieldCanvas);
		gameFieldCanvas.widthProperty().bind(gameFieldCanvasPane.widthProperty());
		gameFieldCanvas.heightProperty().bind(gameFieldCanvasPane.heightProperty());
		gameFieldCanvasPane.setStyle("-fx-background-color: white");
		this.graphicsContext = gameFieldCanvas.getGraphicsContext2D();

		return gameFieldCanvasPane;
	}

	private HBox createStatusBar(String message) {
		HBox statusHBox = new HBox();
		this.statusBarLabel = new Label(message);
		statusHBox.getChildren().add(statusBarLabel);
		return statusHBox;
	}


	@Override
	public Command getCommand() {
		return guiEventHandler.getCommand();
	}

	@Override
	public void render(BoardView view) {
		this.view = view;
		EntityType entityType;
		XY boardSize = view.getSize();
		double portionX = gameFieldCanvas.getWidth() / boardSize.getX();
		double portionY = gameFieldCanvas.getHeight() / boardSize.getY();
		graphicsContext.clearRect(0, 0, gameFieldCanvas.getWidth(), gameFieldCanvas.getHeight());

		for (int y = 0; y < boardSize.getY(); y++) {
			for (int x = 0; x < boardSize.getX(); x++) {
				entityType = view.getEntityType(x, y);
				graphicsContext.setFill(entityType.getColor());
				if (entityType.getIsCircle() == 0) {
					graphicsContext.fillRect(x * portionX, y * portionY, portionX, portionY);
				} else if (entityType.getIsCircle() == 1) {
					graphicsContext.fillOval(x * portionX, y * portionY, portionX, portionY);
				}
			}
		}

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				borderPane.setRight(createRightVBox());
			}
		});

	}
	
	private VBox createRightVBox() { 
		VBox rightVBox = new VBox();
		
		if (this.view != null) {
			String[] squirrelList = view.getSquirrelAndEnergy();
			for (String sqNameEner : squirrelList) {
				rightVBox.getChildren().add(new Label(sqNameEner));
			}
		}

		return rightVBox;

	}

	public void specifyStatusBar(String message) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				statusBarLabel.setText(message);
			}
		});
	}
	

}
