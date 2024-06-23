package hotelLUNA.bean;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.RestController;



@SuppressWarnings("unused")
public class Admin {
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String id;
	private String aname;
	private String password;
	
}
