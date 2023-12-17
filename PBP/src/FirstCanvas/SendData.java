package FirstCanvas; // 혹시 모르니까 같은 패키지로 지정

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendData {
    private int data; // 전송할 데이터

    public SendData(int data) {
        this.data = data;
    }

    public void sendDataToServer(String serverHost, int serverPort) {
        try (Socket socket = new Socket(serverHost, serverPort);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            // 서버로 데이터 전송
            out.writeObject(data);
            System.out.println("데이터 전송 완료");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
