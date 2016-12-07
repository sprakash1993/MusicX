package musicx.model;

public class Playlist {

	private int playlistId;
	private String userName;

	// This constructor can be used for reading records from MySQL, where we have all fields.
	public Playlist(String userName) {
		this.userName = userName;
	}

	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	@Override
	public String toString() {
		return String.format("[Playlist] playlistId=%s userName=%s",
				playlistId,
				userName);
	}

}