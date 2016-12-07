package musicx;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import musicx.dao.AlbumsDao;
import musicx.dao.TracksDao;
import musicx.dao.UsersDao;
import musicx.model.Users;


public class InserterAditya {

	public static void main(String args[]) throws SQLException {

		// DAO Instances
		UsersDao usersDao = UsersDao.getInstance();
		AlbumsDao albumsDao = AlbumsDao.getInstance();
		TracksDao tracksDao = TracksDao.getInstance();
		
		
		//UsersDao
		Users user1 = new Users("aditya2705", "dagewga", 77, 
				"Aditya", "Rathi", Users.Gender.M, new Timestamp(101250105L), 1527984529);
		Users user2 = new Users("watsonemma", "beauty", 77, 
				"Emma", "Watson", Users.Gender.F, new Timestamp(135434616L), 1613458760);
		
		//Create
		user1 = usersDao.create(user1);
		user2 = usersDao.create(user2);
		
		//Read
		Users sampleUser = usersDao.getUserByUsername(user1.getUsername());
		System.out.format("Reading user: username:%s fn:%s ln:%s pass:%s gender:%s location_id:%s "
				+ "phone_number:%s\n", 
				sampleUser.getUsername(),sampleUser.getFirstname(), sampleUser.getLastname(), 
				sampleUser.getPassword(),sampleUser.getGender().toString(), sampleUser.getLocation_id(),
				sampleUser.getPhone_number()+"");
		
		//Delete
		usersDao.delete(user1);
		usersDao.delete(user2);
		

	}
}
