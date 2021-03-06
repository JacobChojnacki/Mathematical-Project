package com.example.mnprojekt.solve;

import com.example.mnprojekt.MainApplication;
import com.example.mnprojekt.methods.CalculateHandler;
import com.example.mnprojekt.methods.ConsolHandler;
import com.example.mnprojekt.methods.ODEIntegrator;
import com.example.mnprojekt.methods.PointTX;
import com.example.mnprojekt.methods.inter.ODEStep;
import com.example.mnprojekt.methods.methodsChoice.Euler;
import com.example.mnprojekt.methods.methodsChoice.EulerModified;
import com.example.mnprojekt.methods.methodsChoice.RungegoKutty;
import com.example.mnprojekt.table.TableController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class SolverController {

    protected ObservableList<PointTX> list = FXCollections.observableArrayList();

    private ODEStep method;

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
    private LineChart<Double, Double> graph;

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
    private NumberAxis timeAxis;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField x0TextField;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private TextField stepTextField;

    public void setMethod(ODEStep method) {
        this.method = method;
    }

    private final ConsolHandler consolHandler = new ConsolHandler();

    FileChooser fileChooser = new FileChooser();

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
        timeAxis.setLabel("Time");
        xAxis.setLabel("f(t)");
        fileChooser.setInitialDirectory(new File("C:\\temp"));
    }
    public void setUsername(String labelText) {
        usernameLabel.setText(labelText);
    }

    public void openTableButtonAction(ActionEvent event) {
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

    public void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void confirmButtonAction(ActionEvent event) {
        try{

            consolHandler.clearData();

            CalculateHandler calculateHandler = new CalculateHandler(equationTextField.getText());
            double a = Double.parseDouble(tLeftTextField.getText());
            double b = Double.parseDouble(tRightTextField.getText());
            double x0 = Double.parseDouble(x0TextField.getText());
            double step = Double.parseDouble(stepTextField.getText());
            System.out.println(equationTextField.getText());

            if (eulerButton.isSelected()) {
                setMethod(new Euler());
            } else if (eulerModifiedButton.isSelected()) {
                setMethod(new EulerModified());
            } else if (rungegoButton.isSelected()){
                setMethod(new RungegoKutty());
            } else {
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Method error");
               alert.setContentText("Please choose one of the three methods on the left side");
               alert.showAndWait();
            }

            ODEIntegrator integrator = new ODEIntegrator(a, b, x0, calculateHandler, method,
                    consolHandler);

            integrator.integrate(step);
            list.clear();
            list.addAll(PointTX.getPointsTX(consolHandler.gettList(), consolHandler.getxList()));
            XYChart.Series<Double,Double> series = new XYChart.Series<>();
            for (int i = 0; i < consolHandler.getxList().size(); i++) {
                series.getData().add(new XYChart.Data<>(consolHandler.getTValue(i),
                        consolHandler.getXValue(i)));
            }
            series.setName("x0 = " + x0 + ", step = " +step);
            graph.getData().add(series);}
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Expression alert");
            alert.setContentText("Your expression cannot be empty or you enter a valid data.");
            alert.showAndWait();
        }

    }
    public void saveToFileAction(ActionEvent event){
       Stage stage = new Stage();
       FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Saving Data");
       fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
       fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt_file","*.txt"),
               new FileChooser.ExtensionFilter("csv_file","*.csv"));
       if(!list.isEmpty()){
           File file = fileChooser.showSaveDialog(stage);
           if(file != null){
               saveFile(list,file);
           }
       }
       else{
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Saving error");
           alert.setContentText("There is no data to save.");
           alert.showAndWait();
       }
    }
    public void resetButtonAction(ActionEvent event){
        Collections.singleton(graph.getData().setAll());
    }

    public void helpButtonClick(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("help.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception error) {
            error.printStackTrace();
            error.getCause();
        }
    }

    public void saveFile(ObservableList<PointTX> observableList, File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(PointTX pointTX : observableList) {
                writer.write(String.valueOf(pointTX.getTime()));
                writer.write("\t");
                writer.write(String.valueOf(pointTX.getX()));
                writer.newLine();
            }
            System.out.println(observableList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
