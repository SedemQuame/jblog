package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.apis.apiSource;
import sample.apis.newsApi;
import sample.apis.stories;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class blogPageController implements Initializable {

//    @FXML
//    private Button homepage;
//
//    @FXML
//    private Button techCrunch;
//
//    @FXML
//    private Button bbc;
//
//    @FXML
//    private Button wsj;

    @FXML
    private ChoiceBox<String> newsSource;

    @FXML
    private Button changeNewsSourceBtn;
    @FXML
    private VBox articleList;

    @FXML
    void changeNewsSource(ActionEvent event) {

        articleList.getChildren().clear();

        String source = newsSource.getValue();


        if (source == "TechCrunch")
            addElementsToScene("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");
        else if (source == "Wall Street Journal")
            addElementsToScene("https://newsapi.org/v2/everything?domains=wsj.com&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");
        else if (source == "Al Jazeera English")
            addElementsToScene("https://newsapi.org/v2/top-headlines?sources=al-jazeera-english&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");
        else if (source == "BBC")
            addElementsToScene("https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");
        else if (source == "Bitcoin")
            addElementsToScene("https://newsapi.org/v2/everything?q=bitcoin&from=2019-03-26&sortBy=publishedAt&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");
        else if (source == "Science")
            addElementsToScene("https://newsapi.org/v2/top-headlines?category=science&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");
        else if (source == "Entertainment")
            addElementsToScene("https://newsapi.org/v2/top-headlines?category=entertainment&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");
        else
            addElementsToScene("https://newsapi.org/v2/top-headlines?category=entertainment&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");

    }

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

        newsSource.getItems().addAll("TechCrunch", "Wall Street Journal", "BBC", "Al Jazeera English", "Bitcoin", "Science", "Entertainment");
        newsSource.setValue("TechCrunch");

        addElementsToScene("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");

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

    private void addElementsToScene(String sourceUrl) {

//        Creating newsApi object, to access methods from newsapi.org.
//        ============================================================
        apiSource source1 = new apiSource(sourceUrl);
        source1.useApi();
//        System.out.println(source1.getInline());


        newsApi napi = new newsApi();
        napi = source1.deserialize(napi);

        //        Printing the status of the JSON Response.
        System.out.println("\n\n");
        System.out.println("Status: " + napi.getStatus());
        System.out.println("Total Results: " + napi.getTotalResults());

        List<stories> articles = napi.getArticles();


        for (int i = 0; i < articles.size(); i++) {


            Label storyTitleLabel = new Label();
            ImageView imageView = new ImageView();
            Label photoCreditLabel = new Label();
            Label introductionLabel = new Label();
            Label bodyLabel = new Label();
            Label conclusionLabel = new Label();

            storyTitleLabel.setAlignment(Pos.BASELINE_LEFT);
            storyTitleLabel.setId("storyTtileLabel");
            storyTitleLabel.setPrefHeight(30.0);
            storyTitleLabel.setPrefWidth(900.0);
            storyTitleLabel.setStyle("-fx-text-fill: #1e272e;");
            storyTitleLabel.getStylesheets().add("/com/company/../stylesheets/blogPage.css");
            storyTitleLabel.getStyleClass().add("gotoBlogPage");
            storyTitleLabel.getStylesheets().add("../stylesheets/homePage.css");
            storyTitleLabel.setText(articles.get(i).getTitle());
            storyTitleLabel.setFont(new Font("Carlito", 25.0));
            VBox.setMargin(storyTitleLabel, new Insets(5.0, 0.0, 5.0, 0.0));


            imageView.setId("blogImageView");
            imageView.setPickOnBounds(true);
            VBox.setMargin(imageView, new Insets(0.0));

//            imageView.setImage(new Image(getClass().getResource("../image/imagePlaceholder.jpg").toExternalForm()));

            if (articles.get(i).getUrlToImage() != null) {
                Image image = new Image(articles.get(i).getUrlToImage(), 900, 450, false, false);
                imageView = new ImageView(image);
            } else {
                Image image = new Image("../image/imagePlaceholder.jpg", 900, 450, false, false);
                imageView = new ImageView(image);
            }

//            imageView.setFitHeight(450.0);
//            imageView.setFitWidth(900.0);

            photoCreditLabel.setAlignment(Pos.BASELINE_LEFT);
            photoCreditLabel.setId("photoCreditLabel");
            photoCreditLabel.setPrefHeight(15.0);
            photoCreditLabel.setPrefWidth(900.0);
            photoCreditLabel.setStyle("-fx-text-fill: #808e9b;");
            photoCreditLabel.getStylesheets().add("/com/company/../stylesheets/blogPage.css");
            photoCreditLabel.setText(articles.get(i).getUrlToImage());
            VBox.setMargin(photoCreditLabel, new Insets(0.0, 0.0, 10.0, 0.0));

            introductionLabel.setAlignment(Pos.TOP_LEFT);
            introductionLabel.setId("introductionLabel");
            introductionLabel.setLayoutX(10.0);
            introductionLabel.setLayoutY(515.0);
            introductionLabel.setPrefWidth(900.0);
//            introductionLabel.setStyle("-fx-text-fill: #808e9b;");
            introductionLabel.getStyleClass().add("articleInformation");
            introductionLabel.getStylesheets().add("/com/company/../stylesheets/blogPage.css");
            introductionLabel.setText(articles.get(i).getContent());
            introductionLabel.setWrapText(true);
            VBox.setMargin(introductionLabel, new Insets(0.0, 0.0, 10.0, 0.0));
            introductionLabel.setPadding(new Insets(0.0, 0.0, 10.0, 0.0));
            introductionLabel.setFont(new Font(15.0));

            bodyLabel.setAlignment(Pos.TOP_LEFT);
            bodyLabel.setId("bodyLabel");
            bodyLabel.setLayoutX(10.0);
            bodyLabel.setLayoutY(542.0);
            bodyLabel.setMinWidth(175.0);
            bodyLabel.setPrefWidth(900.0);
//            bodyLabel.setStyle("-fx-text-fill: #808e9b;");
            bodyLabel.getStyleClass().add("articleInformation");
            bodyLabel.getStylesheets().add("/com/company/../stylesheets/blogPage.css");
            bodyLabel.setText(articles.get(i).getAuthor());
            bodyLabel.setWrapText(true);
            VBox.setMargin(bodyLabel, new Insets(0.0, 0.0, 10.0, 0.0));
            bodyLabel.setPadding(new Insets(0.0, 0.0, 10.0, 0.0));
            bodyLabel.setFont(new Font(15.0));

            conclusionLabel.setAlignment(Pos.TOP_LEFT);
            conclusionLabel.setId("conclusionLabel");
            conclusionLabel.setLayoutX(10.0);
            conclusionLabel.setLayoutY(568.0);
            conclusionLabel.setMinWidth(100.0);
            conclusionLabel.setPrefWidth(900.0);
//            conclusionLabel.setStyle("-fx-text-fill: #808e9b;");
            conclusionLabel.getStyleClass().add("articleInformation");
            conclusionLabel.getStylesheets().add("/com/company/../stylesheets/blogPage.css");
            conclusionLabel.setText(articles.get(i).getPublishedAt());
            conclusionLabel.setWrapText(true);
            VBox.setMargin(conclusionLabel, new Insets(0.0, 0.0, 45.0, 0.0));
            conclusionLabel.setPadding(new Insets(0.0, 0.0, 10.0, 0.0));
            conclusionLabel.setFont(new Font(15.0));


            articleList.getChildren().add(storyTitleLabel);
            articleList.getChildren().add(imageView);
            articleList.getChildren().add(photoCreditLabel);
            articleList.getChildren().add(introductionLabel);
            articleList.getChildren().add(bodyLabel);
            articleList.getChildren().add(conclusionLabel);
        }
    }

}
