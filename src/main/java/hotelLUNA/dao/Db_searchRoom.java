package hotelLUNA.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelLUNA.bean.Room;
import hotelLUNA.bean.RoomBook;
import hotelLUNA.dto.SearchRoom_req;
import hotelLUNA.dto.SearchRoom_res;

@Service("db_Searchroom")
public class Db_searchRoom {

	@Autowired
	Connection con;
	 
		@SuppressWarnings("unchecked")
		public List<SearchRoom_res> Search_Room (SearchRoom_req dto,HttpSession session) {
			@SuppressWarnings("rawtypes")
			List<SearchRoom_res> list = new ArrayList();
			List<RoomBook> list2 = (List<RoomBook>) session.getAttribute("list");
			Iterator<RoomBook> itr;
			String sql = "select *"
					+ "from hotel_management_system.room r left join hotel_management_system.roomtype ro \r\n"
					+ "on r.roomtypeID = ro.roomtypeID\r\n"
					+ "left join hotel_management_system.registrationdetail rg on r.roomID = rg.roomID_regidetail\r\n"
					+ "left join hotel_management_system.registration re on rg.registrationID_regidetail = re.registrationID\r\n"
					+ "where re.checkin and re.checkout not between ? and ? \r\n" + " and re.status = ? or r.capacity = ? ;";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,dto.getCheckIn());
				ps.setString(2,dto.getCheckOut());
				ps.setString(3, "inactive");
				ps.setInt(4, dto.getAdultNumber()+dto.getChildrenNumber());
				ResultSet rs = ps.executeQuery();
				String ans = " ";
				while(rs.next()) {
					String roomid = String.valueOf(rs.getInt("r.roomID"));
					if (list2 != null) {
						itr = list2.iterator();
					while (itr.hasNext()) {
						String roomID = itr.next().getRoomId();
						if (roomID.equals(roomid)) {
							ans = "true";
						}
					}
					}
					if (!ans.equals("true")) {
					SearchRoom_res res = new SearchRoom_res();
					System.out.println(rs.getInt("r.roomID"));
					res.setRid(String.valueOf(rs.getInt("r.roomID")));
					res.setAvaliable(rs.getString("r.available"));
					res.setRcapacity(String.valueOf(rs.getInt("r.capacity")));
					res.setRprice(rs.getInt("r.price"));
					res.setRtypename(rs.getString("ro.type"));
					
					list.add(res);
					}
					ans = " ";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		public SearchRoom_res bookOneRoom(Room dto) {
			SearchRoom_res res = new SearchRoom_res();
			String sql = "select r.roomID,r.capacity,r.available,r.price,ro.type\r\n"
					+ " from hotel_management_system.room r \r\n"
					+ " join hotel_management_system.roomtype ro on r.roomtypeID = ro.roomtypeID\r\n"
					+ " where r.roomID =?;";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1,Integer.parseInt(dto.getRid()));
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					res.setRid(String.valueOf(rs.getInt("r.roomID")));
					res.setAvaliable(rs.getString("r.available"));
					res.setRcapacity(String.valueOf(rs.getInt("r.capacity")));
					res.setRprice(rs.getInt("r.price"));
					res.setRtypename(rs.getString("ro.type"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
}
