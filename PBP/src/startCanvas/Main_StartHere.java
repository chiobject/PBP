package startCanvas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
// 프레임 하나에 패널을 바꿔가며 화면 바꾸는 방식
//선택하는 프레임에 이 게임을 패널로 붙이는 방식
// 기본적으로 클래스는 패널이며, 기능은 패널에 추가로 붙인다.

public class Main_StartHere extends JFrame {
	Main_FirstPage firstPage;
	
	public Main_StartHere() {
		this.setSize(1300,810);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		firstPage = new Main_FirstPage(this);
		this.add(firstPage);
		
		this.setVisible(true);
	}	
	
	public static void main(String[] args) {
		new Main_StartHere();
	}
	
	void Main_FiratPageToNext(int i) {
		this.remove(firstPage);
		if(i == 1) {
			startgame = new Game(this);
		}else if(i == 2) {
			explanation = new Game(this);
		}else if(i==3) {
			sub = new Game(this);
		}
		this.repaint();
		this.revalidate();
	}
	

	
	public void Game_MainPnlToNext() {
		this.remove(startgame);
		main2 = new MainPnl2(this);
		this.add(main2);
		this.repaint();
		this.revalidate();
	}
	
	public void MainPnl2ToNext(int i) {
		this.remove(main2);
		if(i==1) { //첫 화면
			this.add(firstPage);
		}else if(i==2) {
			//게임화면
		}
	}

}
