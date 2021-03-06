package musicx.dao;

import musicx.dao.ArtistsDao.ColumnNames;
import musicx.model.Albums;
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
	
	public List<Artists> getArtistsByArtistName(String artistName) throws SQLException {
		List<Artists> artists = new ArrayList<Artists>();

		String selectArtists =
				"SELECT *" +
				" FROM " + TABLE_NAME +
				" WHERE " + ColumnNames.ARTIST_NAME + "=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectArtists);
			selectStmt.setString(1, artistName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String artistId = results.getString("ArtistId");
				String artistUrl = results.getString("ArtistUrl");
				String artistWebsite = results.getString("ArtistWebsite");
				
				Artists artist = new Artists(artistId, artistName, artistUrl, artistWebsite);
				artists.add(artist);
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
		return artists;
	}
	
	public List<Artists> getAllArtists() throws SQLException {
		List<Artists> artistsList = new ArrayList<Artists>();
		String selectArtists = "SELECT x.artist_id, x.artist_name, x.artist_url, x.artist_website "
				+ "FROM (SELECT a.artist_id, a.artist_name, a.artist_url, a.artist_website, avg(c.rating) as average, "
				+ "count(b.track_id) as count " + "FROM artists a JOIN tracks b JOIN reviews c "
				+ "ON a.artist_id = b.artist_id AND b.track_id = c.track_id " + "GROUP BY a.artist_id "
				+ "ORDER BY count DESC,average DESC LIMIT 15) as x UNION "
				+ "(SELECT a.artist_id, a.artist_name, a.artist_url, a.artist_website " 
				+ "FROM artists a JOIN tracks b "
				+ "ON a.artist_id = b.artist_id " + "GROUP BY a.artist_id " + "LIMIT 15);";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectArtists);
			results = selectStmt.executeQuery();
			while (results.next()) {
				String artistId = results.getString(ColumnNames.ARTIST_ID.toString());
				String artistName = results.getString(ColumnNames.ARTIST_NAME.toString());
				String artistUrl = results.getString(ColumnNames.ARTIST_URL.toString());
				String artistWebsite = results.getString(ColumnNames.ARTIST_WEBSITE.toString());
			

				Artists artist = new Artists(artistId, artistName, artistUrl, artistWebsite);


				artistsList.add(artist);
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
		return artistsList;
	}

}