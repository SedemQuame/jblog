package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Datasource;
import sample.model.Story;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class homePageController implements Initializable, EventHandler<ActionEvent> {
    @FXML
    private Button homepage;

    @FXML
    private Button techCrunch;

    @FXML
    private Button bbc;

    @FXML
    private Button wsj;

    @FXML
    private VBox jblogArticles;

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
    public void handle(ActionEvent event) {
        loadStage("../fxml/blogPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        Creating datasource object, to access methods to interact with database.
//        ========================================================================

        Datasource source = new Datasource();

//        Storing data from database in an array.
//        =======================================
        List<Story> stories;
        stories = source.queryAllStories();


//        Creating elements to add to the fxml Ui dynamically.
//        ====================================================

        for (Story story : stories) {

            HBox hBox = new HBox();

            VBox vBox = new VBox();
            Button articleName = new Button();
            Label categoryName = new Label();

            ImageView imageView = new ImageView();


//            Setting properties for vBox VBox.
            vBox.setPrefHeight(80.0);
            vBox.setPrefWidth(190.0);

//            Setting properties for articleName Label.
            articleName.setAlignment(Pos.BASELINE_LEFT);
            articleName.setMaxHeight(30.0);
            articleName.setMaxHeight(15.0);
            articleName.setMaxWidth(190.0);
            articleName.getStyleClass().add("gotoBlogPage");
            articleName.getStylesheets().add("../stylesheets/homePage.css");
            articleName.setText(story.getTitle());
            articleName.setFont(new Font(16.0));

//           Adding events to the articleName label.
            articleName.setOnAction(this::handle);


            VBox.setMargin(articleName, new Insets(0.0, 0.0, 5.0, 0.0));

            categoryName.setAlignment(Pos.TOP_LEFT);
            categoryName.setLayoutX(10.0);
            categoryName.setLayoutY(10.0);
            categoryName.setMaxHeight(30.0);
            categoryName.setPrefHeight(15.0);
            categoryName.setPrefWidth(190.0);
            categoryName.setStyle("-fx-text-fill: #485460;");
            categoryName.setText(story.getCategory());

            imageView.setFitHeight(80.0);
            imageView.setFitWidth(100.0);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);

            HBox.setMargin(imageView, new Insets(0.0));
            imageView.setImage(new Image(getClass().getResource("../image/imagePlaceholder.jpg").toExternalForm()));
            VBox.setMargin(hBox, new Insets(5.0, 0.0, 5.0, 0.0));
            hBox.setPadding(new Insets(8.0, 0.0, 0.0, 5.0));

            hBox.setLayoutX(10.0);
            hBox.setLayoutY(218.0);
            hBox.setPrefHeight(80.0);
            hBox.setPrefWidth(200.0);
            hBox.getStyleClass().add("highLightArticle");
            hBox.getStylesheets().add("../stylesheets/homePage.css");

            vBox.getChildren().add(articleName);
            vBox.getChildren().add(categoryName);

            hBox.getChildren().add(vBox);
            hBox.getChildren().add(imageView);

            jblogArticles.getChildren().add(hBox);

        }



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
