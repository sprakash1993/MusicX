package musicx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import musicx.model.Reviews;


public class ReviewsDao {

	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	public ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException {
		
		String insertReviews = "INSERT INTO reviews(username, track_id, rating, description) VALUES(?,?,?,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;		
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReviews,Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, review.getUsername());
			insertStmt.setString(2, review.getTrack_id());
			insertStmt.setFloat(3, review.getRating());
			insertStmt.setString(4, review.getDescription());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int postId = -1;
			
			if(resultKey.next()) {
				postId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			review.setReview_id(postId);
			return review;
			
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
	
	public Reviews getReviewById(int reviewId) throws SQLException {
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectReviews = "SELECT * FROM reviews WHERE review_id=?;";
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, reviewId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String description = results.getString("description");
				float rating= results.getFloat("rating");
				String userName = results.getString("username");
				String track_id = results.getString("track_id");
				
				Reviews review = new Reviews(userName, track_id, description, rating);
				return review;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
		return null;
		
	}
	
	public List<Reviews> getReviewsByUserName(String userName) throws SQLException{
		
		List<Reviews> reviews = new ArrayList<Reviews>();
		
		String selectReview = "SELECT * FROM reviews WHERE username=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				int review_id = results.getInt("review_id");
				String description = results.getString("description");
				float rating= results.getFloat("rating");
				String track_id = results.getString("track_id");
				
				Reviews review = new Reviews(review_id, userName, track_id, description, rating);
				
				reviews.add(review);
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
		return reviews;
	}
	
	public List<Reviews> getAllReviews() throws SQLException{
		List<Reviews> reviews = new ArrayList<Reviews>();
		
		String selectReview = "SELECT * FROM reviews;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				int review_id = results.getInt("review_id");
				String track_id = results.getString("track_id");
				String description = results.getString("description");
				float rating= results.getFloat("rating");
				String userName = results.getString("username");
				
				Reviews review = new Reviews(review_id, userName, track_id, description, rating);
				
				reviews.add(review);
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
		return reviews;
	}
	

	public List<Reviews> getReviewsByTrackId(String track_id) throws SQLException{
		
		List<Reviews> reviews = new ArrayList<Reviews>();
		
		String selectReview = "SELECT * FROM reviews WHERE track_id=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setString(1, track_id);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				int review_id = results.getInt("review_id");
				String description = results.getString("description");
				float rating= results.getFloat("rating");
				String userName = results.getString("username");
				
				Reviews review = new Reviews(review_id, userName, track_id, description, rating);
				
				reviews.add(review);
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
		return reviews;
	}
	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM reviews WHERE review_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReview_id());
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
