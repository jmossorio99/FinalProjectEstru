package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import exceptions.VertexDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.AdjacencyListGraph;
import model.AdjacencyMatrixGraph;
import javafx.fxml.Initializable;
import model.Edge;
import model.Vertex;

public class MainWindowController implements Initializable{

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
    void allCityButtonPressed(ActionEvent event) {

    }

    @FXML
    void goButtonPressed(ActionEvent event) {

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
		if(colombianMapAdj == null) {
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
			colombianMapAdj.insertEdge(0, 2, 270000);
			colombianMapAdj.insertEdge(0, 3, 240000);
			colombianMapAdj.insertEdge(0, 4, 300000);
			colombianMapAdj.insertEdge(1, 3, 280000);
			colombianMapAdj.insertEdge(2,3, 100000);
			colombianMapAdj.insertEdge(2, 4, 200000);
			colombianMapAdj.insertEdge(3, 5, 230000);
			colombianMapAdj.insertEdge(3, 6, 210000);
			colombianMapAdj.insertEdge(3, 7, 310000);
			colombianMapAdj.insertEdge(3, 8, 285000);
		}
		if(colombianMapMat == null) {
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
			colombianMapAdj.insertEdge(0, 2, 270000);
			colombianMapAdj.insertEdge(0, 3, 240000);
			colombianMapAdj.insertEdge(0, 4, 300000);
			colombianMapAdj.insertEdge(1, 3, 280000);
			colombianMapAdj.insertEdge(2,3, 100000);
			colombianMapAdj.insertEdge(2, 4, 200000);
			colombianMapAdj.insertEdge(3, 5, 230000);
			colombianMapAdj.insertEdge(3, 6, 210000);
			colombianMapAdj.insertEdge(3, 7, 310000);
			colombianMapAdj.insertEdge(3, 8, 285000);
		}
		if(elected == 0) {
			ArrayList<Vertex<String>> cities = colombianMapAdj.getVertices();
			ArrayList<Integer> drawn = new ArrayList<Integer>();
			for(Vertex<String> city : cities) {
				Circle circle = new Circle();
				circle.setLayoutX(city.getxCoordinate());
				circle.setLayoutY(city.getyCoordinate());
				circle.setRadius(4);
				circle.setFill(Color.DODGERBLUE);
				circle.setVisible(true);
				circle.setOnMouseEntered( e -> {
					cityFocused(circle.getLayoutX(), circle.getLayoutY());
				});
				drawingPane.getChildren().add(circle);
				for(Edge<String> edge : city.getAdjacencyList()) {
					if(!drawn.contains(edge.getId())) {
						Line line = new Line();
						line.setStroke(Color.DODGERBLUE);
						line.setStartX(edge.getVertexFrom().getxCoordinate());
						line.setStartY(edge.getVertexFrom().getyCoordinate());
						line.setEndX(edge.getVertexTo().getxCoordinate());
						line.setEndY(edge.getVertexTo().getyCoordinate());
						drawingPane.getChildren().add(line);
					}
				}
			}
			
		}else {
			ArrayList<Vertex<String>> cities = colombianMapMat.getVertices();
			ArrayList<Integer> drawn = new ArrayList<Integer>();
			for(Vertex<String> city : cities) {
				Circle circle = new Circle();
				circle.setLayoutX(city.getxCoordinate());
				circle.setLayoutY(city.getyCoordinate());
				circle.setRadius(4);
				circle.setFill(Color.DODGERBLUE);
				circle.setVisible(true);
				circle.setOnMouseEntered(e -> {
					cityFocused(circle.getLayoutX(), circle.getLayoutY());
				}
						);
				for(Edge<String> edge : city.getAdjacencyList()) {
					if(!drawn.contains(edge.getId())) {
						Line line = new Line();
						line.setStroke(Color.DODGERBLUE);
						line.setStartX(edge.getVertexFrom().getxCoordinate());
						line.setStartY(edge.getVertexFrom().getyCoordinate());
						line.setEndX(edge.getVertexTo().getxCoordinate());
						line.setEndY(edge.getVertexTo().getyCoordinate());
						drawingPane.getChildren().add(line);
					}
				}
				drawingPane.getChildren().add(circle);
			}
		}
	}

	private void cityFocused(double x, double y) {
		ArrayList<Vertex<String>> cities = null;
		if(elected == 0) {
			cities = colombianMapAdj.getVertices();
			for(Vertex<String> city : cities) {
				if(x == city.getxCoordinate() && y == city.getyCoordinate()) {
					cityLabel.setText(city.getValue());
				}
			}
		}else {
			cities = colombianMapMat.getVertices();
			for(Vertex<String> city : cities) {
				if(x == city.getxCoordinate() && y == city.getyCoordinate()) {
					cityLabel.setText(city.getValue());
				}
			}
		}
	}

	
}
