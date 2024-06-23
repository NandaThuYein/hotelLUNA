package hotelLUNA.bean;

import javax.validation.constraints.NotEmpty;


public class CustomBook {
	@NotEmpty(message="dont be blank")
		private String customName;
	@NotEmpty(message="dont be blank")
		private String customEmail;
	@NotEmpty(message="dont be blank")
		private String customPhone;
	@NotEmpty(message="dont be blank")
		private String customNRCno;
		private String roomId;
		private int roomPrice;
		private String roomType;
		private String bookCheckIn;
		private String bookCheckOut;
		private int bookTotalPrice;
		private int perNight;
		private int adultNumber;
		private int childNumber;
		public String getCustomName() {
			return customName;
		}
		public void setCustomName(String customName) {
			this.customName = customName;
		}
		public String getCustomEmail() {
			return customEmail;
		}
		public void setCustomEmail(String customEmail) {
			this.customEmail = customEmail;
		}
		public String getCustomPhone() {
			return customPhone;
		}
		public void setCustomPhone(String customPhone) {
			this.customPhone = customPhone;
		}
		public String getCustomNRCno() {
			return customNRCno;
		}
		public void setCustomNRCno(String customNRCno) {
			this.customNRCno = customNRCno;
		}
		public String getRoomId() {
			return roomId;
		}
		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}
		public int getRoomPrice() {
			return roomPrice;
		}
		public void setRoomPrice(int roomPrice) {
			this.roomPrice = roomPrice;
		}
		public String getRoomType() {
			return roomType;
		}
		public void setRoomType(String roomType) {
			this.roomType = roomType;
		}
		public String getBookCheckIn() {
			return bookCheckIn;
		}
		public void setBookCheckIn(String bookCheckIn) {
			this.bookCheckIn = bookCheckIn;
		}
		public String getBookCheckOut() {
			return bookCheckOut;
		}
		public void setBookCheckOut(String bookCheckOut) {
			this.bookCheckOut = bookCheckOut;
		}
		public int getBookTotalPrice() {
			return bookTotalPrice;
		}
		public void setBookTotalPrice(int bookTotalPrice) {
			this.bookTotalPrice = bookTotalPrice;
		}
		public int getPerNight() {
			return perNight;
		}
		public void setPerNight(int perNight) {
			this.perNight = perNight;
		}
		public int getAdultNumber() {
			return adultNumber;
		}
		public void setAdultNumber(int adultNumber) {
			this.adultNumber = adultNumber;
		}
		public int getChildNumber() {
			return childNumber;
		}
		public void setChildNumber(int childNumber) {
			this.childNumber = childNumber;
		}
		
}
