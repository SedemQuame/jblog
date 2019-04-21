package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Datasource;
import sample.model.Story;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class storyListPageController implements Initializable {

    @FXML
    private VBox container;

    @FXML
    private TableView<Story> storyInfoTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Datasource source = new Datasource();

        TableColumn<Story, String> storyId = new TableColumn<Story, String>("Story ID");
        TableColumn<Story, String> title = new TableColumn<Story, String>("Title");
        TableColumn<Story, String> category = new TableColumn<Story, String>("Category");
        TableColumn<Story, String> introduction = new TableColumn<Story, String>("Introduction");
        TableColumn<Story, String> body = new TableColumn<Story, String>("Body");
        TableColumn<Story, String> conclusion = new TableColumn<Story, String>("Conclusion");
        TableColumn<Story, String> imageUrl = new TableColumn<Story, String>("Image Url");


//      Changing column widths.
//      =======================

//        storyId.setPrefWidth(30.0);
        title.setPrefWidth(30.0);
        category.setPrefWidth(94.0);
        introduction.setPrefWidth(94.0);
        body.setPrefWidth(94.0);
        conclusion.setPrefWidth(30.0);
        imageUrl.setPrefWidth(94.0);


//        Adding table headers.
        storyInfoTable.getColumns().addAll(title, category, introduction, body, conclusion, imageUrl);

        final ObservableList<Story> data = FXCollections.observableArrayList();

//        Open database.
        source.open();

        List<Story> queriedStories = source.queryAllStories();

        for (int m = 0; m < queriedStories.size(); m++) {
            Story newStory = new Story();
            newStory = queriedStories.get(m);

            String storyTitle = newStory.getTitle();
            String storyCategory = newStory.getCategory();
            String storyIntroduction = newStory.getIntroduction();
            String storyBody = newStory.getBody();
            String storyConclusion = newStory.getConclusion();
            String storyImageUrl = newStory.getImageUrl();
//            Where yon := years on earth.
//            ----------------------------
            int id = newStory.getStoryId();


            data.add(new Story(storyTitle, storyCategory, storyIntroduction, storyBody, storyConclusion, storyImageUrl, id));
        }


//        Close database.
        source.close();

//        Associating data values with respective columns.
//        ------------------------------------------------
//        title.setCellValueFactory(new PropertyValueFactory<Story, String>("title"));
        title.setCellValueFactory(new PropertyValueFactory<Story, String>("title"));
        category.setCellValueFactory(new PropertyValueFactory<Story, String>("category"));
        introduction.setCellValueFactory(new PropertyValueFactory<Story, String>("introduction"));
        body.setCellValueFactory(new PropertyValueFactory<Story, String>("body"));
        conclusion.setCellValueFactory(new PropertyValueFactory<Story, String>("age"));
        imageUrl.setCellValueFactory(new PropertyValueFactory<Story, String>("conclusion"));


//        Adding data to the table.
//        -------------------------
        storyInfoTable.setItems(data);

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
