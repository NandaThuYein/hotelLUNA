package hotelLUNA.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hotelLUNA.dto.Customer_req;
import hotelLUNA.dto.Customer_res;
import hotelLUNA.dto.RegiBook_res;
import hotelLUNA.dto.Room_res;

@Repository
public class Db_regibook {

	@Autowired
	Connection con;
	 
	
		//----registration-------
	
	//to show all registration table at registrationlist
	public List<Customer_res> showallregi(){
		String show = "select * from customer c right join registration r on c.customerID = r.customerID\r\n"
				+ " right join registrationdetail re on r.registrationID = re.registrationID_regidetail ";
		List<Customer_res> list = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Customer_res c = new Customer_res();
				c.setCid(String.valueOf(rs.getInt("c.customerID")));
				c.setCname(rs.getString("c.customerName"));
				c.setCemail(rs.getString("c.email"));
				RegiBook_res rb = new RegiBook_res();
				rb.setRbdid(String.valueOf(rs.getInt("re.regidetailID")));
				rb.setRbid(String.valueOf(rs.getInt("r.registrationID")));
				rb.setCin(String.valueOf(rs.getString("r.checkin")));
				rb.setCout(String.valueOf(rs.getString("r.checkout")));
				rb.setStatus(rs.getString("r.status"));
				rb.setTprice(String.valueOf(rs.getInt("r.totalprice")));
				Room_res r= new Room_res();
				r.setRid(rs.getString("re.roomID_regidetail"));
				c.setRb(rb);
				c.setRoom(r);
				
				list.add(c);
			}
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return list;
	}
	
	//to show specific registration data at registration list
	public List<Customer_res> specificreg(Customer_req req) {
		List<Customer_res> list = new ArrayList<>();
		String show = "select * from customer c right join registration r on c.customerID = r.customerID\r\n"
				+ " right join registrationdetail re on r.registrationID = re.registrationID_regidetail \r\n"
				+ " where c.customerName =? and r.registrationID =?;\r\n"
				+ "";
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ps.setString(1,req.getCname());
			ps.setInt(2,Integer.valueOf(req.getRb().getRbid()));
			
			ResultSet rs = ps.executeQuery();
			
				while(rs.next()){
					Customer_res c = new Customer_res();
					c.setCid(String.valueOf(rs.getInt("c.customerID")));
					c.setCname(rs.getString("c.customerName"));
					c.setCemail(rs.getString("c.email"));
					RegiBook_res rb = new RegiBook_res();
					rb.setRbdid(String.valueOf(rs.getInt("re.regidetailID")));
					rb.setRbid(String.valueOf(rs.getInt("r.registrationID")));
					rb.setCin(String.valueOf(rs.getString("r.checkin")));
					rb.setCout(String.valueOf(rs.getString("r.checkout")));
					rb.setStatus(rs.getString("r.status"));
					rb.setTprice(String.valueOf(rs.getInt("r.totalprice")));
					Room_res r= new Room_res();
					r.setRid(rs.getString("re.roomID_regidetail"));
					c.setRb(rb);
					c.setRoom(r);
					
					list.add(c);
				}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
		
	}
	
	//to show specific customer data at registrationupdatepage
		public Customer_res showatregupdatepage(Customer_req req) {
			Customer_res c = null;
			
			String show ="select c.customerID,c.customerName,c.email,r.registrationID,r.checkin,r.checkout,re.roomID_regidetail,r.status,re.regidetailID from customer c \r\n"
					+ "right join registration r on c.customerID = r.customerID\r\n"
					+ "join registrationdetail re on r.registrationID = re.registrationID_regidetail\r\n"
					+ "where c.customerID=? and re.regidetailID=?;";
			
			try {
				PreparedStatement ps = con.prepareStatement(show);
				ps.setInt(1,Integer.valueOf(req.getCid()));
				ps.setInt(2,Integer.valueOf(req.getRb().getRbdid()));
				
				
				ResultSet rs = ps.executeQuery();
					while(rs.next()) {
					
					c = new Customer_res();
					c.setCid(String.valueOf(rs.getInt("c.customerID")));
					c.setCname(rs.getString("c.customerName"));
					c.setCemail(rs.getString("c.email"));
					RegiBook_res rb = new RegiBook_res();
					rb.setRbid(String.valueOf(rs.getInt("r.registrationID")));
					rb.setRbdid(String.valueOf(rs.getInt("re.regidetailID")));
					rb.setCin(String.valueOf(rs.getString("r.checkin")));
					rb.setCout(String.valueOf(rs.getString("r.checkout")));
					rb.setStatus(rs.getString("r.status"));
					Room_res r= new Room_res();
					r.setRid(rs.getString("re.roomID_regidetail"));
					c.setRb(rb);
					c.setRoom(r);
				}
					
			} catch (SQLException e) {
				
				e.printStackTrace();
			}		
			return c;
		}
	
	//to update registration data
	public void update_reg(Customer_req req) {
		Date cin = Date.valueOf(req.getRb().getCin());
		Date cout = Date.valueOf(req.getRb().getCout());
		String update = "update customer c right join registration r on c.customerID = r.customerID\r\n"
				+ "join registrationdetail re on r.registrationID = re.registrationID_regidetail\r\n"
				+ "set c.customerName=?,c.email=?,r.checkin=?,r.checkout=?,re.roomID_regidetail=?\r\n"
				+ " where c.customerID=? and re.regidetailID=?;";
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1,req.getCname());
			ps.setString(2,req.getCemail());
			ps.setDate(3,cin);
			ps.setDate(4,cout);
			ps.setInt(5,Integer.valueOf(req.getRoom().getRid()));
			ps.setInt(6,Integer.valueOf(req.getCid()));
			ps.setInt(7,Integer.valueOf(req.getRb().getRbdid()));
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// to delete registration data
		public void deletereg(Customer_req req) {
			String delete = "delete c.*,re.*,r.* from  customer c right join registration r on c.customerID = r.customerID\r\n"
					+ " right join registrationdetail re on r.registrationID = re.registrationID_regidetail\r\n"
					+ " where c.customerID=? and re.regidetailID=?";
			
			try {
				PreparedStatement ps = con.prepareStatement(delete);
				ps.setInt(1,Integer.valueOf(req.getCid()));
				ps.setInt(2,Integer.valueOf(req.getRb().getRbdid()));
				ps.execute();
		
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		//to set active/inactive and if customer is active room will be "not free" if inactive room will be "free" 
		public void atvNinatvreg(Customer_req req){
			String check = "update customer c right join registration r on c.customerID = r.customerID\r\n"
					+ "join registrationdetail re on r.registrationID = re.registrationID_regidetail\r\n"
					+ "join room ro on ro.roomID = re.roomID_regidetail set ro.available=?,r.status=?\r\n"
					+ " where c.customerID=? and re.regidetailID=?;";
			try {
				PreparedStatement ps = con.prepareStatement(check);
				ps.setString(1,req.getRoom().getAvaliable());
				ps.setString(2,req.getRb().getStatus());
				ps.setInt(3,Integer.valueOf(req.getCid()));
				ps.setInt(4,Integer.valueOf(req.getRb().getRbdid()));
				
				ps.executeUpdate();
				
				System.out.println("db cid "+req.getCid());
				System.out.println("db  rbid "+req.getRb().getRbdid());
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
			//---------bookinglist----------------
	
	//to show all bookinglist data at bookinglist	
		public List<Customer_res> showallbooking(){
			String show = "select * from customer c right join booking b on c.customerID = b.bookingcustomerID\r\n"
					+ " right join bookingdetail be on b.bookingID  = be.bookingID_bookingdetail\r\n"
					+ " join room room on room.roomID = be.roomID_bookingdetail;";
			List<Customer_res> list = new ArrayList<>();
			
			try {
				PreparedStatement ps = con.prepareStatement(show);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Customer_res c = new Customer_res();
					c.setCid(String.valueOf(rs.getInt("c.customerID")));
					c.setCname(rs.getString("c.customerName"));
					c.setCemail(rs.getString("c.email"));
					RegiBook_res rb = new RegiBook_res();
					rb.setRbdid(String.valueOf(rs.getInt("be.bookingdetailID")));
					rb.setRbid(String.valueOf(rs.getInt("b.bookingID")));
					rb.setCin(String.valueOf(rs.getDate("b.bookingcheckin")));
					rb.setCout(String.valueOf(rs.getDate("b.bookingcheckout")));
					rb.setStatus(rs.getString("b.bookingstatus"));
					rb.setTprice(String.valueOf(rs.getInt("b.bookingtotalprice")));
					Room_res r= new Room_res();
					r.setRid(rs.getString("be.roomID_bookingdetail"));
					c.setRb(rb);
					c.setRoom(r);
					
					list.add(c);
				}
				
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
			return list;
		}
	
		//to show specific registration data at booking list
		public List<Customer_res> specificbook(Customer_req req) {
			List<Customer_res> list = new ArrayList<>();
			
			String show = "select * from customer c right join booking b on c.customerID = b.bookingcustomerID\r\n"
					+ " right join bookingdetail be on b.bookingID = be.bookingID_bookingdetail \r\n"
					+ " where c.customerName =? and b.bookingID =?;\r\n"
					+ "";
			
			try {
				PreparedStatement ps = con.prepareStatement(show);
				ps.setString(1,req.getCname());
				ps.setInt(2,Integer.valueOf(req.getRb().getRbdid()));
				
				ResultSet rs = ps.executeQuery();
				
					while(rs.next()){
						Customer_res c = new Customer_res();
						c.setCid(String.valueOf(rs.getInt("c.customerID")));
						c.setCname(rs.getString("c.customerName"));
						c.setCemail(rs.getString("c.email"));
						RegiBook_res rb = new RegiBook_res();
						rb.setRbdid(String.valueOf(rs.getInt("be.bookingdetailID")));
						rb.setRbid(String.valueOf(rs.getInt("b.bookingID")));
						rb.setCin(String.valueOf(rs.getDate("b.bookingcheckin")));
						rb.setCout(String.valueOf(rs.getDate("b.bookingcheckout")));
						rb.setStatus(rs.getString("b.bookingstatus"));
						rb.setTprice(String.valueOf(rs.getInt("b.bookingtotalprice")));
						Room_res r= new Room_res();
						r.setRid(rs.getString("be.roomID_bookingdetail"));
						c.setRb(rb);
						c.setRoom(r);
						
						list.add(c);
					}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return list;
			
		}
		
		//to show specific customer data at bookingupdatepage
			public Customer_res showatbookupdatepage(Customer_req req) {
				Customer_res c = null;

				String show ="select * from customer c \r\n"
						+ "right join booking b on c.customerID = b.bookingcustomerID\r\n"
						+ "join bookingdetail be on b.bookingID = be.bookingID_bookingdetail\r\n"
						+ "where c.customerID=? and be.bookingdetailID=?;";
				
				try {
					PreparedStatement ps = con.prepareStatement(show);
					ps.setInt(1,Integer.valueOf(req.getCid()));
					ps.setInt(2,Integer.valueOf(req.getRb().getRbdid()));
					
					
					ResultSet rs = ps.executeQuery();
						while(rs.next()) {
						
						c = new Customer_res();
						c.setCid(String.valueOf(rs.getInt("c.customerID")));
						c.setCname(rs.getString("c.customerName"));
						c.setCemail(rs.getString("c.email"));
						RegiBook_res rb = new RegiBook_res();
						rb.setRbdid(String.valueOf(rs.getInt("be.bookingdetailID")));
						rb.setRbid(String.valueOf(rs.getInt("b.bookingID")));
						rb.setCin(String.valueOf(rs.getDate("b.bookingcheckin")));
						rb.setCout(String.valueOf(rs.getDate("b.bookingcheckout")));
						rb.setStatus(rs.getString("b.bookingstatus"));
						Room_res r= new Room_res();
						r.setRid(rs.getString("be.roomID_bookingdetail"));
						c.setRb(rb);
						c.setRoom(r);
						
					}
						
				} catch (SQLException e) {
					
					e.printStackTrace();
				}		
				return c;
			}
		
		//to update registration data
		public void update_book(Customer_req req) {
			Date cin = Date.valueOf(req.getRb().getCin());
			Date cout = Date.valueOf(req.getRb().getCout());
		
			String update = "update customer c right join booking b on c.customerID = b.bookingcustomerID\r\n"
					+ "join bookingdetail be on b.bookingID = be.bookingID_bookingdetail\r\n"
					+ "set c.customerName=?,c.email=?,b.bookingcheckin=?,b.bookingcheckout=?,be.roomID_bookingdetail=?\r\n"
					+ " where c.customerID=? and  be.bookingdetailID=?;";
			try {
				PreparedStatement ps = con.prepareStatement(update);
				ps.setString(1,req.getCname());
				ps.setString(2,req.getCemail());
				ps.setDate(3,cin);
				ps.setDate(4,cout);
				ps.setInt(5,Integer.valueOf(req.getRoom().getRid()));
				ps.setInt(6,Integer.valueOf(req.getCid()));
				ps.setInt(7,Integer.valueOf(req.getRb().getRbdid()));
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// to delete registration data
			public void deletebook(Customer_req req) {
				String delete = "delete c.*,be.*,b.* from  customer c right join booking b on c.customerID = b.bookingcustomerID\r\n"
						+ " right join bookingdetail be on b.bookingID = be.bookingID_bookingdetail\r\n"
						+ " where c.customerID=? and  be.bookingdetailID=?";
				
				try {
					PreparedStatement ps = con.prepareStatement(delete);
					ps.setInt(1,Integer.valueOf(req.getCid()));
					ps.setInt(2,Integer.valueOf(req.getRb().getRbdid()));
					ps.execute();
			
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
	
	//approve
	public Customer_res approve(int i) {
		Customer_res c = null;
		String show =" select b.* from customer c right join booking b on c.customerID = b.bookingcustomerID\r\n"
				+ " where c.customerID=?";
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ps.setInt(1,i);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			
			c = new Customer_res();
			c.setCid(String.valueOf(rs.getInt("b.bookingcustomerID")));
			RegiBook_res rb = new RegiBook_res();
			rb.setCin(String.valueOf(rs.getDate("b.bookingcheckin")));
			rb.setCout(String.valueOf(rs.getDate("b.bookingcheckout")));
			rb.setTprice(rs.getString("b.bookingtotalprice"));
			rb.setStatus("active");
			c.setRb(rb);
		
//			System.out.println("data  :"+c.getCid());
//			System.out.println(c.getRb().getTprice());
//			System.out.println(c.getRb().getCin());
//			System.out.println(c.getRb().getCout());

			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	// insert into regi table while approve processing
	public void insertintoregi(Customer_res res) {
		
		Date cin = Date.valueOf(res.getRb().getCin());
		
		Date cout = Date.valueOf(res.getRb().getCout());
		String insert = "insert into registration(checkin,checkout,customerID,totalprice,status)\r\n"
				+ " values(?,?,?,?,?);";
		
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setDate(1,cin);
			ps.setDate(2, cout);
			ps.setInt(3,Integer.valueOf(res.getCid()));
			ps.setString(4,res.getRb().getTprice());
			ps.setString(5,res.getRb().getStatus());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	// to get registratin id 
	public Customer_res forregidetail(int i) {
		Customer_res c = null;
	
		String show = "select * from registration where customerID =?";
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ps.setInt(1,i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			
			c = new Customer_res();
			RegiBook_res res = new RegiBook_res();
			res.setRbid(String.valueOf(rs.getInt("registrationID")));
			c.setRb(res);
			}
			
	
			
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return c;
	}
	
	//to get room id 
	public List<Customer_res> forregiid(int i) {
		Customer_res c = null;
		List<Customer_res> list = new ArrayList<Customer_res>();
		String show = "select * from bookingdetail where bookingID_bookingdetail =?";
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ps.setInt(1,i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			
			c = new Customer_res();
			Room_res ro = new Room_res();
			ro.setRid(String.valueOf(rs.getInt("roomID_bookingdetail")));
			c.setRoom(ro);
			System.out.println(c.getRoom().getRid());
			list.add(c);
			}
			
			
			
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void insertregidetail(Customer_res res,List<Customer_res> list) {

		String insert = "insert into registrationdetail (registrationID_regidetail,roomID_regidetail)\r\n"
				+ " values(?,?);";
		
		try {
			for(Customer_res data : list) {
				PreparedStatement ps = con.prepareStatement(insert);
			ps.setInt(1,Integer.valueOf(res.getRb().getRbid()));
			ps.setInt(2,Integer.valueOf(data.getRoom().getRid()));
			ps.executeUpdate();
			
			System.out.println(res.getRb().getRbid());
			System.out.println(data.getRoom().getRid());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public void deleteafterapprove(int i) {
		String delete = "delete be.*,b.* from  customer c right join booking b on c.customerID = b.bookingcustomerID\r\n"
				+ " right join bookingdetail be on b.bookingID = be.bookingID_bookingdetail\r\n"
				+ " where c.customerID=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1,i);
			
			ps.execute();
	
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
}
