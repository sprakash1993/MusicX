package musicx.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import musicx.model.Albums;


public class AlbumsDao {
	protected ConnectionManager connectionManager;

	private static AlbumsDao instance = null;

	protected AlbumsDao() {
		connectionManager = new ConnectionManager();
	}

	public static AlbumsDao getInstance() {
		if (instance == null) {
			instance = new AlbumsDao();
		}
		return instance;
	}

	public Albums create(Albums album) throws SQLException {
		String insertAlbum = "INSERT INTO Albums(album_id, album_title, album_url) " 
	+ "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Albums has an auto-generated key. So we want to retrieve
			// that key.
			insertStmt = connection.prepareStatement(insertAlbum);

			insertStmt.setString(1, album.getAlbum_id());
			insertStmt.setString(2, album.getAlbum_title());
			insertStmt.setString(3, album.getAlbum_url());			

			insertStmt.executeUpdate();

			return album;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Albums getAlbumByAlbumId(String album_id) throws SQLException {
		String selectAlbum = "SELECT album_id,album_title,album_url " 
				+ "WHERE album_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAlbum);
			selectStmt.setString(1, album_id);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String resultAlbumId = results.getString("album_id");
				String resultAlbumTitle = results.getString("album_title");
				String resultAlbumUrl = results.getString("album_url");

				Albums album = new Albums(resultAlbumId, resultAlbumTitle, resultAlbumUrl);

				return album;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<Albums> getAlbumsByAlbumTitleQuery(String query) throws SQLException {
		List<Albums> albumsList = new ArrayList<Albums>();
		String selectAlbums = "SELECT album_id,album_title,album_url " 
				+ "FROM Albums " + "WHERE album_title COLLATE UTF8_GENERAL_CI LIKE '%?%';";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAlbums);
			selectStmt.setString(1, query);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultAlbumId = results.getString("album_id");
				String resultAlbumTitle = results.getString("album_title");
				String resultAlbumUrl = results.getString("album_url");

				Albums album = new Albums(resultAlbumId, resultAlbumTitle, resultAlbumUrl);


				albumsList.add(album);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return albumsList;
	}
	
	public List<Albums> getAllAlbums() throws SQLException {
		List<Albums> albumsList = new ArrayList<Albums>();
		String selectAlbums = "SELECT album_id,album_title,album_url " 
				+ "FROM Albums LIMIT 15";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAlbums);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultAlbumId = results.getString("album_id");
				String resultAlbumTitle = results.getString("album_title");
				String resultAlbumUrl = results.getString("album_url");

				Albums album = new Albums(resultAlbumId, resultAlbumTitle, resultAlbumUrl);


				albumsList.add(album);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return albumsList;
	}
	
	/**
	 * Delete the Albums instance. This runs a DELETE statement.
	 */
	public Albums delete(Albums album) throws SQLException {
		String deleteAlbum = "DELETE FROM Albums WHERE album_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAlbum);
			deleteStmt.setString(1, album.getAlbum_id());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the
			// Albums instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}