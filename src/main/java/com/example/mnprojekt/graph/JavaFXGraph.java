package com.example.mnprojekt.graph;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JavaFXGraph extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        // GitHubTest 4 attempt

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("C:\\Users\\ZEPHYRUS\\IdeaProjects\\JavaFXGraph\\src\\main\\resources\\fxml\\graph.fxml")));
        Scene scene= new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();

    }
}
