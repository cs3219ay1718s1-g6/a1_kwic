package gui;

import component.Alphabetizer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    //-----------------------------------------------
    // Querying Primary Stage
    //-----------------------------------------------
    private static Stage currentPrimaryStage;
    private static void setCurrentPrimaryStage(Stage primaryStage) {
        currentPrimaryStage = primaryStage;
    }
    static Stage getCurrentPrimaryStage() {
        return currentPrimaryStage;
    }

    //-----------------------------------------------
    // Launching
    //-----------------------------------------------
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("controller.fxml"));
        primaryStage.setTitle("KWIC A1 - Implict Invocation");
        primaryStage.setScene(new Scene(root, 640, 480));
        setCurrentPrimaryStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
