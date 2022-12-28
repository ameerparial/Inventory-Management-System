
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConnectionToDB {
	Connection con;
	Statement stat;
	int cid =0;
	
	public void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IMS", "root", "sukkur");
			stat = con.createStatement();	
		}catch(Exception e) {JOptionPane.showMessageDialog(null, "con: "+e.getMessage());}
		
	}
	
	public void close() {
		try {
			con.close();
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "close: "+e.getMessage());
		}
		
	}
	
	public void showTable() {
		getConnection();
		try {
			ResultSet rs = stat.executeQuery("Select first_name from employees limit 2");
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		close();
		
	}
	public int generateID() {
		getConnection();
		try {
			ResultSet rs = stat.executeQuery("Select count(*) from Customer");
			if (rs.next()) {
				return rs.getInt(1)+1;
			}
				
		}catch(Exception e) {JOptionPane.showMessageDialog(null, "gen: "+e.getMessage());}
		close();
		
		return 0;
	}
	
	public void addCustomer(String uname, String fname, String lname, String email, String address, String pwd) {
		getConnection();
		try {
			String query = "insert into customer values  (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, generateID());
			st.setString(2, fname);
			st.setString(3, lname);
			st.setString(4, uname);
			st.setString(5, pwd);
			st.setString(6, email);
			st.setString(7, address);
			
			int a = st.executeUpdate();
			
			if( a > 0) {
				JOptionPane.showMessageDialog(null, "Customer is added Successfully!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Customer is added Unsuccessfully!");
			}
			
			
		}catch(Exception e) {JOptionPane.showMessageDialog(null, "cus: "+e.getMessage());}
		
		
		close();
	
		
	}
	public int genID() {
		getConnection();
		try {
			ResultSet rs = stat.executeQuery("Select count(*) from Employees");
			if (rs.next()) {
				return rs.getInt(1)+1;
			}
				
		}catch(Exception e) {JOptionPane.showMessageDialog(null, "gen: "+e.getMessage());}
		close();
		
		return 0;
		
		
	}
	
	public void addEmployee(String uname, String fname, String lname, String email, String role, String pwd) {
		getConnection();
		try {
			String query = "insert into Employees values  (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, genID());
			st.setString(2, fname);
			st.setString(3, lname);
			st.setString(4, uname);
			st.setString(5, pwd);
			st.setString(6, email);
			st.setString(7, role);
			
			int a = st.executeUpdate();
			
			if( a > 0) {
				JOptionPane.showMessageDialog(null, "Employee is added Successfully!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Employee is added Unsuccessfully!");
			}
			
			
		}catch(Exception e) {JOptionPane.showMessageDialog(null, "cus: "+e.getMessage());}
		
		
		close();
		
	}
	
	public boolean getLogin(String uname, String pass) {
		getConnection();
		try {
			
			String query = "select * from customer where username='"+uname+"' AND password='"+pass+"'";
			ResultSet rs = stat.executeQuery(query);
			if(rs.next()) {
				new ShoppingCartFrame(rs.getInt(1)).setVisible(true);
				new LoginFrame().setVisible(false);
				return true;
			}
			
			else {
				//System.out.println("Ameer");
				query = "select role from Employees where username='"+uname+"' AND password='"+pass+"'";
				rs = stat.executeQuery(query);
				if(rs.next()) {
					if(rs.getString(1).equals("Manager")) {
						new ManagerEmpFrame("M").setVisible(true);
					}
					else
						new ManagerEmpFrame("E").setVisible(true);
					
					return true;
				}
				
				JOptionPane.showMessageDialog(null, "Either Username or password is incorrect!");
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		close();
		return false;
	}
	
	public int getID() {	
		getConnection();
		try {
			ResultSet rs = stat.executeQuery("Select count(*) from Item");
			if (rs.next()) {
				return rs.getInt(1)+1;
			}
				
		}catch(Exception e) {JOptionPane.showMessageDialog(null, "gen_Item: "+e.getMessage());}
		close();
		
		return 0;
		
	}
	
	public void addItem(String in, double price) {
		getConnection();
		try {
			
			String query = "insert into Item values  (?, ?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, getID());
			st.setString(2, in);
			st.setDouble(3, price);
			
			int a = st.executeUpdate();
		
			if( a > 0) {
				JOptionPane.showMessageDialog(null, "Item is added Successfully!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Item is added Unsuccessfully!");
			}
			
		}catch(Exception e) {JOptionPane.showMessageDialog(null, "cus: "+e.getMessage());}
		close();
	}
	
	public void deleteItem(String item) {
		getConnection();
		try {
			int a = stat.executeUpdate("Delete from Item where item_name='"+item+"'");
			if (a > 0) 
				JOptionPane.showMessageDialog(null, "Successfull! Deleted.");
			
			
		}catch(Exception e) {JOptionPane.showMessageDialog(null, e.getMessage());}
		
		
		close();
		
	}	

	public void addToCart(int cid, int item_id, int q, double price) {
		getConnection();
		try {
			String query = "insert into Cart values  (?, ?,  ?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, cid);
			st.setInt(2, item_id);
			st.setInt(3, q);
			st.setDouble(4, price);
			
			int a = st.executeUpdate();
		
			if( a > 0) {
				
				JOptionPane.showMessageDialog(null, "Added to Cart is added Successfully!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Added to Cart is added Unsuccessfully!");
			}
			
		}catch(Exception e) {JOptionPane.showMessageDialog(null, "cus: "+e.getMessage());}	
			
	}
	
	public ArrayList<String> getItems() {
		getConnection();
		ArrayList<String> list = new ArrayList<>(); 
		try {
			String query = "Select Item_name from Item";
			ResultSet rs = stat.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		close();
		
		return list; 
	}
	public double getPrice(String item) {
		getConnection();
		try {
			String query = "Select Price from item where item_name='"+item+"'";
			ResultSet rs = stat.executeQuery(query);
			if(rs.next())
				return rs.getDouble(1);
			
		}catch(Exception e) {
			
		}
		
		close();
		return 0;
	}
	public int getItemID(String item) {
		getConnection();
		try {
			String query = "Select itemID from item where item_name='"+item+"'";
			ResultSet rs = stat.executeQuery(query);
			if(rs.next())
				return rs.getInt(1);
			
		}catch(Exception e) {
			
		}
		close();
		return 0;
	}
	
	public ArrayList getList(int cid){
		
		getConnection();
		ArrayList list = new ArrayList();
		try {
			String q = "select item_name, qty, cart.price from cart inner join item on item.itemid = cart.itemid and cart.cid ='"+cid+"'";
			ResultSet rs = stat.executeQuery(q);
			while(rs.next()) {
				list.add(rs.getString(1));
				list.add(rs.getInt(2));
				list.add(rs.getDouble(3));
			}
			
		}catch(Exception e) {}
		
		
		close();
		
		
		return list;
	}
	
	public void deleteCart(int id, String item) {
		getConnection();
		try {
			int i = getItemID(item);
			System.out.println("id: "+i);
			String query = "Delete from Cart where itemID='"+i+"' AND  cid='"+id+"'";
			i = stat.executeUpdate(query);
			if( i > 0) 
				JOptionPane.showMessageDialog(null, "Deleted from Cart is added Successfully!");
			
		}catch(Exception e) {
			
		}
		
		close();
	}
	
	public double goToPend(int cid) {
		getConnection();
		double price = 0;
		try {
			String q = "Select * from Cart where cid ='"+cid+"'";
			ResultSet rs = stat.executeQuery(q);
			while(rs.next()) {
				q = "Insert into Pending values (?, ? , ? ,?)";
				PreparedStatement prst = con.prepareStatement(q);
				prst.setInt(1, rs.getInt(1));
				prst.setInt(2, rs.getInt(2));
				prst.setInt(3, rs.getInt(3));
				prst.setDouble(4, rs.getDouble(4));
				
				price+= rs.getInt(3)*rs.getDouble(4);
				int i = prst.executeUpdate();
				//if( i > 0) 
					//JOptionPane.showMessageDialog(null, "Added to Pending Successfully!");

			}
			q = "Delete from Cart where cid ='"+cid+"'";
			int a = stat.executeUpdate(q);
			
			
			
		}catch(Exception e) {}
		
		
		close();
		return price;
		
	}
	
	public void deletePend() {
		getConnection();
		try {
			
			int a = stat.executeUpdate("delete from Pending");
			
			
		}catch(Exception e) {}
		
		close();
	}
	
	public void updateItem(String item, double price) {
		getConnection();
		try {
			PreparedStatement prst = con.prepareStatement("Update item set price='"+price+"' where item_name='"+item+"'");
			int a = prst.executeUpdate();
			if(a>0) {
				System.out.println("Updated");
			}
			
			
			
			
		}catch(Exception e) {}
		
		close();
		
	}

}
