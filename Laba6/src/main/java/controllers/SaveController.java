package controllers;

import io.SaveTable;
import items.FoodResidus;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import screens.SaveWindow;

import java.io.File;

/**
 * Created by danil on 10.06.2017.
 */
public class SaveController {
    public static void SaveDefaultButton(Button button, ObservableList<FoodResidus> data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SaveTable saveTable=new SaveTable("saveDefault", data);
                saveTable.start();
                Stage stage = (Stage) SaveWindow.getInstace().getSaveDefaultButton().getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void SaveChooseButton(Button button, ObservableList<FoodResidus> data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML файлы", "*.xml"));
                File selectedFile = fileChooser.showOpenDialog(null);
                SaveTable saveTable = new SaveTable("save", data, selectedFile);
                saveTable.start();
                Stage stage = (Stage) SaveWindow.getInstace().getSaveChooseButton().getScene().getWindow();
                stage.close();
            }
        });
    }
}
