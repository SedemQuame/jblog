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
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class signUpPageController implements Initializable {

    /*FXML Sign Up Components.*/
//    ========================

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField age;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button loginPageLink;

    @FXML
    void createNewUser(ActionEvent event) {

        System.out.println("Sign Up Button Clicked.");
        if (event.getSource() == signUpBtn) {

            /*Add things to database and perform adequate checks here.*/
            /*========================================================*/
            System.out.println("\n\n");

            String firstname = firstName.getText();
            String lastname = lastName.getText();
            int userage = parseInt(age.getText());
            int phone = parseInt(phoneNumber.getText());
            String email = emailAddress.getText();
            String pwsd = password.getText();
            System.out.println("Password: " + pwsd);

            String confirmPswd = confirmPassword.getText();
            System.out.println("Confirm Password: " + confirmPswd);

            System.out.println(pwsd.length());

            if (pwsd.equals(confirmPswd) && pwsd.length() >= 8) {
                Datasource source = new Datasource();
//           open database.
//                source.open();
                try {
                    Datasource.populateUsersTable(firstname, lastname, userage, email, phone, pwsd);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//           close database.
//                source.close();

                /*Change scene.*/
                /*=============*/
                loadStage("../fxml/homePage.fxml");
                //      Hiding the stage.
                ((Node) event.getSource()).getScene().getWindow().hide();
            } else {
//                todo
//                Print error message unto screen.

                if (pwsd.length() < 8) {
                    System.out.println("Password is less than 8 characters.");
                } else if (!pwsd.equals(confirmPswd)) {
                    System.out.println("Passwords do not match.");
                }

            }

        } else {
            System.out.println("Display user error message.");
            System.out.println("Don't move to new page.");
        }

    }


    @FXML
    public void gotoLoginPage(ActionEvent event) {
        System.out.println("Login Button Clicked.");
        if (event.getSource() == loginPageLink) {
            loadStage("../fxml/loginPage.fxml");
            //      Hiding the stage.
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException event) {
            event.printStackTrace();
        }
    }


}

