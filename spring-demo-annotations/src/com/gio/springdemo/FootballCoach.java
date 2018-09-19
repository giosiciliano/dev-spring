package com.gio.springdemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("happyFortuneService")
public class FootballCoach implements Coach {

	// define a default constructor
	public FootballCoach() {
		System.out.println(">> FootballCoach: inside default constructor");
	}
	
	@Override
	public String getDailyWorkout() {
		return "Tackle drills and wind sprints";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return null;
	}

}
