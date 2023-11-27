//package main;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import field.field;
//
//public class data {
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> createAndShowGUI());
//	}
//
//	private static void createAndShowGUI() {
//		JFrame frame = new JFrame("Square Grid Example");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLayout(new GridLayout(9, 9));
//
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				field field = new field(0,"daf"); // 각 정사각형이 가지는 Field 객체 생성
//				SquarePanel squarePanel = new SquarePanel(i, j, field);
//				squarePanel.setPreferredSize(new Dimension(50, 50));
//				squarePanel.addMouseListener(new SquareClickListener(squarePanel));
//				frame.add(squarePanel);
//			}
//		}
//
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//	}
//
//	static class SquarePanel extends JPanel {
//		private final int row;
//		private final int col;
//		private final field field;
//
//		public SquarePanel(int row, int col, field field) {
//			this.row = row;
//			this.col = col;
//			this.field = field;
//		}
//	}
//
//	static class SquareClickListener extends MouseAdapter {
//		private final SquarePanel squarePanel;
//
//		public SquareClickListener(SquarePanel squarePanel) {
//			this.squarePanel = squarePanel;
//		}
//
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			System.out.println("Clicked at row: " + squarePanel.row + ", col: " + squarePanel.col);
//			// 클릭 이벤트에 대한 추가 로직을 작성할 수 있습니다.
//			// field 변수를 통해 SquarePanel이 가진 Field 객체에 접근 가능
//		}
//	}
//}