package view;

import java.util.ArrayList;

import controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.AdjacencyListGraph;
import model.AdjacencyMatrixGraph;
import model.Vertex;

public class Main extends Application {

	
	
	public static void main(String[] args) {
		
		launch();

	}

	@Override
	public void start(Stage window) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/MainWindow.fxml"));
		Scene scene = new Scene(loader.load());
		MainWindowController controller = (MainWindowController) loader.getController();
		controller.setElected(0);
		window.setScene(scene);
		window.show();

	}

}
