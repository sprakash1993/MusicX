package musicx.model;

public class Genres {

	int genre_id;
	String genre_title, genre_url;
	
	public Genres(int genre_id, String genre_title, String genre_url) {
		super();
		this.genre_id = genre_id;
		this.genre_title = genre_title;
		this.genre_url = genre_url;
	}
	
	public int getGenre_id() {
		return genre_id;
	}
	
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	
	public String getGenre_title() {
		return genre_title;
	}
	
	public void setGenre_title(String genre_title) {
		this.genre_title = genre_title;
	}
	
	public String getGenre_url() {
		return genre_url;
	}
	
	public void setGenre_url(String genre_url) {
		this.genre_url = genre_url;
	}
	
	
}
