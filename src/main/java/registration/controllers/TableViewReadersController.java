package registration.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import registration.model.ReaderUser;
import registration.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class TableViewReadersController implements Initializable{
    @FXML private TableView<ReaderUser> tableView;
    @FXML private TableColumn<ReaderUser,String> userNameColumn;
    @FXML private TableColumn<ReaderUser,String> fullNameColumn;
    @FXML private TableColumn<ReaderUser,String> addressColumn;
    @FXML private TableColumn<ReaderUser,String> phoneNumberColumn;

    private static ReaderUser selectedUser;

    public static ReaderUser getSelectedUser() {
        return selectedUser;
    }

    @FXML
    private void handleViewBorrowedBooks(MouseEvent event){
        if(event.getClickCount()>1)
        {
            selectedUser=tableView.getSelectionModel().getSelectedItem();
            if(selectedUser!=null)
                try{
                    Parent p= FXMLLoader.load(getClass().getResource("/fxml/tableview_borrowdata.fxml"));
                    Scene scene2=new Scene(p);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene2);
                    window.show();
                }catch (IOException e){
                    e.printStackTrace();
                }
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/librarian.fxml"));
        Scene scene1=new Scene(p);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Pagina bibliotecar");
        window.setScene(scene1);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameColumn.setCellValueFactory(new PropertyValueFactory<ReaderUser,String>("username"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<ReaderUser,String>("fullname"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<ReaderUser,String>("address"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<ReaderUser,String>("phonenumber"));

        tableView.setItems(getReaderUsers());

    }
    public ObservableList<ReaderUser> getReaderUsers(){
        UserService us=new UserService();
         ObservableList<ReaderUser> readers=FXCollections.observableArrayList();
         for(ReaderUser r:us.getReaderUsers())
              readers.add((ReaderUser) r);
         return readers;
    }

}
