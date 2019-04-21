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
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class userListPageController implements Initializable {


    @FXML
    private VBox container;

    @FXML
    private TableView<User> userInfoTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Datasource source = new Datasource();


        TableColumn<User, String> userId = new TableColumn<>("ID");
        TableColumn<User, String> firstname = new TableColumn<User, String>("First name");
        TableColumn<User, String> lastname = new TableColumn<User, String>("Last name");
        TableColumn<User, String> email = new TableColumn<User, String>("Email");
        TableColumn<User, String> age = new TableColumn<>("Age");
        TableColumn<User, String> phonenumber = new TableColumn<User, String>("Phone number");


//      Changing column widths.
//      =======================

        userId.setPrefWidth(30.0);
        firstname.setPrefWidth(94.0);
        lastname.setPrefWidth(94.0);
        email.setPrefWidth(94.0);
        age.setPrefWidth(30.0);
        phonenumber.setPrefWidth(94.0);


//        Adding table headers.
        userInfoTable.getColumns().addAll(userId, firstname, lastname, email, age, phonenumber);

        final ObservableList<User> data = FXCollections.observableArrayList();

//        Open database.
        source.open();

        List<User> queriedUsers = source.queryAllUsers();

        for (int m = 0; m < queriedUsers.size(); m++) {
            User newUser = new User();
            newUser = queriedUsers.get(m);

            String fname = newUser.getFirstname();
            String lname = newUser.getFirstname();
            String mail = newUser.getFirstname();
//            Where yon := years on earth.
//            ----------------------------
            int yon = newUser.getAge();
            int digits = newUser.getPhonenumber();
            int id = newUser.getUserId();

            data.add(new User(fname, lname, mail, yon, digits, id));
        }


//        Close database.
        source.close();

//        Associating data values with respective columns.
//        ------------------------------------------------
        userId.setCellValueFactory(new PropertyValueFactory<User, String>("userId"));
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        age.setCellValueFactory(new PropertyValueFactory<User, String>("age"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<User, String>("phonenumber"));


//        Adding data to the table.
//        -------------------------
        userInfoTable.setItems(data);

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


