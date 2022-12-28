

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	ConnectionToDB con = new ConnectionToDB();

	public ItemFrame() {
		setTitle("LIST OF ITEMS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(0, 0, 610, 37);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("List Of Items");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(0, 286, 610, 81);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane itemTF = new JTextPane();
		itemTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (itemTF.getText().equals("Item")) {
					itemTF.setText("");
					itemTF.setForeground(Color.black);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (itemTF.getText().equals("")) {
					itemTF.setText("Item");
					itemTF.setForeground(Color.GRAY);
				}
			}
		});
		itemTF.setForeground(Color.GRAY);
		itemTF.setFont(new Font("Times New Roman", Font.BOLD, 14));
		itemTF.setBounds(0, 0, 246, 31);
		itemTF.setText("Item");
		panel_1.add(itemTF);
		
		JTextPane priceTF = new JTextPane();
		priceTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (priceTF.getText().equals("Price")) {
					priceTF.setText("");
					priceTF.setForeground(Color.black);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (priceTF.getText().equals("")) {
					priceTF.setText("Price");
					priceTF.setForeground(Color.GRAY);
				}
			}
		});
		priceTF.setText("Price");
		priceTF.setForeground(Color.GRAY);
		priceTF.setFont(new Font("Times New Roman", Font.BOLD, 14));
		priceTF.setBounds(0, 39, 246, 31);
		panel_1.add(priceTF);
		
		JButton addItemBtn = new JButton("Add Item");
		addItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double price = Double.parseDouble(priceTF.getText()); 
				con.addItem(itemTF.getText(), price);
				
			}
		});
		addItemBtn.setBounds(369, 0, 109, 23);
		panel_1.add(addItemBtn);
		
		JButton delBtn = new JButton("Delete Item");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con.deleteItem(itemTF.getText());
			}
		});
		delBtn.setBounds(369, 47, 109, 23);
		panel_1.add(delBtn);
		
		JButton utmBtn = new JButton("Update Item");
		utmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con.updateItem(itemTF.getText(), Integer.parseInt(priceTF.getText()));
			}
		});
		utmBtn.setBounds(491, 25, 109, 23);
		panel_1.add(utmBtn);
		
		table = new JTable();
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
		jsp.setBounds(0, 62, 610, 213);
		contentPane.add(jsp);
	}
}
