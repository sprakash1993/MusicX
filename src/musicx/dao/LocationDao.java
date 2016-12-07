package musicx.dao;

import musicx.model.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationDao {

	private ConnectionManager connectionManager;

	private static LocationDao instance = null;
	public static String TABLE_NAME = "location";


	public LocationDao() {
		connectionManager = new ConnectionManager();
	}

	public static LocationDao getInstance() {
		if(instance == null) {
			instance = new LocationDao();
		}
		return instance;
	}

	public enum ColumnNames {
		LOCATION_ID("location_id"),
		STATE("state"),
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

	public Location create(Location location) throws SQLException {
		String insertLocation =
			"INSERT INTO " + TABLE_NAME + "(" + ColumnNames.STATE + ") " +
			"VALUES(?);";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLocation,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, location.getState());
			insertStmt.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int locationId = -1;
			if(resultKey.next()) {
				locationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			location.setLocationId(locationId);
			return location;
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

	public Location updateState(Location location, String newState) throws SQLException {
		String updateLocation = "UPDATE " + TABLE_NAME + " SET " + ColumnNames.STATE + "=? WHERE " + ColumnNames.LOCATION_ID + "=?;";

		Connection connection = null;
		PreparedStatement updateStmt = null;

		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLocation);
			updateStmt.setString(1, newState);
			updateStmt.executeUpdate();

			location.setState(newState);
			return location;
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

	public Location delete(Location location) throws SQLException {
		String deleteLocation = "DELETE FROM " + TABLE_NAME + " WHERE " + ColumnNames.LOCATION_ID + "=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLocation);
			deleteStmt.setInt(1, location.getLocationId());
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

	public Location getLocationByLocationId(int locationId) throws SQLException {
		String selectLocation =
			"SELECT *" +
			" FROM " + TABLE_NAME +
			" WHERE " + ColumnNames.LOCATION_ID + "=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setInt(1, locationId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String state = results.getString(ColumnNames.STATE.toString());
				Location location = new Location(state);
				location.setLocationId(locationId);
				return location;
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
	
	public List<Location> getLocationByState(String state) throws SQLException {
		List<Location> location = new ArrayList<Location>();

		String selectLocation =
				"SELECT *" +
				" FROM " + TABLE_NAME +
				" WHERE " + ColumnNames.STATE + "=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setString(1, state);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int locationId = results.getInt("LocationId");
				Location locations = new Location(state);
				locations.setLocationId(locationId);
				location.add(locations);
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
		return location;
	}
	

	public List<Location> getAllLocation() throws SQLException {
		List<Location> locations = new ArrayList<Location>();

		String selectLocation =
				"SELECT *" +
				" FROM " + TABLE_NAME + ";";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			
			results = selectStmt.executeQuery();
			while(results.next()) {
				int locationId = results.getInt("location_id");
				String state = results.getString("state");
				Location location = new Location(state);
				location.setLocationId(locationId);
				locations.add(location);
				
				//
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
		return locations;
	}
	
}