package screens;

import any.Utility;
import controllers.InfoController;
import controllers.MainScreenController;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by danil on 04.05.2017.
 */
public class InfoWindow {
    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainPane;
    private Button InfoOKbutton;
    private Text text;
    private static InfoWindow infoWindow;
    private ResourceBundle resourceBundle;
    private ObservableList data;
    private InfoWindow(){}
    public static synchronized InfoWindow getInstace(){
        if(infoWindow==null){
            infoWindow=new InfoWindow();
        }
        return infoWindow;
    }
    public void loadInfoWindow(ObservableList data){
        this.data=data;
        drawPanes(data);
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        scene=new Scene(mainPane, 550, 150);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
        setLocale(MainScreenController.CurrentLocale);
    }
    private void drawPanes(ObservableList data){
        mainPane=new AnchorPane();
        text = new Text();
        mainPane.getChildren().add(text);
        AnchorPane.setTopAnchor(text,20.0);
        AnchorPane.setLeftAnchor(text,20.0);

    }
    private void drawButton(){
        InfoOKbutton =new Button();
        mainPane.getChildren().add(InfoOKbutton);
        AnchorPane.setRightAnchor(InfoOKbutton,220.0);
        AnchorPane.setLeftAnchor(InfoOKbutton,220.0);
        AnchorPane.setBottomAnchor(InfoOKbutton, 32.0);
    }
    public void setLocale(Locale locale){
        if(data==null){
            return;
        }
        resourceBundle= ResourceBundle.getBundle("Locale",locale);
        text.setText(Utility.GetLocalizedString(resourceBundle.getString("CollectionType"))
                +data.getClass()
                +Utility.GetLocalizedString(resourceBundle.getString("ElementsCount"))
                +data.size());
        InfoOKbutton.setText(Utility.GetLocalizedString(resourceBundle.getString("Ok")));
    }
    private void setCSS(){
        InfoOKbutton.setId("button");
    }
    private void setControllers(){
        InfoController.InfoOKbutton(InfoOKbutton);
    }

    public Button getInfoOKbutton() {
        return InfoOKbutton;
    }
}
