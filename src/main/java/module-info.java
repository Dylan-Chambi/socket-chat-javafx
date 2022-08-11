module upb.isc.clientsocketui2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens upb.isc.clientsocketui2 to javafx.fxml;
    exports upb.isc.clientsocketui2;
}