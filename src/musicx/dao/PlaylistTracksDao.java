package musicx.dao;

import musicx.model.PlaylistTracks;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistTracksDao {

	protected ConnectionManager connectionManager;

	private static PlaylistTracksDao instance = null;
	public static final String TABLE_NAME = "Playlist_tracks";

	protected PlaylistTracksDao() {
		connectionManager = new ConnectionManager();
	}

	public static PlaylistTracksDao getInstance() {
		if(instance == null) {
			instance = new PlaylistTracksDao();
		}
		return instance;
	}

	public enum ColumnNames {
		PLAYLIST_ID("playlist_id"),
		TRACK_ID("track_id");
		
		private String name;

		ColumnNames(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}

	public PlaylistTracks create(PlaylistTracks playlistTrack) throws SQLException {
		String insertPlaylistTrack =
			"INSERT INTO " + TABLE_NAME + "(" + ColumnNames.PLAYLIST_ID + "," + ColumnNames.TRACK_ID + ") " +
			"VALUES(?,?);";

		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPlaylistTrack);
			insertStmt.setInt(1, playlistTrack.getPlaylistId());
			insertStmt.setString(2, playlistTrack.getTrackId());
			insertStmt.executeUpdate();
				return playlistTrack;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public PlaylistTracks updatePlaylistId(PlaylistTracks playlistTrack, int newPlaylistId) throws SQLException {
		String updatePlaylistTrack = "UPDATE " + TABLE_NAME + " SET " + ColumnNames.PLAYLIST_ID + "=? WHERE " + ColumnNames.TRACK_ID + "=?;";

		Connection connection = null;
		PreparedStatement updateStmt = null;

		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePlaylistTrack);
			updateStmt.setInt(1, newPlaylistId);
			updateStmt.setString(2, playlistTrack.getTrackId());
			updateStmt.executeUpdate();

			playlistTrack.setPlaylistId(newPlaylistId);
			return playlistTrack;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public PlaylistTracks delete(PlaylistTracks playlistTrack) throws SQLException {
		String deletePlaylistTrack = "DELETE FROM " + TABLE_NAME + " WHERE " + ColumnNames.PLAYLIST_ID + "=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePlaylistTrack);
			deleteStmt.setInt(1, playlistTrack.getPlaylistId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public PlaylistTracks getPlaylistTrackByPlaylistId(int playlistId) throws SQLException {
		String selectPlaylistTrack =
			"SELECT *" +
			" FROM " + TABLE_NAME +
			" WHERE " + ColumnNames.PLAYLIST_ID + "=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlaylistTrack);
			selectStmt.setInt(1, playlistId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String trackId = results.getString(ColumnNames.TRACK_ID.toString());
				PlaylistTracks playlistTrack = new PlaylistTracks(playlistId, trackId);
				return playlistTrack;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
}