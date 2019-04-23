package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class blogPageController implements Initializable {

    @FXML
    private Button homepage;

    @FXML
    private Button techCrunch;

    @FXML
    private Button bbc;

    @FXML
    private Button wsj;

    @FXML
    void gotoHomePage(ActionEvent event) {
        loadStage("../fxml/homePage.fxml");
        //      Hiding the stage.
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void gotoStoryTemplate(ActionEvent event) {
        loadStage("../fxml/blogPage.fxml");
        //      Hiding the stage.
        ((Node) event.getSource()).getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
//      //            stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException event) {
            event.printStackTrace();
        }
    }

}
