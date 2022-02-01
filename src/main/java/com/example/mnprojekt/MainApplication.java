package com.example.mnprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

/**
 *      @author Jakub Chojnacki
 *      @version 1.0.6
 *      @since 18.01.2022
 */
public class MainApplication extends Application {

    /**
     * Metoda sluzaca do twarcia okna logowania
     * @param stage - otwieranie okna z JAVAFX
     * @throws IOException - blad w przypadku problemu z wyswietleniem okna
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        stage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}