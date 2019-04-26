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
import sample.apis.apiSource;
import sample.apis.newsApi;
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
    private VBox globalStories;

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

        source.open();

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
            articleName.setFont(new Font("System Bold", 14.0));


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

            getJblogArticles().getChildren().add(hBox);

        }

        source.close();


//        Creating newsApi object, to access methods from newsapi.org.
//        ============================================================


        apiSource source1 = new apiSource("\n" +
                "https://newsapi.org/v2/everything?q=apple&from=2019-04-24&to=2019-04-24&sortBy=popularity&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");
        source1.useApi();
//        System.out.println(source1.getInline());


        newsApi napi = new newsApi();
        napi = source1.deserialize(napi);

        //        Printing the status of the JSON Response.
        System.out.println("\n\n");
        System.out.println("Status: " + napi.getStatus());
        System.out.println("Total Results: " + napi.getTotalResults());

//        Creating elements to add to the fxml Ui dynamically.
//        ====================================================

        List<sample.apis.stories> listOfStories = napi.getArticles();
        for (int x = 0; x < 15; x++) {
//            Iterating through the stories.
            HBox hBox1 = new HBox();
            VBox vBox1 = new VBox();
            Button storyTitleLabel = new Button();
            storyTitleLabel.setAlignment(Pos.BASELINE_LEFT);
            storyTitleLabel.getStyleClass().add("gotoBlogPage");
            storyTitleLabel.getStylesheets().add("../stylesheets/homePage.css");


            Label storyDescriptionLabel = new Label();
            Label storyAuthorLabel = new Label();
            VBox vBox2 = new VBox();
            ImageView imageView0 = new ImageView();

            hBox1.setPrefHeight(60.0);
            hBox1.setPrefWidth(200.0);

            vBox1.setPrefHeight(0.0);
            vBox1.setPrefWidth(400.0);

            storyTitleLabel.setPrefHeight(20.0);
            storyTitleLabel.setPrefWidth(400.0);
            storyTitleLabel.setStyle("-fx-background-color: white;");
            storyTitleLabel.setText(listOfStories.get(x).getTitle());
            VBox.setMargin(storyTitleLabel, new Insets(0.0, 0.0, 5.0, 0.0));
            storyTitleLabel.setFont(new Font("System Bold", 14.0));

//           Adding events to the articleName label.
//           =======================================
            storyTitleLabel.setOnAction(this::handle);

            storyDescriptionLabel.setPrefWidth(400.0);
            storyDescriptionLabel.setStyle("-fx-background-color: white;");
            storyDescriptionLabel.setText(listOfStories.get(x).getDescription());
            storyDescriptionLabel.setWrapText(true);

            VBox.setMargin(storyDescriptionLabel, new Insets(0.0, 0.0, 5.0, 0.0));

            storyAuthorLabel.setLayoutX(10.0);
            storyAuthorLabel.setLayoutY(35.0);
            storyAuthorLabel.setPrefHeight(70.0);
            storyAuthorLabel.setPrefWidth(400.0);
            storyAuthorLabel.setStyle("-fx-background-color: white;");
            storyAuthorLabel.setText(listOfStories.get(x).getAuthor());

//            Adding title, description, and author name.
//            ===========================================
            vBox1.getChildren().add(storyTitleLabel);
            vBox1.getChildren().add(storyDescriptionLabel);
            vBox1.getChildren().add(storyAuthorLabel);


            vBox2.setPrefHeight(0.0);
            vBox2.setPrefWidth(130.0);
            HBox.setMargin(vBox2, new Insets(0.0, 0.0, 0.0, 20.0));

            imageView0.setFitHeight(100.0);
            imageView0.setFitWidth(200.0);
            imageView0.setPickOnBounds(true);

//            Image image = new Image(listOfStories.get(x).getUrlToImage());
//            imageView0.setImage(image);
            VBox.setMargin(hBox1, new Insets(0.0, 0.0, 10.0, 0.0));

//            Adding imageView to Vbox2.
//            ==========================
            vBox2.getChildren().add(imageView0);


            hBox1.getChildren().add(vBox1);
            hBox1.getChildren().add(vBox2);
            getGlobalStories().getChildren().add(hBox1);
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

    private VBox getGlobalStories() {
        return globalStories;
    }

    private VBox getJblogArticles() {
        return jblogArticles;
    }
}
