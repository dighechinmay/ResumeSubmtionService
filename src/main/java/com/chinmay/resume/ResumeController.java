package com.chinmay.resume;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/resumes/")
public class ResumeController {
	
   static HashMap<String,String> map;
   public InputStream inputStream;
	
	public ResumeController() throws IOException {
	
				generateQuestionAnswers();
		
	}
	
	/*
	 * We save our question key and values in java properties file.
	 * We use a Hashmap to save and retrieve the values for each question.
	 * In future a new candidate can save his own property file and reuse the method/response.
	 */
	

	
	public void generateQuestionAnswers() throws IOException {
		
		 map = new HashMap<>();
		
		Properties prop = new Properties();
		String propFileName = "resumeQuestions.properties";
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found.");
		}
		
		
	    map.put("Ping",prop.getProperty("Ping"));
	    map.put("Name",prop.getProperty("Name"));
	    map.put("Email Address",prop.getProperty("Email Address"));
	    map.put("Phone",prop.getProperty("Phone"));
	    map.put("Position",prop.getProperty("Position"));
	    map.put("Source",prop.getProperty("Source"));
	    map.put("Degree",prop.getProperty("Degree"));
	    map.put("Years",prop.getProperty("Years"));
	    map.put("Referrer",prop.getProperty("Referrer"));
	    map.put("Resume",prop.getProperty("Resume"));
	    map.put("Status",prop.getProperty("Status"));
		
		
	}
	
	
	

	@RequestMapping("/chinmaydighe")
	public String mapAll(@RequestParam("q") String question, @RequestParam("d") String puzzle) {
	    PuzzleSolution ps = new PuzzleSolution();
	    
		if(map.containsKey(question)) {
			return map.get(question);
		
		}else {
			return ps.solution(puzzle);
		}

		
	}


	
}