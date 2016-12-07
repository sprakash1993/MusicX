package musicx.model;

public class Reviews {

	
	int review_id;
	String username, track_id, description;
	float rating;
	
	public Reviews(String username, String track_id, String description, float rating) {
		super();
		this.username = username;
		this.track_id = track_id;
		this.description = description;
		this.rating = rating;
	}
	public Reviews(int review_id, String username, String track_id, String description, float rating) {
		super();
		this.review_id = review_id;
		this.username = username;
		this.track_id = track_id;
		this.description = description;
		this.rating = rating;
	}
	
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getRating() {
		return rating;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	
}
