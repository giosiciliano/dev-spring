package com.gio.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class PracticeConfig {
	
	// define bean for our sad fortune service
	@Bean
	public FortuneService luckyFortuneService() {
		return new LuckyFortuneService();
	}
	
	// define bean for our swim coach and inject dependency
	@Bean
	public Coach soccerCoach() {
		return new SoccerCoach(luckyFortuneService());
	}

}
