package hotelLUNA.dto;

public class SearchRoom_req {
	private String checkIn;
	private String checkOut;
	private Integer adultNumber;
	private Integer childrenNumber;
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public Integer getAdultNumber() {
		return adultNumber;
	}
	public void setAdultNumber(Integer adultNumber) {
		this.adultNumber = adultNumber;
	}
	public Integer getChildrenNumber() {
		return childrenNumber;
	}
	public void setChildrenNumber(Integer childrenNumber) {
		this.childrenNumber = childrenNumber;
	}
}
