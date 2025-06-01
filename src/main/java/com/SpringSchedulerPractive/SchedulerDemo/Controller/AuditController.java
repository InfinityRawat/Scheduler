package com.SpringSchedulerPractive.SchedulerDemo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SpringSchedulerPractive.SchedulerDemo.Entity.UserAudit;
import com.SpringSchedulerPractive.SchedulerDemo.Services.ScheduleService;

@RestController
@RequestMapping("/api/v1")
public class AuditController {

	@Autowired
	private ScheduleService service;
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/old")
	private List<UserAudit> gerOlderData() {
		  List<UserAudit> auditDetails = service.getLogsOlderThanThreeMonths().orElse(new ArrayList<UserAudit>());

			return auditDetails;
	}
}
