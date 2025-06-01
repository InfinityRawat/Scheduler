package com.SpringSchedulerPractive.SchedulerDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.SpringSchedulerPractive.SchedulerDemo.Entity.UserAudit;
import com.SpringSchedulerPractive.SchedulerDemo.Repository.UserAuditRepo;

import jakarta.annotation.PostConstruct;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "3s")
public class SchedulerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerDemoApplication.class, args);
	}
	@Autowired
	private UserAuditRepo repo;
	@PostConstruct
	public void createSampleUsers() {
		 long currentTime = System.currentTimeMillis();
	        long threeMonthsAgo = currentTime - (90L * 24 * 60 * 60 * 1000);

	        List<String> names = Arrays.asList("John", "Jane", "Alice", "Bob", "Tom", "Jerry", "Sam", "Max", "Lily", "Zoe",
	                                           "Rick", "Morty", "Bruce", "Clark", "Diana", "Barry", "Lois", "Lex", "Peter", "Tony");

	        for (int i = 0; i < 20; i++) {
	            String name = names.get(i);
	            long loginTime, logoutTime;

	            if (i < 10) {
	                // 10 users with logoutTime older than 3 months
	                logoutTime = getRandomTime(threeMonthsAgo - (60L * 24 * 60 * 60 * 1000), threeMonthsAgo - (1L * 24 * 60 * 60 * 1000)); // 2-3 months ago
	                loginTime = logoutTime - (1L * 60 * 60 * 1000); // logged in 1 hour before logout
	            } else {
	                // 10 users with recent logoutTime (within 3 months)
	                loginTime = getRandomTime(threeMonthsAgo, currentTime - (1L * 60 * 60 * 1000));
	                logoutTime = loginTime + (1L * 60 * 60 * 1000); // logged out 1 hour after login
	            }
	            
	            repo.save(UserAudit.builder().name(name).loginTime(loginTime).logoutTime(logoutTime).build());
	}
	}
	        
	        private static long getRandomTime(long startInclusive, long endInclusive) {
	            return ThreadLocalRandom.current().nextLong(startInclusive, endInclusive);
	        }
}
