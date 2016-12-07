package musicx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import musicx.model.Favourites;

public class FavouritesDao {

	protected ConnectionManager connectionManager;

	private static FavouritesDao instance = null;
	public FavouritesDao() {
		connectionManager = new ConnectionManager();
	}
	public static FavouritesDao getInstance() {
		if(instance == null) {
			instance = new FavouritesDao();
		}
		return instance;
	}

	public Favourites create(Favourites favourite) throws SQLException {
		String insertFavourites = "INSERT INTO favourites(username,track_id) values (?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFavourites);
			
			insertStmt.setString(1, favourite.getUsername());
			insertStmt.setString(2, favourite.getTrack_id());
			
			insertStmt.executeUpdate();
			return favourite;
			
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
	
	public List<Favourites> getFavouritesByUsername(String userName) throws SQLException {
		
		List<Favourites> favourites = new ArrayList<Favourites>();			
		
		String selectFavourites = "SELECT username, track_id from favourites where username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
	
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFavourites);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				String track_id = results.getString("track_id");
				Favourites favourite = new Favourites(userName, track_id);
				favourites.add(favourite);				
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
		return favourites;
		
	}
		
public Favourites delete(Favourites favourite) throws SQLException {
		
		String deleteFavourites = "DELETE FROM favourites WHERE username=? and track_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFavourites);
			deleteStmt.setString(1, favourite.getUsername());
			deleteStmt.setString(2, favourite.getTrack_id());
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
