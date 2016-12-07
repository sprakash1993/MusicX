package musicx.model;


public class Albums {
	
	private String album_id, album_title, album_url;

	public Albums(String album_id, String album_title, String album_url) {
		super();
		this.album_id = album_id;
		this.album_title = album_title;
		this.album_url = album_url;
	}

	public String getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}

	public String getAlbum_title() {
		return album_title;
	}

	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}

	public String getAlbum_url() {
		return album_url;
	}

	public void setAlbum_url(String album_url) {
		this.album_url = album_url;
	}
	
	
	
}
