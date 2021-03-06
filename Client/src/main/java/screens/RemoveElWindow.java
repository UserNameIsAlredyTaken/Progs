package screens;

import any.Utility;
import controllers.MainScreenController;
import controllers.RemoveElController;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by danil on 05.05.2017.
 */

public class RemoveElWindow {
    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainPane;
    private Button RemoveElOKbutton;
    private Button RemoveElCancelButton;
    private TextField textField;
    private static RemoveElWindow removeElWindow;
    private ResourceBundle resourceBundle;
    private boolean wasLoaded = false;

    private RemoveElWindow(){}
    public static synchronized RemoveElWindow getInstace(){
        if(removeElWindow==null){
            removeElWindow=new RemoveElWindow();
        }
        return removeElWindow;
    }

    public void loadRemoveElWindow(ObservableList data){
        drawPanes();
        drawButton();
        setControllers(data);
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 500, 140);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
        wasLoaded = true;
        setLocale(MainScreenController.CurrentLocale);
    }
    private void drawPanes(){
        mainPane=new AnchorPane();
        textField=new TextField();
        mainPane.getChildren().add(textField);
        AnchorPane.setTopAnchor(textField,20.0);
        AnchorPane.setLeftAnchor(textField,20.0);
        AnchorPane.setRightAnchor(textField,20.0);
    }
    private void drawButton(){
        RemoveElOKbutton =new Button();
        mainPane.getChildren().add(RemoveElOKbutton);
        AnchorPane.setRightAnchor(RemoveElOKbutton,160.0);
        AnchorPane.setLeftAnchor(RemoveElOKbutton,270.0);
        AnchorPane.setBottomAnchor(RemoveElOKbutton, 32.0);
        RemoveElCancelButton =new Button();
        mainPane.getChildren().add(RemoveElCancelButton);
        AnchorPane.setRightAnchor(RemoveElCancelButton,270.0);
        AnchorPane.setLeftAnchor(RemoveElCancelButton,160.0);
        AnchorPane.setBottomAnchor(RemoveElCancelButton, 32.0);
    }
    private void setCSS(){
        RemoveElOKbutton.setId("button");
        RemoveElCancelButton.setId("button");
    }
    private void setControllers(ObservableList data){
        RemoveElController.RemoveElOKbutton(RemoveElOKbutton, data);
        RemoveElController.RemoveElCancelButton(RemoveElCancelButton);
    }
    public void setLocale(Locale locale){
        if(!wasLoaded){
            return;
        }
        resourceBundle= ResourceBundle.getBundle("Locale",locale);
        RemoveElOKbutton.setText(Utility.GetLocalizedString(resourceBundle.getString("Ok")));
        RemoveElCancelButton.setText(Utility.GetLocalizedString(resourceBundle.getString("Cancel")));
        textField.setText(Utility.GetLocalizedString(resourceBundle.getString("EnterTheStringInJson")));
    }

    public Button getRemoveElOKbutton() {
        return RemoveElOKbutton;
    }

    public Button getRemoveElCancelButton() {
        return RemoveElCancelButton;
    }

    public TextField getTextField() {
        return textField;
    }
}

