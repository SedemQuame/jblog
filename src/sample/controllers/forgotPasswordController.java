package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class forgotPasswordController implements Initializable {

    @FXML
    private TextField secretQuestion;

    @FXML
    private TextField newPassword;

    @FXML
    private Button changePassword;

    @FXML
    private Button loginPageLink;

    @FXML
    void changePasswordBtn(ActionEvent event) {
        System.out.println("Method to handle changing password in the database.");
    }

    @FXML
    void gotoLoginPage(ActionEvent event) {
        System.out.println("Going to the login page after changing the passwords in the database..");

        if (event.getSource() == loginPageLink) {
            loadStage("../fxml/loginPage.fxml");
            //      Hiding the stage.
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
//            stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException event) {
            event.printStackTrace();
        }
    }
}
