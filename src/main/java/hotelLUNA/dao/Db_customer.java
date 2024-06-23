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


import hotelLUNA.bean.SearchCustomer;
import hotelLUNA.dto.Customer_req;
import hotelLUNA.dto.Customer_res;
import hotelLUNA.dto.RegiBook_res;
import hotelLUNA.dto.Room_res;

@Repository
public class Db_customer {

	@Autowired
	Connection con;
	
	//searching specific user where customerlist
	public List<Customer_res>  customer_Search(SearchCustomer sc) {
		List<Customer_res> list = new ArrayList<>();
		String search = "select c.customerID,c.customerName,c.email,r.checkin,r.checkout,re.roomID_regidetail,r.status,re.registrationID_regidetail,re.regidetailID from customer c \r\n"
				+ "right join registration r on c.customerID = r.customerID\r\n"
				+ "join registrationdetail re on r.registrationID = re.registrationID_regidetail\r\n"
				+ "where c.customerName=? and c.phone=?;";
		try {
			PreparedStatement ps = con.prepareStatement(search);
			ps.setString(1,sc.getCname());
			ps.setString(2,sc.getCphone());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Customer_res c = new Customer_res();
				c.setCid(String.valueOf(rs.getInt("c.customerID")));
				c.setCname(rs.getString("c.customerName"));
				c.setCemail(rs.getString("c.email"));
				RegiBook_res rb = new RegiBook_res();
				rb.setRbid(String.valueOf(rs.getInt("re.regidetailID")));
				rb.setCin(String.valueOf(rs.getString("r.checkin")));
				rb.setCout(String.valueOf(rs.getString("r.checkout")));
				rb.setStatus(rs.getString("r.status"));
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
	
	//to show all data in customer at customerlist page
	public List<Customer_res> showalldata_C() {
		List<Customer_res> list = new ArrayList<>();
		String search = "select c.customerID,c.customerName,c.email,r.checkin,r.checkout,re.roomID_regidetail,r.status,re.registrationID_regidetail,re.regidetailID from customer c \r\n"
				+ "right join registration r on c.customerID = r.customerID\r\n"
				+ "join registrationdetail re on r.registrationID = re.registrationID_regidetail;";
		try {
			PreparedStatement ps = con.prepareStatement(search);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Customer_res c = new Customer_res();
				c.setCid(String.valueOf(rs.getInt("c.customerID")));
				c.setCname(rs.getString("c.customerName"));
				c.setCemail(rs.getString("c.email"));
				RegiBook_res rb = new RegiBook_res();
				rb.setRbid(String.valueOf(rs.getInt("re.regidetailID")));
				rb.setCin(String.valueOf(rs.getString("r.checkin")));
				rb.setCout(String.valueOf(rs.getString("r.checkout")));
				rb.setStatus(rs.getString("r.status"));
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
	
	//to show specific customer data at customerupdatepage
	public Customer_res showatupdatepage(Customer_req req) {
		Customer_res c = null;
		
		String show ="select c.customerID,c.customerName,c.email,r.checkin,r.checkout,re.roomID_regidetail,r.status,re.regidetailID from customer c \r\n"
				+ "right join registration r on c.customerID = r.customerID\r\n"
				+ "join registrationdetail re on r.registrationID = re.registrationID_regidetail\r\n"
				+ "where c.customerID=? and re.regidetailID=?;";
		
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ps.setInt(1,Integer.valueOf(req.getCid()));
			ps.setInt(2,Integer.valueOf(req.getRb().getRbid()));
			
			
			ResultSet rs = ps.executeQuery();
				while(rs.next()) {
				
				c = new Customer_res();
				c.setCid(String.valueOf(rs.getInt("c.customerID")));
				c.setCname(rs.getString("c.customerName"));
				c.setCemail(rs.getString("c.email"));
				RegiBook_res rb = new RegiBook_res();
				rb.setRbid(String.valueOf(rs.getInt("re.regidetailID")));
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
	
	//to update customer data
	public void update_c(Customer_req req) {
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
			ps.setInt(7,Integer.valueOf(req.getRb().getRbid()));
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// to delete room data
	public void deleteroom(Customer_req req) {
		String delete = "delete c.*,re.*,r.* from  customer c right join registration r on c.customerID = r.customerID\r\n"
				+ " right join registrationdetail re on r.registrationID = re.registrationID_regidetail\r\n"
				+ " where c.customerID=? and re.regidetailID=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1,Integer.valueOf(req.getCid()));
			ps.setInt(2,Integer.valueOf(req.getRb().getRbid()));
			ps.execute();
	
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	//to set active/inactive and if customer is active room will be "not free" if inactive room will be "free" 
	public void atvNinatv(Customer_req req){
		String check = "update customer c right join registration r on c.customerID = r.customerID\r\n"
				+ "join registrationdetail re on r.registrationID = re.registrationID_regidetail\r\n"
				+ "join room ro on ro.roomID = re.roomID_regidetail set ro.available=?,r.status=?\r\n"
				+ " where c.customerID=? and re.regidetailID=?;";
		try {
			PreparedStatement ps = con.prepareStatement(check);
			ps.setString(1,req.getRoom().getAvaliable());
			ps.setString(2,req.getRb().getStatus());
			ps.setInt(3,Integer.valueOf(req.getCid()));
			ps.setInt(4,Integer.valueOf(req.getRb().getRbid()));
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
