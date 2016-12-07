package musicx.model;


public class Tracks {
	
	private String track_id, album_id, artist_id, genre_id, 
	track_title, track_url, track_duration, track_information,
	track_number, track_composer, track_bit_rate;

	public Tracks(String track_id, String album_id, String artist_id, String genre_id, String track_title,
			String track_url, String track_duration, String track_information, String track_number,
			String track_composer, String track_bit_rate) {
		super();
		this.track_id = track_id;
		this.album_id = album_id;
		this.artist_id = artist_id;
		this.genre_id = genre_id;
		this.track_title = track_title;
		this.track_url = track_url;
		this.track_duration = track_duration;
		this.track_information = track_information;
		this.track_number = track_number;
		this.track_composer = track_composer;
		this.track_bit_rate = track_bit_rate;
	}

	public String getTrack_id() {
		return track_id;
	}

	public void setTrack_id(String track_id) {
		this.track_id = track_id;
	}

	public String getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}

	public String getArtist_id() {
		return artist_id;
	}

	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}

	public String getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(String genre_id) {
		this.genre_id = genre_id;
	}

	public String getTrack_title() {
		return track_title;
	}

	public void setTrack_title(String track_title) {
		this.track_title = track_title;
	}

	public String getTrack_url() {
		return track_url;
	}

	public void setTrack_url(String track_url) {
		this.track_url = track_url;
	}

	public String getTrack_duration() {
		return track_duration;
	}

	public void setTrack_duration(String track_duration) {
		this.track_duration = track_duration;
	}

	public String getTrack_information() {
		return track_information;
	}

	public void setTrack_information(String track_information) {
		this.track_information = track_information;
	}

	public String getTrack_number() {
		return track_number;
	}

	public void setTrack_number(String track_number) {
		this.track_number = track_number;
	}

	public String getTrack_composer() {
		return track_composer;
	}

	public void setTrack_composer(String track_composer) {
		this.track_composer = track_composer;
	}

	public String getTrack_bit_rate() {
		return track_bit_rate;
	}

	public void setTrack_bit_rate(String track_bit_rate) {
		this.track_bit_rate = track_bit_rate;
	}

	
	
	
}
