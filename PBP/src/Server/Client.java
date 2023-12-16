package Server;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Client extends JFrame implements ActionListener, AdjustmentListener, ItemListener {
    JLabel nameLabel, divisionLabel;
    JButton divisionButton;
    private int divisionN = 10;
    private String Name;

    // 서버 접속
    private Socket soc;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ClientThread ct;
    private ServerThread st;

    public Client() {
        super("Client");
    }

    public void initialize() {
        JPanel mainPanel = new JPanel();
        nameLabel = new JLabel("TestT");
        mainPanel.add(nameLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        divisionLabel = new JLabel("클라이언트");

        divisionButton = new JButton("확인");
        divisionButton.addActionListener(this);

        buttonPanel.add(divisionLabel);
        buttonPanel.add(divisionButton);

        getContentPane().add(mainPanel, "North");
        getContentPane().add(buttonPanel, "South");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Toolkit kit = getToolkit();
        Dimension dmen = kit.getScreenSize();
        setLocation((int) ((dmen.width - getSize().width) / 8),
                (int) ((dmen.height - getSize().height) / 8));
        setSize(500, 400);
        setResizable(false);
        setVisible(true);
    }

    public void updateData(ServerData data) {
        ServerData sd = null;
        switch (data.getState()) {
            case ServerData.LOGIN:
                if (data.getDivision() + 10 == divisionN) {
                    Name = data.getName();
                    nameLabel.setText(data.getName() + "님 환영합니다.");
                    divisionN = 11;
                    break;
                } else if (data.getDivision() < divisionN) {
                    Name = data.getName();
                    nameLabel.setText(data.getName() + "님 환영합니다.");
                    divisionN = 12;
                    break;
                }
                // 이런식으로 각 클라이언트에게 값을 지정하고 그 지정한 값을
                // 이용하여 서버가 클라한테 값을 보내주면 됩니다.
            case ServerData.SENDDATA:
                if (data.getDivision() == 11 && divisionN == 11) {
                    nameLabel.setText(data.getMessage() + "값을 받았습니다.");
                    divisionN = 11;
                    break;
                } else if (data.getDivision() == 12 && divisionN == 12) {
                    nameLabel.setText(data.getMessage() + "값을 받았습니다.");
                    divisionN = 12;
                    break;
                }
                break;
        }
    }

    static String name;

    // 서버 접속
    public void makeConnection(String host, String nam) {
        try {
            soc = new Socket(host, 0302);
            this.name = nam;
        } catch (IOException ioe) {
            System.out.println("makeConnection : " + ioe);
            return;
        }
        try {
            out = new ObjectOutputStream(soc.getOutputStream());
            in = new ObjectInputStream(soc.getInputStream());
        } catch (IOException ioe) {
            System.out.println("makeConnection : " + ioe);
            setVisible(false);
            return;
        }
        ServerData data = new ServerData(name, "가 입장\n", ServerData.LOGIN, 0);
        sendMessage(data);
        ct = new ClientThread(this, in);
    }

    // 게임 데이터 정보 보내는 것
    public synchronized void sendMessage(ServerData data) {
        try {
            out.writeObject(data);
            System.out.println("작동중");
        } catch (IOException ioe) {
            System.out.println("sendMessage1 error Ioe :" + ioe);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(divisionButton)) {
            divisionLabel.setText("클라이언트Num" + divisionN);
            ServerData sndData = new ServerData("", "", ServerData.SENDDATA, divisionN);
            sendMessage(sndData);
        }
    }

   //  public static void main(String args[]) {
   //  Client test = new Client();
  //   test.initialize();
     //}
}


//public void updateData(ServerData data) {  
//	서버와 클라이언트가 받는 데이터를 최종적으로 여기서 받고 값이 변경하기 때문에중요한 메서드. 
//	 자바 "직렬화(Serialization)" 를 사용.
//	즉, 쉽게 말해서 구조체를 byte형식으로 바꿔서 데이터를 보냈다고 생각하면됨.
//	public void makeConnection(String host, String nam) {  서버 접속메서드. 
//	소스를 해석 하자면 소켓에 접속을 하고, 그것을 ObjectOutputStream(객체 직렬화) 해서 데이터를 Out(보내거나)In(받음.)그리고 
//	"ServerData" 라는 서버에 ServerData.Login 가 있는 위치로 데이터를이름과 정보를 보냅니다.그리고 ClientThread가 실행.
//	 public synchronized void sendMessage(ServerData data)서버한테 데이터 보내는 메서드.
//






