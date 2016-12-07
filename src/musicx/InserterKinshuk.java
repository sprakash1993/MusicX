package musicx;

import java.sql.SQLException;
import musicx.dao.*;
import musicx.model.*;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

	private static ArtistsDao artistsDao = ArtistsDao.getInstance();
	private static PlayCountDao playCountDao = PlayCountDao.getInstance();

	
	private static void fetchArtists(List<Artists> artists) throws SQLException {
		System.out.println("Fetching artists");
		for(Artists art : artists) {
			System.out.println(artistsDao.getArtistByArtistId(art.getArtistId()));
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		
		System.out.println("Creating 2 artists");
		List<Artists> artists = new ArrayList<Artists>();
		artists.add(new Artists("1", "Avicii", "xyz.url", "avicii.com"));
		artists.add(new Artists("2", "Martin Garix", "pqr.url", "martingarix.com"));
		
		for(Artists art : artists) {
			artistsDao.create(art);
		}
		
		System.out.println("Fetching artists");
		fetchArtists(artists);
		
		System.out.println("Updating Website for artists");
		artistsDao.updateArtistWebsite(artists.get(0), "iamavicii.com");
		artistsDao.updateArtistWebsite(artists.get(1), "iamdjmartin.com");
		
		System.out.println("Fetching artists");
		fetchArtists(artists);
		
		System.out.println("Deleting artist Avicii");
		artistsDao.delete(artists.get(0));
		
		System.out.println("Fetching artists");
		fetchArtists(artists);

	}
}
