package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {

//                Creating a new source object.
        sample.model.Datasource source = new sample.model.Datasource();

//        Checking State Of The Database.
//        -------------------------------
        if (!source.open()) {
            System.out.println("Can't open source");
            return;
        }
        source.createTables();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/storyPage.fxml"));
        primaryStage.setTitle("Sign Up Page");
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
    }
}