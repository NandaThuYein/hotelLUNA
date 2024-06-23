package hotelLUNA.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import hotelLUNA.dto.Admin_req;
import hotelLUNA.dto.Admin_res;



@Repository
public class Db_admin {
	
	@Autowired
	Connection con;
	
	// log in and log out 
	public Admin_res  login(Admin_req req) {
		Admin_res res = null;
		String in = "select * from admin "
				+ "where adminID=? and adminPassword=?;";
		try {
			PreparedStatement ps = con.prepareStatement(in);
			ps.setInt(1,Integer.parseInt(req.getId()));
			ps.setString(2,req.getPassword());
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				res = new Admin_res();
				res.setId(rs.getString("adminID"));
				res.setAname(rs.getString("adminName"));
				res.setPassword(rs.getString("adminPassword"));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//to insert admin
	public void insertadmin(Admin_req req) {
		String insert = "insert into admin(adminID,adminName,adminPassword) values(?,?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setInt(1,Integer.valueOf(req.getId()));
			ps.setString(2,req.getAname());
			ps.setString(3,req.getPassword());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// show all admin data at admin page
	public List<Admin_res> showalladmin(){
		Admin_res res = null;
		List<Admin_res> list = new ArrayList<>();
		String show = "select * from admin;";
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				res =  new Admin_res();
				res.setId(String.valueOf(rs.getInt("adminID")));
				res.setAname(rs.getString("adminName"));
				res.setPassword(rs.getString("adminPassword"));
				
				list.add(res);
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
		}
		
	// show specific admin data at admin page
	public List<Admin_res> showoneadmin(Admin_req req){
		List<Admin_res> list = new ArrayList<>();
		Admin_res res = null;
		String show = "select * from admin where adminID=? and adminName=?;";
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ps.setInt(1,Integer.valueOf(req.getId()));
			ps.setString(2,req.getAname());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new Admin_res();
				res.setId(String.valueOf(rs.getInt("adminID")));
				res.setAname(rs.getString("adminName"));
				res.setPassword(rs.getString("adminPassword"));
				
				list.add(res);
				}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Admin_res showone(Admin_req req){
		Admin_res res = null;
		String show = "select * from admin where adminID=?;";
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ps.setInt(1,Integer.valueOf(req.getId()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new Admin_res();
				res.setId(String.valueOf(rs.getInt("adminID")));
				res.setAname(rs.getString("adminName"));
				res.setPassword(rs.getString("adminPassword"));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return res;
	}
	
	// update admin data
	public void updateadmin(Admin_req req) {
		
		String insert = "update admin set adminID=?,adminName=?,adminPassword=? where adminID=?;";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			
			ps.setInt(1,Integer.valueOf(req.getId()));
			ps.setString(2,req.getAname());
			ps.setString(3,req.getPassword());
			ps.setInt(4,Integer.valueOf(req.getId()));
			ps.executeUpdate();
			
			System.out.println(req.getAname()+req.getId()+req.getPassword());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// delete admin data
	public void deleteadmin(Admin_req req) {
		String delete = "delete from admin where adminID=?; ";
		
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1,Integer.valueOf(req.getId()));
			ps.execute();	
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
}
