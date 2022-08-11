package upb.isc.colorpickerclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SocketViewController implements Initializable {
    @FXML
    private CheckBox realTimeCB;
    @FXML
    private ColorPicker colorPickerID;
    @FXML
    private Label connectionIPLabel;
    @FXML
    private Button sendButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectionIPLabel.setText(ClientSocket.getInstance().getServerIP() + ":" + ClientSocket.getInstance().getPort());
        colorPickerID.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(realTimeCB.isSelected()) {
                sendNewColorFromPicker();
            }
        });
    }



    public void exitOnClick(ActionEvent actionEvent) {
        try {
            ClientSocket.getInstance().disconnect();
            Parent root = FXMLLoader.load(HomeViewController.class.getResource("home-view.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnClick(ActionEvent actionEvent) {
        sendNewColorFromPicker();
    }

    public void sendNewColorFromPicker(){
        ClientSocket.getInstance().sendNewColor(colorPickerID.getValue().toString());
    }


}
