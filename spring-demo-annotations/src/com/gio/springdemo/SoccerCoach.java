package com.gio.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SoccerCoach implements Coach {

	private FortuneService fortuneService;
	
	
	public SoccerCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Kick the ball, its fun";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	
}
