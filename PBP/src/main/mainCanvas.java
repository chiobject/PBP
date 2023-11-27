package main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class mainCanvas extends JPanel implements ActionListener, Runnable, MouseListener {
    private static final int RECTANGLE_SIZE = 64;
    public map map = new map();	
    
    private Thread worker;
    private Timer timer;
    private boolean stop;
    int x,y;
    Point field = new Point(x,y);
    
    ImageIcon seaicon2= new ImageIcon("C:\\Users\\Chiobject\\git\\PBP\\images\\background\\sea2.jpg");
    ImageIcon seaicon1 = new ImageIcon("C:\\Users\\Chiobject\\git\\PBP\\images\\background\\sea1.png");

    public mainCanvas() {
        setLayout(new GridLayout(9, 9));

        map.reset();
        map.create();

        // Adding MouseListener to the panel
        addMouseListener(this);
    }
    
    public void start() {
    	stop = false;
		worker = new Thread(this);
		worker.start();
		repaint();
    }
    public void paint(Graphics g) {
    	super.paint(g);
        timer = new Timer(1000, this);  // 1000ms마다 actionPerformed 호출
        timer.start();

        // 그림의 크기
        int imageWidth = map.max_x * RECTANGLE_SIZE;
        int imageHeight = map.max_y * RECTANGLE_SIZE;

        // 패널의 크기
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // 그림을 화면 중앙에 그리기 위한 좌표 계산
        field.x = (panelWidth - imageWidth) / 2;
        field.y = (panelHeight - imageHeight) / 2;

        // 그림 그리기
        for (int i = 0; i < map.max_x*2; i ++) {
        	for(int j = 0; j < map.max_y*1.5; j++) {
        		g.drawImage(seaicon1.getImage(),i*RECTANGLE_SIZE,j*RECTANGLE_SIZE-7,this);
        	}
        }

        for (int i = 0; i < map.max_x; i++) {
            for (int j = 0; j < map.max_y; j++) {
                if (map.field[i][j].type != 0)
                    g.drawRect(field.x + i * RECTANGLE_SIZE + 1, field.y + j * RECTANGLE_SIZE + 1, RECTANGLE_SIZE, RECTANGLE_SIZE);
                g.drawString(map.field[i][j].name, field.x + i * RECTANGLE_SIZE + 1, field.y + j * RECTANGLE_SIZE + RECTANGLE_SIZE);
            }
        }
        
        
        System.out.println("repaint");
    }

    // Implementing the mouseClicked method
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Check if the click is inside any field
        for (int i = 0; i < map.max_x; i++) {
            for (int j = 0; j < map.max_y; j++) {
                if (isMouseInsideRect(field.x + i * RECTANGLE_SIZE + 1, field.y + j * RECTANGLE_SIZE + 1, RECTANGLE_SIZE, RECTANGLE_SIZE, mouseX, mouseY)) {
                    System.out.println("Field Clicked at: (" + i + ", " + j + ") class : (" + map.field[i][j].name + ")");
                    break;
                }
            }
        }
    }
    
    public void run() {
    	while(!stop) {
    		repaint();
    		System.out.println("Hi!");
    		try {
				worker.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    // Helper method to check if the mouse is inside a rectangle
    private boolean isMouseInsideRect(int rectX, int rectY, int rectWidth, int rectHeight, int mouseX, int mouseY) {
        return mouseX >= rectX && mouseX <= rectX + rectWidth && mouseY >= rectY && mouseY <= rectY + rectHeight;
    }

    // Other methods from the interfaces (not implemented here)
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}
