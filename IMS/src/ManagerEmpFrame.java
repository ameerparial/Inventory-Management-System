

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerEmpFrame extends JFrame {

	private JPanel contentPane;
	ConnectionToDB con = new ConnectionToDB();

	public ManagerEmpFrame(String role) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 334);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pendingPn = new JPanel();
		pendingPn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to clear Pending Orders?", "Clear Pending Orders", JOptionPane.YES_NO_CANCEL_OPTION);
				if ( a == JOptionPane.YES_OPTION ) {
					con.deletePend();
					JOptionPane.showMessageDialog(null, "Successfully Cleared");
					
				}
				else {
					JOptionPane.showMessageDialog(null, "NOT DONE!");
				}
			}
		});
		pendingPn.setBackground(Color.GREEN);
		pendingPn.setBounds(123, 118, 144, 42);
		contentPane.add(pendingPn);
		
		JLabel lblNewLabel = new JLabel("Mark Pending Order");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		pendingPn.add(lblNewLabel);
		
		JPanel addEmpPn = new JPanel();
		addEmpPn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CreateAccountFrame  ob = new CreateAccountFrame();
				ob.setVisible(true);
				
			}
		});
		addEmpPn.setBackground(Color.GREEN);
		addEmpPn.setBounds(340, 118, 144, 42);
		contentPane.add(addEmpPn);
		
		JLabel lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setFont(new Font("Tahoma", Font.BOLD, 14));
		addEmpPn.add(lblAddEmployee);
		
		JPanel addCusPn = new JPanel();
		addCusPn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new CreateCusAcc().setVisible(true);				
			}
		});
		addCusPn.setBackground(Color.GREEN);
		addCusPn.setBounds(123, 207, 144, 42);
		contentPane.add(addCusPn);
		
		JLabel lblAddCustomer = new JLabel("Add Customer");
		lblAddCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		addCusPn.add(lblAddCustomer);
		
		JPanel itemModPn = new JPanel();
		itemModPn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ItemFrame().setVisible(true);
			}
		});
		itemModPn.setBackground(Color.GREEN);
		itemModPn.setBounds(340, 207, 144, 42);
		contentPane.add(itemModPn);
		
		JLabel addItemPn = new JLabel("Item Modification");
		addItemPn.setFont(new Font("Tahoma", Font.BOLD, 14));
		itemModPn.add(addItemPn);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(0, 0, 650, 42);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel usernameLB = new JLabel("Username");
		usernameLB.setForeground(Color.WHITE);
		usernameLB.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLB.setBounds(491, 11, 149, 26);
		panel.add(usernameLB);
		
		JButton lgBtn = new JButton("Logout");
		lgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame().setVisible(true);	
			}
		});
		lgBtn.setBounds(505, 261, 89, 23);
		contentPane.add(lgBtn);

		//this should be when Employee is login
		if(role.equals("E")) {
			usernameLB.setText("Employee Login");
			this.remove(addEmpPn);
			itemModPn.setBounds(itemModPn.getBounds().x, itemModPn.getBounds().y-50, itemModPn.getBounds().width, itemModPn.getBounds().height);
		}
		else {
			usernameLB.setText("Manager Login");
			this.remove(itemModPn);
		}
		
	}
	
	
}
