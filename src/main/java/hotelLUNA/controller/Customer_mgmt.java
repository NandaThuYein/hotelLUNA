package hotelLUNA.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hotelLUNA.bean.Customer;
import hotelLUNA.bean.SearchCustomer;
import hotelLUNA.dao.Db_customer;
import hotelLUNA.dto.Customer_req;
import hotelLUNA.dto.Customer_res;
import hotelLUNA.dto.RegiBook_req;
import hotelLUNA.dto.Room_req;


@Controller
public class Customer_mgmt {

	@Autowired
	Db_customer db;
	
	// to customerlist
	@GetMapping("/customerlist")
	public ModelAndView customerlist(HttpServletRequest request){
		List<Customer_res> list = db.showalldata_C();
		request.getSession().setAttribute("cus",list);
		return new ModelAndView("customerlistpage","customer",new SearchCustomer() );
	}
	
	
	//to search specific customer at customerlistpage
	@PostMapping("/searchCustomer")
	public String search_customer(@ModelAttribute("customer") @Valid SearchCustomer sc,BindingResult res,HttpServletRequest request) {
		if(res.hasErrors()) {
			return "customerlistpage";
		}else {
			if(db.customer_Search(sc)!=null) {
				List<Customer_res> list = db.customer_Search(sc);
				request.setAttribute("cus",list);
				return "customerlistpage";
			}else {
				request.setAttribute("error","NOT FOUND!!!");
				return "customerlistpage";
				}
			}
		}
	
	//to show specific customer data at customerupdatepage 
	@GetMapping("/updateCustomer/{cid}/{rbid}")
	public String toupdate(@PathVariable Map<String,String> pathvar,HttpServletRequest request,ModelMap model){

		Customer_req req = new Customer_req();
		req.setCid(pathvar.get("cid"));
		RegiBook_req rb = new RegiBook_req();
		rb.setRbid(pathvar.get("rbid"));
		
		req.setRb(rb);
		model.addAttribute("customer",db.showatupdatepage(req));
		return "customerupdatepage";
	}
	
	//to update customer data
	@PostMapping("/update")
	public String updateCustomer(@ModelAttribute("customer") Customer customer) {
		
		Customer_req req = new Customer_req();
		req.setCid(customer.getCid());
		req.setCname(customer.getCname());
		req.setCemail(customer.getCemail());
		RegiBook_req rb= new RegiBook_req();
		Room_req room = new Room_req();
		rb.setRbid(customer.getRb().getRbid());
		
		
		rb.setCin(customer.getRb().getCin());
		rb.setCout(customer.getRb().getCout());
		req.setRb(rb);
		room.setRid(customer.getRoom().getRid());
		req.setRoom(room);
		
		db.update_c(req);
		
		return "redirect:/customerlist";
	}
	
	// to delete customer data
	@GetMapping("/deletecustomer/{cid}/{rbid}")
	public String deletecustomer(@PathVariable Map<String,String> pathvar) {
		Customer_req req = new Customer_req();
		req.setCid(pathvar.get("cid"));
		RegiBook_req rb = new RegiBook_req();
		rb.setRbid(pathvar.get("rbid"));
		
		req.setRb(rb);
		db.deleteroom(req);
		
		return "redirect:/customerlist";
	}
	
	//to set active/inactive and if customer is active room will be "not free" if inactive room will be "free"
	@GetMapping("/aandi/{cid}/{rbid}/{status}")
	public String activeAndinactive(@PathVariable Map<String,String> pathvar) {
		
		Customer_req req = new Customer_req();
		req.setCid(pathvar.get("cid"));
		RegiBook_req rb = new RegiBook_req();
		rb.setRbid(pathvar.get("rbid"));
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
		
		db.atvNinatv(req);
		
		return "redirect:/customerlist";
	}
}
