package Server;

import java.io.Serializable;
import java.util.Vector;

// This class represents the data that will be transmitted between the client and the server
public class ServerData implements Serializable {
    private String message;
    private String userName;
    private int state, count, division;
    private Vector<String> v, turn;

    // Constants for different states
    public static final int LOGIN = 10;    // User login
    public static final int START = 20;    // Game start
    public static final int LOGOUT = 30;   // User logout
    public static final int SENDDATA = 40; // Sending game data

    // Constructor
    // Initializes the user name, message, state, and division variables
    public ServerData(String Name, String message, int state, int division) {
        this.userName = Name;
        this.message = message;
        this.state = state;
        this.division = division;
    }

    // Getter method for user name
    public String getName() {
        return this.userName;
    }

    // Getter method for the message data
    public String getMessage() {
        return this.message;
    }

    // Getter method for the state variable
    public int getState() {
        return this.state;
    }

    // Getter method for the division variable
    public int getDivision() {
        return this.division;
    }

    // Getter method for the log information
    public Vector<String> getLoginfo() {
        return this.v;
    }

    // Setter method for log information
    public void setLoginfo(Vector<String> v) {
        this.v = v;
    }
}
