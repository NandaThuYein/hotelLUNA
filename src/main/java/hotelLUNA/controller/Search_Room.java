package hotelLUNA.controller;

import java.util.ArrayList;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotelLUNA.bean.CustomBook;
import hotelLUNA.bean.Room;
import hotelLUNA.bean.RoomBook;
import hotelLUNA.bean.SearchRoom;
import hotelLUNA.dao.Db_searchRoom;
import hotelLUNA.dto.SearchRoom_req;
import hotelLUNA.dto.SearchRoom_res;

@SuppressWarnings("unused")
@Controller
public class Search_Room {
	@Autowired
	Db_searchRoom dao;
	@GetMapping("/")
	public ModelAndView homePage() {
		return new ModelAndView ("index","sroom",new SearchRoom());
	}
	
	@PostMapping("/homeSearchRoom")
	public String homeSearchRoom(@ModelAttribute("sroom") @Validated SearchRoom searchRoom,BindingResult br,ModelMap model,RedirectAttributes redirectAttribute,HttpSession session) {
		if (br.hasErrors()) {
			return "index";
		}else {
			SearchRoom_req dto = new SearchRoom_req();
			dto.setCheckIn(searchRoom.getCheckIn());
			dto.setCheckOut(searchRoom.getCheckOut());
			dto.setAdultNumber(searchRoom.getAdultNumber());
			dto.setChildrenNumber(searchRoom.getChildrenNumber());
			List<SearchRoom_res> list = dao.Search_Room(dto,session);
			if (list.size() == 0) {
				model.addAttribute("error","No data found");
				return "index";
			}else {
				redirectAttribute.addFlashAttribute("cIn",searchRoom.getCheckIn());
				redirectAttribute.addFlashAttribute("cOut",searchRoom.getCheckOut());
				redirectAttribute.addFlashAttribute("adultN",searchRoom.getAdultNumber());
				redirectAttribute.addFlashAttribute("cNum",searchRoom.getChildrenNumber());
				redirectAttribute.addFlashAttribute("list",list);
			return "redirect:/roomPage";
			}
		}
	}
	@GetMapping("/roomPage")
	public ModelAndView roomPage () {
	return new ModelAndView ("room","sroom",new SearchRoom());
	}
	@GetMapping("/bookServlet/{rid}/{cIn}/{cOut}/{adultN}/{cNum}")
	public ModelAndView BookServlet(@PathVariable  Map<String,String> pathvar ,RedirectAttributes redirect,ModelMap model) {
		Room room = new Room();
		room.setRid(pathvar.get("rid"));
		model.addAttribute("checkIn",pathvar.get("cIn"));
		model.addAttribute("checkOut",pathvar.get("cOut"));
		model.addAttribute("aNumber",pathvar.get("adultN"));
		model.addAttribute("cNumber",pathvar.get("cNum"));
		SearchRoom_res res = dao.bookOneRoom(room);
		model.addAttribute("roomid",res.getRid());
		model.addAttribute("type",res.getRtypename());
		model.addAttribute("price",res.getRprice());
		return new ModelAndView ("Booking","book",new CustomBook());
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/customBooking")
	public String CustomBooking(@ModelAttribute("book") @Validated CustomBook bean,RedirectAttributes redirect,BindingResult bs,HttpSession session,ModelMap model) {
		if (bs.hasErrors()) {
			return "Booking";
		}
		RoomBook room = new RoomBook();
		room.setRoomId(bean.getRoomId());
		room.setRoomTotalPrice(bean.getBookTotalPrice());
		room.setRoomType(bean.getRoomType());
		room.setPerNight(bean.getPerNight());
		room.setAdultNumber(bean.getAdultNumber());
		room.setChildNumber(bean.getChildNumber());
		
		session.setAttribute("cName", bean.getCustomName());
		session.setAttribute("cEmail", bean.getCustomEmail());
		session.setAttribute("cPhone", bean.getCustomPhone());
		session.setAttribute("cNRCno", bean.getCustomNRCno());
		session.setAttribute("checkIn", bean.getBookCheckIn());
		session.setAttribute("checkOut", bean.getBookCheckOut());
		List<RoomBook> list = (List<RoomBook>) session.getAttribute("list");
		if(list == null ) {
			list = new ArrayList();
		}
		list.add(room);
		session.setAttribute("list", list);
		Iterator<RoomBook> itr = list.iterator();
		int allTotalPrice = 0;
		while(itr.hasNext()) {
			allTotalPrice += itr.next().getRoomTotalPrice();
		}
		session.setAttribute("allTotalPrice",allTotalPrice);
		return "redirect:/finalreceiptPage";
	}
}
