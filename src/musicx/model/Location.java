package musicx.model;

public class Location {



	public Location(int locationId, String state) {
		super();
		this.locationId = locationId;
		this.state = state;
	}


	private int locationId;
	private String state;


	// This constructor can be used for reading records from MySQL, where we have all fields.
	public Location(String state) {
		this.state = state;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	@Override
	public String toString() {
		return String.format("[Location] locationId=%s state=%s",
				locationId,
				state);
	}

}