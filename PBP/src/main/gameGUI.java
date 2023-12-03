package main;

import java.awt.*;
import javax.swing.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class gameGUI {

    private JFrame frame;
    private JTextArea logTextArea;  // logTextArea를 멤버 변수로 이동
    private static map map = new map();	
    private static mainCanvas maincanvas = new mainCanvas();
    private static subCanvas subcanvas = new subCanvas();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    gameGUI window = new gameGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public gameGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300,810);
        frame.setResizable(false);

        // Canvas를 패널에 추가
        JPanel mainPanel = new JPanel();
        maincanvas.start();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(maincanvas, BorderLayout.CENTER);
        mainPanel.setPreferredSize(new Dimension(900, 700));
        frame.getContentPane().add(mainPanel);

        // subPanel 추가
        JPanel subPanel = new JPanel();
        subcanvas.start();
        subPanel.setLayout(new BorderLayout());
        subPanel.add(subcanvas, BorderLayout.CENTER);
        subPanel.setPreferredSize(new Dimension(400, 700));
        frame.getContentPane().add(subPanel, BorderLayout.EAST);

        // logPanel 추가
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setPreferredSize(new Dimension(1300, 110));
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        logPanel.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(logPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menu = new JMenu("메뉴");
        menuBar.add(menu);

        JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
        menu.add(mntmNewMenuItem);

        // System.out 리디렉션
//        PrintStream printStream = new PrintStream(new CustomOutputStream(logTextArea));
//        PrintStream customOut = new CustomPrintStream(logTextArea);
//        System.setOut(printStream);
//        System.setOut(customOut);
//        System.setErr(printStream);

        frame.setVisible(true);
    }

    public void appendLog(String log) {
        logTextArea.append(log + "\n");
    }

    // CustomOutputStream 클래스 정의
//    class CustomPrintStream extends PrintStream {
//        private JTextArea textArea;
//
//        public CustomPrintStream(JTextArea textArea) {
//            super(new CustomOutputStream(textArea));
//            this.textArea = textArea;
//        }
//
//        @Override
//        public void println(String x) {
//            // JTextArea에 문자열을 추가
//            textArea.append(x + "\n");
//            // JTextArea를 스크롤하여 가장 최근에 추가된 텍스트를 표시
//            textArea.setCaretPosition(textArea.getDocument().getLength());
//        }
//    }
//    
//    class CustomOutputStream extends OutputStream {
//        private JTextArea textArea;
//
//        public CustomOutputStream(JTextArea textArea) {
//            this.textArea = textArea;
//        }
//
//        @Override
//        public void write(int b) throws IOException {
//            // JTextArea에 바이트를 추가
//            textArea.append(String.valueOf((char) b));
//            // JTextArea를 스크롤하여 가장 최근에 추가된 텍스트를 표시
//            textArea.setCaretPosition(textArea.getDocument().getLength());
//        }
//    }
    
    public static map getMap() {
    	return map;
    }
    
    public static mainCanvas getMainCanvas() {
		return maincanvas;
	}
	
    public static subCanvas getSubCanvas() {
		return subcanvas;
	}
	
}
