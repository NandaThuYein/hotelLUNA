package hotelLUNA.bean;

public class RoomBook {
		private String roomId;
		private int roomTotalPrice;
		private String roomType;
		private int perNight;
		private int adultNumber;
		private int childNumber;
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
		public int getPerNight() {
			return perNight;
		}
		public void setPerNight(int perNight) {
			this.perNight = perNight;
		}
		public String getRoomId() {
			return roomId;
		}
		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}
		public int getRoomTotalPrice() {
			return roomTotalPrice;
		}
		public void setRoomTotalPrice(int roomTotalPrice) {
			this.roomTotalPrice = roomTotalPrice;
		}
		public String getRoomType() {
			return roomType;
		}
		public void setRoomType(String roomType) {
			this.roomType = roomType;
		}
}
