package hotelLUNA.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class SearchRoom {
	@NotEmpty(message="Don't be blank!!!")
		private String checkIn;
	@NotEmpty(message="Don't be blank!!!")
		private String checkOut;
	@Min(value=0,message="Must be equal or greater than 0")
	@Max(value=2,message="Must be equal or less than 2")
		private Integer adultNumber;
    @Min(value=0,message="Must be equal or greater than 0")
	
	@Max(value=2,message="Must be equal or less than 2")
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
