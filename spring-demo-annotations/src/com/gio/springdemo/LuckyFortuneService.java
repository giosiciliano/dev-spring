package com.gio.springdemo;

import org.springframework.stereotype.Component;

@Component
public class LuckyFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "1 billion dollars is coming your way, what what!!";
	}

}
