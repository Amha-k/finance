package com.saas.employee.event;

import com.saas.employee.service.EmployeeEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeEventProducingJob {
    private final EmployeeEventService employeeEventService;

    @Scheduled(cron = "${rabbitmq.publish-employee-events-job-cron}")
    @SchedulerLock(name = "sendEmployeeEvents")
    public void sendEmployeeEvents() {
        LockAssert.assertLocked();
        log.info("Publishing employee events at: {}", Instant.now());
        employeeEventService.sendEmployeeEvents();
    }
}

