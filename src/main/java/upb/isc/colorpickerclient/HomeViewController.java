package upb.isc.colorpickerclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeViewController implements Initializable {
    @FXML
    private TextField serverIPTF;
    @FXML
    private TextField portTF;
    @FXML
    private Button btnConnect;

    @FXML
    public void onClickConnect(ActionEvent actionEvent) {
        System.out.println("Text serverIP: " + serverIPTF.getText());
        System.out.println("Text port: " + portTF.getText());

        System.out.println("Prompt serverIP: " + serverIPTF.getPromptText());
        System.out.println("Prompt port: " + portTF.getPromptText());

        String serverIP = (serverIPTF.getText().isEmpty()) ? ( serverIPTF.getPromptText() ): serverIPTF.getText();
        String port = (portTF.getText().isEmpty()) ? ( portTF.getPromptText() ): portTF.getText();


        ClientSocket.getInstance().setServerIP(serverIP);
        ClientSocket.getInstance().setPort(Integer.parseInt(port));
        String connectionStatus = ClientSocket.getInstance().connect(serverIP, Integer.parseInt(port));
        if(connectionStatus.equals("Connected")) {
            try {
                Parent root = FXMLLoader.load(SocketViewController.class.getResource("socket-view.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Error: " + connectionStatus);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ClientSocket.getInstance().getServerIP() != null) {
            serverIPTF.setPromptText(ClientSocket.getInstance().getServerIP());
        }
        if(ClientSocket.getInstance().getPort() != 0) {
            portTF.setPromptText("" + ClientSocket.getInstance().getPort());
        }
    }


}
