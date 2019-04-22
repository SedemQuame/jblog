package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Datasource;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class storyPageController implements Initializable {

    @FXML
    private Button homepage;

    @FXML
    private Button storyboard;

    @FXML
    private Button storylist;

    @FXML
    private Button userlist;

    @FXML
    private TextField storyTitle;

    @FXML
    private TextField category;

    @FXML
    private TextArea introduction;

    @FXML
    private TextArea body;

    @FXML
    private TextArea conclusion;

    @FXML
    private TextField imageUrl;

    @FXML
    private Button submitStory;

    @FXML
    void gotoHomePage(ActionEvent event) {
        loadStage("../fxml/homePage.fxml");
        //      Hiding the stage.
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void gotoStoryBoardPage(ActionEvent event) {
        loadStage("../fxml/storyPage.fxml");
        //      Hiding the stage.
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void gotoStoryListPage(ActionEvent event) {
        loadStage("../fxml/storyListPage.fxml");
        //      Hiding the stage.
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void gotoStoryUserPage(ActionEvent event) {
        loadStage("../fxml/userListPage.fxml");
        //      Hiding the stage.
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void addNewStoryToDatabase(ActionEvent event) {
        System.out.println("Adding new story to the database.");
        if (event.getSource() == submitStory) {


            System.out.println("\n\n");

            /*Add things to database and perform adequate checks here.*/
            /*========================================================*/
            String title = storyTitle.getText();
            String storyCategory = category.getText();
            String storyIntro = introduction.getText();
            String storyBody = body.getText();
            String storyConclusion = conclusion.getText();
            String storyImage = imageUrl.getText();

            if (title.isEmpty() || storyCategory.isEmpty()) {

                System.out.println("Either title area or category textfield is empty.");

            } else {

                Datasource source = new Datasource();

//           open database.
                source.open();

                try {
                    Datasource.populateStoriesTable(title, storyCategory, storyIntro, storyBody, storyConclusion, storyImage);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//           close database.
                source.close();

                /*Change scene.*/
                /*=============*/
                loadStage("../fxml/homePage.fxml");
                //      Hiding the stage.
                ((Node) event.getSource()).getScene().getWindow().hide();
            }

        } else {
            System.out.println("Display user error message.");
            System.out.println("Don't move to new page.");
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

