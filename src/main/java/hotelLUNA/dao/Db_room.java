package hotelLUNA.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hotelLUNA.dto.Room_req;
import hotelLUNA.dto.Room_res;

@Repository
public class Db_room {

	@Autowired
	Connection con;

	//for inserting room
	public void	insertroom(Room_req req) {
		String insert = "insert into room(roomID,capacity,price,roomtypeID,available) values(?,?,?,?,?);";
		
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setInt(1,Integer.valueOf(req.getRid()));
			ps.setInt(2,Integer.valueOf(req.getRcapacity()));
			ps.setInt(3,Integer.valueOf(req.getRprice()));
			ps.setInt(4,Integer.valueOf(req.getRtype()));
			ps.setString(5,req.getAvaliable());
			
			ps.executeUpdate();
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
		
		//for showing all of room at searchroom page		
		public List<Room_res> showroom() {
		Room_res res = null;
		List<Room_res> list = new ArrayList<>();
		String show = "select * from room r join roomtype rt on r.roomtypeID = rt.roomtypeID;";
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				res = new Room_res();
				res.setRid(String.valueOf(rs.getInt("roomID")));
				res.setAvaliable(rs.getString("available"));
				res.setRcapacity(String.valueOf(rs.getInt("capacity")));
				res.setRprice(String.valueOf(rs.getInt("price")));
				res.setRtype(String.valueOf(rs.getInt("roomtypeID")));
				res.setRtypename(rs.getString("rt.type"));
				
				list.add(res);
				
			}
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
		//searching specific room 
		public List<Room_res> specificroom(Room_req req) {
			List<Room_res> list = new ArrayList<>();
			String search = "select * from room r join roomtype rt on r.roomtypeID = rt.roomtypeID where r.roomID=?;";
			Room_res res = null;
			try {
				PreparedStatement ps = con.prepareStatement(search);
				ps.setInt(1,Integer.valueOf(req.getRid()));
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					
					res = new Room_res();
					res.setRid(String.valueOf(rs.getInt("roomID")));
					res.setAvaliable(rs.getString("available"));
					res.setRcapacity(String.valueOf(rs.getInt("capacity")));
					res.setRprice(String.valueOf(rs.getInt("price")));
					res.setRtype(String.valueOf(rs.getInt("roomtypeID")));
					res.setRtypename(rs.getString("rt.type"));	
					
					list.add(res);	
				}
						
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				return list;
			}
		
		// to show specific room data at updateroom page
		public Room_res showsroom(Room_req req) {
			String search = "select * from room r join roomtype rt on r.roomtypeID = rt.roomtypeID where r.roomID=?;";
			Room_res res = null;
			try {
				PreparedStatement ps = con.prepareStatement(search);
				ps.setInt(1,Integer.valueOf(req.getRid()));
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					
					res = new Room_res();
					res.setRid(String.valueOf(rs.getInt("roomID")));
					res.setAvaliable(rs.getString("available"));
					res.setRcapacity(String.valueOf(rs.getInt("capacity")));
					res.setRprice(String.valueOf(rs.getInt("price")));
					res.setRtype(String.valueOf(rs.getInt("roomtypeID")));
					res.setRtypename(rs.getString("rt.type"));	
					
				
				}
						
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				return res;
			}
	
		//update room data
		public void updateroom(Room_req req) {
			String update = "update room set roomID=?,capacity=?,price=?,roomtypeID=?,available=? where roomID=?;";
			
			try {
				PreparedStatement ps = con.prepareStatement(update);
				ps.setInt(1,Integer.valueOf(req.getRid()));
				ps.setInt(2,Integer.valueOf(req.getRcapacity()));
				ps.setInt(3,Integer.valueOf(req.getRprice()));
				ps.setInt(4,Integer.valueOf(req.getRtype()));
				ps.setString(5,req.getAvaliable());
				ps.setInt(6,Integer.valueOf(req.getRid()));
				
				ps.executeUpdate();
					
			} catch (SQLException e) {
				
				e.printStackTrace();
			}						
		}
		
		// to delete room data
		public void deleteroom(int rid) {
			String delete = "delete from room where roomID=?";
		
			try {
				PreparedStatement ps = con.prepareStatement(delete);
				ps.setInt(1,rid);
				ps.execute();
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
