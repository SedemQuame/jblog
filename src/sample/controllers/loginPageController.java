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
import sample.model.Datasource;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginPageController implements Initializable {


    @FXML
    private TextField emailAddress;

    @FXML
    private TextField password;

    @FXML
    private Button loginUser;

    @FXML
    private Button signupLink;


    @FXML
    void logUserIntoAccount(ActionEvent event) {
        System.out.println("Going to the user's account (i.e homePage.fxml)");

        String email = emailAddress.getText();
        String pwsd = password.getText();

        Datasource source = new Datasource();

//        open database.
        source.open();

//User authentication.
        if (Datasource.logIntoUserAccount(email, pwsd)) {
            /*Goto the homepage after user verification.*/
//           --------------------------------------------
            loadStage("../fxml/homePage.fxml");
            //      Hiding the stage.
            ((Node) event.getSource()).getScene().getWindow().hide();
        }

//        close database.
        source.close();
    }

    @FXML
    void gotoSignUpPage(ActionEvent event) {
        System.out.println("Going to the sign up page.");
        loadStage("../fxml/signupPage.fxml");
        //      Hiding the stage.
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void gotoForgotPasswordPage(ActionEvent event) {
        loadStage("../fxml/forgotPassword.fxml");
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
