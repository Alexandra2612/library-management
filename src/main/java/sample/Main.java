package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.services.BookService;
import library.services.UserService;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        UserService.loadUsersFromFile();
        BookService.loadBooksFromFile();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
