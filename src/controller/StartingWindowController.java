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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StartingWindowController implements Initializable{

	ObservableList<String> OL = FXCollections.observableArrayList("Time","Money","Distance");
	
	@FXML
    private TextField OriginButton;

    @FXML
    private TextField DestinyButton;

    @FXML
    private Button SearchButton;

    @FXML
    private ChoiceBox<String> FilterChoiceBox;

    @FXML
    private TextArea RouteTextArea;

    @FXML
    private Label QuantityTextField;

    @FXML
    void searchClicked(ActionEvent event) {

    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
		FilterChoiceBox.setItems(OL);
		
		
	}

}
