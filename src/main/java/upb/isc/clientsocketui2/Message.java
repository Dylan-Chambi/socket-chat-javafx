package upb.isc.clientsocketui2;


public class Message {
    private String username;
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(String username, String message) {
        this.username = username;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\"username\":\"" + username + "\",\"message\":\"" + message + "\"}";
    }
}
