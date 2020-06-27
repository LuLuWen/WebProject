package demo.Controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


import demo.model.Venue;
import demo.mongoDB.Repository.VenueRepository;


@Controller
public class htmlController {
	
	@Autowired
	private mongoDBController mongoDBController;
	
	@ResponseBody
    @RequestMapping("/foursquareApiList")
    public static ResponseEntity<String> getAuthData(@RequestParam(required = false) String code,@RequestParam String zone, @RequestParam String YourSection) throws JSONException {
        //Object result="";
        System.out.println(code);
        //System.out.println("getAuthData");
        String client_id = "NK2RXK242DVBHO00FVAG3ZNVX0N0IY4MZTNK4LWGMBN2H121";
        String client_secret = "I0BO414II2GJHRHC2HCXGYM1YYKH0YAE3IEAMFKAIZU11ITT";
        if(code == null){
            System.out.println(zone);
            System.out.println(YourSection);
            String prefix = "https://api.foursquare.com/v2/venues/explore";
            //api = "venues/explore?near=taipei";
            String userlessAuth = "?near=" + zone + "&section=" + YourSection + "&client_id=" + client_id + "&client_secret=" + client_secret + "&v=20190610";
            RestTemplate restTemplate = new RestTemplate();
            //System.out.println(prefix + api);
            //result = restTemplate.getForObject(prefix + userlessAuth, Object.class);
            ResponseEntity<String> response = restTemplate.getForEntity(prefix + userlessAuth, String.class);        
            System.out.println(response);
            //parseValue(response);
            return response;

        }
        return null;
        /*else {


            System.out.println("------");

            String url = "http://140.121.197.137:8080/result";
            String tokenUrl = "https://foursquare.com/oauth2/access_token" +
                    "?client_id=" + client_id +
                    "&client_secret=" + client_secret +
                    "&grant_type=authorization_code" +
                    "&redirect_uri=" + url +
                    "&code=" + code;

            result = userAuth(tokenUrl,api);
            return result.toString();
        }*/
        
        /*//JSON URL to Java object
        Staff obj = mapper.readValue(new URL("http://some-domains/api/staff.json"), Staff.class);

        //JSON string to Java Object
        Staff obj = mapper.readValue("{'name' : 'mkyong'}", Staff.class);*/
        //parseValue(result.toString());
        
    }
	

	/*public static List<Venue> parseValue(ResponseEntity<String> json) throws JSONException{
		
		JSONObject obj = new JSONObject(json);
        String summary = obj.getJSONObject("meta").getString("requestId");

        System.out.println(summary);

        /*JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            System.out.println(post_id);
        }
		return null;
	}*/
	
	@GetMapping("/findAllCheckLists")
	public ResponseEntity<List<Venue>> getVenues() {
		
		return mongoDBController.getVenues();
	}
	
    @PostMapping("/addToChecklist")
	public ResponseEntity<Venue> add (@RequestBody Venue venue) {
    	//System.out.println(memberAccount+"!!!!!!");
		mongoDBController.createVenue(venue);
	
		return new ResponseEntity<Venue>(venue, HttpStatus.CREATED);
	}
    
    @DeleteMapping("/delete/{verifyId}")
	public ResponseEntity<HttpStatus> delete (@PathVariable int verifyId) {
    	//System.out.println(memberAccount+"!!!!!!");
		mongoDBController.deleteVenue(verifyId);
	
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	
}
