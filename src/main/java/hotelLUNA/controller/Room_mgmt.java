package hotelLUNA.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hotelLUNA.bean.Room;
import hotelLUNA.dao.Db_room;
import hotelLUNA.dto.Room_req;
import hotelLUNA.dto.Room_res;

@Controller
public class Room_mgmt {

	@Autowired
	Db_room db;
	
	//welcome to addroom page
	@GetMapping("/addroom")
	public ModelAndView addroom(HttpServletRequest request) {
		List<Room_res> list1 = db.showroom();
		request.getSession().setAttribute("rm",list1);
		return new ModelAndView("addroom","room",new Room());
	}
	
	//to insert room 
	@PostMapping("/insetroom")
	public String insertroom(@ModelAttribute("room") Room room) {
		Room_req req= new Room_req();
		req.setRid(room.getRid());
		req.setRcapacity(room.getRcapacity());
		req.setRprice(room.getRprice());
		req.setAvaliable("free");
		req.setRtype(room.getRtype());
		
		db.insertroom(req);
		
		return "redirect:/addroom";
	}
	
	//welcome to searchroom page
	@GetMapping("/searchroom")
	public ModelAndView searchroom(HttpServletRequest request) {
		List<Room_res> list1 = db.showroom();
		request.getSession().setAttribute("rm",list1);
		return new ModelAndView("searchroom","room",new Room());
	}
	
	//to search specific room data at searchroom page
	@GetMapping("/searchspecificroom")
	public String searchsroom(@ModelAttribute("room") Room room,HttpServletRequest request) {
		Room_req req = new Room_req();
		req.setRid(room.getRid());
		List<Room_res> list = db.specificroom(req);
		request.getSession().setAttribute("rm",list);
		System.out.println("this is room id "+ room.getRid());
		return "searchroom";
	}
	
	//to show specific room data at updateroom page
	@GetMapping("/updateroom/{rid}")
	public String showagain(@PathVariable int rid,HttpServletRequest request,ModelMap model) {
		String id = String.valueOf(rid);
		Room_req req = new Room_req();
		req.setRid(id);
		model.addAttribute("room",db.showsroom(req));
		return"updateroom";
	}
	
	//to update room data
	@PostMapping("/updateroomdata")
	public String update(@ModelAttribute("room") Room room) {
		Room_req req = new Room_req();
		req.setRid(room.getRid());
		req.setRcapacity(room.getRcapacity());
		req.setRprice(room.getRprice());
		req.setRtype(room.getRtype());
		req.setAvaliable(room.getAvaliable());
		db.updateroom(req);
	
		return "redirect:/searchroom";
	}
	
	//to delet room data
	@GetMapping("/deleteroomdata/{rid}")
	public String delete(@PathVariable int rid) {
		db.deleteroom(rid);
		
		return "redirect:/searchroom";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
