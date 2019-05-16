package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GenericGraph;

public class Main extends Application {

	public static void main(String[] args) {

		launch();

	}

	@Override
	public void start(Stage window) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/StartingWindowView.fxml"));
		Scene scene = new Scene(loader.load());
		window.setScene(scene);
		window.show();

	}

}
