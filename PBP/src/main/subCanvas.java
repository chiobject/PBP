package main;

import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class subCanvas extends JPanel implements Runnable, MouseListener{
	protected boolean stop;
	
	protected Point mouse = new Point();

	protected Thread worker;
	
	subCanvas(){
		addMouseListener(this);
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
	
	public void paint(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.black);
		g.drawRect(30, 50, getWidth()-150, getWidth()-150);
		
		
		
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