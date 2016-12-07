package musicx.model;

public class Favourites {

	String username, track_id;

	public Favourites(String username, String track_id) {
		super();
		this.username = username;
		this.track_id = track_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTrack_id() {
		return track_id;
	}

	public void setTrack_id(String track_id) {
		this.track_id = track_id;
	}
	
	
}
