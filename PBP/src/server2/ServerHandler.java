package server2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;
import java.util.ArrayList;

// 클라이언트와의 통신을 관리하는 ServerHandler 클래스
public class ServerHandler extends Thread {
    private Socket s; // 클라이언트와의 소켓 연결
    private BufferedReader i; // 클라이언트로부터의 입력 스트림
    private PrintWriter o; // 클라이언트로의 출력 스트림
    private TetrisServer server; // 테트리스 서버 객체
    private UUID clientUUID; // 클라이언트에게 할당된 UUID
    private ArrayList<UUID> uuidList = new ArrayList<>();
    private ArrayList<Integer> broodList = new ArrayList<>();
    
    // 생성자: 테트리스 서버 및 소켓을 초기화
    public ServerHandler(TetrisServer server, Socket s) throws IOException {
        this.s = s;
        this.server = server;
        this.clientUUID = UUID.randomUUID(); // 클라이언트에게 UUID 할당
        InputStream ins = s.getInputStream(); // 클라이언트로부터의 입력 스트림 생성
        OutputStream os = s.getOutputStream(); // 클라이언트로의 출력 스트림 생성
        i = new BufferedReader(new InputStreamReader(ins)); // 입력 스트림을 버퍼드 리더로 래핑
        o = new PrintWriter(new OutputStreamWriter(os), true); // 출력 스트림을 PrintWriter로 래핑
        uuidList.add(null);
        sendClientUUID(); // 클라이언트에게 할당된 UUID 전송
        sendUUIDList();
    }

    // 클라이언트에게 할당된 UUID를 전송
    private void sendClientUUID() {
        o.println(clientUUID.toString());
        uuidList.add(clientUUID);
    }
    
    private void sendUUIDList() {
        StringBuilder uuidString = new StringBuilder();
        for (UUID uuid : uuidList) {
        	if(uuid != null) {
        		uuidString.append(uuid.toString()).append(";");
        	}
        }
        if (uuidString.length() > 0) {
            uuidString.deleteCharAt(uuidString.length() - 1); // 마지막 쉼표 제거
        }
        o.println(uuidString.toString());
    }

    // 스레드 실행 메서드
    public void run() {
        try {
            server.register(this); // 클라이언트 핸들러를 서버에 등록
            while (true) {
                String msg = i.readLine(); // 클라이언트로부터 메시지를 읽음
                broadcast(msg); // 읽은 메시지를 다른 클라이언트에게 방송
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.unregister(this); // 클라이언트 핸들러를 서버에서 제거
        try {
            i.close();
            o.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 클라이언트로 메시지 출력
    protected void println(String message) {
        o.println(message);
        clientUUID = UUID.randomUUID();
    }

    // 메시지를 모든 클라이언트에게 방송
    protected void broadcast(String message) {
        server.broadcast(clientUUID.toString() + ": " + message);
    }
}
