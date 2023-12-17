//package server2;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Point;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.AdjustmentEvent;
//import java.awt.event.AdjustmentListener;
//
//import java.io.IOException;
//import java.io.InterruptedIOException;
//
//import java.net.InetAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Random;
//import java.util.Vector;
//
//import javax.swing.BorderFactory;
//import javax.swing.Box;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollBar;
//import javax.swing.JScrollPane;
//import javax.swing.JTextPane;
//import javax.swing.text.SimpleAttributeSet;
//import javax.swing.text.StyledDocument;
//
//public class Server extends JFrame implements Runnable, ActionListener,AdjustmentListener{
//	private JButton startButton, exitButton; // Button
//	private JScrollBar bar; // ScrollBar
//	private JTextPane statePane; // TextPane Component
//	private SimpleAttributeSet attriState; // SimpleAttributeSet
//	private StyledDocument docState; // StyledDocument
//	private JLabel titleLb, stateLb; // Label
//	private JOptionPane jopt; // JOpationPane
//	private InetAddress ip;	
//	private int clientOne, clientTwo, clientThree;
//	private String userOne, userTwo, userThree;	
//	private Vector vector;
//
//	
//	private Point point;	
//	
//	private ServerSocket servSoc;	
//	private Socket soc;
//	
//	private boolean bStop;	
//	private boolean bStart = false;	
//	static ServerAbout cp;	
//	static ServerThread st;
//
//	
//	public Server() {		
//		super("Server");	
//		}
//
//	public void initialize() {
//		JPanel pnNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
//		stateLb = new JLabel("Server Stop...", JLabel.LEFT);
//		stateLb.setOpaque(false);		
//		pnNorth.add(stateLb);
//		
//		JPanel pnCenter = new JPanel(new BorderLayout());
//		
//		titleLb = new JLabel("  SERVER MESSAGE  ", JLabel.CENTER);	
//		
//		statePane = new JTextPane();		
//		statePane.setBorder(BorderFactory.createRaisedBevelBorder());		
//		statePane.setEditable(false);
//
//		JScrollPane scroll = new JScrollPane(statePane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		scroll.setOpaque(false);
//
//		bar = scroll.getVerticalScrollBar();		
//		docState = statePane.getStyledDocument();
//		
//		pnCenter.add(titleLb, "North");		
//		pnCenter.add(scroll, "Center");		// Box		
//		Box boxBottom = Box.createHorizontalBox();
//		
//		// South Panel		
//		JPanel pnBottom = new JPanel();
//
//		// Button		
//		startButton = new JButton(" START ");		
//		exitButton = new JButton(" EXIT  ");		
//		exitButton.setEnabled(bStart);
//		
//		pnBottom.add(startButton);		
//		pnBottom.add(exitButton);		
//		getContentPane().add(pnNorth, "North");		
//		getContentPane().add(pnCenter, "Center");		
//		getContentPane().add(pnBottom, "South");
//		
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);		
//		startButton.addActionListener(this);		
//		exitButton.addActionListener(this);		
//		Toolkit kit = getToolkit();		
//		Dimension dmen = kit.getScreenSize();	
//		setLocation((int) ((dmen.width - getSize().width) / 8),		
//		(int) ((dmen.height - getSize().height) / 8));	
//		setSize(300, 300);		setResizable(false);	
//		docState = statePane.getStyledDocument();		
//		setVisible(true);
//
//	}
//	
//	public void actionPerformed(ActionEvent ae) {		
//		Object o = ae.getSource();		
//		if (o.equals(startButton)) {			
//			bStop = true;			
//			Thread t = new Thread(this);			
//			t.start();			
//			startButton.setEnabled(!bStop);		
//			exitButton.setEnabled(bStop);		
//			} else if (o.equals(exitButton)) {		
//				bStop = (!beforeExit());		
//				closeServ();		
//				}	
//		}
//	
//	// 서버 나가기	
//	public boolean beforeExit() {		
//		JLabel content = new JLabel("게임서버를 종료시키겠습니까?!");		
//		int confirm = jopt.showConfirmDialog(null, content, "종료확인",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
//		if (confirm == 1)			
//			return false;	
//		return true;	
//		}
//
//	// 스크롤바설정
//	public void adjustmentValueChanged(AdjustmentEvent ae) {
//		bar.setValue(bar.getMaximum());		
//		bar.removeAdjustmentListener(this);	
//		}
//	
//	// 서버 run	
//	public void run() {	
//		cp = new ServerAbout();		
//		try {		
//			servSoc = new ServerSocket(0302);			
//			stateLb.setText("Server Start!!! \n");			
//			stateMessage(" Connecting Complete\n Server IP: "+ ip.getLocalHost().getHostAddress() + "\n");			
//			System.out.println("Complete");			
//			System.out.println("");		
//			} 
//		catch (IOException e) {			
//			System.out.println("Socket Error : " + e);			
//			return;		
//			}		
//		while (bStop) {			
//			try {				
//				soc = servSoc.accept();			
//				} 
//			catch (InterruptedIOException e) {				
//				continue;			
//				}
//			catch (IOException e) {				
//				stateLb.setText("Server Stopped...\n");				
//				continue;			
//				}			
//			try {			
//				st = new ServerThread(this, soc);				
//				if (bStart) { // 게임 진행중	
//					ServerData data = new ServerData(st.getName(),"죄송합니다...게임이 진행중입니다.\n잠시 후에 다시 시도해주세요", 0, 0);			
//					st.sendMessage(data);				
//					continue;				
//					}			
//				} catch (IOException e) {	
//					System.out.println("Socket Error : " + e.toString());				
//					continue;			
//				}		
//			}
//		
//		}
//	
//	// 서버 닫기	
//	void closeServ() {		
//		ServerData data = new ServerData("Server", "서버를 종료를 시도합니다.",ServerData.LOGOUT, 0);
//		cp.broadCasting(data);		
//		cp.setStop(data);		
//		if (st != null)			
//			st.setStop();		
//		bStop = false;		
//		try {			
//			if (soc != null) {			
//				soc.close();			
//				stateMessage("클라이언트와 연결된 소켓들을 닫습니다.");			
//				System.out.println("소켓OFF");		
//				}			
//			servSoc.close();			
//			System.out.println("소켓OFF");			
//			stateMessage("소켓OFF");		
//			} catch (IOException e) {		
//				String msg = e.toString() + "\n";		
//				jopt.showMessageDialog(null, msg, "ninini",jopt.INFORMATION_MESSAGE);	
//				jopt.setLocation(point.x+ (int) ((getSize().width - jopt.getSize().width) / 2),point.y
//						+ (int) ((getSize().height - jopt.getSize().width) / 2));		
//				}		
//		setVisible(bStop);		
//		dispose();		
//		System.exit(0);	
//		}
//	
//	// 상태 메시지	
//	public void stateMessage(String msg) {	
//		try {			
//			attriState = new SimpleAttributeSet();	
//			statePane.setCaretPosition(docState.getEndPosition().getOffset() - 1);	
//			docState.insertString(statePane.getCaretPosition(), msg, attriState);	
//			bar.addAdjustmentListener(this);	
//			} catch (Exception ex) {	
//				String contents = "메세지를 띄울수가 없습니다... \n 네트워크에 문제가 발생한듯...";	
//				jopt.showMessageDialog(null, contents, "Server",jopt.INFORMATION_MESSAGE);		
//				System.out.println("GameServer StateMessage error :" + ex);	
//				}	
//		}	
//	private int divisionNN = 0;
//	
//	
//	// 데이터를 받거나 보내는 쪽	
//	public void updateData(ServerData data) {
//		ServerData sndData = null;
//		int count = cp.getLogName().size();		
//		switch (data.getState()) {		
//		case ServerData.LOGIN:			
//			stateMessage(data.getName() + "님이 접속하였습니다. 접속자수:" + (count + 1)+ "\n");		
//			divisionNN = count;		
//			sndData = new ServerData(data.getName(), "하이", ServerData.LOGIN,divisionNN);		
//			if (count == 0) {				
//				clientOne = 1;				
//				userOne = data.getName();			
//				} else if (count == 1) {			
//					clientTwo = 2;			
//					userTwo = data.getName();			
//					} else if (count == 2) {	
//						clientThree = 3;			
//						userThree = data.getName();		
//						}		
//			cp.addClient(st);		
//			cp.broadCasting(sndData);		
//			break;		
//			case ServerData.SENDDATA:	
//				if (data.getDivision() == 11) {		
//					stateMessage(userOne + "에게 데이터를 보냅니다.\n");			
//					sndData = new ServerData(userOne, "One Client!",		
//							ServerData.SENDDATA, 11);				
//					cp.addClient(st);				
//					cp.broadCasting(sndData);			
//					break;			
//					} else if (data.getDivision() == 12) {		
//						stateMessage(userTwo + "에게 데이터를 보냅니다.\n");	
//						sndData = new ServerData(userTwo, "Two Client!",	
//								ServerData.SENDDATA, 12);			
//						cp.addClient(st);			
//						cp.broadCasting(sndData);			
//						break;		
//						} else if (data.getDivision() == 13) {	
//							stateMessage(userThree + "에게 데이터를 보냅니다.\n");		
//							sndData = new ServerData(userThree, "Three Client!",	
//									ServerData.SENDDATA, data.getDivision());		
//							cp.broadCasting(sndData);			
//							break;			
//							}		
//				break;	
//				}	
//		}
//	
//	public static void main(String args[]) {
//		Server test = new Server();	
//		test.initialize();	
//		}
//	}
//
//
//
//
////public void actionPerformed() 이부분은 버튼 클릭시 이벤트 처리
//// public boolean beforeExit()  이부분은 서버 나가는 메서드
////public void run() 서버 run 부분 서버 접속하는 부분
////public void updateData(ServerData data) 이부분이 중요. 클라에서 받는 데이터가 변하는거에 대해서 여기서 처리.
//
//
//			
//		
//		
//
//
//	
