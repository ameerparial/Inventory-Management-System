

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;

public class CreateAccountFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTF;
	private JTextField passTF;
	private JTextField firstnameTF;
	private JTextField lastnameTF;
	private JTextField emailTF;
	private JPanel loginPn;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JLabel lblCancel;
	ConnectionToDB connection = new ConnectionToDB();
	
	/**
	 * Create the frame.
	 */
	public CreateAccountFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 426, 488);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameTF = new JTextField();
		usernameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				if(usernameTF.getText().equals("Username")) {
					usernameTF.setText("");
					usernameTF.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(usernameTF.getText().equals("")) {
					usernameTF.setText("Username");
					usernameTF.setForeground(new Color(153, 153, 153));
				}
				
			}
		});
		usernameTF.setForeground(new Color(153, 153, 153));
		usernameTF.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		usernameTF.setText("Username");
		usernameTF.setBounds(64, 60, 287, 30);
		contentPane.add(usernameTF);
		usernameTF.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 51, 0));
		panel.setBounds(64, 11, 287, 30);
		contentPane.add(panel);
		
		JLabel titleLB = new JLabel("Create new Account");
		titleLB.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(titleLB);
		
		passTF = new JTextField();
		passTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(passTF.getText().equals("Password")) {
					passTF.setText("");
					passTF.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(passTF.getText().equals("")) {
					passTF.setText("Password");
					passTF.setForeground(new Color(153, 153, 153));
				}
			}
		});
		passTF.setForeground(new Color(153, 153, 153));
		passTF.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		passTF.setText("Password");
		passTF.setColumns(10);
		passTF.setBounds(64, 112, 287, 30);
		contentPane.add(passTF);
		
		firstnameTF = new JTextField();
		firstnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(firstnameTF.getText().equals("First Name")) {
					firstnameTF.setText("");
					firstnameTF.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(firstnameTF.getText().equals("")) {
					firstnameTF.setText("First Name");
					firstnameTF.setForeground(new Color(153,153,153));
				}
			}
		});
		firstnameTF.setForeground(new Color(153, 153, 153));
		firstnameTF.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		firstnameTF.setText("First Name");
		firstnameTF.setColumns(10);
		firstnameTF.setBounds(64, 168, 287, 30);
		contentPane.add(firstnameTF);
		
		lastnameTF = new JTextField();
		lastnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(lastnameTF.getText().equals("Last Name")) {
					lastnameTF.setText("");
					lastnameTF.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(lastnameTF.getText().equals("")) {
					lastnameTF.setText("Last Name");
					lastnameTF.setForeground(new Color(153, 153, 153));
				}
			}
		});
		lastnameTF.setForeground(new Color(153, 153, 153));
		lastnameTF.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lastnameTF.setText("Last Name");
		lastnameTF.setColumns(10);
		lastnameTF.setBounds(64, 225, 287, 30);
		contentPane.add(lastnameTF);
		
		emailTF = new JTextField();
		emailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(emailTF.getText().equals("Email Address")) {
					emailTF.setText("");
					emailTF.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(emailTF.getText().equals("")) {
					emailTF.setText("Email Address");
					emailTF.setForeground(new Color(153, 153, 153));
				}
			}
		});
		emailTF.setForeground(new Color(153, 153, 153));
		emailTF.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		emailTF.setText("Email Address");
		emailTF.setColumns(10);
		emailTF.setBounds(64, 279, 287, 30);
		contentPane.add(emailTF);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(64, 330, 287, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Manager");
		comboBox.addItem("Employee");
		
		loginPn = new JPanel();
		loginPn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				loginPn.setBackground(new Color(0, 204, 0));
				panel_2.setBackground(Color.GREEN);				
				System.out.println(usernameTF.getText());
				System.out.println(passTF.getText());
				System.out.println(firstnameTF.getText());
				System.out.println(lastnameTF.getText());
				System.out.println(emailTF.getText());
				String role  = (String)comboBox.getSelectedItem();
				connection.addEmployee(usernameTF.getText(), firstnameTF.getText(), lastnameTF.getText(), emailTF.getText(), role, passTF.getText());
				
			}
		});
		loginPn.setBackground(Color.GREEN);
		loginPn.setBounds(244, 396, 107, 23);
		contentPane.add(loginPn);
		
		lblNewLabel = new JLabel("Create");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
		loginPn.add(lblNewLabel);
		
		panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panel_2.setBackground(new Color(0, 204, 0));
				loginPn.setBackground(Color.GREEN);
				dispose();
				new LoginFrame().setVisible(true);	
		//		String role = comboBox.
			//	connection.addEmployee(usernameTF.getText(), firstnameTF.getText(), lastnameTF.getText(), emailTF.getText(), , passTF.getText());

				
			}
		});
		
		panel_2.setBackground(Color.GREEN);
		panel_2.setBounds(64, 396, 107, 23);
		contentPane.add(panel_2);
		
		lblCancel = new JLabel("Cancel");
		panel_2.add(lblCancel);
		
		
	}
}
