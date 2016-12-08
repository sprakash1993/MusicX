package musicx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import musicx.model.Genres;

public class GenresDao {

	protected ConnectionManager connectionManager;

	private static GenresDao instance = null;
	public GenresDao() {
		connectionManager = new ConnectionManager();
	}
	public static GenresDao getInstance() {
		if(instance == null) {
			instance = new GenresDao();
		}
		return instance;
	}

	public Genres create(Genres genre) throws SQLException {
		String insertGenres = "INSERT INTO genres(genre_id,genre_title,genre_url) values (?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGenres);
			
			insertStmt.setInt(1, genre.getGenre_id());
			insertStmt.setString(2, genre.getGenre_title());
			insertStmt.setString(3, genre.getGenre_url());
			
			insertStmt.executeUpdate();
			return genre;
			
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
	
	public Genres getGenreByGenreId(int genre_id) throws SQLException {

		String selectGenres = "SELECT genre_id, genre_title, genre_url from genres where genre_id=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGenres);
			selectStmt.setInt(1, genre_id);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				
				String title = results.getString("genre_title");
				String url = results.getString("genre_url");
				
				Genres genre = new Genres(genre_id, title, url);
				
				return genre;
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
	
	public List<Genres> getAllGenres() throws SQLException {

		String selectGenres = "SELECT genre_id, genre_title, genre_url from genres limit 15;";
		
		List<Genres> genres= new ArrayList<Genres>();
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGenres);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				
				int genre_id = results.getInt("genre_id");
				String title = results.getString("genre_title");
				String url = results.getString("genre_url");
				
				Genres genre = new Genres(genre_id, title, url);
				
				genres.add(genre);
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
		return genres;
	
	}
	
	
	public Genres delete(int genre_id) throws SQLException {
		
		String deleteGenres = "DELETE FROM genres WHERE genre_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGenres);
			deleteStmt.setInt(1, genre_id);
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
	

}
