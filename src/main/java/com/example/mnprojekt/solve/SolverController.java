package com.example.mnprojekt.solve;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.mnprojekt.MainApplication;
import com.example.mnprojekt.graph.Grapher;
import com.example.mnprojekt.methods.ConsolHandler;
import com.example.mnprojekt.methods.ODEIntegrator;
import com.example.mnprojekt.methods.PointTX;
import com.example.mnprojekt.methods.inter.ODEEquation;
import com.example.mnprojekt.methods.methodsChoice.Euler;
import com.example.mnprojekt.methods.inter.ODEStep;
import com.example.mnprojekt.methods.methodsChoice.EulerModified;
import com.example.mnprojekt.methods.methodsChoice.RungegoKutty;
import com.example.mnprojekt.methods.save.SaveToFileHandler;
import com.example.mnprojekt.table.TableController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SolverController {

    protected ObservableList<PointTX> list = FXCollections.observableArrayList();

    public ObservableList<PointTX> getList() {
        return list;
    }

    FileChooser fileChooser = new FileChooser();

    private ODEStep method;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField equationTextField;

    @FXML
    private RadioButton eulerButton;

    @FXML
    private RadioButton eulerModifiedButton;

    @FXML
    private LineChart<?, ?> graph;

    @FXML
    private Button openTableButton;

    @FXML
    private RadioButton rungegoButton;

    @FXML
    private Button safeToFileButton;

    @FXML
    private TextField tLeftTextField;

    @FXML
    private TextField tRightTextField;

    @FXML
    private CategoryAxis timeAxis;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField x0TextField;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private TextField stepTextField;

    private Grapher grapher;

    public ODEStep getMethod() {
        return method;
    }

    public void setMethod(ODEStep method) {
        this.method = method;
    }

    private ConsolHandler consolHandler = new ConsolHandler();

    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'solver.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'solver.fxml'.";
        assert equationTextField != null : "fx:id=\"equationTextField\" was not injected: check your FXML file 'solver.fxml'.";
        assert eulerButton != null : "fx:id=\"eulerButton\" was not injected: check your FXML file 'solver.fxml'.";
        assert eulerModifiedButton != null : "fx:id=\"eulerModifiedButton\" was not injected: check your FXML file 'solver.fxml'.";
        assert graph != null : "fx:id=\"graph\" was not injected: check your FXML file 'solver.fxml'.";
        assert openTableButton != null : "fx:id=\"openTableButton\" was not injected: check your FXML file 'solver.fxml'.";
        assert rungegoButton != null : "fx:id=\"rungegoButton\" was not injected: check your FXML file 'solver.fxml'.";
        assert safeToFileButton != null : "fx:id=\"safeToFileButton\" was not injected: check your FXML file 'solver.fxml'.";
        assert tLeftTextField != null : "fx:id=\"tLeftTextField\" was not injected: check your FXML file 'solver.fxml'.";
        assert tRightTextField != null : "fx:id=\"tRightTextField\" was not injected: check your FXML file 'solver.fxml'.";
        assert timeAxis != null : "fx:id=\"timeAxis\" was not injected: check your FXML file 'solver.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'solver.fxml'.";
        assert x0TextField != null : "fx:id=\"x0TextField\" was not injected: check your FXML file 'solver.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'solver.fxml'.";

        ToggleGroup radioGroup = new ToggleGroup();
        eulerButton.setToggleGroup(radioGroup);
        eulerModifiedButton.setToggleGroup(radioGroup);
        rungegoButton.setToggleGroup(radioGroup);

    }

    public void openTableButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("table.fxml"));
            Parent root = loader.load();
            TableController table = loader.getController();
            table.setList(list);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception error) {
            error.printStackTrace();
            error.getCause();
        }
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void confirmButtonAction(ActionEvent actionEvent) throws IOException {
//        ODEEquation odeEquation = (x, t) -> Double.parseDouble(equationTextField.getText().toString());
        ODEEquation odeEquation = (x, t) -> -2 * t * t * t + 12 * t * t - 20 * t + 8.5;

        System.out.println(odeEquation);
        double a = Double.parseDouble(tLeftTextField.getText().toString());
        double b = Double.parseDouble(tRightTextField.getText().toString());
        double x0 = Double.parseDouble(x0TextField.getText().toString());
        double step = Double.parseDouble(stepTextField.getText().toString());

        if (eulerButton.isSelected()) {
            setMethod(new Euler());
        } else if (eulerModifiedButton.isSelected()) {
            setMethod(new EulerModified());
        } else {
            setMethod(new RungegoKutty());
        }

        ODEIntegrator integrator = new ODEIntegrator(a, b, x0, odeEquation, method,
                consolHandler);

        integrator.integrate(step);
        list.addAll(PointTX.getPointsTX(consolHandler.gettList(), consolHandler.getxList()));
        System.out.println(list);
        consolHandler.print2Columns();
        
    }

    @FXML
    void saveToFileAction(ActionEvent e) throws IOException {
        File file = fileChooser.showSaveDialog(new Stage());
        SaveToFileHandler save = new SaveToFileHandler();
        if(file != null){

        }
    }
}
