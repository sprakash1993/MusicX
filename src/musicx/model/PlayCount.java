package musicx.model;

public class PlayCount {

	private String trackId;
	private String userName;
	private int count;

	public PlayCount(String trackId, String userName, int count) {
		this.trackId = trackId;
		this.userName = userName;
		this.count = count;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return String.format("[PlayCount] trackId=%s userName=%s count=%s",
				trackId,
				userName,
				count);
	}

}
