package musicx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import musicx.dao.FavouritesDao;
import musicx.dao.GenresDao;
import musicx.dao.ReviewsDao;
import musicx.model.Favourites;
import musicx.model.Genres;
import musicx.model.Reviews;

public class testPrakash {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		//Favourites f = new Favourites("aadams1g", "10");
		
		//FavouritesDao fd = new FavouritesDao();
		//List<Favourites> favourites = new ArrayList<Favourites>();
		
		//GenresDao gd = new GenresDao();
		//Genres g = new Genres(200000, "sp", "blahblah");
		
		Reviews r = new Reviews("aadams1g", "10", "Hello", 4.5F);
		ReviewsDao rd = new ReviewsDao();
		try{
			//fd.create(f);
			//gd.create(g);
			//favourites = fd.getFavouritesByUsername("aadams1g");
			
			//System.out.println(favourites.size());
			//r=rd.create(r);
			//System.out.println(rd.getReviewById(r.getReview_id()).getRating());
			
			//List<Reviews> reviews = new ArrayList<Reviews>();
			//reviews = rd.getReviewsByTrackId("10");
			//System.out.println(reviews.size());
			
			//reviews = rd.getReviewsByUserName("aadams1g");
			//System.out.println(reviews.size());
			//fd.delete(f);
			//System.out.println(gd.getGenreByGenreId(200000).getGenre_title());
			//gd.delete(200000);
			//r.setReview_id(11);
			rd.delete(r);
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {			
			
		}
	}

}
