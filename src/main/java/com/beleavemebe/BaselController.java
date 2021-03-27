package com.beleavemebe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BaselController {

    private Stage stage;
    private List<Data> data;

    @FXML
    private TextArea text;

    @FXML
    private Button loadFileButton;

    @FXML
    private Button showChartsButton;

    @FXML
    void loadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Откройте .csv файл");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma separated values", "*.csv"));
        File csv = fileChooser.showOpenDialog(stage);
        if (csv == null) return;
        update(csv);
    }

    void update(File csv) {
        Parser parser = new Parser(csv);
        data = parser.getOutput();
        Calculator calculator = new Calculator(data);
        text.setText(calculator.formResult());
    }

    @FXML
    void export() throws IOException {
        if (data == null || data.size() == 0) {
            AlertBox.display("", "Сначала загрузите файл");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text file", "*.txt"));
        File export = fileChooser.showSaveDialog(stage);
        if (export == null) return;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(export));
        bufferedWriter.write(text.getText());
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    @FXML
    void showCharts(ActionEvent event) throws Exception {
        if (data == null || data.size() == 0) {
            AlertBox.display("", "Сначала загрузите файл");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("charts.fxml"));
        Parent root = loader.load();
        ChartsController controller = loader.getController();
        controller.setData(data);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        Image icon = new Image(new File("src\\main\\java\\com\\beleavemebe\\charts.png").toURI().toString());
        stage.getIcons().add(icon);
        stage.setTitle("Графики");
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}

