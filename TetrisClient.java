package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

import main.gameGUI;

public class TetrisClient extends Thread {
	private String host; // 호스트(서버 주소)를 저장할 변수
	private int port; // 포트(통신용 역할)를 저장할 변수
	private BufferedReader input; // 입력 스트림
	private PrintWriter output; // 출력 스트림
	private String key, inputValue, dirnum, x, y,selectX,selectY; // 데이터 관련 변수들
	private UUID uuid;

	/**
	 *
	 * @param host         서버의 호스트명 또는 IP 주소
	 * @param port         서버가 수신 대기하는 포트 번호
	 */

	public TetrisClient(String host, int port, UUID uuid) {
		this.host = host;
		this.port = port;
		this.uuid = uuid;
		key = uuid.toString() + ";";
		System.out.println("내 키: " + key);
		
		//이 uuid를 플레이어별로 따로 나눠줘야 됨
		gameGUI.getData().player1.setUUID(uuid.toString());
	}

	/**
	 * 서버로 게임 데이터를 전송합니다.
	 */
	public void send() {
		inputValue = String.valueOf(gameGUI.getData().inputValue);
		dirnum = String.valueOf(gameGUI.getData().dirnum);
		x = String.valueOf(gameGUI.getMainCanvas().getFieldSelectPoint().x);
		y = String.valueOf(gameGUI.getMainCanvas().getFieldSelectPoint().y);
		selectX = String.valueOf(gameGUI.getMainCanvas().getSelect().x);
		selectY = String.valueOf(gameGUI.getMainCanvas().getSelect().y);
		if (gameGUI.getData().inputValue != 0) {
			gameGUI.getSubCanvas().unitSummon(gameGUI.getData().inputValue, gameGUI.getData().dirnum,
					gameGUI.getMainCanvas().getFieldSelectPoint().x, gameGUI.getMainCanvas().getFieldSelectPoint().y, 
					gameGUI.getMainCanvas().getSelect().x,gameGUI.getMainCanvas().getSelect().y);
			gameGUI.getData().map.getField(gameGUI.getMainCanvas().getSelect().x, gameGUI.getMainCanvas().getSelect().y)
					.startSummonCooldown();
			System.out.println("소환!");
		}
		output.println(key +  x + ";" + y + ";" + inputValue + ";" + dirnum + ";" + selectX + ";" + selectY);
		gameGUI.getData().inputValue = 0;
	}

	/**
     * 클라이언트 스레드의 주 실행 메서드입니다.
     */
    public void run() {
        System.out.println("클라이언트 시작!");
        Socket socket;
        try {
            socket = new Socket(host, port);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            input = new BufferedReader(new InputStreamReader(inputStream));
            output = new PrintWriter(new OutputStreamWriter(outputStream), true);
            
            while (true) {
            	
                String line = input.readLine();
                if (line.length() != 0) {
                    String[] parsedData = line.split(";");
                    String checkKey = parsedData[0] + ";";
                    if (!checkKey.equals(key) && parsedData.length > 1) {
                    	int[] intArray = new int[parsedData.length];
                    	int inputvalue = Integer.parseInt(parsedData[3]);
                    	int dirnum = Integer.parseInt(parsedData[4]);
                    	int selectX = Integer.parseInt(parsedData[5]);
                    	int selectY = Integer.parseInt(parsedData[6]);
                    	if("null".equals(parsedData[1]) && "null".equals(parsedData[2])) {
                    	}
                    	else {
                    		int x = Integer.parseInt(parsedData[1]);
                    		int y = Integer.parseInt(parsedData[2]);
                    		if(Integer.parseInt(parsedData[3]) != 0) {
                        		gameGUI.getSubCanvas().unitSummon(inputvalue, dirnum,x,y,selectX,selectY);
                        	}
                    	}
                 
                    	try {
                        	
                        } catch (Exception e) {
                            System.out.println("클라이언트 문제: " + e);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
