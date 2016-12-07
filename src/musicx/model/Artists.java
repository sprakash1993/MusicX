package musicx.model;

public class Artists {

	private String artistId;
	private String artistName;
	private String artistUrl;
	private String artistWebsite;

	// This constructor can be used for reading records from MySQL, where we have all fields.
	public Artists(String artistId, String artistName, String artistUrl, String artistWebsite) {
		this.artistId = artistId;
		this.artistName = artistName;
		this.artistUrl = artistUrl;
		this.artistWebsite = artistWebsite;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getArtistUrl() {
		return artistUrl;
	}

	public void setArtistUrl(String artistUrl) {
		this.artistUrl = artistUrl;
	}
	
	public String getArtistWebsite() {
		return artistWebsite;
	}

	public void setArtistWebsite(String artistWebsite) {
		this.artistWebsite = artistWebsite;
	}


	@Override
	public String toString() {
		return String.format("[Artists] artistId=%s artistName=%s artistUrl=%s artistWebsite=%s",
				artistId,
				artistName,
				artistUrl,
				artistWebsite);
	}

}