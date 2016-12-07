package musicx.dao;

import musicx.model.Artists;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistsDao {

	private ConnectionManager connectionManager;

	private static ArtistsDao instance = null;
	public static String TABLE_NAME = "Artists";


	protected ArtistsDao() {
		connectionManager = new ConnectionManager();
	}


	public static ArtistsDao getInstance() {
		if(instance == null) {
			instance = new ArtistsDao();
		}
		return instance;
	}

	public enum ColumnNames {
		ARTIST_ID("artist_id"),
		ARTIST_NAME("artist_name"),
		ARTIST_URL("artist_url"),
		ARTIST_WEBSITE("artist_website");

		private String name;

		ColumnNames(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public Artists create(Artists artist) throws SQLException {
		String insertArtist =
			"INSERT INTO " + TABLE_NAME + "(" + ColumnNames.ARTIST_ID + "," + ColumnNames.ARTIST_NAME + "," + ColumnNames.ARTIST_URL + "," + ColumnNames.ARTIST_WEBSITE + ") " +
			"VALUES(?,?,?,?);";

		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertArtist);
			insertStmt.setString(1, artist.getArtistId());
			insertStmt.setString(2, artist.getArtistName());
			insertStmt.setString(3, artist.getArtistUrl());
			insertStmt.setString(4, artist.getArtistWebsite());
			insertStmt.executeUpdate();
  		    return artist;
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

	public Artists updateArtistWebsite(Artists artist, String newArtistWebsite) throws SQLException {
		String updateArtist = "UPDATE " + TABLE_NAME + " SET " + ColumnNames.ARTIST_WEBSITE + "=? WHERE " + ColumnNames.ARTIST_NAME + "=?;";

		Connection connection = null;
		PreparedStatement updateStmt = null;

		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateArtist);
			updateStmt.setString(1, newArtistWebsite);
			updateStmt.setString(2, artist.getArtistName());
			updateStmt.executeUpdate();

			artist.setArtistWebsite(newArtistWebsite);
			return artist;
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

	public Artists delete(Artists artist) throws SQLException {
		String deleteArtist = "DELETE FROM " + TABLE_NAME + " WHERE " + ColumnNames.ARTIST_NAME + "=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteArtist);
			deleteStmt.setString(1, artist.getArtistName());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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

	public Artists getArtistByArtistId(String artistId) throws SQLException {
		String selectArtist =
			"SELECT *" +
			" FROM " + TABLE_NAME +
			" WHERE " + ColumnNames.ARTIST_ID + "=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectArtist);
			selectStmt.setString(1, artistId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String artistName = results.getString(ColumnNames.ARTIST_NAME.toString());
				String artistUrl = results.getString(ColumnNames.ARTIST_URL.toString());
				String artistWebsite = results.getString(ColumnNames.ARTIST_WEBSITE.toString());
				Artists artist = new Artists(artistId, artistName, artistUrl, artistWebsite);
				return artist;
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