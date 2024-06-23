package hotelLUNA.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hotelLUNA.bean.Admin;
import hotelLUNA.dao.Db_admin;
import hotelLUNA.dao.Db_customer;
import hotelLUNA.dao.Db_room;
import hotelLUNA.dto.Admin_req;
import hotelLUNA.dto.Admin_res;
import hotelLUNA.dto.Customer_res;
import hotelLUNA.dto.Room_res;


@Controller
public class Admin_mgmt {
	
	//-------login and logout----------------
	
	@Autowired
	Db_admin db;

	@Autowired
	Db_customer cdb;
	
	@Autowired
	Db_room rdb;
	//
	@GetMapping("/adminpage")
	public ModelAndView welcome() {
		return new ModelAndView("adminLogin","admin",new Admin());
	}
	
	@GetMapping("/adminViewPage")
	public String adminPage() {
		return "adminpage";
	}
	
	@PostMapping("/process")
	public String login(@ModelAttribute("admin") @Valid Admin lio,BindingResult res,HttpServletRequest request,HttpSession session) {
		
		if(res.hasErrors()) {
			
			List<ObjectError> list = res.getAllErrors();
			for(ObjectError er : list) {
				System.out.println(er);}			
			return "adminLogin";
		}else {
			Admin_req  req = new Admin_req();
			req.setId(lio.getId());
			req.setPassword(lio.getPassword());
			
			if(db.login(req)!=null) {
				Admin_res admin = db.login(req);
				session.setAttribute("adminName",admin.getAname());
			// for showing all customer data
				List<Customer_res> list = cdb.showalldata_C();
				request.getSession().setAttribute("cus",list);
				
				//for showing all room data
				List<Room_res> list1 = rdb.showroom();
				request.getSession().setAttribute("rm",list1);
				//for login admin account
				request.getSession().setAttribute("loginacc",db.login(req));				
				return "redirect:/adminViewPage";
			}else {
				request.setAttribute("wrong","account is invalid");
				return "adminLogin";
			}	
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,HttpSession session){
		session.invalidate();
		request.getSession().invalidate();
	System.out.println("this is session"+request.getSession().getAttribute("loginacc"));	
		return "redirect:/";
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
			//---admin-------
	
	//to insert admin data
	@GetMapping("/admin")
	public ModelAndView welcomea(HttpServletRequest request) {
		
		List<Admin_res> list = db.showalladmin();
		request.getSession().setAttribute("ad",list);
		return new ModelAndView("adminsearch","admin",new Admin());
	}
	@GetMapping("/register")
		public ModelAndView inserta(HttpServletRequest request) {
		
		return new ModelAndView("adminRegister","admin",new Admin());
	}
	
	
	@PostMapping("/updateadmin")
	public String inseta(@ModelAttribute("admin") Admin admin) {
		Admin_req req = new Admin_req();
		req.setId(admin.getId());
		req.setAname(admin.getAname());
		req.setPassword(admin.getPassword());
		
		db.updateadmin(req);
		
		return "redirect:/admin";
	}
	
	@PostMapping("/search")
	public String search(@ModelAttribute("admin") Admin admin,HttpServletRequest request) {
		Admin_req req = new Admin_req();
		req.setId(admin.getId());
		req.setAname(admin.getAname());
		List<Admin_res> list1 = db.showoneadmin(req);
		request.setAttribute("ad",list1);
		return "adminsearch";
	}
	
	@GetMapping("/toupdate/{id}")
	public String update(@PathVariable int id,Model model ) {
		Admin_req req = new Admin_req();
		req.setId(String.valueOf(id));
		Admin_res res = db.showone(req);
		model.addAttribute("admin", res);
		return "adminRegister";
	}
	
	@GetMapping("/toinsert")
public ModelAndView adminR(HttpServletRequest request) {
		
		return new ModelAndView("adminR","admin",new Admin());
	}
	
	@PostMapping("/insertadmin")
	public String inset(@ModelAttribute("admin") Admin admin) {
		Admin_req req = new Admin_req();
		req.setId(admin.getId());
		req.setAname(admin.getAname());
		req.setPassword(admin.getPassword());
		
		db.insertadmin(req);
		
		return "redirect:/admin";
	}
	
	@GetMapping("/deletea/{id}")
	public String delete(@PathVariable int id ) {
		Admin_req req = new Admin_req();
		req.setId(String.valueOf(id));
		db.deleteadmin(req);
		
		return "adminRegister";
	}
}
