package hotelLUNA.bean;

import javax.validation.constraints.NotEmpty;

public class SearchCustomer {

	@NotEmpty(message="dont be blank")
	private String cname;
	@NotEmpty(message="dont be blank")
	private String cphone;
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
