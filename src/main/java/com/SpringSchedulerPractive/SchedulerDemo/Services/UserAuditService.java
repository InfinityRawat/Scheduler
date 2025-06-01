package com.SpringSchedulerPractive.SchedulerDemo.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.SpringSchedulerPractive.SchedulerDemo.Entity.UserAudit;
import com.SpringSchedulerPractive.SchedulerDemo.Repository.UserAuditRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuditService {

		private UserAuditRepo repo;
		private static final Logger Log = LoggerFactory.getLogger(UserAuditService.class);
		
		public void saveAudit(UserAudit user) {
			try {
			repo.save(user);
			} catch(Exception e) {
				Log.error(e.getLocalizedMessage());
			}
			
		}
		
}
