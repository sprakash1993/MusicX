package musicx.dao;

import musicx.model.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDao {

	private ConnectionManager connectionManager;

	private static PlaylistDao instance = null;
	public static String TABLE_NAME = "Playlist";


	protected PlaylistDao() {
		connectionManager = new ConnectionManager();
	}

	public static PlaylistDao getInstance() {
		if(instance == null) {
			instance = new PlaylistDao();
		}
		return instance;
	}

	public enum ColumnNames {
		PLAYLIST_ID("playlist_id"),
		USERNAME("username");

		private String name;

		ColumnNames(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public Playlist create(Playlist playlist) throws SQLException {
		String insertPlaylist =
			"INSERT INTO " + TABLE_NAME + "(" + ColumnNames.USERNAME + ") " +
			"VALUES(?);";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPlaylist,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, playlist.getUserName());
			insertStmt.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int playlistId = -1;
			if(resultKey.next()) {
				playlistId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			playlist.setPlaylistId(playlistId);
			return playlist;
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
	
	public Playlist delete(Playlist playlist) throws SQLException {
		String deletePlaylist = "DELETE FROM " + TABLE_NAME + " WHERE " + ColumnNames.PLAYLIST_ID + "=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePlaylist);
			deleteStmt.setInt(1, playlist.getPlaylistId());
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

	public Playlist getPlaylistByPlaylistId(int playlistId) throws SQLException {
		String selectPlaylist =
			"SELECT *" +
			" FROM " + TABLE_NAME +
			" WHERE " + ColumnNames.PLAYLIST_ID + "=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlaylist);
			selectStmt.setInt(1, playlistId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String userName = results.getString(ColumnNames.USERNAME.toString());
				Playlist playlist = new Playlist(userName);
				playlist.setPlaylistId(playlistId);
				return playlist;
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
	
	public List<Playlist> getPlaylistByUserName(String userName) throws SQLException {
		List<Playlist> playlist = new ArrayList<Playlist>();

		String selectLocation =
				"SELECT *" +
				" FROM " + TABLE_NAME +
				" WHERE " + ColumnNames.USERNAME + "=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int playlistId = results.getInt("PlaylistId");
				Playlist playlists = new Playlist(userName);
				playlists.setPlaylistId(playlistId);
				playlist.add(playlists);
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
		return playlist;
	}
	

}