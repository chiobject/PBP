package startCanvas;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_FirstPage extends JPanel implements ActionListener {
	Main_StartHere main;
	private JButton btnGame1, btnGame2, btnGame3;
	
	public Main_FirstPage(Main_StartHere main) {
		this.main=main;
		
		makeBtn();
	}
	
	private void makeBtn() {
		btnGame1 = new JButton("123");
		btnGame2 = new JButton("123");
		btnGame3 = new JButton("123");
		
		btnGame1.addActionListener(this);
		btnGame2.addActionListener(this);
		btnGame3.addActionListener(this);
		
		this.add(btnGame1);
		this.add(btnGame2);
		this.add(btnGame3);
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnGame1) {
			main.Main_FiratPageToNext(1);
		}else if(e.getSource()==btnGame2) {
			main.Main_FiratPageToNext(2);
		}else if (e.getSource()==btnGame3) {
			main.Main_FiratPageToNext(3);
		}
	}

}
