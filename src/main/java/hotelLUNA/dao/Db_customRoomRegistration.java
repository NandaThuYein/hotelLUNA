package hotelLUNA.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hotelLUNA.dto.Customer_req;
import hotelLUNA.dto.Customer_res;
import hotelLUNA.dto.RegiBooking_req;
import hotelLUNA.dto.RegibookingDetail;

@Repository
public class Db_customRoomRegistration {
	
	@Autowired
	Connection con;
	 
        public int customRoomRegi(Customer_req dto) {
        	int result = 0;
        	String sql = "insert into customer(customerName,email,phone,nrc)"
        					+"values(?,?,?,?);";
        	try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, dto.getCname());
				ps.setString(2, dto.getCemail());
				ps.setString(3, dto.getCphone());
				ps.setString(4, dto.getCnrc());
				result = ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Database insert error");
			}
        	return result;
        }
        
        public Customer_res selectCustomID(Customer_req dto) {
        	Customer_res res = new Customer_res();
        	String sql = "select * from hotel_management_system.customer where phone = ?";
        	try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, dto.getCphone());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					res.setCid(String.valueOf(rs.getInt("customerID")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	return res;
        }
        public int regiBooking(RegiBooking_req dto) {
        	int result = 0;
        	String sql = "insert into hotel_management_system.booking(bookingcheckin,bookingcheckout,bookingcustomerID,bookingTotalPrice,bookingstatus)"
        					+"values(?,?,?,?,?);";
        	try {
				PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1,dto.getCheckIn());
					ps.setString(2, dto.getCheckOut());
					ps.setInt(3, dto.getCustomID());
					ps.setString(4, dto.getAllTotalPrice());
					ps.setString(5, dto.getStatus());
				result = ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Database insert error");
			}
        	return result;
        }
        
        public int getBookingId(RegiBooking_req dto) {
        	int bookingId = 0;
        	String sql = "select * from hotel_management_system.booking where bookingcustomerID = ?";
        	try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1,dto.getCustomID());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					bookingId = rs.getInt("bookingID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("bookingID select fail");
			}
        	return bookingId;
        }
        
        public int regiBookingDetail(RegibookingDetail dto) {
        	int result = 0;
        	String sql = "insert into hotel_management_system.bookingdetail(bookingID_bookingdetail,roomID_bookingdetail)"
        					+"values(?,?);";
        	try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, dto.getBookingID());
				ps.setInt(2, dto.getBookingRoomID());
				
				result = ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Database insert error");
			}
        	return result;
        }
      //cancel booking process
    	public void deleteafterapprove(int i) {
    		String delete = "delete be.*,b.*,c.* from  customer c right join booking b on c.customerID = b.bookingcustomerID\r\n"
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
