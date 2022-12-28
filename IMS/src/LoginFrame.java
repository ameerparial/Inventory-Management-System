

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	ConnectionToDB con = new ConnectionToDB();

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 402);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean t = con.getLogin(textField.getText(), passwordField.getText());
				if(t) {
					setVisible(false);
				}
				
			}
		});
		loginBtn.setBackground(Color.ORANGE);
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginBtn.setBounds(55, 251, 109, 23);
		contentPane.add(loginBtn);
		
		JButton createBtn = new JButton("Create Account");
		createBtn.setBackground(Color.ORANGE);
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CreateCusAcc().setVisible(true);
				
			}
		});
		createBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		createBtn.setBounds(55, 301, 270, 23);
		contentPane.add(createBtn);
		
		JButton logoutBtn = new JButton("Exit");
		logoutBtn.setBackground(Color.ORANGE);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		logoutBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logoutBtn.setBounds(210, 251, 115, 23);
		contentPane.add(logoutBtn);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(51, 43, 109, 25);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(50, 84, 275, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(51, 148, 109, 25);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(51, 183, 275, 39);
		contentPane.add(passwordField);
	}
	public static void main(String[] rf) {
		new LoginFrame().setVisible(true);
	}
}
