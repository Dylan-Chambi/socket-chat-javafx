module upb.isc.clientsocketui2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens upb.isc.colorpickerclient to javafx.fxml;
    exports upb.isc.colorpickerclient;
}