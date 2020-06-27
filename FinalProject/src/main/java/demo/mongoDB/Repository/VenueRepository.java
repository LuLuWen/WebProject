package demo.mongoDB.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import demo.model.Venue;

public interface VenueRepository extends MongoRepository<Venue, Integer>{

	//List<Member> findByPassword(String password);
	
	//List<Member> findByAddress(String address);
}
