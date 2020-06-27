package demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.model.Venue;
import demo.mongoDB.Repository.VenueRepository;

@RestController
public class mongoDBController {

	@Autowired
	private VenueRepository venueRepository;
	
	//@GetMapping("/findAllMembers")
	public ResponseEntity<List<Venue>> getVenues() {
		List<Venue> venueList = venueRepository.findAll();
		return new ResponseEntity<List<Venue>>(venueList, HttpStatus.OK);
	}
	
	@PostMapping("/createVenue")
	public Venue createVenue(@RequestBody Venue venue) {
		venueRepository.save(venue);
		
		return venue;
	}
	
	@DeleteMapping("/deleteVenue/{venueId}")
	public ResponseEntity<HttpStatus> deleteVenue(@PathVariable int verifyId) {
		venueRepository.deleteById(verifyId);;
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
