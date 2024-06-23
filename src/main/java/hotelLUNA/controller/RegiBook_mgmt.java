package hotelLUNA.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hotelLUNA.bean.Customer;
import hotelLUNA.dao.Db_regibook;
import hotelLUNA.dto.Customer_req;
import hotelLUNA.dto.Customer_res;
import hotelLUNA.dto.RegiBook_req;
import hotelLUNA.dto.Room_req;

@Controller
public class RegiBook_mgmt {
	
	@Autowired
	Db_regibook db;
	
			//-----------registration page-----------------------
	
	//welcome to registrationlist page
	@GetMapping("/registrationlist")
	public ModelAndView wbookinglist(HttpServletRequest request) {
		List<Customer_res> list = db.showallregi();
		request.getSession().setAttribute("rb", list);
		return new ModelAndView("registrationlist","rebo",new Customer());
	}
	
	//show specific registration at registratiionlist page
	@PostMapping("/searchrbcustomer")
	public String search(@ModelAttribute("rebo") Customer c,HttpServletRequest request) {
		Customer_req creq = new Customer_req();
		creq.setCname(c.getCname());
		RegiBook_req rbreq = new RegiBook_req();
		rbreq.setRbid(c.getRb().getRbid());
		creq.setRb(rbreq);
		
		List<Customer_res> list1 = db.specificreg(creq); 
		request.getSession().setAttribute("rb", list1);
 		return "registrationlist";
				
	}
	
	//to show specific registration data at registrationupdatepage 
		@GetMapping("/updatereg/{cid}/{rbdid}")
		public String toupdate(@PathVariable Map<String,String> pathvar,HttpServletRequest request,ModelMap model){

			Customer_req req = new Customer_req();
			req.setCid(pathvar.get("cid"));
			RegiBook_req rb = new RegiBook_req();
			rb.setRbdid(pathvar.get("rbdid"));
			
			req.setRb(rb);
			model.addAttribute("rebo",db.showatregupdatepage(req));
			return "registrationupdatepage";
		}
	
	//to update registration data
	@PostMapping("/updateregdata")
	public String updateregistration(@ModelAttribute("rebo") Customer customer) {
		
		Customer_req req = new Customer_req();
		req.setCid(customer.getCid());
		req.setCname(customer.getCname());
		req.setCemail(customer.getCemail());
		RegiBook_req rb= new RegiBook_req();
		Room_req room = new Room_req();
		rb.setRbid(customer.getRb().getRbid());
		rb.setRbdid(customer.getRb().getRbdid());
		
		rb.setCin(customer.getRb().getCin());
		rb.setCout(customer.getRb().getCout());
		req.setRb(rb);
		room.setRid(customer.getRoom().getRid());
		req.setRoom(room);
		System.out.println("customer "+customer.getRb().getRbdid());
		db.update_reg(req);
		
		
		
		return "redirect:/registrationlist";
	}
	
	// to delete registration data
		@GetMapping("/deletereg/{cid}/{rbid}")
		public String deleteregistration(@PathVariable Map<String,String> pathvar) {
			Customer_req req = new Customer_req();
			req.setCid(pathvar.get("cid"));
			RegiBook_req rb = new RegiBook_req();
			rb.setRbdid(pathvar.get("rbid"));
			
			req.setRb(rb);
			db.deletereg(req);
			
			return "redirect:/registrationlist";
		}
	
		//to set active/inactive and if customer is active room will be "not free" if inactive room will be "free"
		@GetMapping("/aandireg/{cid}/{rbdid}/{status}")
		public String activeAndinactive(@PathVariable Map<String,String> pathvar) {
			
			Customer_req req = new Customer_req();
			req.setCid(pathvar.get("cid"));
			RegiBook_req rb = new RegiBook_req();
			rb.setRbdid(pathvar.get("rbdid"));
			Room_req room = new Room_req();
			
			
			String status = pathvar.get("status");
			if(status.equals("active")) {
				
				rb.setStatus("inactive");
				room.setAvaliable("free");
			}else {
				rb.setStatus("active");
				room.setAvaliable("not free");
			}
			req.setRb(rb);
			req.setRoom(room);
			
			db.atvNinatvreg(req);
			
			return "redirect:/registrationlist";
		}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
		//-------------bookinglist--------------------
	
	//welcome to bookinglist
	@GetMapping("/bookinglist")	
	public ModelAndView bookinglist(HttpServletRequest request) {
		List<Customer_res> list = db.showallbooking();
		request.getSession().setAttribute("bk", list);
		
		return new ModelAndView("bookinglist","book",new Customer());
	}
	
	//show specific booking at bookinglist page
	@PostMapping("/searchbcustomer")
	public String searchbooking(@ModelAttribute("book") Customer c,HttpServletRequest request) {
		Customer_req creq = new Customer_req();
		creq.setCname(c.getCname());
		RegiBook_req rbreq = new RegiBook_req();
		rbreq.setRbdid(c.getRb().getRbid());
		creq.setRb(rbreq);
		
		List<Customer_res> list1 = db.specificbook(creq); 
		request.getSession().setAttribute("bk", list1);
 		return "bookinglist";
				
	}
	
	//to show specific booking data at bookingupdatepage 
		@GetMapping("/updatebook/{cid}/{rbdid}")
		public String toupdatebooking(@PathVariable Map<String,String> pathvar,HttpServletRequest request,ModelMap model){

			Customer_req req = new Customer_req();
			req.setCid(pathvar.get("cid"));
			RegiBook_req rb = new RegiBook_req();
			rb.setRbdid(pathvar.get("rbdid"));
			
			req.setRb(rb);
			model.addAttribute("book",db.showatbookupdatepage(req));
			return "bookingupdatepage";
		}
	
	//to update booking data
	@PostMapping("/updatebook")
	public String updatebooking(@ModelAttribute("book") Customer customer) {
		
		Customer_req req = new Customer_req();
		req.setCid(customer.getCid());
		req.setCname(customer.getCname());
		req.setCemail(customer.getCemail());
		RegiBook_req rb= new RegiBook_req();
		Room_req room = new Room_req();
		rb.setRbdid(customer.getRb().getRbid());
		
		
		rb.setCin(customer.getRb().getCin());
		rb.setCout(customer.getRb().getCout());
		req.setRb(rb);
		room.setRid(customer.getRoom().getRid());
		req.setRoom(room);
		
		db.update_book(req);
		
		return "redirect:/bookinglist";
	}
	
	// to delete booking data
		@GetMapping("/deletebook/{cid}/{rbid}")
		public String deletebooking(@PathVariable Map<String,String> pathvar) {
			Customer_req req = new Customer_req();
			req.setCid(pathvar.get("cid"));
			RegiBook_req rb = new RegiBook_req();
			rb.setRbdid(pathvar.get("rbid"));
			
			req.setRb(rb);
			db.deletebook(req);
			
			return "redirect:/bookinglist";
		}
	
	//to approve
	@GetMapping("/approve/{cid}/{rbdid}/{rbid}")
	public String approve(@PathVariable Map<String,String> pathvar) {
		Customer_res res = db.approve(Integer.valueOf(pathvar.get("cid")));
		
		db.insertintoregi(res);
		 Customer_res res1 = db.forregidetail(Integer.valueOf(pathvar.get("cid")));
		List<Customer_res> list = db.forregiid(Integer.valueOf(pathvar.get("rbid")));
		
		db.insertregidetail(res1,list);
		db.deleteafterapprove(Integer.valueOf(pathvar.get("cid")));
		return "redirect:/bookinglist";
	}
}
