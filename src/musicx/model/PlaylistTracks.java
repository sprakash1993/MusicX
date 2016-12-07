package musicx.model;

public class PlaylistTracks {

	private int playlistId;
	private String trackId;

	public PlaylistTracks(int playlistId, String trackId) {
		this.playlistId = playlistId;
		this.trackId = trackId;
	}

	
	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	@Override
	public String toString() {
		return String.format("[PlaylistTracks] playlistId=%s trackId=%s",
				playlistId,
				trackId);
	}


}
