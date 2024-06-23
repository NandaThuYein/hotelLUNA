package hotelLUNA.bean;



public class Customer {
	
	
	private String cid;
	private String cname;

	private String cemail;

	private String cphone;

	private String cnrc;
	private Room room;
	private RegiBook rb;
	
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
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public RegiBook getRb() {
		return rb;
	}
	public void setRb(RegiBook rb) {
		this.rb = rb;
	}
	
	
}
