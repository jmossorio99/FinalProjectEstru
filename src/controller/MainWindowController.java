package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import exceptions.VertexDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import model.AdjacencyListGraph;
import model.AdjacencyMatrixGraph;
import javafx.fxml.Initializable;
import model.Edge;
import model.Vertex;

public class MainWindowController implements Initializable {

	@FXML
	private TextField fromTextField;

	@FXML
	private TextField toTextField;

	@FXML
	private Label cityLabel;
	@FXML
	private Pane drawingPane;
	private AdjacencyListGraph<String> colombianMapAdj;
	private AdjacencyMatrixGraph<String> colombianMapMat;
	private int elected;

	@FXML
	void goButtonPressed(ActionEvent event) {

	}

	@FXML
	void allCityButtonPressed(ActionEvent event) {
		if(elected == 0) {
			try {
				AdjacencyListGraph<String> kruskal = colombianMapAdj.kruskal();
				ArrayList<Vertex<String>> cities = kruskal.getVertices();
				ArrayList<Integer> drawn = new ArrayList<Integer>();
				for (Vertex<String> city : cities) {
					for (Edge<String> edge : city.getAdjacencyList()) {
						if (!drawn.contains(edge.getId())) {
							Line line = new Line();
							line.setStroke(Color.TOMATO);
							line.setStartX(edge.getVertexFrom().getxCoordinate());
							line.setStartY(edge.getVertexFrom().getyCoordinate());
							line.setEndX(edge.getVertexTo().getxCoordinate());
							line.setEndY(edge.getVertexTo().getyCoordinate());
							Label label = new Label("" + edge.getData());
							label.setLayoutX(((line.getStartX() + line.getEndX()) / 2)-10);
							label.setLayoutY(((line.getStartY() + line.getEndY()) / 2)-10);
							drawingPane.getChildren().addAll(line, label);
							drawn.add(edge.getId());
						}
					}
				}
			} catch (VertexDoesNotExistException e) {
				e.printStackTrace();
			}
		}else {
			try {
				AdjacencyListGraph<String> kruskal = colombianMapMat.kruskal();
				ArrayList<Vertex<String>> cities = kruskal.getVertices();
				ArrayList<Integer> drawn = new ArrayList<Integer>();
				for (Vertex<String> city : cities) {
					for (Edge<String> edge : city.getAdjacencyList()) {
						if (!drawn.contains(edge.getId())) {
							Line line = new Line();
							line.setStroke(Color.TOMATO);
							line.setStartX(edge.getVertexFrom().getxCoordinate());
							line.setStartY(edge.getVertexFrom().getyCoordinate());
							line.setEndX(edge.getVertexTo().getxCoordinate());
							line.setEndY(edge.getVertexTo().getyCoordinate());
							Label label = new Label("" + edge.getData());
							label.setLayoutX(((line.getStartX() + line.getEndX()) / 2)-10);
							label.setLayoutY(((line.getStartY() + line.getEndY()) / 2)-10);
							drawingPane.getChildren().addAll(line, label);
							drawn.add(edge.getId());
						}
					}
				}
			} catch (VertexDoesNotExistException e) {
				e.printStackTrace();
			}
		}
	}

	public void setElected(int elected) {
		this.elected = elected;
		try {
			initialConditions();
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	private void initialConditions() throws VertexDoesNotExistException {
		if (colombianMapAdj == null) {
			colombianMapAdj = new AdjacencyListGraph<>(false);
			colombianMapAdj.insertVertex("Cali", 84, 337);
			colombianMapAdj.insertVertex("Leticia", 285, 609);
			colombianMapAdj.insertVertex("Medellin", 112, 229);
			colombianMapAdj.insertVertex("Bogota", 158, 290);
			colombianMapAdj.insertVertex("Cartagena", 114, 88);
			colombianMapAdj.insertVertex("Valledupar", 180, 82);
			colombianMapAdj.insertVertex("Cucuta", 205, 179);
			colombianMapAdj.insertVertex("Neiva", 120, 356);
			colombianMapAdj.insertVertex("Santa Marta", 156, 53);
			colombianMapAdj.insertEdge(0, 2, 2.7);
			colombianMapAdj.insertEdge(0, 3, 2.4);
			colombianMapAdj.insertEdge(0, 4, 3);
			colombianMapAdj.insertEdge(1, 3, 2.8);
			colombianMapAdj.insertEdge(2, 3, 1);
			colombianMapAdj.insertEdge(2, 4, 2);
			colombianMapAdj.insertEdge(3, 5, 2.3);
			colombianMapAdj.insertEdge(3, 6, 2.1);
			colombianMapAdj.insertEdge(3, 7, 3.1);
			colombianMapAdj.insertEdge(3, 8, 2.85);
		}
		if (colombianMapMat == null) {
			colombianMapMat = new AdjacencyMatrixGraph<>(false);
			colombianMapMat.insertVertex("Cali", 84, 337);
			colombianMapMat.insertVertex("Leticia", 285, 609);
			colombianMapMat.insertVertex("Medellin", 112, 229);
			colombianMapMat.insertVertex("Bogota", 158, 290);
			colombianMapMat.insertVertex("Cartagena", 114, 88);
			colombianMapMat.insertVertex("Valledupar", 180, 82);
			colombianMapMat.insertVertex("Cucuta", 205, 179);
			colombianMapMat.insertVertex("Neiva", 120, 356);
			colombianMapMat.insertVertex("Santa Marta", 156, 53);
			colombianMapAdj.insertEdge(0, 2, 2.7);
			colombianMapAdj.insertEdge(0, 3, 2.4);
			colombianMapAdj.insertEdge(0, 4, 3);
			colombianMapAdj.insertEdge(1, 3, 2.8);
			colombianMapAdj.insertEdge(2, 3, 1);
			colombianMapAdj.insertEdge(2, 4, 2);
			colombianMapAdj.insertEdge(3, 5, 2.3);
			colombianMapAdj.insertEdge(3, 6, 2.1);
			colombianMapAdj.insertEdge(3, 7, 3.1);
			colombianMapAdj.insertEdge(3, 8, 2.85);
		}
		if (elected == 0) {
			ArrayList<Vertex<String>> cities = colombianMapAdj.getVertices();
			ArrayList<Integer> drawn = new ArrayList<Integer>();
			for (Vertex<String> city : cities) {
				Circle circle = new Circle();
				circle.setLayoutX(city.getxCoordinate());
				circle.setLayoutY(city.getyCoordinate());
				circle.setRadius(7);
				circle.setFill(Color.DODGERBLUE);
				circle.setVisible(true);
				drawingPane.getChildren().add(circle);
				circle.setOnMouseEntered(mouseHandler);
				drawingPane.setOnMouseClicked(mouseHandler);
				for (Edge<String> edge : city.getAdjacencyList()) {
					if (!drawn.contains(edge.getId())) {
						Line line = new Line();
						line.setStroke(Color.DODGERBLUE);
						line.setStartX(edge.getVertexFrom().getxCoordinate());
						line.setStartY(edge.getVertexFrom().getyCoordinate());
						line.setEndX(edge.getVertexTo().getxCoordinate());
						line.setEndY(edge.getVertexTo().getyCoordinate());
						Label label = new Label("" + edge.getData());
						label.setLayoutX(((line.getStartX() + line.getEndX()) / 2)-10);
						label.setLayoutY(((line.getStartY() + line.getEndY()) / 2)-10);
						drawingPane.getChildren().addAll(line, label);
						drawn.add(edge.getId());
					}
				}
			}
		} else {
			ArrayList<Vertex<String>> cities = colombianMapMat.getVertices();
			ArrayList<Integer> drawn = new ArrayList<Integer>();
			for (Vertex<String> city : cities) {
				Circle circle = new Circle();
				circle.setLayoutX(city.getxCoordinate());
				circle.setLayoutY(city.getyCoordinate());
				circle.setRadius(7);
				circle.setFill(Color.DODGERBLUE);
				circle.setVisible(true);
				drawingPane.getChildren().add(circle);
				circle.setOnMouseEntered(mouseHandler);
				drawingPane.setOnMouseClicked(mouseHandler);
				for (Edge<String> edge : city.getAdjacencyList()) {
					if (!drawn.contains(edge.getId())) {
						Line line = new Line();
						line.setStroke(Color.DODGERBLUE);
						line.setStartX(edge.getVertexFrom().getxCoordinate());
						line.setStartY(edge.getVertexFrom().getyCoordinate());
						line.setEndX(edge.getVertexTo().getxCoordinate());
						line.setEndY(edge.getVertexTo().getyCoordinate());
						Label label = new Label("" + edge.getData());
						label.setLayoutX(((line.getStartX() + line.getEndX()) / 2)-10);
						label.setLayoutY(((line.getStartY() + line.getEndY()) / 2)-10);
						drawingPane.getChildren().addAll(line, label);
						drawn.add(edge.getId());
					}
				}
			}
		}
	}

	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (event.getSource().equals(drawingPane)) {
				createCityPressed(event);
			} else {
				Circle circle = (Circle) event.getSource();
				cityFocused(circle.getLayoutX(), circle.getLayoutY());
			}
		}
	};

	private void cityFocused(double x, double y) {
		ArrayList<Vertex<String>> cities = null;
		if (elected == 0) {
			cities = colombianMapAdj.getVertices();
			for (Vertex<String> city : cities) {
				if (x == city.getxCoordinate() && y == city.getyCoordinate()) {
					cityLabel.setText(city.getValue());
				}
			}
		} else {
			cities = colombianMapMat.getVertices();
			for (Vertex<String> city : cities) {
				if (x == city.getxCoordinate() && y == city.getyCoordinate()) {
					cityLabel.setText(city.getValue());
				}
			}
		}
	}

	@FXML
	void reload(){
		if(elected == 0) {
			elected = 1;
			drawingPane.getChildren().clear();
			try {
				initialConditions();
			} catch (VertexDoesNotExistException e) {
				e.printStackTrace();
			}
		}else {
			elected = 0;
			try {
				initialConditions();
				drawingPane.getChildren().clear();
			} catch (VertexDoesNotExistException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	void createCityPressed(MouseEvent event) {
		double x = event.getX();
		double y = event.getY();
		String name = "";
		TextInputDialog dialog = new TextInputDialog("Name...");
		dialog.setTitle("New City");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter the name of the city:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			name = result.get();
			colombianMapAdj.insertVertex(name, x, y);
			colombianMapMat.insertVertex(name, x, y);
			Circle circle = new Circle();
			circle.setLayoutX(x);
			circle.setLayoutY(y);
			circle.setRadius(7);
			circle.setFill(Color.DODGERBLUE);
			circle.setVisible(true);
			circle.setOnMouseEntered(mouseHandler);
			drawingPane.getChildren().add(circle);
		}
	}

}
