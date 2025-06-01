package com.SpringSchedulerPractive.SchedulerDemo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.SpringSchedulerPractive.SchedulerDemo.Entity.UserAudit;

import jakarta.transaction.Transactional;

public interface UserAuditRepo extends JpaRepository<UserAudit, Integer> {

	Optional<List<UserAudit>> findAllByLogoutTimeLessThan(long time);
	
	@Modifying
	@Transactional
	@Query(value = "delete from user_audit ua where ua.logout_time < :time",nativeQuery = true)
	void deleteAllByLogoutTimeLessThan(long time);

}
