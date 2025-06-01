package com.SpringSchedulerPractive.SchedulerDemo.Services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.SpringSchedulerPractive.SchedulerDemo.Entity.UserAudit;
import com.SpringSchedulerPractive.SchedulerDemo.Repository.UserAuditRepo;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Service
public class ScheduleService {
	
		@Autowired
		private UserAuditRepo repo;
		
		Logger LOG = LoggerFactory.getLogger(getClass());

		public Optional<List<UserAudit>> getLogsOlderThanThreeMonths() {
		
			long nintyDaysAgo = Instant.now().minusSeconds(3*30*24*60*60).toEpochMilli();
			System.out.println(nintyDaysAgo);
			Optional<List<UserAudit>> allByLogoutLessThan = repo.findAllByLogoutTimeLessThan(nintyDaysAgo);
			if(!allByLogoutLessThan.isEmpty()) {
				LOG.info(" DELETING USER DATA LOG...........");
				return  allByLogoutLessThan;
			}
			else {
				LOG.info("no data available to delete");
			}
				return Optional.empty();
		}
		

		@Scheduled(cron = "0 * * * * *")
		@SchedulerLock(name = "lockDeleteSchedulerForOtherInstance",lockAtLeastFor = "10s", lockAtMostFor = "20s")
		public void deleteUserOlderThanThreeMonth() {
		
			try {
			long nintyDaysAgo = Instant.now().minusSeconds(3*30*24*60*60).toEpochMilli();
			LOG.info(" Log before this period will going to be deleted {}", nintyDaysAgo);
			repo.deleteAllByLogoutTimeLessThan(nintyDaysAgo);
			} catch(Exception ex) {
				LOG.error(" Exception is : ",ex);
			}
			
		}
}
