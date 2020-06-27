package demo.UserAuth;

import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

public class UserAuth {
	
	@GetMapping("/login")
	public ModelAndView method(@RequestParam("api") String api) {
		String client_id = "13301TJ2BRKR220TYTF3S5WKLHQFM01JW2IZA51ISYW0F02I";
		String url = "http://140.121.197.136:8080/user";
		
		String projectUrl = "https://foursquare.com/oauth2/authenticate" + 
				"?client_id=" + client_id +
				"&response_type=code" +
				"&redirect_uri=" + url;
		System.out.println(projectUrl);
		return new ModelAndView("redirect:" + projectUrl);
	}
	
	public static String getAuthData(@RequestParam(required = false) String code, @RequestParam("api") String api) {
		String client_id = "13301TJ2BRKR220TYTF3S5WKLHQFM01JW2IZA51ISYW0F02I";
		String client_secret = "00LVDBTHZYSFOO0XQYGHYESNRG0C2YBRBQTKREPNRXXY1EPU";
		String url = "http://140.121.197.136:8080";
		
		String tokenUrl = "https://foursquare.com/oauth2/access_token" + 
				"?client_id=" + client_id +
				"client_secret=" + client_secret +
				"grant_type=authorization_code" +
				"&redirect_uri=" + url +
				"&code=" + code;
	
		//System.out.println(projectUrl);
		String result = userAuth(tokenUrl , api);
		return result;
		
	}
	
	@RequestMapping("/foursquareApiList")
	public static String userAuth(String tokenUrl, String api) {
		RestTemplate restTemplate = new RestTemplate();
		String myToken = restTemplate.getForObject(tokenUrl, String.class);
		System.out.println(myToken);
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> map = springParser.parseMap(myToken);
		int i = 0;
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			myToken="";
			System.out.println(entry.getValue());
			myToken = entry.getValue().toString();
			i++;
		}
		return myToken;
		
	}
}
