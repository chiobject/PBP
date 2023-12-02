package main;

import java.awt.*;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class subCanvas extends JPanel implements Runnable, MouseListener{
	protected boolean stop;
	private JLabel button1 = new JLabel("버튼 1");
	private JLabel button2 = new JLabel("버튼 2");
	private JLabel button3 = new JLabel("버튼 3");
	private JLabel button4 = new JLabel("버튼 4");
	protected Point mouse = new Point();

	protected Thread worker;
	
	subCanvas(){
		addMouseListener(this);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		setLayout(null);
		
		button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 클릭 시 수행할 동작을 여기에 추가
                
            }
        });

	}
	
	void start() {
		stop = false;
		
		worker = new Thread(this);
		worker.start();
		
		requestFocus();
		repaint();
	}
	
	void stop() {
		stop = true;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawRect(30, 50, getWidth()-150, getWidth()-150);
		button1.setBorder(new LineBorder(Color.black, 1, true));    //원하는 라벨에 사용
		button1.setBounds(getWidth()/2 - 50, getHeight() / 2, 100, 20); // 필요에 따라 위치 및 크기 조정
		button2.setBounds(150, 0, 100, 20); // 필요에 따라 위치 및 크기 조정
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 int mouseX = e.getX();
	     int mouseY = e.getY();
//	     
//	     if (isMouseInsideRect(field.x + i * RECTANGLE_SIZE + 1, field.y + j * RECTANGLE_SIZE + 1, RECTANGLE_SIZE, RECTANGLE_SIZE, mouseX, mouseY)) {
//             
//             break;
//         }
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("x: " + mouse.x + " | y: " + mouse.y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!stop) {
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}