package demo.example;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class Example {
		
		@GetMapping("/urlConnection")
		public static JSONPObject URLConnection() {
			
		try {
			URL url = new URL("");
			URLConnection urlcon = url.openConnection();
			InputStream is = urlcon.getInputStream();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
		}
}
