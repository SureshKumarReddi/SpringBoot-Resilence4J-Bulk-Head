package com.Suresh.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class BulkHeadController {

	private static final Logger logger = LoggerFactory.getLogger(BulkHeadController.class);
	
	
	@GetMapping("/getMessage")
	@Bulkhead(name = "bulk",fallbackMethod = "fallback")
	public ResponseEntity<String> getMessage(@RequestParam(name = "name" , defaultValue = "hello") String name){
		
		return ResponseEntity.ok(" the entered user name is ... "  +name);
	}
	
	
	public ResponseEntity<String> fallback(){
		logger.info(" >>>  Fallback method called   >>>>>>");
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("The service is getting too many reqests and unable to process the requests...");
	}
}
