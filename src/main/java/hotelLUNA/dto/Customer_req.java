package hotelLUNA.dto;

public class Customer_req {

	private String cid;
	private String cname;
	private String cemail;
	private String cphone;
	private String cnrc;
	private Room_req room;
	private RegiBook_req rb;
	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * @return the cemail
	 */
	public String getCemail() {
		return cemail;
	}
	/**
	 * @param cemail the cemail to set
	 */
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	/**
	 * @return the cphone
	 */
	public String getCphone() {
		return cphone;
	}
	/**
	 * @param cphone the cphone to set
	 */
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	/**
	 * @return the cnrc
	 */
	public String getCnrc() {
		return cnrc;
	}
	/**
	 * @param cnrc the cnrc to set
	 */
	public void setCnrc(String cnrc) {
		this.cnrc = cnrc;
	}
	public Room_req getRoom() {
		return room;
	}
	public void setRoom(Room_req room) {
		this.room = room;
	}
	public RegiBook_req getRb() {
		return rb;
	}
	public void setRb(RegiBook_req rb) {
		this.rb = rb;
	}

	
}
