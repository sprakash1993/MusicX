package musicx.dao;

import musicx.model.PlayCount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayCountDao {

	protected ConnectionManager connectionManager;

	private static PlayCountDao instance = null;
	public static final String TABLE_NAME = "Playcount";

	protected PlayCountDao() {
		connectionManager = new ConnectionManager();
	}

	public static PlayCountDao getInstance() {
		if(instance == null) {
			instance = new PlayCountDao();
		}
		return instance;
	}

	public enum ColumnNames {
		TRACK_ID("track_id"),
		USERNAME("username"),
		COUNT("count");

		private String name;

		ColumnNames(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}

	public PlayCount create(PlayCount playCount) throws SQLException {
		String insertPlayCount =
			"INSERT INTO " + TABLE_NAME + "(" + ColumnNames.TRACK_ID + "," + ColumnNames.USERNAME + "," + ColumnNames.COUNT + ") " +
			"VALUES(?,?,?);";

		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPlayCount);
			insertStmt.setString(1, playCount.getTrackId());
			insertStmt.setString(2, playCount.getUserName());
			insertStmt.setInt(3, playCount.getCount());
			insertStmt.executeUpdate();
			return playCount;
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

	public PlayCount delete(PlayCount playCount) throws SQLException {
		String deletePlayCount = "DELETE FROM " + TABLE_NAME + " WHERE " + ColumnNames.TRACK_ID + "=?;";

		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePlayCount);
			deleteStmt.setString(1, playCount.getTrackId());
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

	public PlayCount getPlayCountById(String trackId) throws SQLException {
		String selectPlayCount =
			"SELECT * " +
			" FROM " + TABLE_NAME +
			" WHERE " + ColumnNames.TRACK_ID + "=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlayCount);
			selectStmt.setString(1, trackId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String userName = results.getString("UserName");
				int count = results.getInt("Count");
				
				PlayCount playCount = new PlayCount(trackId, userName, count);
				return playCount;
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

	public List<PlayCount> getPlayCountByUserName(String userName) throws SQLException {
		List<PlayCount> playCount = new ArrayList<PlayCount>();

		String selectPlayCount =
			"SELECT * " +
			" FROM " + TABLE_NAME +
			" WHERE " + ColumnNames.USERNAME + "=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlayCount);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String trackId = results.getString("TrackId");
				int count = results.getInt("Count");

				PlayCount playcount = new PlayCount(trackId, userName, count);
				playCount.add(playcount);
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
		return playCount;
	}

	
}