//package server2;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//public class Login extends JFrame implements ActionListener {
//    JButton okButton, cancelButton;
//    JTextField ipTextField, nameTextField;
//    JLabel ipLabel, nameLabel;
//
//    public Login() {
//        super("Login");
//    }
//
//    public void initialize() {
//        JPanel mainPanel = new JPanel();
//
//        nameLabel = new JLabel("Name:");
//        ipLabel = new JLabel("Address:");
//        
//
//        nameTextField = new JTextField(15);
//        ipTextField = new JTextField(15);
//        ipTextField.setText("127.0.0.1");
//        JPanel northPanel = new JPanel(new GridLayout(2, 2));
//        northPanel.add(nameLabel);
//        northPanel.add(nameTextField);
//        northPanel.add(ipLabel);
//        northPanel.add(ipTextField);
//
//        mainPanel.add(northPanel);
//
//        okButton = new JButton("OK");
//        cancelButton = new JButton("Cancel");
//
//        JPanel southPanel = new JPanel(new GridLayout(1, 2));
//        southPanel.add(okButton);
//        southPanel.add(cancelButton);
//
//        getContentPane().add(mainPanel, BorderLayout.CENTER);
//        getContentPane().add(southPanel, BorderLayout.SOUTH);
//
//        okButton.addActionListener(this);
//        cancelButton.addActionListener(this);
//        nameTextField.addActionListener(this);
//        ipTextField.addActionListener(this);
//
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent we) {
//                System.exit(0);
//            }
//        });
//
//        Toolkit kit = getToolkit();
//        Dimension dmen = kit.getScreenSize();
//        setSize(400, 300);
//        setLocation((int) ((dmen.width - getSize().width) / 2), (int) ((dmen.height - getSize().height)) / 2);
//        setResizable(false);
//
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object o = e.getSource();
//        if (o.equals(okButton)) {
//            String host;
//            String name;
//            host = ipTextField.getText().trim();
//            name = nameTextField.getText().trim();
//            Client test = new Client();
//            test.makeConnection(host, name);
//            test.initialize();
//            setVisible(false);
//        } else if (o.equals(cancelButton)) {
//            JLabel contents = new JLabel("Exiting the application.");
//            JOptionPane.showMessageDialog(this, contents, "Notification", JOptionPane.INFORMATION_MESSAGE);
//            setVisible(false);
//            dispose();
//        }
//    }
//
//    public static void main(String[] args) {
//        Login test = new Login();
//       test.initialize();
//    }
//}