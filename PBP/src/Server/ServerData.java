package Server;

import java.io.Serializable;
import java.util.Vector;
import java.util.UUID;

// This class represents the data that will be transmitted between the client and the server
public class ServerData implements Serializable {
    private String message;
    private String userName;
    private int state, count;
    //private UUID division1;
    //private UUID division2;
    private Vector<String> v, turn;
    UUID division1 = UUID.randomUUID();
    UUID division2 = UUID.randomUUID();

    // Constants for different states
    public static final int LOGIN = 10;    // User login
    public static final int START = 20;    // Game start
    public static final int LOGOUT = 30;   // User logout
    public static final int SENDDATA = 40; // Sending game data
    public static final int choose = 0;

    // Constructor
    // Initializes the user name, message, state, and division variables
    public ServerData(String Name, String message, int state, UUID division1, UUID division2) {
        this.userName = Name;
        this.message = message;
        this.state = state;
        this.division1 = division1;
        this.division2 = division2;
        this.v = new Vector<>();
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
    public UUID getDivision1() {
        return this.division1;
    }
    
    public UUID getDivision2() {
        return this.division2;
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
