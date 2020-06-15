package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import registration.services.BookService;
import registration.services.UserService;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        UserService.loadUsersFromFile();
        BookService.loadBooksFromFile();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
