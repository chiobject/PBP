package FirstCanvas; // 혹시 모르니까 같은 패키지로 지정

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Server.ServerData;

public class SendData {
    private int selectedCharacter; // 전송할 데이터

    public SendData(int selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    public void sendDataToServer(String serverHost, int serverPort) {
        try (Socket socket = new Socket(serverHost, serverPort);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            // 서버로 데이터 전송
        	 ServerData serverData = new ServerData("", "", ServerData.SENDDATA, null, null, selectedCharacter);
             out.writeObject(serverData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
