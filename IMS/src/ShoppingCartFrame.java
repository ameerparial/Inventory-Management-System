

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class ShoppingCartFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	int noOfRows = 1;
	DefaultTableModel model;
	ConnectionToDB con = new ConnectionToDB();
	int item_id =0;
	double totalPrice =0;

	public ShoppingCartFrame(int cid) {
		setBackground(Color.ORANGE);
		setTitle("Shopping Cart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 502);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//setting Table
		
		table = new JTable();
		Object[] cols = {"Sr.", "Item", "Quantity", "Price"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(cols);
		table.setModel(model);
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.red);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setRowHeight(20);
		table.getAutoCreateRowSorter();
	
		//table.setBounds(36, 195, 722, 193);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(36, 192, 722, 213);
		contentPane.add(jsp);
		//Initiallizing table
		
		ArrayList list2 = con.getList(cid);
		System.out.println(list2.size());
//		int j =0;
//		while(!list2.isEmpty()) {
//			if(j<list2.size()) { 
//				Object[] rowData = {noOfRows++, list2.remove(0), list2.remove(1), list2.remove(2)};
//				System.out.println(Arrays.toString(rowData));
//				
//			}
//			j++;
//		//	model.addRow(rowData);	
//		}
		for(int i = 0;i<list2.size();i = i) {
			Object[] arr = new Object[4];
			arr[0] = noOfRows++;
			arr[1] = list2.get(i++);
			arr[2] = list2.get(i++);
			arr[3] = list2.get(i++);
			
			model.addRow(arr);
		}
		
		JComboBox comboBox = new JComboBox();
		ArrayList<String> list = con.getItems();
		for(int i=0;i<list.size();i++) {
			comboBox.addItem(list.get(i));	
		}
		comboBox.setBounds(168, 55, 590, 22);
		
		contentPane.add(comboBox);
		
		JTextPane quantityTF = new JTextPane();
		quantityTF.setBackground(SystemColor.window);
		quantityTF.setBounds(76, 94, 93, 28);
		contentPane.add(quantityTF);
		
		JButton addCartBtn = new JButton("Add to Cart");
		addCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String item = (String)comboBox.getSelectedItem();
				int q = Integer.parseInt(quantityTF.getText());
				double price =con.getPrice(item);
				item_id = con.getItemID(item);
				con.addToCart(cid, item_id, q, price);
				Object[] rowData = {noOfRows++, item, q, price};
				totalPrice = q*price+totalPrice;
				model.addRow(rowData);
				
				
			}
		});
		addCartBtn.setBackground(Color.ORANGE);
		addCartBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		addCartBtn.setBounds(627, 160, 131, 23);
		contentPane.add(addCartBtn);
		
		JButton checkoutBtn = new JButton("Check Out");
		checkoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Store the current order
				//Clear the cart
				//Show message
				//System.out.println(totalPrice);
				totalPrice = con.goToPend(cid);
				JOptionPane.showMessageDialog(null, "Your order is successfully Completed! Total Price : "+totalPrice);
				
				
			}
		});
		checkoutBtn.setBackground(Color.ORANGE);
		checkoutBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		checkoutBtn.setBounds(36, 416, 117, 23);
		contentPane.add(checkoutBtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 791, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel usernameLb = new JLabel("Customer Login");
		usernameLb.setFont(new Font("Times New Roman", Font.BOLD, 18));
		usernameLb.setBounds(634, 11, 147, 14);
		panel.add(usernameLb);
		
		JButton delItemBtn = new JButton("Delete Item");
		delItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				if(selected>=0) {
					//con.remove();
					int row = table.getSelectedRow();
					String item = (String)model.getValueAt(row, 1);
					System.out.println(cid+"  "+item);
					con.deleteCart(cid, item);
					Integer q = (Integer)model.getValueAt(row, 2);
					Double price = (Double) model.getValueAt(row, 3);
					model.removeRow(table.getSelectedRow());
					totalPrice -= q*price;
				}
				else
					JOptionPane.showMessageDialog(null, "First Select row then press Delete!");
			}
		});
		delItemBtn.setBackground(Color.ORANGE);
		delItemBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		delItemBtn.setBounds(641, 416, 117, 23);
		contentPane.add(delItemBtn);
		
		JLabel lblNewLabel = new JLabel("QTY");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setBounds(36, 94, 46, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SELECT ITEM");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(36, 59, 122, 18);
		contentPane.add(lblNewLabel_1);
		
		JButton lgBtn = new JButton("Logout");
		lgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame().setVisible(true);	
			}
		});
		lgBtn.setBounds(513, 417, 89, 23);
		contentPane.add(lgBtn);
		
	}
}
