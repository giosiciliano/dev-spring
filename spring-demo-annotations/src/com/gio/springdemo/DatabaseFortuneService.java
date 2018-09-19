package com.gio.springdemo;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DatabaseFortuneService implements FortuneService {

	private List<String> temps = new ArrayList<String>();
	
	@Override
	public String getFortune() {
		
		Random myRandom = new Random();
		int index = myRandom.nextInt(temps.size());
		
		return temps.get(index);
	}
	
	// init stuff
	@PostConstruct
	public void init() {
		System.out.println(">> DatabaseFortuneService: in init()");
		Scanner inFile;
		String token = "";
		
		try {
			inFile = new Scanner(new File("C:\\Dev\\java\\workspaces\\dev-spring\\spring-demo-annotations\\src\\fortunes"));
			
			// while loop
		    while (inFile.hasNextLine()) {
		      // find next line
		      token = inFile.nextLine();
		      temps.add(token);
		    }
		    
			inFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
