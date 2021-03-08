package entities;

public class Hotel {

	private String hotelName;
	private int hotelPrice;

	public Hotel(String hotelName, int hotelPrice) {
		this.hotelName = hotelName;
		this.hotelPrice = hotelPrice;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getHotelPrice() {
		return hotelPrice;
	}

	public void setHotelPrice(int hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

}
