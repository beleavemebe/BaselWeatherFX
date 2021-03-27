package com.beleavemebe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class Basel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene1.fxml"));
            Parent root = loader.load();
            ((BaselController) loader.getController()).setStage(stage);
            Scene scene = new Scene(root);

            Image icon = new Image(new File("src\\main\\java\\com\\beleavemebe\\Basel.png").toURI().toURL().toString());
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Basel");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}