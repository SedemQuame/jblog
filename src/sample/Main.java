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

//        Final Statement to be run.
//        source.close();
//
//        apiSource source1 = new apiSource("https://newsapi.org/v2/everything?q=bitcoin&from=2019-03-25&sortBy=publishedAt&apiKey=a6a86fbcf32e4e6a9a36bdcd3786b4c9");
//        source1.useApi();
////        System.out.println(source1.getInline());
//
//
//        newsApi napi = new newsApi();
//        napi = source1.deserialize(napi);
//
//        //        Printing the status of the JSON Response.
//        System.out.println("\n\n");
//        System.out.println("Status: " + napi.getStatus());
//        System.out.println("Total Results: " + napi.getTotalResults());
//
//        List<stories> listOfStories = napi.getArticles();
//        for (int x = 0; x < listOfStories.size(); x++) {
////            Iterating through the stories.
//
//            System.out.println("Arthur: " + listOfStories.get(x).getAuthor());
//            System.out.println("Title: " + listOfStories.get(x).getTitle());
//            System.out.println("Description: " + listOfStories.get(x).getDescription());
//            System.out.println("Url: " + listOfStories.get(x).getUrl());
//            System.out.println("UrlToImage: " + listOfStories.get(x).getUrlToImage());
//            System.out.println("PublishedAt: " + listOfStories.get(x).getPublishedAt());
//            System.out.println("Content: " + listOfStories.get(x).getContent());
//
//            System.out.println("\n\n");
//        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        The initial FXMLLoader
//        ----------------------

        Parent root = FXMLLoader.load(getClass().getResource("fxml/storyPage.fxml"));

        primaryStage.setTitle("Sign Up Page");
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
    }
}