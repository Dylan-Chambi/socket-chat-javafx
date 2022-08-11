package upb.isc.clientsocketui2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SocketViewController implements Initializable {
    @FXML
    private Label connectionIPLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button sendButton;
    @FXML
    private TextField messageFieldTF;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectionIPLabel.setText(ClientSocket.getInstance().getServerIP() + ":" + ClientSocket.getInstance().getPort());
        usernameLabel.setText(ClientSocket.getInstance().getUsername());

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
        sendMessageFromTextField();
    }

    public void onEnterPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().toString().equals("ENTER")) {
            sendMessageFromTextField();
        }
    }

    public void sendMessageFromTextField() {
        ClientSocket.getInstance().sendMessage(messageFieldTF.getText());
        messageFieldTF.setText("");
    }
}
