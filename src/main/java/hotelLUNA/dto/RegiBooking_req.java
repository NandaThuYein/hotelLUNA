package hotelLUNA.dto;

public class RegiBooking_req {
		private String checkIn;
		private String checkOut;
		private int    customID;
		private String allTotalPrice;
		private String status;
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
		public int getCustomID() {
			return customID;
		}
		public void setCustomID(int customID) {
			this.customID = customID;
		}
		public String getAllTotalPrice() {
			return allTotalPrice;
		}
		public void setAllTotalPrice(String allTotalPrice) {
			this.allTotalPrice = allTotalPrice;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
}
