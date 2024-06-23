package hotelLUNA.controller;

import java.util.Iterator;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hotelLUNA.bean.CustomBook;
import hotelLUNA.bean.Customer;
import hotelLUNA.bean.RoomBook;
import hotelLUNA.dao.Db_customRoomRegistration;
import hotelLUNA.dto.Customer_req;
import hotelLUNA.dto.Customer_res;
import hotelLUNA.dto.RegiBooking_req;
import hotelLUNA.dto.RegibookingDetail;
import hotelLUNA.email.Foremail;

@Controller
public class CustomerRoom_Registration {
	
	@Autowired
	Db_customRoomRegistration dao;
	
	@Autowired
	Foremail email;
	
	@GetMapping("/customRoomRegistration")
	public String crRegistration(HttpSession session,HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<RoomBook> list = (List<RoomBook>) session.getAttribute("list");
		if (list.isEmpty()) {
			return "redirect:/";
		}
		String cname = String.valueOf(session.getAttribute("cName")).toString();
		String cemail = String.valueOf(session.getAttribute("cEmail")).toString();
		String cphone = String.valueOf(session.getAttribute("cPhone")).toString();
		String cnrc =  String.valueOf(session.getAttribute("cNRCno")).toString();
		String cin =  String.valueOf(session.getAttribute("checkIn")).toString();
		String cout = String.valueOf(session.getAttribute("checkOut")).toString();
		String allPrice = String.valueOf(session.getAttribute("allTotalPrice")).toString();
		String status = "booking";
				
		Customer_req req = new Customer_req();
		req.setCname(cname);
		req.setCemail(cemail);
		req.setCphone(cphone);
		req.setCnrc(cnrc);
		int result = dao.customRoomRegi(req);
		if (result == 0) {
			System.out.println("Customer Insert Fail");
		}else {
			System.out.println("Customer Insert Successful");
		}
		Customer_res res = dao.selectCustomID(req);
		int customId = Integer.parseInt(res.getCid());
		RegiBooking_req re = new RegiBooking_req();
		re.setCheckIn(cin);
		re.setCheckOut(cout);
		re.setCustomID(customId);
		re.setAllTotalPrice(allPrice);
		re.setStatus(status);
		int ans = dao.regiBooking(re);
		if (ans == 0) {
			System.out.println("Booking Insert Fail");
		}else {
			System.out.println("Booking Insert Successful");
		}
		int bookingID = dao.getBookingId(re);
		Iterator<RoomBook> itr = list.iterator();
		while(itr.hasNext()) {
			RegibookingDetail reqb = new RegibookingDetail();
			reqb.setBookingID(bookingID);
			reqb.setBookingRoomID(Integer.parseInt(itr.next().getRoomId()));
			ans = dao.regiBookingDetail(reqb);
			if (ans == 0) {
				System.out.println("bookingDetail insert fail");
			}else {
				System.out.println("BookingDetail insert successful");
			}
		}
		request.getSession().setAttribute("id",res.getCid());
		Customer cus = new Customer();
		cus.setCid(res.getCid());
		cus.setCname(cname);
		cus.setCemail(cemail);
		request.setAttribute("custo",cus);
		CustomBook cc = new CustomBook();
		cc.setBookCheckIn(cin);
		email.sendemail(cus,cc);
		
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/cancelPage")
	public String cancel(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@SuppressWarnings("unchecked")
	@GetMapping("/cancelRoomPage/{roomId}/{roomPrice}")
	public String cancelRoom(@PathVariable  Map<String,String> pathvar,HttpSession session) {
		String roomId = pathvar.get("roomId");
		int roomTotalPrice = Integer.parseInt(pathvar.get("roomPrice"));
		List<RoomBook> list = (List<RoomBook>) session.getAttribute("list");
		Iterator<RoomBook> itr = list.iterator();
		while (itr.hasNext()) {
			if (itr.next().getRoomId().equals(roomId)) {
			itr.remove();
			}
		}
		String price = (String) session.getAttribute("allTotalPrice").toString();
		int allPrice = Integer.parseInt(price);
		session.removeAttribute("allTotalPrice");
		int allTotal = allPrice - roomTotalPrice;
		session.setAttribute("allTotalPrice", allTotal);
		return "redirect:/finalreceiptPage";
	}
	@GetMapping("/finalreceiptPage")
	public String finalPage() {
		return "finalreceipt";
	}
	@GetMapping("/tomail")
	public void toemail(HttpServletRequest request) {
		
		String i = (String) request.getSession().getAttribute("id");
		Customer cus = (Customer) request.getAttribute("custo");
	dao.deleteafterapprove(Integer.valueOf(i));
	email.thz(cus);	
	request.getSession().removeAttribute("id");
	}
}
