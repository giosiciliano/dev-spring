package com.gio.springdemo;

public class BasketballCoach implements Coach {
	
	// define a private field for the dependency
	private FortuneService fortuneService;
		
	// define a constructor for dependency injection
	public BasketballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Shoot 100 3s, do 1 suicide for every missed basket";
	}

	@Override
	public String getDailyFortune() {
		
		// use my fortuneService to get a fortune 
		return fortuneService.getFortune();
	}


}
