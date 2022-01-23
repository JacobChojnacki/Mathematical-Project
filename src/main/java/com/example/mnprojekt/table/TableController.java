package com.example.mnprojekt.table;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.mnprojekt.methods.PointTX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController {

    private ObservableList<PointTX> list= FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PointTX> table;

    @FXML
    private TableColumn<PointTX, Double> time;

    @FXML
    private TableColumn<PointTX, Double> x;

    public void setList(ObservableList<PointTX> list) {
        table.setItems(list);
    }

    @FXML
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'table.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'table.fxml'.";
        assert x != null : "fx:id=\"x\" was not injected: check your FXML file 'table.fxml'.";
        list.clear();
        time.setCellValueFactory(new PropertyValueFactory<PointTX, Double>("time"));
        x.setCellValueFactory(new PropertyValueFactory<PointTX, Double>("x"));
    }

}

