package musicx.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import musicx.model.Users;


public class UsersDao {
	protected ConnectionManager connectionManager;

	private static UsersDao instance = null;

	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}

	public static UsersDao getInstance() {
		if (instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users user) throws SQLException {
		String insertUser = "INSERT INTO Users(username,password,location_id,"
				+ "firstname,lastname,gender,birthdate, phone_number) " 
	+ "VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Users has an auto-generated key. So we want to retrieve
			// that key.
			insertStmt = connection.prepareStatement(insertUser);

			insertStmt.setString(1, user.getUsername());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setInt(3, user.getLocation_id());
			insertStmt.setString(4, user.getFirstname());
			insertStmt.setString(5, user.getLastname());
			insertStmt.setString(6, user.getGender().toString());
			insertStmt.setDate(7, new Date(user.getBirthdate().getTime()));
			insertStmt.setInt(8, user.getPhone_number());
			

			insertStmt.executeUpdate();

			return user;
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

	public Users getUserByUsername(String username) throws SQLException {
		String selectUser = "SELECT username,password,location_id,"
				+ "firstname,lastname,gender,birthdate,phone_number " 
				+ "FROM Users WHERE username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String resultUsername = results.getString("username");
				String resultPassword = results.getString("password");
				int resultLocationId = results.getInt("location_id");
				String resultFirstname = results.getString("firstname");
				String resultLastname = results.getString("lastname");
				Users.Gender resultGender = Users.Gender.valueOf(results.getString("gender"));
				Timestamp resultBirthDate = results.getTimestamp("birthdate");
				int resultPhone = results.getInt("phone_number");

				Users user = new Users(resultUsername, resultPassword, resultLocationId, 
						resultFirstname, resultLastname, resultGender, resultBirthDate, resultPhone);

				return user;
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
	
	public List<Users> getUsersByLocationId(int location_id) throws SQLException {
		List<Users> usersList = new ArrayList<Users>();
		String selectUsers = "SELECT username,password,location_id,"
				+ "firstname,lastname,gender,birthdate,phone_number " 
				+ "FROM Users " + "WHERE location_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setInt(1, location_id);
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultUsername = results.getString("username");
				String resultPassword = results.getString("password");
				int resultLocationId = results.getInt("location_id");
				String resultFirstname = results.getString("firstname");
				String resultLastname = results.getString("lastname");
				Users.Gender resultGender = Users.Gender.valueOf(results.getString("gender"));
				Timestamp resultBirthDate = results.getTimestamp("birthdate");
				int resultPhone = results.getInt("phone_number");

				Users user = new Users(resultUsername, resultPassword, resultLocationId, 
						resultFirstname, resultLastname, resultGender, resultBirthDate, resultPhone);


				usersList.add(user);
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
		return usersList;
	}
	
	public List<Users> getUsersByGender(Users.Gender gender) throws SQLException {
		List<Users> usersList = new ArrayList<Users>();
		String selectUsers = "SELECT username,password,location_id,"
				+ "firstname,lastname,gender,birthdate,phone_number " 
				+ "FROM Users " + "WHERE gender=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, gender.toString());
			results = selectStmt.executeQuery();
			while (results.next()) {

				String resultUsername = results.getString("username");
				String resultPassword = results.getString("password");
				int resultLocationId = results.getInt("location_id");
				String resultFirstname = results.getString("firstname");
				String resultLastname = results.getString("lastname");
				Users.Gender resultGender = Users.Gender.valueOf(results.getString("gender"));
				Timestamp resultBirthDate = results.getTimestamp("birthdate");
				int resultPhone = results.getInt("phone_number");

				Users user = new Users(resultUsername, resultPassword, resultLocationId, 
						resultFirstname, resultLastname, resultGender, resultBirthDate, resultPhone);


				usersList.add(user);
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
		return usersList;
	}
	
	public Users updateUser(Users user) throws SQLException {
		String updateCompany = "UPDATE Users SET firstname = ?"
				+ ", lastname = ?, phone_number = ? WHERE username=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCompany);
			updateStmt.setString(1, user.getFirstname());
			updateStmt.setString(2, user.getLastname());
			updateStmt.setInt(3, user.getPhone_number());
			updateStmt.setString(4, user.getUsername());
			updateStmt.executeUpdate();

			return user;
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

	/**
	 * Delete the Users instance. This runs a DELETE statement.
	 */
	public Users delete(String username) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE username=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, username);
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the
			// Users instance.
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