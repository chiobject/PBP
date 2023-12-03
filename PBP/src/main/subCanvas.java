package main;

import java.awt.*;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class subCanvas extends JPanel implements Runnable, MouseListener{
	protected boolean stop;
	private JLabel button1 = new JLabel("버튼 1");
	private JLabel button2 = new JLabel("버튼 2");
	private JLabel button3 = new JLabel("버튼 3");
	private JLabel button4 = new JLabel("버튼 4");
	protected Point mouse = new Point();
	int x,y;
	private Point select = new Point(x,y);
	protected Thread worker;
	private String field_Name;
	private LineBorder lineborder = new LineBorder(Color.black, 1, true);
	private Font fontTitle = new Font("굴림", Font.BOLD, 27);
	private Font fontButton = new Font("굴림", Font.BOLD, 20);
	private BufferedImage offScreenImage;
	
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
                gameGUI.getMap().field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y].button1();
            }
        });
		button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 클릭 시 수행할 동작을 여기에 추가
                gameGUI.getMap().field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y].button2();
            }
        });
		button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 클릭 시 수행할 동작을 여기에 추가
                gameGUI.getMap().field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y].button3();
            }
        });
		button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 클릭 시 수행할 동작을 여기에 추가
                gameGUI.getMap().field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y].button4();
            }
        });
		button1.setFont(fontButton);
		button2.setFont(fontButton);
		button3.setFont(fontButton);
		button4.setFont(fontButton);
		button1.setHorizontalAlignment(JLabel.CENTER);
		button1.setVerticalAlignment(JLabel.CENTER);
		button2.setHorizontalAlignment(JLabel.CENTER);
		button2.setVerticalAlignment(JLabel.CENTER);
		button3.setHorizontalAlignment(JLabel.CENTER);
		button3.setVerticalAlignment(JLabel.CENTER);
		button4.setHorizontalAlignment(JLabel.CENTER);
		button4.setVerticalAlignment(JLabel.CENTER);

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
		if (offScreenImage == null) {
            offScreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        Graphics offScreenGraphics = offScreenImage.getGraphics();
        
        super.paintComponent(offScreenGraphics);
        
		field_Name = gameGUI.getMap().field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y].name;
		offScreenGraphics.setColor(Color.lightGray);
		offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
		offScreenGraphics.setColor(Color.black);
		offScreenGraphics.drawRect(getWidth()/8, 50, getWidth()-100, getWidth()-100);
		
		offScreenGraphics.setFont(fontTitle);
		offScreenGraphics.drawString("["+field_Name+"]", getWidth()/2-43, getWidth()-5);
		
		switch(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y].getbuttonCount()) {
		case 0:
			System.out.println("d");
			button1.setText("");  // 버튼 텍스트 지우기
		    button2.setText("");
		    button3.setText("");
		    button4.setText("");
		    
		    button1.setBorder(null);  // 버튼 경계 초기화
		    button2.setBorder(null);
		    button3.setBorder(null);
		    button4.setBorder(null);
			break;
		case 1:
			gameGUI.getSubCanvas().getButton(1).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(1));
			button2.setText("");
		    button3.setText("");
		    button4.setText("");
		    
			button1.setBorder(lineborder); 
		    
			button2.setBorder(null);
			button3.setBorder(null);
			button4.setBorder(null);
			button1.setBounds(getWidth()/6, getHeight() / 2 + 130, 100, 50);
			break;
		
		case 2:
			gameGUI.getSubCanvas().getButton(1).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(1));
			gameGUI.getSubCanvas().getButton(2).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(2));
			button3.setText("");
			button4.setText("");
			
			button1.setBorder(lineborder);    
			button2.setBorder(lineborder); 
			
			button3.setBorder(null);
			button4.setBorder(null);
			button1.setBounds(getWidth()/6, getHeight() / 2 + 130, 100, 50); 
			button2.setBounds(getWidth()/2+35 , getHeight() / 2 + 130, 100, 50); 
			break;
			
		case 3:
			gameGUI.getSubCanvas().getButton(1).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(1));
			gameGUI.getSubCanvas().getButton(2).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(2));
			gameGUI.getSubCanvas().getButton(3).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(3));
			button4.setText("");
			
			button1.setBorder(lineborder);    
			button2.setBorder(lineborder);    
			button3.setBorder(lineborder);   
			button4.setBorder(null);
			
			button1.setBounds(getWidth()/6, getHeight() / 2 + 130, 100, 50); 
			button2.setBounds(getWidth()/2+35 , getHeight() / 2 + 130, 100, 50); 
			button3.setBounds(getWidth()/6, getHeight() / 2 + 210, 100, 50);
			System.out.println("3");
			break;
			
		case 4:
			gameGUI.getSubCanvas().getButton(1).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(1));
			gameGUI.getSubCanvas().getButton(2).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(2));
			gameGUI.getSubCanvas().getButton(3).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(3));
			gameGUI.getSubCanvas().getButton(4).setText(gameGUI.getMap().field[gameGUI.getMainCanvas().select.x]
					[gameGUI.getMainCanvas().select.y].getButtonName(4));
			
			button1.setBorder(lineborder);    
			button2.setBorder(lineborder);  
			button3.setBorder(lineborder); 
			button4.setBorder(lineborder);  
			
			button1.setBounds(getWidth()/6, getHeight() / 2 + 130, 100, 50); 
			button2.setBounds(getWidth()/2+35 , getHeight() / 2 + 130, 100, 50); 
			button3.setBounds(getWidth()/6, getHeight() / 2 + 210, 100, 50);
			button4.setBounds(getWidth()/2+35 , getHeight() / 2 + 210, 100, 50); 
			break;
		}
		
		offScreenGraphics.dispose();

        // 더블 버퍼링을 사용하여 이미지를 화면에 그립니다.
        g.drawImage(offScreenImage, 0, 0, this);
        
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 int mouseX = e.getX();
	     int mouseY = e.getY();
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
	
	public JLabel getButton(int number) {
		if(number == 1) {return button1;}
		else if(number==2) {return button2;}
		else if(number==3) {return button3;}
		else {return button4;}
		
	}
}