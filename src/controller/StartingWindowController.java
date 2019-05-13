package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class StartingWindowController implements Initializable {

	ObservableList<String> OL = FXCollections.observableArrayList("Time", "Money", "Distance");

	@FXML
	private Button SearchButton;

	@FXML
	private ChoiceBox<String> filterChoiceBox;

	@FXML
	private ChoiceBox<String> originChoiceBox;

	@FXML
	private ChoiceBox<String> destinyChoiceBox;

	@FXML
	private Label totalLabel;

	@FXML
	void searchClicked(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		filterChoiceBox.setItems(OL);

	}

}
