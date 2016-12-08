package musicx.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import musicx.model.Tracks;


public class TracksDao {
	protected ConnectionManager connectionManager;

	private static TracksDao instance = null;

	protected TracksDao() {
		connectionManager = new ConnectionManager();
	}

	public static TracksDao getInstance() {
		if (instance == null) {
			instance = new TracksDao();
		}
		return instance;
	}
	
	private int noOfRecords;
	
	private int noOfSpecialRecords;
	
	private int noOfTracksByGenre;
	
	private int noOfTracksByArtists;
	
	private int noOfTracksByAlbums;
    

	public Tracks create(Tracks track) throws SQLException {
		String insertTrack = "INSERT INTO Tracks(track_id, album_id, artist_id, genre_id,"+ 
	"track_title, track_url, track_duration, track_information,"+
	"track_number, track_composer, track_bit_rate) " 
	+ "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Tracks has an auto-generated key. So we want to retrieve
			// that key.
			insertStmt = connection.prepareStatement(insertTrack);

			insertStmt.setString(1, track.getTrack_id());
			insertStmt.setString(2, track.getAlbum_id());
			insertStmt.setString(3, track.getArtist_id());
			insertStmt.setString(4, track.getGenre_id());
			insertStmt.setString(5, track.getTrack_title());
			insertStmt.setString(6, track.getTrack_url());
			insertStmt.setString(7, track.getTrack_duration());
			insertStmt.setString(8, track.getTrack_information());
			insertStmt.setString(9, track.getTrack_number());
			insertStmt.setString(10, track.getTrack_composer());
			insertStmt.setString(11, track.getTrack_bit_rate());
			

			insertStmt.executeUpdate();

			return track;
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

	public Tracks getTrackByTrackId(String track_id) throws SQLException {
		String selectTrack = "SELECT  track_id, album_id, artist_id, genre_id,"+ 
	"track_title, track_url, track_duration, track_information,"+
	"track_number, track_composer, track_bit_rate " 
	+ "FROM Tracks "+ "WHERE track_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrack);
			selectStmt.setString(1, track_id);
			results = selectStmt.executeQuery();

			if (results.next()) {
				
				String resultTrackId = results.getString("track_id");
				String resultAlbumId = results.getString("album_id");
				String resultArtistId = results.getString("artist_id");
				String resultGenreId = results.getString("genre_id");
				String resultTrackTitle = results.getString("track_title");
				String resultTrackUrl = results.getString("track_url");
				String resultTrackDuration = results.getString("track_duration");
				String resultTrackInfo = results.getString("track_information");
				String resultTrackNumber = results.getString("track_number");
				String resultComposer = results.getString("track_composer");
				String resultBitRate = results.getString("track_bit_rate");
				String resultImageFile = results.getString("track_image_file");

				Tracks track = new Tracks(resultTrackId, resultAlbumId, resultArtistId, 
						resultGenreId, resultTrackTitle, resultTrackUrl, resultTrackDuration, 
						resultTrackInfo, resultTrackNumber, resultComposer, resultBitRate, resultImageFile);

				return track;
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
	
	public List<Tracks> getTracksByAlbumId(String album_id,int offset, 
            int noOfTracks) throws SQLException {
		List<Tracks> tracksList = new ArrayList<Tracks>();
		String selectTracks = "SELECT track_id, album_id, artist_id, genre_id,"+ 
	"track_title, track_url, track_duration, track_information,"+
	"track_number, track_composer, track_bit_rate, track_image_file " 
	+ "FROM Tracks " + "WHERE album_id=? LIMIT " + offset + ", "+noOfTracks;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTracks);
			selectStmt.setString(1, album_id);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultTrackId = results.getString("track_id");
				String resultAlbumId = results.getString("album_id");
				String resultArtistId = results.getString("artist_id");
				String resultGenreId = results.getString("genre_id");
				String resultTrackTitle = results.getString("track_title");
				String resultTrackUrl = results.getString("track_url");
				String resultTrackDuration = results.getString("track_duration");
				String resultTrackInfo = results.getString("track_information");
				String resultTrackNumber = results.getString("track_number");
				String resultComposer = results.getString("track_composer");
				String resultBitRate = results.getString("track_bit_rate");
				String resultImageFile = results.getString("track_image_file");

				Tracks track = new Tracks(resultTrackId, resultAlbumId, resultArtistId, 
						resultGenreId, resultTrackTitle, resultTrackUrl, resultTrackDuration, 
						resultTrackInfo, resultTrackNumber, resultComposer, resultBitRate, resultImageFile);


				tracksList.add(track);
			}
			

			results.close();
			selectStmt=connection.prepareStatement("SELECT FOUND_ROWS()");
			results = selectStmt.executeQuery();
			//System.out.println(tracksList.size());
			if(results.next())
				this.noOfRecords = results.getInt(1);
			
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
		return tracksList;
	}
	
	public int getNoOfTracksByAlbum() {
    	//System.out.println(noOfRecords);
        return noOfTracksByAlbums;
    }
	
	public List<Tracks> getTracksByArtistId(String artist_id,int offset, 
            int noOfTracks) throws SQLException {
		List<Tracks> tracksList = new ArrayList<Tracks>();
		String selectTracks = "SELECT track_id, album_id, artist_id, genre_id,"+ 
	"track_title, track_url, track_duration, track_information,"+
	"track_number, track_composer, track_bit_rate, track_image_file " 
				+ "FROM Tracks " + "WHERE artist_id=? LIMIT " + offset + ", "+noOfTracks;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTracks);
			selectStmt.setString(1, artist_id);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultTrackId = results.getString("track_id");
				String resultAlbumId = results.getString("album_id");
				String resultArtistId = results.getString("artist_id");
				String resultGenreId = results.getString("genre_id");
				String resultTrackTitle = results.getString("track_title");
				String resultTrackUrl = results.getString("track_url");
				String resultTrackDuration = results.getString("track_duration");
				String resultTrackInfo = results.getString("track_information");
				String resultTrackNumber = results.getString("track_number");
				String resultComposer = results.getString("track_composer");
				String resultBitRate = results.getString("track_bit_rate");
				String resultImageFile = results.getString("track_image_file");

				Tracks track = new Tracks(resultTrackId, resultAlbumId, resultArtistId, 
						resultGenreId, resultTrackTitle, resultTrackUrl, resultTrackDuration, 
						resultTrackInfo, resultTrackNumber, resultComposer, resultBitRate, resultImageFile);


				tracksList.add(track);
			}
			
			
			results.close();
			selectStmt=connection.prepareStatement("SELECT FOUND_ROWS()");
			results = selectStmt.executeQuery();
			//System.out.println(tracksList.size());
			if(results.next())
				this.noOfRecords = results.getInt(1);
			
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
		return tracksList;
	}
	
	  public int getNoOfTracksByArtist() {
	    	//System.out.println(noOfRecords);
	        return noOfTracksByArtists;
	    }
	
	public List<Tracks> getTracksByGenreId(int genre_id,int offset, 
            int noOfTracks) throws SQLException {
		List<Tracks> tracksList = new ArrayList<Tracks>();
		String selectTracks = "SELECT track_id, album_id, artist_id, genre_id,"+ 
	"track_title, track_url, track_duration, track_information,"+
	"track_number, track_composer, track_bit_rate, track_image_file " 
				+ "FROM Tracks " + "WHERE genre_id=? LIMIT " + offset + ", "+noOfTracks;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTracks);
			selectStmt.setInt(1, genre_id);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultTrackId = results.getString("track_id");
				String resultAlbumId = results.getString("album_id");
				String resultArtistId = results.getString("artist_id");
				String resultGenreId = results.getString("genre_id");
				String resultTrackTitle = results.getString("track_title");
				String resultTrackUrl = results.getString("track_url");
				String resultTrackDuration = results.getString("track_duration");
				String resultTrackInfo = results.getString("track_information");
				String resultTrackNumber = results.getString("track_number");
				String resultComposer = results.getString("track_composer");
				String resultBitRate = results.getString("track_bit_rate");
				String resultImageFile = results.getString("track_image_file");

				Tracks track = new Tracks(resultTrackId, resultAlbumId, resultArtistId, 
						resultGenreId, resultTrackTitle, resultTrackUrl, resultTrackDuration, 
						resultTrackInfo, resultTrackNumber, resultComposer, resultBitRate, resultImageFile);


				tracksList.add(track);
			}
			
			results.close();
			selectStmt=connection.prepareStatement("SELECT FOUND_ROWS()");
			results = selectStmt.executeQuery();
			//System.out.println(tracksList.size());
			if(results.next())
				this.noOfRecords = results.getInt(1);
			
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
		return tracksList;
	}
	
	  public int getNoOfTracksByGenre() {
	    	//System.out.println(noOfRecords);
	        return noOfTracksByGenre;
	    }
	
	
	public List<Tracks> getAllTracks(String query,int offset, 
            int noOfTracks) throws SQLException {
		System.out.println("hello");
		List<Tracks> tracksList = new ArrayList<Tracks>();
		String selectTracks = "SELECT SQL_CALC_FOUND_ROWS track_id, album_id, artist_id, genre_id,"+ 
	"track_title, track_url, track_duration, track_information,"+
	"track_number, track_composer, track_bit_rate, track_image_file " 
				+ "FROM Tracks WHERE track_title LIKE ? LIMIT " + offset + ", "+noOfTracks;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTracks);
			selectStmt.setString(1, "%" + query + "%");
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultTrackId = results.getString("track_id");
				String resultAlbumId = results.getString("album_id");
				String resultArtistId = results.getString("artist_id");
				String resultGenreId = results.getString("genre_id");
				String resultTrackTitle = results.getString("track_title");
				String resultTrackUrl = results.getString("track_url");
				String resultTrackDuration = results.getString("track_duration");
				String resultTrackInfo = results.getString("track_information");
				String resultTrackNumber = results.getString("track_number");
				String resultComposer = results.getString("track_composer");
				String resultBitRate = results.getString("track_bit_rate");
				String resultImageFile = results.getString("track_image_file");

				Tracks track = new Tracks(resultTrackId, resultAlbumId, resultArtistId, 
						resultGenreId, resultTrackTitle, resultTrackUrl, resultTrackDuration, 
						resultTrackInfo, resultTrackNumber, resultComposer, resultBitRate, resultImageFile);


				tracksList.add(track);
		
			}
			results.close();
			selectStmt=connection.prepareStatement("SELECT FOUND_ROWS()");
			results = selectStmt.executeQuery();
			//System.out.println(tracksList.size());
			if(results.next())
				this.noOfRecords = results.getInt(1);
			
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
		return tracksList;
	}
	

    public int getNoOfRecords() {
    	System.out.println(noOfRecords);
        return noOfRecords;
    }
	/**
	 * Delete the Tracks instance. This runs a DELETE statement.
	 */
	public Tracks delete(Tracks track) throws SQLException {
		String deleteTrack = "DELETE FROM Tracks WHERE track_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTrack);
			deleteStmt.setString(1, track.getTrack_id());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the
			// Tracks instance.
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
	
	//////////////////////for the bottom 4
	public List<Tracks> getTopTracks(int offset, 
            int noOfTracks) throws SQLException {
		
		List<Tracks> tracksList = new ArrayList<Tracks>();
		String selectTracks = "SELECT a.track_id, a.album_id, a.artist_id, a.genre_id,"+ 
	"a.track_title, a.track_url, a.track_duration, a.track_information,"+
	"a.track_number, a.track_composer, a.track_bit_rate, a.track_image_file, "
	+ "avg(b.rating) as average, count(b.review_id) as count " 
				+ "FROM tracks a JOIN reviews b "
				+ "ON a.track_id = a.track_id GROUP BY b.track_id "+
"ORDER BY count DESC, average DESC LIMIT " + offset + ", "+noOfTracks;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTracks);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultTrackId = results.getString("track_id");
				String resultAlbumId = results.getString("album_id");
				String resultArtistId = results.getString("artist_id");
				String resultGenreId = results.getString("genre_id");
				String resultTrackTitle = results.getString("track_title");
				String resultTrackUrl = results.getString("track_url");
				String resultTrackDuration = results.getString("track_duration");
				String resultTrackInfo = results.getString("track_information");
				String resultTrackNumber = results.getString("track_number");
				String resultComposer = results.getString("track_composer");
				String resultBitRate = results.getString("track_bit_rate");
				String resultImageFile = results.getString("track_image_file");

				Tracks track = new Tracks(resultTrackId, resultAlbumId, resultArtistId, 
						resultGenreId, resultTrackTitle, resultTrackUrl, resultTrackDuration, 
						resultTrackInfo, resultTrackNumber, resultComposer, resultBitRate, resultImageFile);


				tracksList.add(track);
		
			}
			results.close();
			selectStmt=connection.prepareStatement("SELECT FOUND_ROWS()");
			results = selectStmt.executeQuery();
			//System.out.println(tracksList.size());
			if(results.next())
				this.noOfSpecialRecords = results.getInt(1);
			
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
		return tracksList;
	}
	
	public List<Tracks> getMostPlayedTracks(int offset, 
            int noOfTracks) throws SQLException {
		
		List<Tracks> tracksList = new ArrayList<Tracks>();
		String selectTracks = "SELECT a.track_id, a.album_id, a.artist_id, a.genre_id,"+ 
	"a.track_title, a.track_url, a.track_duration, a.track_information,"+
	"a.track_number, a.track_composer, a.track_bit_rate, a.track_image_file"
	+ ", sum(if (b.count is not null, b.count, 0)) as totalCount "+
"FROM tracks a JOIN playcount b ON a.track_id = b.track_id "+
"GROUP BY a.track_id ORDER BY totalCount DESC LIMIT " + offset + ", "+noOfTracks;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTracks);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultTrackId = results.getString("track_id");
				String resultAlbumId = results.getString("album_id");
				String resultArtistId = results.getString("artist_id");
				String resultGenreId = results.getString("genre_id");
				String resultTrackTitle = results.getString("track_title");
				String resultTrackUrl = results.getString("track_url");
				String resultTrackDuration = results.getString("track_duration");
				String resultTrackInfo = results.getString("track_information");
				String resultTrackNumber = results.getString("track_number");
				String resultComposer = results.getString("track_composer");
				String resultBitRate = results.getString("track_bit_rate");
				String resultImageFile = results.getString("track_image_file");

				Tracks track = new Tracks(resultTrackId, resultAlbumId, resultArtistId, 
						resultGenreId, resultTrackTitle, resultTrackUrl, resultTrackDuration, 
						resultTrackInfo, resultTrackNumber, resultComposer, resultBitRate, resultImageFile);


				tracksList.add(track);
		
			}
			results.close();
			selectStmt=connection.prepareStatement("SELECT FOUND_ROWS()");
			results = selectStmt.executeQuery();
			//System.out.println(tracksList.size());
			if(results.next())
				this.noOfSpecialRecords = results.getInt(1);
			
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
		return tracksList;
	}
	
	public List<Tracks> getTracksFromPopularArtists(int offset, 
            int noOfTracks) throws SQLException {
		
		List<Tracks> tracksList = new ArrayList<Tracks>();
		String selectTracks = "SELECT t.track_id, t.album_id, t.artist_id, t.genre_id,"+ 
	"t.track_title, t.track_url, t.track_duration, t.track_information,"+
	"t.track_number, t.track_composer, t.track_bit_rate, t.track_image_file "
	+ "FROM tracks t JOIN (SELECT a.artist_id, a.artist_name, avg(c.rating) as average, count(b.track_id) "
	+ "as count FROM artists a JOIN tracks b JOIN reviews c "
	+ "ON a.artist_id = b.artist_id AND b.track_id = c.track_id "+
"GROUP BY a.artist_id ORDER BY count DESC,average DESC LIMIT 15) as x on t.artist_id = x.artist_id "+
"LIMIT " + offset + ", "+noOfTracks;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTracks);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultTrackId = results.getString("track_id");
				String resultAlbumId = results.getString("album_id");
				String resultArtistId = results.getString("artist_id");
				String resultGenreId = results.getString("genre_id");
				String resultTrackTitle = results.getString("track_title");
				String resultTrackUrl = results.getString("track_url");
				String resultTrackDuration = results.getString("track_duration");
				String resultTrackInfo = results.getString("track_information");
				String resultTrackNumber = results.getString("track_number");
				String resultComposer = results.getString("track_composer");
				String resultBitRate = results.getString("track_bit_rate");
				String resultImageFile = results.getString("track_image_file");

				Tracks track = new Tracks(resultTrackId, resultAlbumId, resultArtistId, 
						resultGenreId, resultTrackTitle, resultTrackUrl, resultTrackDuration, 
						resultTrackInfo, resultTrackNumber, resultComposer, resultBitRate, resultImageFile);


				tracksList.add(track);
		
			}
			results.close();
			selectStmt=connection.prepareStatement("SELECT FOUND_ROWS()");
			results = selectStmt.executeQuery();
			//System.out.println(tracksList.size());
			if(results.next())
				this.noOfSpecialRecords = results.getInt(1);
			
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
		return tracksList;
	}
	
	public List<Tracks> getTracksFromPopularAlbums(int offset, 
            int noOfTracks) throws SQLException {
		
		List<Tracks> tracksList = new ArrayList<Tracks>();
		String selectTracks = "SELECT t.track_id, t.album_id, t.artist_id, t.genre_id,"+ 
				"t.track_title, t.track_url, t.track_duration, t.track_information,"+
				"t.track_number, t.track_composer, t.track_bit_rate, t.track_image_file "
	+ "FROM tracks t JOIN (SELECT a.album_id, a.album_title, avg(c.rating) as average, count(b.track_id) "
	+ "as count FROM albums a JOIN tracks b JOIN reviews c "
	+ "ON a.album_id = b.album_id AND b.track_id = c.track_id "+
"GROUP BY a.album_id ORDER BY count DESC,average DESC LIMIT 15) as x on t.album_id = x.album_id "+
"LIMIT " + offset + ", "+noOfTracks;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTracks);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultTrackId = results.getString("track_id");
				String resultAlbumId = results.getString("album_id");
				String resultArtistId = results.getString("artist_id");
				String resultGenreId = results.getString("genre_id");
				String resultTrackTitle = results.getString("track_title");
				String resultTrackUrl = results.getString("track_url");
				String resultTrackDuration = results.getString("track_duration");
				String resultTrackInfo = results.getString("track_information");
				String resultTrackNumber = results.getString("track_number");
				String resultComposer = results.getString("track_composer");
				String resultBitRate = results.getString("track_bit_rate");
				String resultImageFile = results.getString("track_image_file");

				Tracks track = new Tracks(resultTrackId, resultAlbumId, resultArtistId, 
						resultGenreId, resultTrackTitle, resultTrackUrl, resultTrackDuration, 
						resultTrackInfo, resultTrackNumber, resultComposer, resultBitRate, resultImageFile);


				tracksList.add(track);
		
			}
			results.close();
			selectStmt=connection.prepareStatement("SELECT FOUND_ROWS()");
			results = selectStmt.executeQuery();
			//System.out.println(tracksList.size());
			if(results.next())
				this.noOfSpecialRecords = results.getInt(1);
			
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
		return tracksList;
	}

	public int getNoOfSpecialRecords() {
		return noOfSpecialRecords;
	}

	
	

}